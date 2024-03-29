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
    private Tile spawnTile;

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
//        checkedTiles.add("126,64");
        while(!todo.isEmpty()){
            Tile current = todo.poll();
            lastTile.printNeighbours();
            int x = current.getX();
            int y = current.getY();

            if (x == 127 || x == 0 || y == 127 || y == 0) {
                continue;
            }
//            current.addNeighbour(lastTile);
//
//            if (current.getID().equals("64,126")) {
//                path.put(current, Integer.MAX_VALUE);
//                Tile topNeighbour = new Tile(127, 64);
//                Tile bottomNeighbour = new Tile(125, 64);
//                Tile leftNeighbour = new Tile(126, 63);
//                Tile rightNeighbour = new Tile(126, 65);
//                current.addNeighbour(topNeighbour);
//                current.addNeighbour(bottomNeighbour);
//                current.addNeighbour(leftNeighbour);
//                current.addNeighbour(rightNeighbour);
//                path.put(topNeighbour, Integer.MAX_VALUE-1);
//                path.put(bottomNeighbour, Integer.MAX_VALUE-1);
//                path.put(leftNeighbour, Integer.MAX_VALUE-1);
//                path.put(rightNeighbour, Integer.MAX_VALUE-1);
//                checkedTiles.add(current.getID());
//                checkedTiles.add(topNeighbour.getID());
//                checkedTiles.add(bottomNeighbour.getID());
//                checkedTiles.add(leftNeighbour.getID());
//                checkedTiles.add(rightNeighbour.getID());
//            }

            int distanceValue = path.get(lastTile);

            //tile rechts
            if (collisionLayer[y+1][x] != collisionTileID){
                Tile newTile = new Tile(y+1, x);
                if (!checkedTiles.contains(newTile.getID())) {
                    todo.add(newTile);
                    checkedTiles.add(newTile.getID());
                    path.put(newTile, distanceValue+1);
                }

                if (y+1 != 127){
                   current.addNeighbour(newTile);
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
                if (y-1 != 0){
                    current.addNeighbour(newTile);
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
                if (x+1 != 127){
                    current.addNeighbour(newTile);
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
                if (x-1 != 0){
                    current.addNeighbour(newTile);
                }
            }
            
            if (current.getID().equals("64,126")){
                System.out.println("spawnTile made");
                this.spawnTile = current;
                System.out.println(spawnTile);
            }

//            path.put(current, distanceValue+1);

            lastTile = current;
            checkedTiles.add(current.getID());
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
    public Tile getSpawnTile(){
        return this.spawnTile;
    }

    public Tile getTileFromPosition(Point2D point) {
        for (Tile tile : path.keySet()) {
            if (tile.getX() ==point.getX() && tile.getY() == point.getY()) {
                System.out.println("getTileFromPos");
                return tile;
            }
        }
        return null;
    }
}
