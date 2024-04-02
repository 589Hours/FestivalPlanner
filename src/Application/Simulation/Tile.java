package Application.Simulation;

import java.util.ArrayList;

public class Tile {
    private int y; // Y-coördinaat van de tegel
    private int x; // X-coördinaat van de tegel
    private int distanceValue; // Afstandswaarde van de tegel (voor padvinderalgoritmen)
    private String ID; // Uniek ID van de tegel
    private ArrayList<Tile> neighbours; // Lijst van naburige tegels

    // Constructor voor de tegel met opgegeven y- en x-coördinaten
    public Tile(int y, int x) {
        this.y = y;
        this.x = x;
        this.ID = y + "," + x; // Uniek ID aanmaken op basis van y- en x-coördinaten
        this.neighbours = new ArrayList<>(); // Lijst van buren initialiseren
    }

    // Methode om het ID van de tegel op te halen
    public String getID() {
        return ID;
    }

    // Methode om de x-coördinaat van de tegel in te stellen
    public void setX(int x) {
        this.x = x;
    }

    // Methode om de y-coördinaat van de tegel in te stellen
    public void setY(int y) {
        this.y = y;
    }

    // Methode om de x-coördinaat van de tegel op te halen
    public int getX() {
        return x;
    }

    // Methode om de y-coördinaat van de tegel op te halen
    public int getY() {
        return y;
    }

    // Methode om de x-coördinaat van het punt van de tegel op te halen
    public int getPointX() {
        return x * 32; // Vermenigvuldiging met 32 omdat het een tegelbreedte van 32 heeft
    }

    // Methode om de y-coördinaat van het punt van de tegel op te halen
    public int getPointY() {
        return y * 32; // Vermenigvuldiging met 32 omdat het een tegelhoogte van 32 heeft
    }

    // Methode om een buurtegel toe te voegen aan de lijst van buren
    public void addNeighbour(Tile tile) {
        neighbours.add(tile);
    }

    // Methode om de afstandswaarde van de tegel in te stellen
    public void setDistanceValue(int distanceValue) {
        this.distanceValue = distanceValue;
    }

    // Methode om de afstandswaarde van de tegel op te halen
    public int getDistanceValue() {
        return distanceValue;
    }

    // Methode om te controleren of een tegel een buur van deze tegel is
    public boolean isNeighbour(Tile tile) {
        return neighbours.contains(tile);
    }

    // Methode om de lijst van buren op te halen
    public ArrayList<Tile> getNeighbours() {
        return neighbours;
    }

    // Overschrijf methode om een String representatie van de tegel te retourneren
    @Override
    public String toString() {
        return "Tile{" +
                "y=" + y +
                ", x=" + x +
                ", ID='" + ID + '\'' +
                '}';
    }

    // Methode om de buren van de tegel af te drukken
    public void printNeighbours() {
        System.out.println(neighbours);
    }
}
