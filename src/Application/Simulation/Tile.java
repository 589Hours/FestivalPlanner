package Application.Simulation;

public class Tile {
    private int y;
    private int x;
    private String ID;
    public Tile(int y, int x) {
        this.y = y;
        this.x = x;
        this.ID = y+","+x;
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
}
