package Application.Simulation;

import java.awt.geom.Point2D;

public class PathFinder {

    private int[][] collisionLayer;
    private Point2D targetPoint;
    public PathFinder(Point2D targetPoint) {
        this.targetPoint = targetPoint;
    }

    public void setCollisionLayer(int[][] collisionLayer) {
        this.collisionLayer = collisionLayer;
    }

    public int[][] getCollisionLayer() {
        return collisionLayer;
    }
}
