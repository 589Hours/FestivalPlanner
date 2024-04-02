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
import java.awt.geom.Point2D;
import java.nio.file.Path;
import java.util.ArrayList;

public class Simulation {

    private Map map;
    private ResizableCanvas canvas;
    private Camera camera;
    private ArrayList<Visitor> visitors = new ArrayList<>();
    private FestivalPlan festivalPlan;
    private int hours;
    private int minutes;
    private int counter;
    private PathFinder[] pathFinders;

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
        hours = 10;
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

        this.festivalPlan = festivalPlan;
        map = new Map("/FestivalMap.json", festivalPlan, this);
        int[][] collisionLayer = map.getCollisionLayer();

        Graph graph = new Graph();

        Tile alphaStage = graph.getNodes()[65][19];
        Tile betaStage = graph.getNodes()[18][110];
        Tile charlieStage = graph.getNodes()[110][110];
        Tile deltaStage = graph.getNodes()[18][40];
        Tile echoStage =  graph.getNodes()[110][40];

        alphaPathFinder = new PathFinder(alphaStage, graph, collisionLayer);
        betaPathFinder = new PathFinder(betaStage, graph, collisionLayer);
        charliePathFinder = new PathFinder(charlieStage, graph, collisionLayer);
        deltaPathFinder = new PathFinder(deltaStage, graph, collisionLayer);
        echoPathFinder = new PathFinder(echoStage, graph, collisionLayer);

        alphaPathFinder.calculateDistanceMapWithGraph();
        betaPathFinder.calculateDistanceMapWithGraph();
        charliePathFinder.calculateDistanceMapWithGraph();
        deltaPathFinder.calculateDistanceMapWithGraph();
        echoPathFinder.calculateDistanceMapWithGraph();

        pathFinders = new PathFinder[5];
        pathFinders[0] = alphaPathFinder;
        pathFinders[1] = betaPathFinder;
        pathFinders[2] = charliePathFinder;
        pathFinders[3] = deltaPathFinder;
        pathFinders[4] = echoPathFinder;


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

        if (visitors.size() == 1){
            visitors.get(0).getPath().draw(g);
        }

        g.setTransform(new AffineTransform());
    }

    public void update(double deltaTime) {

        if (counter % 144 == 0) {
            if (visitors.size() < 1) {
                Visitor visitor = new Visitor(
                        new Point2D.Double(126*32, 64*32),
                        echoPathFinder,
                        0.001,
                        festivalPlan);
                visitors.add(visitor);
            }
        }
        for (Visitor visitor : visitors) {
            visitor.update(visitors, this);
        }
        if(counter % 10 == 0) {
            updateTime();
        }
        counter++;
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
        System.out.println(hours + ":" + minutes);
        map.updateOpacity();
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public PathFinder[] getPathFinders() {
        return pathFinders;
    }

    public FestivalPlan getFestivalPlan() {
        return festivalPlan;
    }
}

