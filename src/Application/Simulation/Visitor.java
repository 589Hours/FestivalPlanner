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

    private Point2D position;
    private Point2D targetPosition;
    private double angle;
    private double speed;
    private BufferedImage image;

    public Visitor(Point2D position, double speed) {
        try {
            this.image = ImageIO.read(this.getClass().getResourceAsStream("/Visitors/MV_Graveyard_Zombies_Skeleton.png"));
            this.image = image.getSubimage(0, 0, image.getWidth() / 11, image.getHeight() / 6);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.position = position;
        this.targetPosition = position;
        this.angle = 0;
        this.speed = speed;
    }

    public void update(ArrayList<Visitor> visitors) {
        if (this.position == this.targetPosition) {
            return;
        }
        double newAngle = Math.atan2(targetPosition.getY() - position.getY(), targetPosition.getX() - position.getX());

        double difference = angle - newAngle;
        
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

        boolean collision = false;

        for (Visitor visitor : visitors) {
            if (visitor != this) {
                if (visitor.position.distance(newPosition) <= 64) {
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

        transform.translate(position.getX() - image.getWidth() / 2, position.getY() - image.getHeight() / 2);
        transform.rotate(this.angle, image.getWidth() / 2, image.getHeight() / 2);

        graphics2D.setColor(Color.red);
//        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        graphics2D.draw(new Ellipse2D.Double(this.targetPosition.getX(), this.targetPosition.getY(), 10, 10));
        graphics2D.drawImage(this.image, transform, null);
    }

    public void setTargetPosition(Point2D targetPosition) {
        this.targetPosition = targetPosition;
    }

}
