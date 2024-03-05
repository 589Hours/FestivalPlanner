package Application.Simulation;

import javafx.scene.input.ScrollEvent;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class Camera {
    private int zoom;
    private Point2D.Double centerPoint;

    public Camera() {
        this.zoom = 1;
        this.centerPoint = new Point2D.Double(0, 0);
    }

    public AffineTransform getTransform() {
        AffineTransform transform = new AffineTransform();
        transform.translate(centerPoint.getX(), centerPoint.getY());
        transform.scale(zoom, zoom);

        return transform;
    }

    public void mouseScroll(ScrollEvent event) {
        if (event.isControlDown()) {
            // Zoom
            System.out.println("Zoom");
            if (event.getDeltaY() > 0) {
                this.zoom += (int) (1 + event.getDeltaY() / 500.0f);
            } else {
                this.zoom += (int) (-1 + event.getDeltaY() / 500.0f);
            }
        } else if (event.isShiftDown()) {
            // X aanpassen
            System.out.println("X");
            centerPoint = new Point2D.Double(centerPoint.getX() + (event.getDeltaX() / 4), centerPoint.getY());
            System.out.println(centerPoint.getX() + ", " + centerPoint.getY());
        } else {
            // Y aanpassen
            System.out.println("Y");
            centerPoint = new Point2D.Double(centerPoint.getX(), centerPoint.getY() + (event.getDeltaY() / 4));
            System.out.println(centerPoint.getX() + ", " + centerPoint.getY());
        }
    }
}
