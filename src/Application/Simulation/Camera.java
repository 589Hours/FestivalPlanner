package Application.Simulation;

import javafx.scene.input.ScrollEvent;

import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

public class Camera {
    private double zoom;
    private Point2D.Double centerPoint;

    public Camera() {
        this.zoom = 0.25;
        this.centerPoint = new Point2D.Double(0, 0);
    }

    public AffineTransform getTransform() {
        AffineTransform transform = new AffineTransform();
        transform.scale(zoom, zoom);
        transform.translate(centerPoint.getX(), centerPoint.getY());
        return transform;
    }

    public Point2D getWorldPosition(Point2D screenPos) throws NoninvertibleTransformException {
        AffineTransform tx = getTransform();
        return tx.inverseTransform(screenPos, null);
    }

    public void mouseScroll(ScrollEvent event) {
        if (event.isControlDown()) {
            // Zoom
            System.out.println("Zoom");
            this.zoom *= 1 + event.getDeltaY()/250.0f;
        } else if (event.isShiftDown()) {
            // X aanpassen
            System.out.println("X");
            centerPoint = new Point2D.Double(centerPoint.getX() + (event.getDeltaX()*1.5), centerPoint.getY());
            System.out.println(centerPoint.getX() + ", " + centerPoint.getY());
        } else {
            // Y aanpassen
            System.out.println("Y");
            centerPoint = new Point2D.Double(centerPoint.getX(), centerPoint.getY() + (event.getDeltaY()*1.5));
            System.out.println(centerPoint.getX() + ", " + centerPoint.getY());
        }
    }

    public double getZoom(){
        return this.zoom;
    }
}
