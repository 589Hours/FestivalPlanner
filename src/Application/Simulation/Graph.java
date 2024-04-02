package Application.Simulation;

public class Graph {
    private Tile[][] nodes = new Tile[128][128]; // 2D-array voor het opslaan van de tegels

    // Constructor voor de Graph
    public Graph(){
        // Loop door elke rij en kolom om tegels te initialiseren
        for (int y = 0; y < 128; y++) {
            for (int x = 0; x < 128; x++) {
                Tile tile = new Tile(y,x); // Maak een nieuwe tegel aan
                nodes[y][x] = tile; // Voeg de tegel toe aan de array
            }
        }
        // Wijs buren toe aan elke tegel
        assignNeighbours();
    }

    // Methode om buren toe te wijzen aan elke tegel
    public void assignNeighbours(){
        // Loop door elke rij en kolom om buren toe te wijzen
        for (int y = 0; y < 128; y++) {
            for (int x = 0; x < 128; x++) {
                Tile tile = nodes[y][x]; // Haal de huidige tegel op
                // Voeg de boventegel toe als buur als die bestaat
                if (y-1 >= 0){
                    tile.addNeighbour(nodes[y-1][x]);
                }
                // Voeg de onderste tegel toe als buur als die bestaat
                if (y+1 < 128){
                    tile.addNeighbour(nodes[y+1][x]);
                }
                // Voeg de linker tegel toe als buur als die bestaat
                if (x-1 >= 0){
                    tile.addNeighbour(nodes[y][x-1]);
                }
                // Voeg de rechter tegel toe als buur als die bestaat
                if (x+1 < 128){
                    tile.addNeighbour(nodes[y][x+1]);
                }

            }
        }
    }

    // Methode om de 2D-array van tegels op te halen
    public Tile[][] getNodes() {
        return nodes;
    }
}
