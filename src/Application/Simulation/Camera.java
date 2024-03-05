package Application.Simulation;

import javafx.scene.input.ScrollEvent;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class Camera {
    private int zoom;
    private Point2D.Double centerPoint;

    public Camera() {
        this.zoom = 1;
        this.centerPoint = new Point2D.Double(0,0);
    }

    public AffineTransform getTransform(){
        AffineTransform transform = new AffineTransform();
        transform.translate(0,0);
        transform.scale(zoom, zoom);

        return transform;
    }

    public void mouseScroll(ScrollEvent event){
        if (event.isControlDown()){
            // Zoom
            System.out.println("Zoom");
            if (event.getDeltaY() > 0) {
                this.zoom += (int) (1 + event.getDeltaY() / 500.0f);
            } else {
                this.zoom += (int) (-1 + event.getDeltaY() / 500.0f);
            }
            System.out.println(this.zoom);
        } else if (event.isShiftDown()){
            // X aanpassen
            System.out.println("X");
            centerPoint = new Point2D.Double(centerPoint.getX() + event.getDeltaY(), centerPoint.getY());
        } else {
            // Y aanpassen
            System.out.println("Y");
            centerPoint = new Point2D.Double(centerPoint.getX(), centerPoint.getY() + event.getDeltaY());
        }
    }
}
