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
import java.util.ArrayList;

public class Simulation extends Application {

    private Map map;
    private ResizableCanvas canvas;
    private Camera camera;
    private ArrayList<Visitor> visitors = new ArrayList<>();
    private PathFinder pathFinder = new PathFinder(new Tile(50, 10));
    private Toilet toilet;

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        canvas.setWidth(1024);
        canvas.setHeight(1024);

        canvas.setOnScroll(event -> camera.mouseScroll(event));
        canvas.setOnMouseMoved(event -> {
            try {
                Point2D mousePos = camera.getWorldPosition(new Point2D.Double(event.getX(), event.getY()));
                for (Visitor visitor : visitors) {
                    visitor.setTargetPosition(mousePos);
                }
            } catch (NoninvertibleTransformException e) {
                throw new RuntimeException(e);
            }

        });

        mainPane.setCenter(canvas);
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());

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
        camera = new Camera();
        map = new Map("/FestivalMap.json", this.pathFinder);
        this.toilet = new Toilet(new Point2D.Double(0,0));
//        for (int i = 0; i < 3; i++) {
//            Visitor visitor = new Visitor(new Point2D.Double(Math.random()*(128*8), Math.random()*(128*8)),1);
//            visitors.add(visitor);
//        }

        pathFinder.calculateDistanceMap();
        start(new Stage());

    }


    public void draw(Graphics2D g) {
        g.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
        g.setBackground(Color.black);
        g.setTransform(camera.getTransform());
        map.draw(g);
        this.toilet.draw(g);
        for (Visitor visitor : visitors) {
            visitor.draw(g);
        }

        pathFinder.draw(g);
        g.setTransform(new AffineTransform());
    }

    public void update(double deltaTime) {
        this.toilet.update(deltaTime);
        for (Visitor visitor : visitors) {
            visitor.update(visitors);
        }
    }
}

