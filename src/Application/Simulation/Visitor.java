package Application.Simulation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Visitor {

    private PathFinder pathFinder;
    private Point2D position;
    private Point2D targetPosition;
    private double angle;
    private double speed;
    private double currentDistance = Integer.MAX_VALUE;
    private BufferedImage spriteSheet;
    private Tile currentTile;
    private ArrayList<BufferedImage> characterDown = new ArrayList<>();
    private ArrayList<BufferedImage> characterLeft = new ArrayList<>();
    private ArrayList<BufferedImage> characterRight = new ArrayList<>();
    private ArrayList<BufferedImage> characterUp = new ArrayList<>();
    private int imageWidth;
    private int imageHeight;
    private double animationCounter;
    private double newAngle;

    public Visitor(Point2D position, PathFinder pathFinder, double speed) {
        try {
            this.spriteSheet = ImageIO.read(this.getClass().getResourceAsStream("/Visitors/MV_Graveyard_Zombies_Skeleton.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.pathFinder = pathFinder;
        this.position = position;
        this.currentTile = pathFinder.getTileFromPosition(new Point2D.Double(126, 64));
        this.targetPosition = position;
        this.speed = speed;
        this.angle = 0;
        this.imageWidth = 47; // 45-50
        this.imageHeight = 100;
        CreateImages();
        this.animationCounter = 0;
        this.newAngle = 0;
    }

    private void CreateImages() {
        for (int x = 0; x < 3; x++) {
            this.characterDown.add(spriteSheet.getSubimage(x * this.imageWidth, 0, this.imageWidth, this.imageHeight));
            this.characterLeft.add(spriteSheet.getSubimage(x * this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight));
            this.characterRight.add(spriteSheet.getSubimage(x * this.imageWidth, 2 * this.imageHeight, this.imageWidth, this.imageHeight));
            this.characterUp.add(spriteSheet.getSubimage(x * this.imageWidth, 3 * this.imageHeight, this.imageWidth, this.imageHeight));
        }
    }

    public void update(ArrayList<Visitor> visitors, double deltaTime) {
        this.animationCounter += (5*deltaTime);
        if (this.animationCounter > 3){
            this.animationCounter = 0;
        }

        if (position.distance(targetPosition) < 20) {
            for (Tile tile : currentTile.getNeighbours()) {

                if (this.pathFinder.path.get(tile) == null)
                    continue;

                int newDistance = this.pathFinder.path.get(tile);

                if (newDistance < currentDistance) {
                    currentDistance = newDistance;

                    double x = tile.getPointX();
                    double y = tile.getPointY();

                    this.targetPosition = new Point2D.Double(x, y);
                    this.currentTile = pathFinder.getTileFromPosition(new Point2D.Double(x / 32, y / 32));

                    //break when a closer tile is found
                    break;
                }
            }
        }

        newAngle = Math.atan2(this.targetPosition.getY() - this.position.getY(), this.targetPosition.getX() - this.position.getX());

        double angleDifference = angle - newAngle;

        while (angleDifference > Math.PI) {
            angleDifference -= 2 * Math.PI;
        }

        while (angleDifference < -Math.PI) {
            angleDifference += 2 * Math.PI;
        }

        if (angleDifference < -0.1) {
            angle += 0.1;
        } else if (angleDifference > 0.1) {
            angle -= 0.1;
        } else {
            angle = newAngle;
        }

        Point2D newPosition = new Point2D.Double(
                position.getX() + speed * Math.cos(angle) * targetPosition.getX(),
                position.getY() + speed * Math.sin(angle) * targetPosition.getY()
        );

        boolean collision = false;

        for (Visitor visitor : visitors) {
            if (visitor != this) {
                //32 so the heads can collide (giving the 2.5D effect we want)
                if (visitor.position.distance(newPosition) <= 32) {
                    collision = true;
                }
            }
        }

        if (!collision) {
            this.position = newPosition;
        } else {
            this.angle += 0.2;
        }
    }

    public void draw(Graphics2D graphics2D) {
        AffineTransform transform = new AffineTransform();

        transform.translate(position.getX() - this.imageWidth / 2.25 , position.getY() - this.imageHeight / 2.3);

        if (this.newAngle >= Math.PI/3 && this.newAngle <= Math.PI*2/3){
            graphics2D.drawImage(this.characterDown.get((int)this.animationCounter), transform, null);
        } else if (this.newAngle <= -Math.PI/3 && this.newAngle >= -Math.PI*2/3){
            graphics2D.drawImage(this.characterUp.get((int)this.animationCounter), transform, null);
        } else if (this.newAngle > -Math.PI/3 && this.newAngle < Math.PI/3){
            graphics2D.drawImage(this.characterRight.get((int)this.animationCounter), transform, null);
        } else {
            graphics2D.drawImage(this.characterLeft.get((int)this.animationCounter), transform, null);
        }

        graphics2D.setColor(Color.green);
        graphics2D.fill(new Ellipse2D.Double(this.position.getX(), this.position.getY(), 10,10));
        graphics2D.setColor(Color.black);
    }

    public void setTargetPosition(Point2D targetPosition) {
        this.targetPosition = targetPosition;
    }

}
