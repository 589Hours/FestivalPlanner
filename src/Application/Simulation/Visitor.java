package Application.Simulation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Visitor {

    private Point2D position;
    private Point2D targetPosition;
    private double angle;
    private double speed;
    private BufferedImage image;

    public Visitor(Point2D position, Point2D targetPosition, double speed) {
        try {
            this.image = ImageIO.read(this.getClass().getResourceAsStream("/Visitors/MV_Graveyard_Zombies_Skeleton.png"));
            this.image = image.getSubimage(0, 0, image.getWidth() / 11, image.getHeight() / 6);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.position = position;
        this.targetPosition = targetPosition;
        this.angle = 0;
        this.speed = speed;
    }

    public void update(ArrayList<Visitor> visitors) {
        double newAngle = Math.atan2(targetPosition.getY() - position.getY(), targetPosition.getX() - position.getX());

        double differance = newAngle - angle;
        if (differance < -Math.PI) {
            differance += 2 * Math.PI;
        }
        if (differance > Math.PI) {
            differance -= 2 * Math.PI;
        }

        if (Math.abs(differance) < 0.1) {
            angle = newAngle;
        } else if (newAngle > 0) {
            angle += 0.1;
        } else {
            angle -= 0.1;
        }

        Point2D newPosition = new Point2D.Double(
                position.getX() + speed * Math.cos(angle),
                position.getY() + speed * Math.sin(angle));

        boolean hasCollision = false;
        for (Visitor visitor : visitors) {
            if (visitor != this){
                if (visitor.position.distance(this.position) < 64){
                    hasCollision = true;
                }
            }
        }

        if (!hasCollision) {
            this.position = newPosition;
        } else {
            this.angle += 0.2;
        }
    }

    public void draw(Graphics2D graphics2D) {
        AffineTransform transform = new AffineTransform();
        transform.translate(position.getX(), position.getY());
        transform.rotate(this.angle);

        graphics2D.drawImage(this.image, transform, null);
    }

    public void setTargetPosition(Point2D targetPosition) {
        this.targetPosition = targetPosition;
    }

}
