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
    private Tile lastTile;
    private Tile spawnTile;
    private Graph graph;

    public PathFinder(Tile targetTile, Graph graph, int[][] collisionLayer) {
        this.targetTile = targetTile;
        this.graph = graph;
        this.collisionLayer = collisionLayer;
    }

    // CollisionID = 78225
    // LocatieID = 78224


    public void calculateDistanceMapWithGraph(){
        Queue<Tile> todo = new LinkedList();

        todo.add(targetTile);
        path.put(targetTile, 0);
        lastTile = targetTile;

        while(!todo.isEmpty()){
            Tile current = todo.poll();

            for (Tile neighbor : current.getNeighbours()) {
                if (collisionLayer[neighbor.getY()][neighbor.getX()] != collisionTileID){
                    if (!path.containsKey(neighbor)){
                        todo.add(neighbor);
                        path.put(neighbor, path.get(current)+1);
                    }
                }
            }

            lastTile = current;
            todo.remove(current);
        }
    }

    public void draw(Graphics2D graphics) {
        for (Tile tile : path.keySet()) {
            double x = tile.getX()*32;
            double y = tile.getY()*32;
            
            graphics.setColor(Color.BLACK);
            graphics.drawString(Integer.toString(path.get(tile)), (int) x, (int) y);
        }
    }

    public Tile getTargetTile() {
        return targetTile;
    }


    public Tile getTileFromPosition(Point2D point) {
        return graph.getNodes()[(int) point.getY()][(int) point.getX()];
    }

}
