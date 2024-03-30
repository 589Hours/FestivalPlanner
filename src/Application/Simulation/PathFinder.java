package Application.Simulation;

import java.awt.*;
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
    private Graph graph;

    //    private int distanceValue = 1;
    public PathFinder(Tile targetTile, Graph graph) {
//        this.targetTile = targetTile;
        this.graph = graph;
    }
    // CollisionID = 78225
    // LocatieID = 78224


    public void calculateDistanceMapWithGraph(){
        Queue<Tile> todo = new LinkedList();
        ArrayList<Tile> checkedTiles = new ArrayList<>();
        this.targetTile = graph.getNodes()[126][64];

        todo.add(targetTile);
        path.put(targetTile, 0);
        lastTile = targetTile;

        while(!todo.isEmpty()){
            Tile current = todo.poll();

            int distanceValue = path.get(lastTile);

            for (Tile neighbor : current.getNeighbours()) {
                if (collisionLayer[neighbor.getY()][neighbor.getX()] != collisionTileID){
                    if (!path.containsKey(neighbor)){
                        path.put(neighbor, distanceValue+1);
                    }
                    if(!checkedTiles.contains(neighbor)) {
                        todo.add(neighbor);
                    }
                }
            }

            lastTile = current;
            checkedTiles.add(current);
            todo.remove(current);
        }
    }


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

            int distanceValue = path.get(lastTile);
            System.out.println(distanceValue);

            //tile rechts
            if (collisionLayer[y+1][x] != collisionTileID){
                Tile newTile = new Tile(y+1, x);
                if (!checkedTiles.contains(newTile.getID())) {
                    todo.add(newTile);
                    checkedTiles.add(newTile.getID());
                    path.put(newTile, distanceValue+1);
                    current.addNeighbour(newTile);
                } else if (current.getY()+1 == lastTile.getY()){
                    current.addNeighbour(lastTile);
                }
            }

            //tile links
            if (collisionLayer[y-1][x] != collisionTileID) {
                Tile newTile = new Tile((y-1), x);
                if (!checkedTiles.contains(newTile.getID())) {
                    todo.add(newTile);
                    checkedTiles.add(newTile.getID());
                    path.put(newTile, distanceValue+1);
                    current.addNeighbour(newTile);
                } else if (current.getY()-1 == lastTile.getY()){
                    current.addNeighbour(lastTile);
                }

            }

            //tile boven
            if (collisionLayer[y][x+1] != collisionTileID) {
                Tile newTile = new Tile(y, x+1);
                if (!checkedTiles.contains(newTile.getID())) {
                    todo.add(newTile);
                    checkedTiles.add(newTile.getID());
                    path.put(newTile, distanceValue+1);
                    current.addNeighbour(newTile);
                } else if (current.getY()+1 == lastTile.getY() ||
                        current.getY()-1 == lastTile.getY()){
                    current.addNeighbour(lastTile);
                }

            }

            //tile onder
            if (collisionLayer[y][x-1] != collisionTileID) {
                Tile newTile = new Tile(y, x-1);
                if (!checkedTiles.contains(newTile.getID())) {
                    todo.add(newTile);
                    checkedTiles.add(newTile.getID());
                    path.put(newTile, distanceValue+1);
                    current.addNeighbour(newTile);
                } else if (current.getY()+1 == lastTile.getY() ||
                        current.getY()-1 == lastTile.getY()){
                    current.addNeighbour(lastTile);
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

    public Tile getSpawnTile(){
        return this.spawnTile;
    }

    public Tile getTileFromPosition(Point2D point) {
        return graph.getNodes()[(int) point.getY()][(int) point.getX()];
    }

    public void printNeighboursforTile(Tile thisTile) {
        for (Tile tile : thisTile.getNeighbours()) {
            System.out.println("Tile: "+ thisTile + " has neighbour " + tile);
        }
    }
}
