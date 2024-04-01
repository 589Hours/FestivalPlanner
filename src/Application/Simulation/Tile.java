package Application.Simulation;

import java.util.ArrayList;

public class Tile {
    private int y;
    private int x;
    private int distanceValue;
    private String ID;
    private ArrayList<Tile> neighbours;
    public Tile(int y, int x) {
        this.y = y;
        this.x = x;
        this.ID = y+","+x;
//        this.distanceValue = -1;
        this.neighbours = new ArrayList<>();
    }

    public String getID() {
        return ID;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getPointX() {
        return x*32;
    }

    public int getPointY() {
        return y*32;
    }

    public void addNeighbour(Tile tile) {
        neighbours.add(tile);
    }
    public void setDistanceValue(int distanceValue) {
        this.distanceValue = distanceValue;
    }

    public int getDistanceValue() {
        return distanceValue;
    }

    public boolean isNeighbour(Tile tile) {
        return neighbours.contains(tile);
    }

    public ArrayList<Tile> getNeighbours() {
//        System.out.println("Tile: "+ this + " has neighbours " + neighbours);
        return neighbours;
    }

    @Override
    public String toString() {
        return "Tile{" +
                "y=" + y +
                ", x=" + x +
                ", ID='" + ID + '\'' +
                '}';
    }

    public void printNeighbours() {
        System.out.println(neighbours);
    }
}
