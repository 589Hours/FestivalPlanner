package Application.Simulation;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class PathFinder {

    private int[][] collisionLayer;
    HashMap<Tile, Integer> path = new HashMap<>();
    private Tile targetTile;
    private int collisionTileID = 78225;
    private int locationID = 78224;
    private Tile lastTile;
//    private int distanceValue = 1;
    public PathFinder(Tile targetTile) {
        this.targetTile = targetTile;
    }
    // CollisionID = 78225
    // LocatieID = 78224

    public void calculateDistanceMap(){
        Queue<Tile> todo = new LinkedList();
        ArrayList<String> checkedTiles = new ArrayList<>();

        todo.add(targetTile);
        path.put(targetTile, 0);
        lastTile = targetTile;
        while(!todo.isEmpty()){
            Tile current = todo.poll();
            int x = current.getX();
            int y = current.getY();
            System.out.println("Current: " + y + "-" + x + "\n");
            if (x == 127 || x == 0 || y == 127 || y == 0) {
                continue;
            }

            int distanceValue = path.get(lastTile);

            //tile rechts
            if (collisionLayer[y+1][x] != collisionTileID){
                Tile newTile = new Tile(y+1, x);
                if (!checkedTiles.contains(newTile.getID())) {
                    todo.add(newTile);
                    checkedTiles.add(newTile.getID());
                    path.put(newTile, distanceValue+1);
                }
            }

            //tile links
            if (collisionLayer[y-1][x] != collisionTileID) {
                Tile newTile = new Tile((y-1), x);
                if (!checkedTiles.contains(newTile.getID())) {
                    todo.add(newTile);
                    checkedTiles.add(newTile.getID());
                    path.put(newTile, distanceValue+1);
                }

            }

            //tile boven
            if (collisionLayer[y][x+1] != collisionTileID) {
                Tile newTile = new Tile(y, x+1);
                if (!checkedTiles.contains(newTile.getID())) {
                    todo.add(newTile);
                    checkedTiles.add(newTile.getID());
                    path.put(newTile, distanceValue+1);
                }

            }

            //tile onder
            if (collisionLayer[y][x-1] != collisionTileID) {
                Tile newTile = new Tile(y, x-1);
                if (!checkedTiles.contains(newTile.getID())) {
                    todo.add(newTile);
                    checkedTiles.add(newTile.getID());
                    path.put(newTile, distanceValue+1);
                }
            }

            lastTile = current;
            checkedTiles.add(current.getID());
            System.out.println(checkedTiles.size());
            todo.remove(current);
        }
    }

    public void draw(Graphics2D graphics) {
        for (Tile tile : path.keySet()) {
            double x = tile.getX()*32;
            double y = tile.getY()*32;
            
            graphics.setColor(Color.BLACK);
            graphics.drawString(Integer.toString(path.get(tile)), (int) x, (int) y);
//            graphics.fill(new Ellipse2D.Double(x, y, 10, 10));
        }
    }
    public void setCollisionLayer(int[][] collisionLayer) {
        this.collisionLayer = collisionLayer;
    }

    public int[][] getCollisionLayer() {
        return collisionLayer;
    }
}
