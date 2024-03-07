package Application.Simulation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Visitor {

    private Point2D position;
    private Point2D targetPosition;
    private double angle;
    private double speed;
    private BufferedImage image;

    public Visitor(Point2D position, Point2D targetPosition, double speed) {
        try {
            this.image = ImageIO.read(this.getClass().getResourceAsStream("/Visitors/MV_Graveyard_Zombies_Skeleton.png"));
            this.image = image.getSubimage(0,0, image.getWidth()/11, image.getHeight()/6);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.position = position;
        this.targetPosition = targetPosition;
        this.angle = 0;
        this.speed = speed;
    }

    public void update(){
        System.out.println(this.position.getX() + ", " + this.position.getY());
    }

    public void draw(Graphics2D graphics2D){
        AffineTransform transform = new AffineTransform();
        transform.translate(position.getX(), position.getY());
        transform.rotate(this.angle);

        graphics2D.drawImage(this.image, transform, null);
    }

}
