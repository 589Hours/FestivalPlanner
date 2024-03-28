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
    private BufferedImage image;
    private Tile currentTile;

    public Visitor(Point2D position, PathFinder pathFinder, Tile spawnTile, double speed) {
        try {
            this.image = ImageIO.read(this.getClass().getResourceAsStream("/Visitors/MV_Graveyard_Zombies_Skeleton.png"));
            this.image = image.getSubimage(0, 0, image.getWidth() / 11, image.getHeight() / 6);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.pathFinder = pathFinder;
        this.position = position;
        this.currentTile = spawnTile;
        this.targetPosition = position;
        this.angle = 0;
        this.speed = speed;
    }

    public void update(ArrayList<Visitor> visitors) {
//        for (Tile tile : pathFinder.path.keySet()) {
//            int newDistance = pathFinder.path.get(tile);
//            System.out.println(newDistance);
//            System.out.println(tile.isNeighbour(tile));
//            if (newDistance < currentDistance && tile.isNeighbour(tile)) {
//                currentDistance = newDistance;
//                this.targetPosition = new Point2D.Double(tile.getPointX(), tile.getPointY());
//            }
//        }

//        if (position.getX() <= targetPosition.getX()+1000 ||
//                position.getX() >= targetPosition.getX()-1000 &&
//                position.getY() <= targetPosition.getY()+1000 ||
//                position.getY() >= targetPosition.getY()-1000){
            for (Tile tile : currentTile.getNeighbours()) {
                int newDistance = pathFinder.path.get(tile);
                System.out.println(newDistance);
                System.out.println(tile.isNeighbour(tile));
                if (newDistance < currentDistance) {
                    currentDistance = newDistance;
                    this.targetPosition = new Point2D.Double(tile.getPointX(), tile.getPointY());
                    this.currentTile = tile;
                }
            }
//        }

        double newAngle = Math.atan2(targetPosition.getY() - position.getY(), targetPosition.getX() - position.getX());

        double difference = angle - newAngle;

//        System.out.println(difference);

        while (difference > Math.PI) {
            difference -= 2 * Math.PI;
        }
        while (difference < -Math.PI) {
            difference += 2 * Math.PI;
        }

        if (difference < -0.1) {
            angle += 0.1;
        } else if (difference > 0.1) {
            angle -= 0.1;
        } else
            angle = newAngle;

        Point2D newPosition = new Point2D.Double(
                position.getX() + speed * Math.cos(angle),
                position.getY() + speed * Math.sin(angle)
        );

//        System.out.println(newPosition);

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

        transform.translate(position.getX() - image.getWidth() /2 , position.getY() - image.getHeight() /1.5);
        transform.rotate(this.angle, image.getWidth() / 2, image.getHeight() / 2);

        graphics2D.setColor(Color.red);
        graphics2D.draw(new Ellipse2D.Double(this.targetPosition.getX(), this.targetPosition.getY(), 10, 10));
        graphics2D.drawImage(this.image, transform, null);
        graphics2D.setColor(Color.green);
        graphics2D.fill(new Ellipse2D.Double(this.position.getX(), this.position.getY(), 10,10));
        graphics2D.setColor(Color.black);
    }

    public void setTargetPosition(Point2D targetPosition) {
        this.targetPosition = targetPosition;
    }

}
