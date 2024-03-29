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
    private BufferedImage spriteSheet;
    private ArrayList<BufferedImage> characterDown = new ArrayList<>();
    private ArrayList<BufferedImage> characterLeft = new ArrayList<>();
    private ArrayList<BufferedImage> characterRight = new ArrayList<>();
    private ArrayList<BufferedImage> characterUp = new ArrayList<>();
    private int imageWidth;
    private int imageHeight;

    public Visitor(Point2D position, double speed) {
        try {
            this.spriteSheet = ImageIO.read(this.getClass().getResourceAsStream("/Visitors/MV_Graveyard_Zombies_Skeleton.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.position = position;
        this.targetPosition = position;
        this.angle = 0;
        this.speed = speed;
        this.imageWidth = 50;
        this.imageHeight = 100;
        CreateImages();
    }

    private void CreateImages() {
        for (int x = 0; x < 3; x++) {
            this.characterDown.add(spriteSheet.getSubimage(x * this.imageWidth, 0, this.imageWidth, this.imageHeight));
            this.characterLeft.add(spriteSheet.getSubimage(x * this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight));
            this.characterRight.add(spriteSheet.getSubimage(x * this.imageWidth, 2 * this.imageHeight, this.imageWidth, this.imageHeight));
            this.characterUp.add(spriteSheet.getSubimage(x * this.imageWidth, 3 * this.imageHeight, this.imageWidth, this.imageHeight));
        }
    }

    public void update(ArrayList<Visitor> visitors, double deltatime) {
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

        transform.translate(position.getX() - spriteSheet.getWidth() / 2, position.getY() - spriteSheet.getHeight() / 1.5);
        transform.rotate(this.angle, this.imageWidth / 2, this.imageHeight / 2);
        
        if (this.angle == -Math.PI/2){
            graphics2D.drawImage(this.characterDown.get(0), transform, null);
        } else if (this.angle == Math.PI/2){
            graphics2D.drawImage(this.characterUp.get(0), transform, null);
        } else if (this.angle > -Math.PI/2 && this.angle < Math.PI/2){
            graphics2D.drawImage(this.characterRight.get(0), transform, null);
        } else {
            graphics2D.drawImage(this.characterLeft.get(0), transform, null);
        }

    }

    public void setTargetPosition(Point2D targetPosition) {
        this.targetPosition = targetPosition;
    }

}
