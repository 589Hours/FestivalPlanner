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

    private Map map; // Kaart voor de simulatie
    private ResizableCanvas canvas; // Canvas voor weergave
    private Camera camera; // Camera voor weergave
    private ArrayList<Visitor> visitors = new ArrayList<>(); // Lijst van bezoekers
    private int timer; // Timer voor het bijhouden van de tijd

    private PathFinder alphaPathFinder; // Padzoeker voor de alfa-scene
    // Andere padzoekers voor verschillende scènes: beta, charlie, delta, echo

    @Override
    public void start(Stage stage) throws Exception {
        // Hoofdpaneel instellen
        BorderPane mainPane = new BorderPane();
        // Canvas maken en instellen
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        canvas.setWidth(1024);
        canvas.setHeight(1024);

        // Eventhandler toevoegen voor scrollen
        canvas.setOnScroll(event -> camera.mouseScroll(event));

        mainPane.setCenter(canvas); // Canvas in het hoofdpaneel plaatsen
        FXGraphics2D g2d = new FXGraphics2D(canvas.getGraphicsContext2D());

        // Starten van de animatietimer voor het bijwerken en tekenen
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

        // Scene instellen en tonen
        stage.setScene(new Scene(mainPane));
        stage.setTitle("Festival Planner");
        stage.show();
        draw(g2d); // Eerste tekenen
    }

    // Methode voor initialisatie van de simulatie
    public void init() throws Exception {
        // Grafiek initialiseren voor padzoekers
        Graph graph = new Graph();
        Tile alphaStage = graph.getNodes()[65][19]; // Alfa-scène tegel
        // Andere scène tegels: beta, charlie, delta, echo
        // Starttegel instellen
        Tile spawnTile = new Tile(126, 64);
        alphaPathFinder = new PathFinder(alphaStage, graph); // Padzoeker voor de alfa-scène

        camera = new Camera(); // Camera initialiseren
        map = new Map("/FestivalMap.json", this.alphaPathFinder); // Kaart initialiseren met padzoeker voor de alfa-scène

        alphaPathFinder.calculateDistanceMapWithGraph(); // Afstandkaart berekenen met de grafiek

        start(new Stage()); // Starten van de simulatie

    }

    // Methode voor tekenen van de simulatie
    public void draw(Graphics2D g) {
        g.clearRect(0, 0, (int) canvas.getWidth(), (int) canvas.getHeight()); // Scherm wissen
        g.setBackground(Color.black); // Achtergrondkleur instellen
        g.setTransform(camera.getTransform()); // Cameratransformatie instellen
        map.draw(g); // Kaart tekenen
        for (Visitor visitor : visitors) {
            visitor.draw(g); // Bezoekers tekenen
        }

        alphaPathFinder.draw(g); // Alfa-padtekenen
        g.setTransform(new AffineTransform()); // Cameratransformatie resetten
    }

    // Methode voor bijwerken van de simulatie
    public void update(double deltaTime) {
        // Timer controleren om bezoekers toe te voegen
        if (timer % 144 == 0) {
            if (visitors.size() < 5) {
                Visitor visitor = new Visitor(
                        new Point2D.Double(126*32, 64*32), // Startpositie
                        alphaPathFinder, // Padzoeker
                        0.001); // Snelheid
                visitors.add(visitor); // Bezoeker toevoegen
            }
        }
        // Bezoekers bijwerken
        for (Visitor visitor : visitors) {
            visitor.update(visitors); // Positie bijwerken
        }
        timer++; // Timer verhogen
    }
}

