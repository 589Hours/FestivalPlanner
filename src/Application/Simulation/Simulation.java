package Application.Simulation;

import com.sun.xml.internal.ws.api.client.WSPortInfo;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.nio.file.Path;
import java.util.ArrayList;

public class Simulation extends Application {
    private String[] names = {"Anna de Boer", "Bram van Dijk", "Carla Jansen", "David Bakker", "Eva Visser", "Frits Smit", "Gina Meijer", "Hans Kok", "Ingrid de Vries", "Jan Peters", "Laura van der Laan", "Maarten Hoekstra", "Nina Jacobs", "Oscar Mulder", "Patty van der Wal", "Quinten de Wit", "Renate Scholten", "Simon Kuijpers", "Tessa van Leeuwen", "Ursula Vos", "Vincent Hendriks", "Wilma Molenaar", "Xander de Groot", "Yvonne van Beek", "Zoë Koster", "Aaron de Jong", "Bianca Bakker", "Casper de Vos", "Daphne van der Linden", "Erik Hendriksen", "Femke van Dam", "Gerard Jansen", "Hannah Smit", "Ivan van Houten", "Julia van der Berg", "Kevin Peters", "Linda Visser", "Max van Dijk", "Nadia de Boer", "Olaf van Rijn", "Paula Kuijpers", "Quincy de Graaf", "Rianne Meijer", "Stefan de Bruijn", "Tara Jacobs", "Udo Janssen", "Vera de Vries", "Wouter van Dam", "Xenia Kok", "Yara van Leeuwen", "Zacharias Hendriks", "Abigail de Haan", "Bas van Buren", "Caroline van Dam", "Daan Jansen", "Elise Kuijpers", "Frank de Jong", "Greetje de Wit", "Harm van der Wal", "Iris van den Berg", "Jeroen Smit", "Karin Peters", "Lucas Bakker", "Marta Visser", "Niels de Boer", "Olivier van Dijk", "Paulien de Vos", "Quirine Hoekstra", "Ramon Vos", "Sanne Jacobs", "Thijs van Leeuwen", "Ulrike Vos", "Vince Hendriks", "Wendy Meijer", "Xavier de Groot", "Yvette van Beek", "Zara Koster", "Albert de Jong", "Britt Bakker", "Cees de Vos", "Diana van der Linden", "Erik Hendriksen", "Fenna van Dam", "Geert Jansen", "Hannah Smit", "Ivo van Houten", "Julia van der Berg", "Kees Peters", "Lisanne Visser", "Max van Dijk", "Nienke de Boer", "Oscar van Rijn", "Paula Kuijpers", "Quinten de Graaf", "Rianne Meijer", "Stefan de Bruijn", "Tara Jacobs", "Udo Janssen", "Vera de Vries", "Wouter van Dam", "Xenia Kok", "Yara van Leeuwen", "Zacharias Hendriks", "Abigail de Haan", "Bas van Buren", "Caroline van Dam", "Daan Jansen", "Elise Kuijpers", "Frank de Jong", "Greetje de Wit", "Harm van der Wal", "Iris van den Berg", "Jeroen Smit", "Karin Peters", "Lucas Bakker", "Marta Visser", "Niels de Boer", "Olivier van Dijk", "Paulien de Vos", "Quirine Hoekstra", "Ramon Vos", "Sanne Jacobs", "Thijs van Leeuwen", "Ulrike Vos", "Vince Hendriks", "Wendy Meijer", "Xavier de Groot", "Yvette van Beek", "Zara Koster", "Adam de Jong", "Bella Bakker", "Cas de Vos", "Demi van der Linden", "Erik Hendriksen", "Fleur van Dam", "Gerard Jansen", "Hannah Smit", "Ivo van Houten", "Julia van der Berg", "Koen Peters", "Lara Visser", "Max van Dijk", "Nienke de Boer", "Oscar van Rijn", "Paula Kuijpers", "Quinten de Graaf", "Rianne Meijer", "Stefan de Bruijn", "Tara Jacobs", "Udo Janssen", "Vera de Vries", "Wouter van Dam", "Xenia Kok", "Yara van Leeuwen", "Zacharias Hendriks", "Abigail de Haan", "Bas van Buren", "Caroline van Dam", "Daan Jansen", "Elise Kuijpers", "Frank de Jong", "Greetje de Wit", "Harm van der Wal", "Iris van den Berg", "Jeroen Smit", "Karin Peters", "Lucas Bakker", "Marta Visser", "Niels de Boer", "Olivier van Dijk", "Paulien de Vos", "Quirine Hoekstra", "Ramon Vos", "Sanne Jacobs", "Thijs van Leeuwen", "Ulrike Vos", "Vince Hendriks", "Wendy Meijer", "Xavier de Groot", "Yvette van Beek", "Zara Koster", "Albert de Jong", "Britt Bakker", "Cees de Vos", "Diana van der Linden", "Erik Hendriksen", "Fenna van Dam", "Geert Jansen", "Hannah Smit", "Ivo van Houten", "Julia van der Berg", "Kees Peters", "Lisanne Visser", "Max van Dijk", "Nienke de Boer", "Oscar van Rijn", "Paula Kuijpers", "Quinten de Graaf", "Rianne Meijer", "Stefan de Bruijn", "Tara Jacobs", "Udo Janssen", "Vera de Vries", "Wouter van Dam", "Xenia Kok", "Yara van Leeuwen", "Zacharias Hendriks", "Abigail de Haan", "Bas van Buren", "Caroline van Dam", "Daan Jansen", "Elise Kuijpers", "Frank de Jong", "Greetje de Wit", "Harm van der Wal", "Iris van den Berg", "Jeroen Smit", "Karin Peters", "Lucas Bakker", "Marta Visser", "Niels de Boer", "Olivier van Dijk", "Paulien de Vos", "Quirine Hoekstra", "Ramon Vos", "Sanne Jacobs", "Thijs van Leeuwen", "Ulrike Vos", "Vince Hendriks", "Wendy Meijer", "Xavier de Groot", "Yvette van Beek", "Zara Koster", "Adam de Jong", "Bella Bakker", "Cas de Vos", "Demi van der Linden", "Erik Hendriksen", "Fleur van Dam", "Gerard Jansen", "Hannah Smit", "Ivo van Houten", "Julia van der Berg", "Koen Peters", "Lara Visser", "Max van Dijk", "Nienke de Boer", "Oscar van Rijn", "Paula Kuijpers", "Quinten de Graaf", "Rianne Meijer", "Stefan de Bruijn", "Tara Jacobs", "Udo Janssen", "Vera de Vries", "Wouter van Dam", "Xenia Kok", "Yara van Leeuwen", "Zacharias Hendriks", "Abigail de Haan", "Bas van Buren", "Caroline van Dam", "Daan Jansen", "Elise Kuijpers", "Frank de Jong", "Greetje de Wit", "Harm van der Wal", "Iris van den Berg", "Jeroen Smit", "Karin Peters", "Lucas Bakker", "Marta Visser", "Niels de Boer", "Olivier van Dijk", "Paulien de Vos", "Quirine Hoekstra", "Ramon Vos", "Sanne Jacobs", "Thijs van Leeuwen", "Ulrike Vos", "Vince Hendriks", "Wendy Meijer", "Xavier de Groot", "Yvette van Beek", "Zara Koster", "Albert de Jong", "Britt Bakker", "Cees de Vos", "Diana van der Linden", "Erik Hendriksen", "Fenna van Dam", "Geert Jansen", "Hannah Smit", "Ivo van Houten", "Julia van der Berg", "Kees Peters", "Lisanne Visser", "Max van Dijk", "Nienke de Boer", "Oscar van Rijn", "Paula Kuijpers", "Quinten de Graaf", "Rianne Meijer", "Stefan de Bruijn", "Tara Jacobs", "Udo Janssen", "Vera de Vries", "Wouter van Dam", "Xenia Kok", "Yara van Leeuwen", "Zacharias Hendriks", "Abigail de Haan", "Bas van Buren", "Caroline van Dam", "Daan Jansen", "Elise Kuijpers", "Frank de Jong", "Greetje de Wit", "Harm van der Wal", "Iris van den Berg", "Jeroen Smit", "Karin Peters", "Lucas Bakker", "Marta Visser", "Niels de Boer", "Olivier van Dijk", "Paulien de Vos", "Quirine Hoekstra", "Ramon Vos", "Sanne Jacobs", "Thijs van Leeuwen", "Ulrike Vos", "Vince Hendriks", "Wendy Meijer", "Xavier de Groot", "Yvette van Beek", "Zara Koster", "Adam de Jong", "Bella Bakker", "Cas de Vos", "Demi van der Linden", "Erik Hendriksen", "Fleur van Dam", "Gerard Jansen", "Hannah Smit", "Ivo van Houten", "Julia van der Berg", "Koen Peters", "Lara Visser", "Max van Dijk", "Nienke de Boer", "Oscar van Rijn", "Paula Kuijpers", "Quinten de Graaf", "Rianne Meijer", "Stefan de Bruijn", "Tara Jacobs", "Udo Janssen", "Vera de Vries", "Wouter van Dam", "Xenia Kok", "Yara van Leeuwen", "Zacharias Hendriks", "Abigail de Haan", "Bas van Buren", "Caroline van Dam", "Daan Jansen", "Elise Kuijpers", "Frank de Jong", "Greetje de Wit", "Harm van der Wal", "Iris van den Berg", "Jeroen Smit", "Karin Peters", "Lucas Bakker", "Marta Visser", "Niels de Boer", "Olivier van Dijk", "Paulien de Vos", "Quirine Hoekstra", "Ramon Vos", "Sanne Jacobs", "Thijs van Leeuwen", "Ulrike Vos", "Vince Hendriks", "Wendy Meijer", "Xavier de Groot", "Yvette van Beek", "Zara Koster", "Albert de Jong", "Britt Bakker", "Cees de Vos", "Diana van der Linden", "Erik Hendriksen", "Fenna van Dam", "Geert Jansen", "Hannah Smit", "Ivo van Houten", "Julia van der Berg", "Kees Peters", "Lisanne Visser", "Max van Dijk", "Nienke de Boer", "Oscar van Rijn", "Paula Kuijpers", "Quinten de Graaf", "Rianne Meijer", "Stefan de Bruijn", "Tara Jacobs", "Udo Janssen", "Vera de Vries", "Wouter van Dam", "Xenia Kok", "Yara van Leeuwen", "Zacharias Hendriks", "Abigail de Haan", "Bas van Buren", "Caroline van Dam", "Daan Jansen", "Elise Kuijpers", "Frank de Jong", "Greetje de Wit", "Harm van der Wal", "Iris van den Berg", "Jeroen Smit", "Karin Peters", "Lucas Bakker", "Marta Visser", "Niels de Boer", "Olivier van Dijk", "Paulien de Vos", "Quirine Hoekstra", "Ramon Vos", "Sanne Jacobs", "Thijs van Leeuwen", "Ulrike Vos", "Vince Hendriks", "Wendy Meijer", "Xavier de Groot", "Yvette van Beek", "Zara Koster", "Adam de Jong", "Bella Bakker", "Cas de Vos", "Demi van der Linden", "Erik Hendriksen", "Fleur van Dam", "Gerard Jansen", "Hannah Smit", "Ivo van Houten", "Julia van der Berg", "Koen Peters", "Lara Visser", "Max van Dijk", "Nienke de Boer", "Oscar van Rijn", "Paula Kuijpers", "Quinten de Graaf", "Rianne Meijer", "Stefan de Bruijn", "Tara Jacobs", "Udo Janssen", "Vera de Vries", "Wouter van Dam", "Xenia Kok", "Yara van Leeuwen", "Zacharias Hendriks", "Abigail de Haan", "Bas van Buren", "Caroline van Dam", "Daan Jansen", "Elise Kuijpers", "Frank de Jong", "Greetje de Wit", "Harm van der Wal", "Iris van den Berg", "Jeroen Smit", "Karin Peters", "Lucas Bakker", "Marta Visser", "Niels de Boer", "Olivier van Dijk", "Paulien de Vos", "Quirine Hoekstra", "Ramon Vos", "Sanne Jacobs", "Thijs van Leeuwen", "Ulrike Vos", "Vince Hendriks", "Wendy Meijer", "Xavier de Groot", "Yvette van Beek", "Zara Koster", "Albert de Jong", "Britt Bakker", "Cees de Vos", "Diana van der Linden", "Erik Hendriksen", "Fenna van Dam", "Geert Jansen", "Hannah Smit", "Ivo van Houten", "Julia van der Berg", "Kees Peters", "Lisanne Visser", "Max van Dijk", "Nienke de Boer", "Oscar van Rijn", "Paula Kuijpers", "Quinten de Graaf", "Rianne Meijer", "Stefan de Bruijn", "Tara Jacobs", "Udo Janssen", "Vera de Vries", "Wouter van Dam", "Xenia Kok", "Yara van Leeuwen", "Zacharias Hendriks", "Abigail de Haan", "Bas van Buren", "Caroline van Dam", "Daan Jansen", "Elise Kuijpers", "Frank de Jong", "Greetje de Wit", "Harm van der Wal", "Iris van den Berg", "Jeroen Smit", "Karin Peters", "Lucas Bakker", "Marta Visser", "Niels de Boer", "Olivier van Dijk", "Paulien de Vos", "Quirine Hoekstra", "Ramon Vos", "Sanne Jacobs", "Thijs van Leeuwen", "Ulrike Vos", "Vince Hendriks", "Wendy Meijer", "Xavier de Groot", "Yvette van Beek", "Zara Koster", "Adam de Jong", "Bella Bakker", "Cas de Vos", "Demi van der Linden", "Erik Hendriksen", "Fleur van Dam", "Gerard Jansen", "Hannah Smit", "Ivo van Houten", "Julia van der Berg", "Koen Peters", "Lara Visser", "Max van Dijk", "Nienke de Boer", "Oscar van Rijn", "Paula Kuijpers", "Quinten de Graaf", "Rianne Meijer", "Stefan de Bruijn", "Tara Jacobs", "Udo Janssen", "Vera de Vries", "Wouter van Dam", "Xenia Kok", "Yara van Leeuwen", "Zacharias Hendriks", "Abigail de Haan", "Bas van Buren", "Caroline van Dam", "Daan Jansen", "Elise Kuijpers", "Frank de Jong", "Greetje de Wit", "Harm van der Wal", "Iris van den Berg", "Jeroen Smit", "Karin Peters", "Lucas Bakker", "Marta Visser", "Niels de Boer", "Olivier van Dijk", "Paulien de Vos", "Quirine Hoekstra", "Ramon Vos", "Sanne Jacobs", "Thijs van Leeuwen", "Ulrike Vos", "Vince Hendriks", "Wendy Meijer", "Xavier de Groot", "Yvette van Beek", "Zara Koster", "Albert de Jong", "Britt Bakker", "Cees de Vos", "Diana van der Linden", "Erik Hendriksen", "Fenna van Dam", "Geert Jansen", "Hannah Smit", "Ivo van Houten", "Julia van der Berg", "Kees Peters", "Lisanne Visser", "Max van Dijk", "Nienke de Boer", "Oscar van Rijn", "Paula Kuijpers", "Quinten de Graaf", "Rianne Meijer", "Stefan de Bruijn", "Tara Jacobs", "Udo Janssen", "Vera de Vries", "Wouter van Dam", "Xenia Kok", "Yara van Leeuwen", "Zacharias Hendriks", "Abigail de Haan", "Bas van Buren", "Caroline van Dam", "Daan Jansen", "Elise Kuijpers", "Frank de Jong", "Greetje de Wit", "Harm van der Wal", "Iris van den Berg", "Jeroen Smit", "Karin Peters", "Lucas Bakker", "Marta Visser", "Niels de Boer", "Olivier van Dijk", "Paulien de Vos", "Quirine Hoekstra", "Ramon Vos", "Sanne Jacobs", "Thijs van Leeuwen", "Ulrike Vos", "Vince Hendriks", "Wendy Meijer", "Xavier de Groot", "Yvette van Beek", "Zara Koster", "Adam de Jong", "Bella Bakker", "Cas de Vos", "Demi van der Linden", "Erik Hendriksen", "Fleur van Dam", "Gerard Jansen", "Hannah Smit", "Ivo van Houten", "Julia van der Berg", "Koen Peters", "Lara Visser", "Max van Dijk", "Nienke de Boer", "Oscar van Rijn", "Paula Kuijpers", "Quinten de Graaf", "Rianne Meijer", "Stefan de Bruijn", "Tara Jacobs", "Udo Janssen", "Vera de Vries", "Wouter van Dam", "Xenia Kok", "Yara van Leeuwen", "Zacharias Hendriks", "Abigail de Haan", "Bas van Buren", "Caroline van Dam", "Daan Jansen", "Elise Kuijpers", "Frank de Jong", "Greetje de Wit", "Harm van der Wal", "Iris van den Berg", "Jeroen Smit", "Karin Peters", "Lucas Bakker", "Marta Visser", "Niels de Boer", "Olivier van Dijk", "Paulien de Vos", "Quirine Hoekstra", "Ramon Vos", "Sanne Jacobs", "Thijs van Leeuwen", "Ulrike Vos", "Vince Hendriks", "Wendy Meijer", "Xavier de Groot", "Yvette van Beek", "Zara Koster", "Albert de Jong", "Britt Bakker", "Cees de Vos", "Diana van der Linden", "Erik Hendriksen", "Fenna van Dam", "Geert Jansen", "Hannah Smit", "Ivo van Houten", "Julia van der Berg", "Kees Peters", "Lisanne Visser", "Max van Dijk", "Nienke de Boer", "Oscar van Rijn", "Paula Kuijpers", "Quinten de Graaf", "Rianne Meijer", "Stefan de Bruijn", "Tara Jacobs", "Udo Janssen", "Vera de Vries", "Wouter van Dam", "Xenia Kok", "Yara van Leeuwen", "Zacharias Hendriks", "Abigail de Haan", "Bas van Buren", "Caroline van Dam", "Daan Jansen", "Elise Kuijpers", "Frank de Jong", "Greetje de Wit", "Harm van der Wal", "Iris van den Berg", "Jeroen Smit", "Karin Peters", "Lucas Bakker", "Marta Visser", "Niels de Boer", "Olivier van Dijk", "Paulien de Vos", "Quirine Hoekstra", "Ramon Vos", "Sanne Jacobs", "Thijs van Leeuwen", "Ulrike Vos", "Vince Hendriks", "Wendy Meijer", "Xavier de Groot", "Yvette van Beek", "Zara Koster", "Adam de Jong", "Bella Bakker", "Cas de Vos", "Demi van der Linden", "Erik Hendriksen", "Fleur van Dam", "Gerard Jansen", "Hannah Smit", "Ivo van Houten", "Julia van der Berg", "Koen Peters", "Lara Visser", "Max van Dijk", "Nienke de Boer", "Oscar van Rijn", "Paula Kuijpers", "Quinten de Graaf", "Rianne Meijer", "Stefan de Bruijn", "Tara Jacobs", "Udo Janssen", "Vera de Vries", "Wouter van Dam", "Xenia Kok", "Yara van Leeuwen", "Zacharias Hendriks", "Abigail de Haan", "Bas van Buren", "Caroline van Dam", "Daan Jansen", "Elise Kuijpers", "Frank de Jong", "Greetje de Wit", "Harm van der Wal", "Iris van den Berg", "Jeroen Smit", "Karin Peters", "Lucas Bakker", "Marta Visser", "Niels de Boer", "Olivier van Dijk", "Paulien de Vos", "Quirine Hoekstra", "Ramon Vos", "Sanne Jacobs", "Thijs van Leeuwen", "Ulrike Vos", "Vince Hendriks", "Wendy Meijer", "Xavier de Groot", "Yvette van Beek", "Zara Koster", "Albert de Jong", "Britt Bakker", "Cees de Vos", "Diana van der Linden", "Erik Hendriksen", "Fenna van Dam", "Geert Jansen", "Hannah Smit", "Ivo van Houten", "Julia van der Berg", "Kees Peters", "Lisanne Visser", "Max van Dijk", "Nienke de Boer", "Oscar van Rijn", "Paula Kuijpers", "Quinten de Graaf", "Rianne Meijer", "Stefan de Bruijn", "Tara Jacobs", "Udo Janssen", "Vera de Vries", "Wouter van Dam", "Xenia Kok", "Yara van Leeuwen", "Zacharias Hendriks", "Abigail de Haan", "Bas van Buren", "Caroline van Dam", "Daan Jansen", "Elise Kuijpers", "Frank de Jong", "Greetje de Wit", "Harm van der Wal", "Iris van den Berg", "Jeroen Smit", "Karin Peters", "Lucas Bakker", "Marta Visser", "Niels de Boer", "Olivier van Dijk", "Paulien de Vos", "Quirine Hoekstra", "Ramon Vos", "Sanne Jacobs", "Thijs van Leeuwen", "Ulrike Vos", "Vince Hendriks", "Wendy Meijer", "Xavier de Groot", "Yvette van Beek", "Zara Koster", "Adam de Jong", "Bella Bakker", "Cas de Vos", "Demi van der Linden", "Erik Hendriksen", "Fleur van Dam", "Gerard Jansen", "Hannah Smit", "Ivo van Houten", "Julia van der Berg", "Koen Peters", "Lara Visser", "Max van Dijk", "Nienke de Boer", "Oscar van Rijn", "Paula Kuijpers", "Quinten de Graaf", "Rianne Meijer", "Stefan de Bruijn", "Tara Jacobs", "Udo Janssen", "Vera de Vries", "Wouter van Dam", "Xenia Kok", "Yara van Leeuwen", "Zacharias Hendriks", "Abigail de Haan", "Bas van Buren", "Caroline van Dam", "Daan Jansen", "Elise Kuijpers", "Frank de Jong", "Greetje de Wit", "Harm van der Wal", "Iris van den Berg", "Jeroen Smit", "Karin Peters", "Lucas Bakker", "Marta Visser", "Niels de Boer", "Olivier van Dijk", "Paulien de Vos", "Quirine Hoekstra", "Ramon Vos", "Sanne Jacobs", "Thijs van Leeuwen", "Ulrike Vos", "Vince Hendriks", "Wendy Meijer", "Xavier de Groot", "Yvette van Beek", "Zara Koster", "Albert de Jong", "Britt Bakker", "Cees de Vos", "Diana van der Linden", "Erik Hendriksen", "Fenna van Dam", "Geert Jansen", "Hannah Smit", "Ivo van Houten", "Julia van der Berg", "Kees Peters", "Lisanne Visser", "Max van Dijk", "Nienke de Boer", "Oscar van Rijn", "Paula Kuijpers"};
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

