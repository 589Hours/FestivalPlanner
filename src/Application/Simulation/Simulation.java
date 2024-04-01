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
    private ArrayList<Toilet> toilets = new ArrayList<>();
    private PathFinder alphaPathFinder;
    private PathFinder betaPathFinder;
    private PathFinder charliePathFinder;
    private PathFinder deltaPathFinder;
    private PathFinder echoPathFinder;
    private PathFinder toiletPathFinder;

    private ArrayList<PathFinder> toiletsPaths = new ArrayList<>();
    private PathFinder toilet1PathFinder;
    private PathFinder toilet2PathFinder;
    private PathFinder toilet3PathFinder;
    private PathFinder toilet4PathFinder;
    private PathFinder toilet5PathFinder;
    private PathFinder toilet6PathFinder;
    private PathFinder toilet7PathFinder;
    private PathFinder toilet8PathFinder;
    private PathFinder toilet9PathFinder;

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
        draw(g2d);
    }

    public void init() throws Exception {
        camera = new Camera();
        map = new Map("/FestivalMap.json", this.alphaPathFinder);
        for (int i = 0; i < 9; i++) {
            this.toilets.add(new Toilet(new Point2D.Double(282 + (i * 99 - (i * 4)), 4312)));
        }

        int[][] collisionLayer = map.getCollisionLayer();

        Graph graph = new Graph();
        Tile alphaStage = graph.getNodes()[65][19];
        Tile betaStage = graph.getNodes()[18][110];
        Tile charlieStage = graph.getNodes()[110][110];
        Tile deltaStage = graph.getNodes()[18][40];
        Tile echoStage = graph.getNodes()[110][40];
        Tile spawnTile = new Tile(126, 64);
        Tile toilets = graph.getNodes()[98][18];

        Tile toilet1 = graph.getNodes()[95][7];
        Tile toilet2 = graph.getNodes()[95][9];
        Tile toilet3 = graph.getNodes()[95][11];
        Tile toilet4 = graph.getNodes()[95][13];
        Tile toilet5 = graph.getNodes()[95][15];
        Tile toilet6 = graph.getNodes()[95][17];
        Tile toilet7 = graph.getNodes()[95][19];
        Tile toilet8 = graph.getNodes()[95][21];
        Tile toilet9 = graph.getNodes()[95][23];

        alphaPathFinder = new PathFinder(alphaStage, graph, collisionLayer);
        betaPathFinder = new PathFinder(betaStage, graph, collisionLayer);
        charliePathFinder = new PathFinder(charlieStage, graph, collisionLayer);
        deltaPathFinder = new PathFinder(deltaStage, graph, collisionLayer);
        echoPathFinder = new PathFinder(echoStage, graph, collisionLayer);

        toiletPathFinder = new PathFinder(toilets, graph, collisionLayer);
        toilet1PathFinder = new PathFinder(toilet1, graph, collisionLayer);
        toilet2PathFinder = new PathFinder(toilet2, graph, collisionLayer);
        toilet3PathFinder = new PathFinder(toilet3, graph, collisionLayer);
        toilet4PathFinder = new PathFinder(toilet4, graph, collisionLayer);
        toilet5PathFinder = new PathFinder(toilet5, graph, collisionLayer);
        toilet6PathFinder = new PathFinder(toilet6, graph, collisionLayer);
        toilet7PathFinder = new PathFinder(toilet7, graph, collisionLayer);
        toilet8PathFinder = new PathFinder(toilet8, graph, collisionLayer);
        toilet9PathFinder = new PathFinder(toilet9, graph, collisionLayer);
        toiletsPaths.add(toilet1PathFinder);
        toiletsPaths.add(toilet2PathFinder);
        toiletsPaths.add(toilet3PathFinder);
        toiletsPaths.add(toilet4PathFinder);
        toiletsPaths.add(toilet5PathFinder);
        toiletsPaths.add(toilet6PathFinder);
        toiletsPaths.add(toilet7PathFinder);
        toiletsPaths.add(toilet8PathFinder);
        toiletsPaths.add(toilet9PathFinder);


        alphaPathFinder.calculateDistanceMapWithGraph();
        spawnTile = alphaPathFinder.getSpawnTile();
        betaPathFinder.calculateDistanceMapWithGraph();
        charliePathFinder.calculateDistanceMapWithGraph();
        deltaPathFinder.calculateDistanceMapWithGraph();
        echoPathFinder.calculateDistanceMapWithGraph();

        toiletPathFinder.calculateDistanceMapWithGraph();
        toilet1PathFinder.calculateDistanceMapWithGraph();
        toilet2PathFinder.calculateDistanceMapWithGraph();
        toilet3PathFinder.calculateDistanceMapWithGraph();
        toilet4PathFinder.calculateDistanceMapWithGraph();
        toilet5PathFinder.calculateDistanceMapWithGraph();
        toilet6PathFinder.calculateDistanceMapWithGraph();
        toilet7PathFinder.calculateDistanceMapWithGraph();
        toilet8PathFinder.calculateDistanceMapWithGraph();
        toilet9PathFinder.calculateDistanceMapWithGraph();

        start(new Stage());
    }


    public void draw(Graphics2D g) {
        g.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
        g.setBackground(Color.black);
        g.setTransform(camera.getTransform());
        map.draw(g);
        for (Toilet toilet : toilets) {
            toilet.draw(g);
        }
        for (Visitor visitor : visitors) {
            if (!visitor.isInToilet()) {
                visitor.draw(g);
            }
        }


        toiletPathFinder.draw(g);
        g.setTransform(new AffineTransform());
    }

    public void update(double deltaTime) {
        for (Toilet toilet : toilets) {
            toilet.update(deltaTime);
        }
        if (timer % 144 == 0) {
            if (visitors.size() < 1) {
                Visitor visitor = new Visitor(
                        new Point2D.Double(126 * 32, 64 * 32),
                        alphaPathFinder,
                        0.001);
                visitors.add(visitor);
            }
        }

        for (Visitor visitor : visitors) {
            visitor.update(visitors, deltaTime);

            if (visitor.getDrinkCounter() >= 100 && !visitor.isGoingToToilet()) {
                visitor.setPrevPathFinder(visitor.getPathFinder());
                if (!visitor.getPathFinder().equals(toiletPathFinder)) {
                    System.out.println("Er moet iemand naar het toilet");
                    visitor.setPathFinder(toiletPathFinder);
                    visitor.setGoingToToilet(true);
                    visitor.setDrinkCounter(0);
                }
            }
            if (visitor.getCurrentTile().toString().equals(visitor.getPathFinder().getTargetTile().toString()) &&
            visitor.isGoingToToilet()) {
                for (Toilet toilet : toilets) {
                    if (!toilet.isOccupied()) {
                        System.out.println("gaat toilet in: " + toilet);
                        toilet.setAnimationStarted(true);
                        visitor.setPathFinder(toiletsPaths.get(toilets.indexOf(toilet)));
                        break;
                    }
                }


            }
            for (Toilet toilet : toilets) {
                if (toilet.isAnimationStarted()) {
                    if (toilet.getAnimationTimer() >= 0 && toilet.getAnimationTimer() < 5) {
                        toilet.setOccupied(true);
                    } else if (toilet.getAnimationTimer() >= 5 && toilet.getAnimationTimer() < 15) {
                        if (visitor.getCurrentTile().equals(visitor.getPathFinder().getTargetTile())) {
                            visitor.setInToilet(true);
                        }
                    } else if (toilet.getAnimationTimer() >= 15 && toilet.getAnimationTimer() < 23) {
                        visitor.setDrinkCounter(0);
                    } else if (toilet.getAnimationTimer() >= 23 && toilet.getAnimationTimer() < 30) {
                        visitor.setInToilet(false);
                        visitor.setPathFinder(visitor.getPrevPathFinder());
                        visitor.setGoingToToilet(false);
                    } else {
                        toilet.setOccupied(false);
                        toilet.setAnimationStarted(false);
                        toilet.setStatus(0);
                        toilet.setAnimationTimer(0);
                    }
                }
            }
        }

        timer++;
    }
}

