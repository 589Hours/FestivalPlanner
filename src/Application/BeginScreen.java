package Application;

import data.Performance;
import org.jfree.fx.FXGraphics2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BeginScreen extends Canvas {

    private FXGraphics2D fxGraphics2D; // Grafische context voor het tekenen
    private BufferedImage festivalPlanner; // Afbeelding voor de festivalplanner
    private BufferedImage tableView; // Afbeelding voor de tabelweergave
    private BufferedImage blockView; // Afbeelding voor de blokweergave
    private BufferedImage startSimulation; // Afbeelding voor het starten van de simulatie

    // Constructor voor het beginvenster
    public BeginScreen() {
        try {
            // Afbeeldingen laden vanuit de bronmap
            this.festivalPlanner = ImageIO.read(this.getClass().getResourceAsStream("/FestivalPlanner.png"));
            this.tableView = ImageIO.read(this.getClass().getResourceAsStream("/Tableview.png"));
            this.blockView = ImageIO.read(this.getClass().getResourceAsStream("/BlockView.png"));
            this.startSimulation = ImageIO.read(this.getClass().getResourceAsStream("/StartSimulation.png"));
        } catch (IOException e) {
            throw new RuntimeException(e); // Uitzondering gooien als er een fout optreedt bij het laden van de afbeeldingen
        }
    }

    // Methode om het beginvenster te tekenen
    public void draw(FXGraphics2D graphics) {
        this.fxGraphics2D = graphics; // Grafische context instellen
        if (graphics != null) {
            drawBeginScreen(graphics); // Het beginvenster tekenen als de grafische context niet leeg is
        }
    }

    // Methode voor het tekenen van het beginvenster
    private void drawBeginScreen(FXGraphics2D g){
        g.drawImage(this.festivalPlanner, 300,0, null); // Festivalplanner-afbeelding tekenen op specifieke locatie
        AffineTransform original = g.getTransform(); // Originele transformatie opslaan
        g.scale(0.5, 0.5); // Schalen van de tekencontext
        g.drawImage(this.tableView, 1100,400, null); // Tabelweergave-afbeelding tekenen op specifieke locatie
        g.drawImage(this.blockView, 1100,700, null); // Blokweergave-afbeelding tekenen op specifieke locatie
        g.scale(1.5, 1.5); // Terug schalen naar oorspronkelijke grootte
        g.drawImage(this.startSimulation, 575,650, null); // Startsimulatie-afbeelding tekenen op specifieke locatie
        g.setTransform(original); // Oorspronkelijke transformatie herstellen
    }

    // Methode om de grafische context op te halen
    public FXGraphics2D getFxGraphics2D() {
        return fxGraphics2D;
    }

    // Methode om te controleren welk onderdeel is aangeklikt op basis van de gegeven muispositie
    public String checkClicked(Point2D point2D) {
        if (point2D.getX() > 560 && point2D.getX() < 1060 && point2D.getY() > 222 && point2D.getY() < 322){
            return "TableView"; // Teruggeven "TableView" als tabelweergave is aangeklikt
        } else if (point2D.getX() > 560 && point2D.getX() < 1060 && point2D.getY() > 370 && point2D.getY() < 470){
            return "BlockView"; // Teruggeven "BlockView" als blokweergave is aangeklikt
        } else if (point2D.getX() > 467 && point2D.getX() < 1167 && point2D.getY() > 520 && point2D.getY() < 620){
            return "StartSimulation"; // Teruggeven "StartSimulation" als starten van simulatie is aangeklikt
        }
        return null; // Teruggeven null als er geen onderdeel is aangeklikt
    }
}