    private ArrayList<PathFinder> foodStands = new ArrayList<>();
    private PathFinder foodStand1PathFinder;
    private PathFinder foodStand2PathFinder;
    private PathFinder foodStand3PathFinder;
    private PathFinder foodStand4PathFinder;
    private PathFinder foodStand5PathFinder;
    private PathFinder foodStand6PathFinder;
    private PathFinder foodStand7PathFinder;
    private PathFinder foodStand8PathFinder;
    private PathFinder foodStand9PathFinder;
    private PathFinder foodStand10PathFinder;
    private PathFinder foodStand11PathFinder;
    private PathFinder foodStand12PathFinder;
    private PathFinder foodStand13PathFinder;
    private PathFinder foodStand14PathFinder;
    private PathFinder foodStand15PathFinder;
    private PathFinder foodStand16PathFinder;
    private PathFinder foodStand17PathFinder;

    private PathFinder exitPathFinder;
    private boolean emergency = false;

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane mainPane = new BorderPane();
        canvas = new ResizableCanvas(g -> draw(g), mainPane);
        canvas.setWidth(1024);
        canvas.setHeight(1024);

        canvas.setOnMousePressed(event -> checkClicked(new Point2D.Double((event.getX() - camera.getTransform().getTranslateX())*(1/camera.getTransform().getScaleX()),
                (event.getY() - camera.getTransform().getTranslateY())*(1/camera.getTransform().getScaleY()))));
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
        Button emergency = new Button("Noodsituatie!");
        emergency.setOnAction(event -> {
            this.emergency = true;
            for (Visitor visitor : visitors) {
                visitor.setPathFinder(exitPathFinder);
            }
        });
        mainPane.setTop(emergency);
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

