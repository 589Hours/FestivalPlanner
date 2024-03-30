package Application.Simulation;

public class Graph {
    private Tile[][] nodes = new Tile[128][128];


    public Graph(){
        for (int y = 0; y < 128; y++) {
            for (int x = 0; x < 128; x++) {
                Tile tile = new Tile(y,x);
                if (y-1 > 0){
                    tile.addNeighbour(new Tile(y-1, x));
                }
                if (y+1 < 128){
                    tile.addNeighbour(new Tile(y+1, x));
                }
                if (x-1 > 0){
                    tile.addNeighbour(new Tile(y,x-1));
                }
                if (x+1 < 128){
                    tile.addNeighbour(new Tile(y,x+1));
                }
                nodes[y][x] = tile;
            }
        }
    }

    public Tile[][] getNodes() {
        return nodes;
    }
}
