package Application.Simulation;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class PathFinder {

    private int[][] collisionLayer;
    HashMap<Tile, Integer> path = new HashMap<>();
    private Point2D targetPoint;
    private int collisionTileID = 78225;
    private int locationID = 78224;
    private Tile lastTile;
//    private int distanceValue = 1;
    public PathFinder(Point2D targetPoint) {
        this.targetPoint = targetPoint;
    }
    // CollisionID = 78225
    // LocatieID = 78224

    public void calculateDistanceMap(){
        Queue<Tile> todo = new LinkedList();
        ArrayList<Tile> done = new ArrayList<>();

        Tile targetTile = new Tile((int) targetPoint.getX(), (int) targetPoint.getY());
        todo.add(targetTile);
        path.put(targetTile, 0);
        lastTile = targetTile;
        while(!todo.isEmpty()){
            Tile current = todo.poll();
            int x = current.getX();
            int y = current.getY();

            int distanceValue = path.get(lastTile);

            lastTile = current;

            //tile rechts
            if (collisionLayer[x+1][y] != collisionTileID ){
                Tile newTile = new Tile(x+1, y);
                if (!done.contains(newTile)) {
                    todo.add(newTile);
                    path.put(newTile, distanceValue+1);
                }
                done.add(newTile);
            }

            //tile links
            if (collisionLayer[y-1][x] != collisionTileID) {
                Tile newTile = new Tile(x-1, y);
                if (!done.contains(newTile)) {
                    todo.add(newTile);
                    path.put(newTile, distanceValue+1);
                }
                done.add(newTile);
            }

            //tile boven
            if (collisionLayer[y][x+1] != collisionTileID) {
                Tile newTile = new Tile(x, y+1);
                if (!done.contains(newTile)) {
                    todo.add(newTile);
                    path.put(newTile, distanceValue+1);
                }
                done.add(newTile);
            }

            //tile onder
            if (collisionLayer[y][x-1] != collisionTileID) {
                Tile newTile = new Tile(x, y-1);
                if (!done.contains(newTile)) {
                    todo.add(newTile);
                    path.put(newTile, distanceValue+1);
                }
                done.add(newTile);
            }
        }
    }
    public void setCollisionLayer(int[][] collisionLayer) {
        this.collisionLayer = collisionLayer;
    }

    public int[][] getCollisionLayer() {
        return collisionLayer;
    }
}
