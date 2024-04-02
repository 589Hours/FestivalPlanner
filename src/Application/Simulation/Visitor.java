package Application.Simulation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Visitor {

    private PathFinder pathFinder; // Padzoeker voor de bezoeker
    private Point2D position; // Positie van de bezoeker
    private Point2D targetPosition; // Doelpositie van de bezoeker
    private double angle; // Hoek van de bezoeker
    private double speed; // Snelheid van de bezoeker
    private double currentDistance = Integer.MAX_VALUE; // Huidige afstandswaarde van de bezoeker
    private BufferedImage image; // Afbeelding van de bezoeker
    private Tile currentTile; // Huidige tegel van de bezoeker

    // Constructor voor de bezoeker met opgegeven positie, padzoeker en snelheid
    public Visitor(Point2D position, PathFinder pathFinder, double speed) {
        try {
            // Afbeelding van de bezoeker inladen
            this.image = ImageIO.read(this.getClass().getResourceAsStream("/Visitors/MV_Graveyard_Zombies_Skeleton.png"));
            // Subafbeelding selecteren (in dit geval de eerste sprite)
            this.image = image.getSubimage(0, 0, image.getWidth() / 11, image.getHeight() / 6);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Padzoeker en positie instellen
        this.pathFinder = pathFinder;
        this.position = position;
        // Huidige tegel instellen op basis van de startpositie
        this.currentTile = pathFinder.getTileFromPosition(new Point2D.Double(126, 64));
        // Doelpositie en snelheid instellen
        this.targetPosition = position;
        this.speed = speed;
        // Hoek initialiseren op 0
        this.angle = 0;
    }

    // Methode voor het bijwerken van de bezoeker
    public void update(ArrayList<Visitor> visitors) {
        // Loop door de buren van de huidige tegel
        for (Tile tile : currentTile.getNeighbours()) {
            // Als er geen pad is naar deze buur, doorgaan met de volgende buur
            if (this.pathFinder.path.get(tile) == null)
                continue;
            // Nieuwe afstandswaarde naar de buur ophalen
            int newDistance = this.pathFinder.path.get(tile);
            // Als de nieuwe afstand korter is dan de huidige afstand
            if (newDistance < currentDistance) {
                currentDistance = newDistance; // Huidige afstand bijwerken
                // Doelpositie instellen op de positie van de buur
                double x = tile.getPointX();
                double y = tile.getPointY();
                this.targetPosition = new Point2D.Double(x, y);
                // Huidige tegel bijwerken
                this.currentTile = pathFinder.getTileFromPosition(new Point2D.Double(x / 32, y / 32));
                // Onderbreken wanneer een dichtere tegel is gevonden
                break;
            }
        }

        // Hoek naar de doelpositie berekenen
        double newAngle = Math.atan2(this.targetPosition.getY() - this.position.getY(), this.targetPosition.getX() - this.position.getX());
        // Hoekverschil berekenen tussen de huidige hoek en de nieuwe hoek
        double angleDifference = angle - newAngle;
        // Hoekverschil binnen bereik van -pi tot pi houden
        while (angleDifference > Math.PI) {
            angleDifference -= 2 * Math.PI;
        }
        while (angleDifference < -Math.PI) {
            angleDifference += 2 * Math.PI;
        }

        // Hoek aanpassen op basis van het hoekverschil
        if (angleDifference < -0.1) {
            angle += 0.1;
        } else if (angleDifference > 0.1) {
            angle -= 0.1;
        } else {
            angle = newAngle;
        }

        // Nieuwe positie berekenen op basis van snelheid en hoek
        Point2D newPosition = new Point2D.Double(
                position.getX() + speed * Math.cos(angle) * targetPosition.getX(),
                position.getY() + speed * Math.sin(angle) * targetPosition.getY()
        );

        boolean collision = false; // Variabele om botsing te controleren

        // Controleren op botsingen met andere bezoekers
        for (Visitor visitor : visitors) {
            if (visitor != this) {// Afstand tussen de huidige bezoeker en de andere bezoeker berekenen
                if (visitor.position.distance(newPosition) <= 32) {
                    collision = true; // Botsing detecteren als de afstand kleiner is dan of gelijk is aan 32
                }
            }
        }

        // Als er geen botsing is, de positie van de bezoeker bijwerken naar de nieuwe positie, anders de hoek verhogen
        if (!collision) {
            this.position = newPosition; // Positie bijwerken naar de nieuwe positie
        } else {
            this.angle += 0.2; // Hoek verhogen als er een botsing is
        }
    }

    // Methode om de bezoeker op het scherm te tekenen
    public void draw(Graphics2D graphics2D) {
        AffineTransform transform = new AffineTransform();

        // Afbeelding vertalen naar de juiste positie op basis van de huidige positie
        transform.translate(position.getX() - image.getWidth() / 2, position.getY() - image.getHeight() / 1.5);

        // Doelpositie markeren met een rode cirkel
        graphics2D.setColor(Color.red);
        graphics2D.draw(new Ellipse2D.Double(this.targetPosition.getX(), this.targetPosition.getY(), 10, 10));

        // Afbeelding van de bezoeker tekenen met de transformatie
        graphics2D.drawImage(this.image, transform, null);

        // Huidige positie markeren met een groene cirkel
        graphics2D.setColor(Color.green);
        graphics2D.fill(new Ellipse2D.Double(this.position.getX(), this.position.getY(), 10, 10));

        // Kleur van de graphics herstellen naar zwart
        graphics2D.setColor(Color.black);
    }

    // Methode om de doelpositie van de bezoeker in te stellen
    public void setTargetPosition(Point2D targetPosition) {
        this.targetPosition = targetPosition;
    }
}
