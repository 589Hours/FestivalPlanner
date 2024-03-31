package Application.Simulation;

import data.FestivalPlan;
import javafx.animation.AnimationTimer;
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

public class Simulation {

    private Map map;
    private ResizableCanvas canvas;
    private Camera camera;
    private ArrayList<Visitor> visitors = new ArrayList<>();
    private int timer;
    private FestivalPlan festivalPlan;
    private int hours;
    private int minutes;
    private int counter;



    private PathFinder alphaPathFinder;
    private PathFinder betaPathFinder;
    private PathFinder charliePathFinder;
    private PathFinder deltaPathFinder;
    private PathFinder echoPathFinder;

    public void start(FestivalPlan festivalPlan) {
        Stage stage = new Stage();
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        canvas.setWidth(1024);
        canvas.setHeight(1024);
        hours = 3;
        minutes = 0;

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

        Tile spawnTile = new Tile(126, 64);
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
    }

    public void init(FestivalPlan festivalPlan) throws Exception {
        camera = new Camera();
        map = new Map("/FestivalMap.json", this.alphaPathFinder);
        int[][] collisionLayer = map.getCollisionLayer();
        this.festivalPlan = festivalPlan;
        map = new Map("/FestivalMap.json", festivalPlan, this);

        Graph graph = new Graph();
        Tile alphaStage = graph.getNodes()[65][19];
        Tile betaStage = graph.getNodes()[18][110];
        Tile charlieStage = graph.getNodes()[110][110];
        Tile deltaStage = graph.getNodes()[18][40];
        Tile echoStage =  graph.getNodes()[110][40];
        Tile spawnTile = new Tile(126, 64);

        alphaPathFinder = new PathFinder(alphaStage, graph, collisionLayer);
        betaPathFinder = new PathFinder(betaStage, graph, collisionLayer);
        charliePathFinder = new PathFinder(charlieStage, graph, collisionLayer);
        deltaPathFinder = new PathFinder(deltaStage, graph, collisionLayer);
        echoPathFinder = new PathFinder(echoStage, graph, collisionLayer);
        

        alphaPathFinder.calculateDistanceMapWithGraph();
        spawnTile = alphaPathFinder.getSpawnTile();
        betaPathFinder.calculateDistanceMapWithGraph();
        charliePathFinder.calculateDistanceMapWithGraph();
        deltaPathFinder.calculateDistanceMapWithGraph();
        echoPathFinder.calculateDistanceMapWithGraph();

        start(festivalPlan);

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
        counter++;
        if (timer % 144 == 0) {
            if (visitors.size() < 5) {
                Visitor visitor = new Visitor(
                        new Point2D.Double(126*32, 64*32),
                        echoPathFinder,
                        0.001);
                visitors.add(visitor);
            }
        }
        for (Visitor visitor : visitors) {
            visitor.update(visitors);
        }
        if(counter % 3 == 0) {
            updateTime();
        }
        timer++;
    }

    private void updateTime() {
        this.minutes++;
        if(this.minutes == 60) {
            this.minutes = 0;
            this.hours++;

            if(this.hours == 24) {
                this.hours = 0;
            }
        }
        map.updateOpacity();
    }

    public int getHours() {

        return hours;
    }

    public int getMinutes() {
        return minutes;
    }
}

