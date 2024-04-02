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


    public ArrayList<Tile> getNeighbours() {
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

}