        Tile foodStand1 = graph.getNodes()[46][45];
        Tile foodStand2 = graph.getNodes()[46][58];
        Tile foodStand3 = graph.getNodes()[46][62];
        Tile foodStand4 = graph.getNodes()[46][66];
        Tile foodStand5 = graph.getNodes()[46][71];
        Tile foodStand6 = graph.getNodes()[46][75];
        Tile foodStand7 = graph.getNodes()[46][81];
        Tile foodStand8 = graph.getNodes()[46][85];
        Tile foodStand9 = graph.getNodes()[45][92];
        Tile foodStand10 = graph.getNodes()[45][95];
        Tile foodStand11 = graph.getNodes()[45][101];
        Tile foodStand12 = graph.getNodes()[45][106];

        Tile foodStand13 = graph.getNodes()[61][69];
        Tile foodStand14 = graph.getNodes()[61][75];
        Tile foodStand15 = graph.getNodes()[61][83];
        Tile foodStand16 = graph.getNodes()[61][91];
        Tile foodStand17 = graph.getNodes()[61][98];

        Tile exitTile = graph.getNodes()[65][127];

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

        foodStand1PathFinder = new PathFinder(foodStand1, graph, collisionLayer);
        foodStand2PathFinder = new PathFinder(foodStand2, graph, collisionLayer);
        foodStand3PathFinder = new PathFinder(foodStand3, graph, collisionLayer);
        foodStand4PathFinder = new PathFinder(foodStand4, graph, collisionLayer);
        foodStand5PathFinder = new PathFinder(foodStand5, graph, collisionLayer);
        foodStand6PathFinder = new PathFinder(foodStand6, graph, collisionLayer);
        foodStand7PathFinder = new PathFinder(foodStand7, graph, collisionLayer);
        foodStand8PathFinder = new PathFinder(foodStand8, graph, collisionLayer);
        foodStand9PathFinder = new PathFinder(foodStand9, graph, collisionLayer);
        foodStand10PathFinder = new PathFinder(foodStand10, graph, collisionLayer);
        foodStand11PathFinder = new PathFinder(foodStand11, graph, collisionLayer);
        foodStand12PathFinder = new PathFinder(foodStand12, graph, collisionLayer);
        foodStand13PathFinder = new PathFinder(foodStand13, graph, collisionLayer);
        foodStand14PathFinder = new PathFinder(foodStand14, graph, collisionLayer);
        foodStand15PathFinder = new PathFinder(foodStand15, graph, collisionLayer);
        foodStand16PathFinder = new PathFinder(foodStand16, graph, collisionLayer);
        foodStand17PathFinder = new PathFinder(foodStand17, graph, collisionLayer);
        foodStands.add(foodStand1PathFinder);
        foodStands.add(foodStand2PathFinder);
        foodStands.add(foodStand3PathFinder);
        foodStands.add(foodStand4PathFinder);
        foodStands.add(foodStand5PathFinder);
        foodStands.add(foodStand6PathFinder);
        foodStands.add(foodStand7PathFinder);
        foodStands.add(foodStand8PathFinder);
        foodStands.add(foodStand9PathFinder);
        foodStands.add(foodStand10PathFinder);
        foodStands.add(foodStand11PathFinder);
        foodStands.add(foodStand12PathFinder);
        foodStands.add(foodStand13PathFinder);
        foodStands.add(foodStand14PathFinder);
        foodStands.add(foodStand15PathFinder);
        foodStands.add(foodStand16PathFinder);
        foodStands.add(foodStand17PathFinder);

