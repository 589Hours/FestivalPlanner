package Application.Simulation;

import javafx.scene.input.ScrollEvent;

import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

    public class Camera {
        private double zoom; // Zoomniveau van de camera
        private Point2D.Double centerPoint; // Het middelpunt van het camerabeeld

        // Constructor voor de camera
        public Camera() {
            this.zoom = 0.25; // Standaard zoomniveau
            this.centerPoint = new Point2D.Double(0, 0); // Standaard middelpunt
        }

        // Methode om de transformatiematrix te krijgen die het camerabeeld representeert
        public AffineTransform getTransform() {
            AffineTransform transform = new AffineTransform();
            transform.scale(zoom, zoom); // Zoom toepassen
            transform.translate(centerPoint.getX(), centerPoint.getY()); // Transleren naar het middelpunt
            return transform;
        }

        // Methode om een schermpositie om te zetten naar wereldcoördinaten
        public Point2D getWorldPosition(Point2D screenPos) throws NoninvertibleTransformException {
            AffineTransform tx = getTransform();
            return tx.inverseTransform(screenPos, null); // Omgekeerde transformatie
        }

        // Methode om muis-scroll gebeurtenissen te behandelen voor zoomen en pannen
        public void mouseScroll(ScrollEvent event) {
            if (event.isControlDown()) {
                // Zoomen wanneer de controletoets is ingedrukt
                this.zoom *= 1 + event.getDeltaY() / 250.0f;
            } else if (event.isShiftDown()) {
                // Horizontaal pannen wanneer de shifttoets is ingedrukt
                centerPoint = new Point2D.Double(centerPoint.getX() + (event.getDeltaX() * 1.5), centerPoint.getY());
            } else {
                // Verticaal pannen
                centerPoint = new Point2D.Double(centerPoint.getX(), centerPoint.getY() + (event.getDeltaY() * 1.5));
            }
        }

        // Methode om het huidige zoomniveau op te halen
        public double getZoom() {
            return this.zoom;
        }
    }

