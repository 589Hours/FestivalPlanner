package Application.Simulation;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.nio.file.Path;
import java.util.ArrayList;

public class Simulation extends Application {

    private Map map;
    private ResizableCanvas canvas;
    private Camera camera;
    private ArrayList<Visitor> visitors = new ArrayList<>();
    private int timer;

    private Tile alphaStage = new Tile(65, 19);
    private Tile betaStage = new Tile(18, 110);
    private Tile charlieStage = new Tile(110, 110);
    private Tile deltaStage = new Tile(18, 40);
    private Tile echoStage = new Tile(110, 40);
    private Tile spawnTile = new Tile(126, 64);

    private PathFinder alphaPathFinder;
    private PathFinder betaPathFinder;
    private PathFinder charliePathFinder;
    private PathFinder deltaPathFinder;
    private PathFinder echoPathFinder;

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        canvas.setWidth(1024);
        canvas.setHeight(1024);

        canvas.setOnScroll(event -> camera.mouseScroll(event));
//        canvas.setOnMouseMoved(event -> {
//            try {
//                Point2D mousePos = camera.getWorldPosition(new Point2D.Double(event.getX(), event.getY()));
//                for (Visitor visitor : visitors) {
//                    visitor.setTargetPosition(mousePos);
//                }
//            } catch (NoninvertibleTransformException e) {
//                throw new RuntimeException(e);
//            }
//        });

        mainPane.setCenter(canvas);
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());

        spawnTile = new Tile(126, 64);
        new AnimationTimer() {
            long last = -1;

            @Override
            public void handle(long now) {
                if (last == -1)
                    last = now;
                update((now - last) / 1000000000.0);
                last = now;
                draw(g2d);
            }
        }.start();

        stage.setScene(new Scene(mainPane));
        stage.setTitle("Festival Planner");
        stage.show();
        draw(g2d);
    }

    public void init() throws Exception {
        alphaPathFinder = new PathFinder(alphaStage);
        alphaPathFinder.path.put(spawnTile, Integer.MAX_VALUE);
        betaPathFinder = new PathFinder(betaStage);
        charliePathFinder = new PathFinder(charlieStage);
        deltaPathFinder = new PathFinder(deltaStage);
        echoPathFinder = new PathFinder(echoStage);

        camera = new Camera();
        map = new Map("/FestivalMap.json", this.alphaPathFinder);

//        for (int i = 0; i < 1; i++) {
//
//        }

        alphaPathFinder.calculateDistanceMap();
//        spawnTile = alphaPathFinder.getSpawnTile();
//        betaPathFinder.calculateDistanceMap();
//        charliePathFinder.calculateDistanceMap();
//        deltaPathFinder.calculateDistanceMap();
//        echoPathFinder.calculateDistanceMap();

        start(new Stage());

    }


    public void draw(Graphics2D g) {
        g.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
        g.setBackground(Color.black);
        g.setTransform(camera.getTransform());
        map.draw(g);
        for (Visitor visitor : visitors) {
            visitor.draw(g);
        }

        alphaPathFinder.draw(g);
        g.setTransform(new AffineTransform());
    }

    public void update(double deltaTime) {
        if (timer % 144 == 0) {
            if (visitors.size() < 1) {
                Visitor visitor = new Visitor(
                        new Point2D.Double(126*32, 64*32),
                        alphaPathFinder,
                        spawnTile,
                        1);
                visitors.add(visitor);
            }
        }
        for (Visitor visitor : visitors) {
            visitor.update(visitors);
        }
        timer++;
    }
}

