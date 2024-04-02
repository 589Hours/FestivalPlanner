package Application.Simulation;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class PathFinder {

    private int[][] collisionLayer; // Botsingslaag voor het vinden van paden
    HashMap<Tile, Integer> path = new HashMap<>(); // Map voor het opslaan van afstanden tot tegels
    private Tile targetTile; // Doeltegel voor het vinden van paden
    private int collisionTileID = 78225; // ID van een tegel die een botsing aangeeft
    private Tile lastTile; // Laatste tegel tijdens het berekenen van het pad
    private Tile spawnTile; // Starttegel
    private Graph graph; // Grafiek voor het vinden van paden

    // Constructor voor de PathFinder
    public PathFinder(Tile targetTile, Graph graph) {
        this.targetTile = targetTile;
        this.graph = graph;
    }

    // Methode om de afstandkaart te berekenen met behulp van de grafiek
    public void calculateDistanceMapWithGraph(){
        Queue<Tile> todo = new LinkedList();

        todo.add(targetTile);
        path.put(targetTile, 0);
        lastTile = targetTile;

        while(!todo.isEmpty()){
            Tile current = todo.poll();

            for (Tile neighbor : current.getNeighbours()) {
                // Controleer of de buurman geen botsingstegel is
                if (collisionLayer[neighbor.getY()][neighbor.getX()] != collisionTileID){
                    // Als de buurman nog niet in het pad is opgenomen, voeg deze toe aan de te doen lijst
                    if (!path.containsKey(neighbor)){
                        todo.add(neighbor);
                        // Update de afstand tot de buurman
                        path.put(neighbor, path.get(current)+1);
                    }
                }
            }

            lastTile = current;
            todo.remove(current);
        }
    }

    // Methode om de afstand tot elke tegel op het pad te tekenen
    public void draw(Graphics2D graphics) {
        for (Tile tile : path.keySet()) {
            double x = tile.getX()*32;
            double y = tile.getY()*32;

            graphics.setColor(Color.BLACK);
            graphics.drawString(Integer.toString(path.get(tile)), (int) x, (int) y);
        }
    }

    // Methode om de botsingslaag in te stellen
    public void setCollisionLayer(int[][] collisionLayer) {
        this.collisionLayer = collisionLayer;
    }

    // Methode om de starttegel op te halen
    public Tile getSpawnTile(){
        return this.spawnTile;
    }

    // Methode om de tegel op basis van een positie op te halen
    public Tile getTileFromPosition(Point2D point) {
        return graph.getNodes()[(int) point.getY()][(int) point.getX()];
    }

    // Methode om buren van een tegel af te drukken
    public void printNeighboursforTile(Tile thisTile) {
        for (Tile tile : thisTile.getNeighbours()) {
            System.out.println("Tile: "+ thisTile + " has neighbour " + tile);
        }
    }
}