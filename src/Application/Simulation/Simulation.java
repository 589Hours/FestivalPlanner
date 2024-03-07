package Application.Simulation;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class Simulation extends Application {

    private Map map;
    private ResizableCanvas canvas;
    private Camera camera;
    private ArrayList<Visitor> visitors = new ArrayList<>();


    @Override
    public void start(Stage stage) throws Exception {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        canvas.setWidth(1024);
        canvas.setHeight(1024);

        canvas.setOnScroll(event -> camera.mouseScroll(event));
        canvas.setOnMouseMoved(event -> {
            for (Visitor visitor : visitors) {
                visitor.setTargetPosition(new Point2D.Double(event.getX(), event.getY()));
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
        stage.setTitle("Fading image");
        stage.show();
        draw(g2d);
    }

    public void init() {
        camera = new Camera();
        map = new Map("/FestivalMap.json");
        for (int i = 0; i < 20; i++) {
            Visitor visitor = new Visitor(new Point2D.Double(Math.random()*(128*8), Math.random()*(128*8)),
                    new Point2D.Double(Math.random()*(128*8), Math.random()*(128*8)),
                    1);
            visitors.add(visitor);
        }

    }


    public void draw(Graphics2D g) {
        g.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight());
        g.setBackground(Color.black);
        map.draw(g, camera.getTransform());
        for (Visitor visitor : visitors) {
            visitor.draw(g);
        }
    }

    public void update(double deltaTime) {
        for (Visitor visitor : visitors) {
            visitor.update(visitors);
        }
    }


    public static void main(String[] args) {
        launch(Simulation.class);
    }

}