        exitPathFinder = new PathFinder(exitTile, graph, collisionLayer);
        exitPathFinder.calculateDistanceMapWithGraph();


        alphaPathFinder.calculateDistanceMapWithGraph();
        spawnTile = alphaPathFinder.getSpawnTile();
        betaPathFinder.calculateDistanceMapWithGraph();
        charliePathFinder.calculateDistanceMapWithGraph();
        deltaPathFinder.calculateDistanceMapWithGraph();
        echoPathFinder.calculateDistanceMapWithGraph();

        toiletPathFinder.calculateDistanceMapWithGraph();
        for (PathFinder toiletsPath : toiletsPaths) {
            toiletsPath.calculateDistanceMapWithGraph();
        }
        for (PathFinder foodStand : foodStands) {
            foodStand.calculateDistanceMapWithGraph();
        }

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

        exitPathFinder.draw(g);
        g.setTransform(new AffineTransform());
    }

    public void update(double deltaTime) {
        for (Toilet toilet : toilets) {
            toilet.update(deltaTime);
        }
        if (timer % 144 == 0) {
            if (visitors.size() < 50 && !this.emergency) {
                Visitor visitor = new Visitor(
                        new Point2D.Double(126 * 32, 64 * 32),
                        alphaPathFinder,
                        0.001,
                        names[visitors.size()],
                        (int) (Math.floor(Math.random() * (60 - 15 + 1)) + 15));
                visitors.add(visitor);
            }
        }
        ArrayList<Visitor> visitorsCopy = new ArrayList<>(visitors);
        for (Visitor visitor : visitorsCopy) {
            visitor.update(visitors, deltaTime);
            if (visitor.getPathFinder().equals(exitPathFinder)) {
                ArrayList<Tile> neighbours = visitor.getPathFinder().getTargetTile().getNeighbours();
                if (visitor.getPathFinder().getTargetTile().equals(visitor.getCurrentTile()) || neighbours.contains(visitor.getCurrentTile())) {
                    visitors.remove(visitor);
                }
            }
            if (!this.emergency) {

                if (visitor.getFoodcounter() <= 10) {
                    if (!foodStands.contains(visitor.getPathFinder())) {
                        visitor.setPrevPathFinder(visitor.getPathFinder());
                        visitor.setPathFinder(foodStands.get((int) (Math.random() * 17)));
                    } else {
                        if (visitor.getPathFinder().getTargetTile().equals(visitor.getCurrentTile())) {
                            visitor.setFoodcounter(100);
                            visitor.setPathFinder(visitor.getPrevPathFinder());
                        }
                    }
                }

                if (visitor.getDrinkCounter() >= 100 && !visitor.isGoingToToilet()) {
                    if (!visitor.getPathFinder().equals(toiletPathFinder)) {
                        System.out.println("Toiletpathfinder: " + toiletPathFinder);
                        System.out.println("Alphapathfinder: " + alphaPathFinder);
                        System.out.println("setting pathfinder: " + visitor.getPathFinder());
                        visitor.setPrevPathFinder(visitor.getPathFinder());
                        System.out.println("Er moet iemand naar het toilet");
                        visitor.setPathFinder(toiletPathFinder);
                    }
                }
                if (visitor.getCurrentTile().equals(toiletPathFinder.getTargetTile()) && visitor.getDrinkCounter() >= 100) {
                    for (Toilet toilet : toilets) {
                        System.out.println("checking toilet: " + toilet);
                        if (!toilet.isOccupied() && !visitor.isGoingToToilet()) {
                            System.out.println("gaat toilet in: " + toilet);
                            visitor.setCurrentToilet(toilet);
                            visitor.setGoingToToilet(true);
                            toilet.setAnimationStarted(true);
                            visitor.setPathFinder(toiletsPaths.get(toilets.indexOf(toilet)));
                            break;
                        }
                    }
                }
                for (Toilet toilet : toilets) {
                    if (toilet.isAnimationStarted() && visitor.getCurrentToilet() == toilet) {
                        if (toilet.getAnimationTimer() >= 0 && toilet.getAnimationTimer() < 5) {
                            toilet.setOccupied(true);
                            System.out.println("0 tot 5");
                        } else if (toilet.getAnimationTimer() >= 5 && toilet.getAnimationTimer() < 15) {
                            if (visitor.getCurrentTile().equals(visitor.getPathFinder().getTargetTile())) {
                                visitor.setInToilet(true);
                            }
                            System.out.println("5 tot 15");
                        } else if (toilet.getAnimationTimer() >= 15 && toilet.getAnimationTimer() < 23) {
                            System.out.println("15 tot 23");
                        } else if (toilet.getAnimationTimer() >= 23 && toilet.getAnimationTimer() < 30) {
                            System.out.println("23 tot 30");
                            visitor.setDrinkCounter(0);
                            visitor.setInToilet(false);
                            visitor.setGoingToToilet(false);
                        } else {
                            System.out.println("else");
                            visitor.setPathFinder(visitor.getPrevPathFinder());
                            visitor.setCurrentToilet(null);
                            toilet.setOccupied(false);
                            toilet.setAnimationStarted(false);
                            toilet.setStatus(0);
                            toilet.setAnimationTimer(0);
                        }
                    }
                }
            }
        }

        timer++;
    }

    public void checkClicked(Point2D point){
        ArrayList<Visitor> visitorsCopy = new ArrayList<>(visitors);
        for (Visitor visitor : visitorsCopy) {
            if (visitors.get(visitors.indexOf(visitor)).isClickedOnMe(point)){
                Alert visitorInfo = new Alert(Alert.AlertType.INFORMATION);
                visitorInfo.setTitle(visitor.getName());
                visitorInfo.setHeaderText(visitor.getName());
                String plan = "";
                if (toilets.contains(visitor.getPathFinder())){
                    plan = "De wc";
                } else if (visitor.getPathFinder().equals(toiletPathFinder)){
                    plan = "De wc";
                } else if (foodStands.contains(visitor.getPathFinder())){
                    plan = "Een foodtruck";
                } else if (visitor.getPathFinder().equals(exitPathFinder)){
                    plan = "De uitgang";
                } else {
                    plan = "Een podium";
                }
                visitorInfo.setContentText("Naam: " + visitor.getName() +
                        "\n" + "Leeftijd: " + visitor.getAge() +
                        "\n" + "Gaat naar: " + plan+
                        "\n" + "Blaas: " + (int)visitor.getDrinkCounter() + "%" +
                        "\n" + "Maag: " + (int)visitor.getFoodcounter() + "%");
                visitorInfo.showAndWait();
            }
        }
    }
}

