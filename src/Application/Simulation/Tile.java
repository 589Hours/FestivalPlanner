package Application.Simulation;

import java.util.ArrayList;

public class Tile {
    private int y;
    private int x;
    private String ID;
    private ArrayList<Tile> neighbours;
    public Tile(int y, int x) {
        this.y = y;
        this.x = x;
        this.ID = y+","+x;
        this.neighbours = new ArrayList<>();
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

}
