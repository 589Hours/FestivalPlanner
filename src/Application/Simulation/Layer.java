package Application.Simulation;

import javax.json.JsonObject;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Layer {
    private int mapWidth;
    private int mapHeight;
    private int layerNum;

    private ArrayList<BufferedImage> tiles;
    private int[][] layerMap;
    private BufferedImage[][] mapTiles;
    private int[][] collisionLayer;

    // Constructor voor de Layer
    public Layer(JsonObject root, int mapWidth, int mapHeight, int layerNum, ArrayList<BufferedImage> tiles) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.layerNum = layerNum;
        this.tiles = tiles;

        // Initialiseren van arrays voor de kaart
        layerMap = new int[mapHeight][mapWidth];
        mapTiles = new BufferedImage[mapHeight][mapWidth];
        collisionLayer = new int[mapHeight][mapWidth];

        // Itereren over de kaartgegevens
        for (int y = 0; y < this.mapHeight; y++) {
            for (int x = 0; x < this.mapWidth; x++) {
                // Tegelindex uit de JSON-gegevens halen
                layerMap[y][x] = root.getJsonArray("layers").getJsonObject(layerNum).getJsonArray("data").getInt((y * 128) + x);

                // Botsingsgegevens opslaan in een aparte array, omdat deze niet hoeft te worden getekend
                if (root.getJsonArray("layers").getJsonObject(layerNum).getString("name").equals("Collision")){
                    collisionLayer[y][x] = layerMap[y][x];
                    continue;
                }

                // Tegelafbeeldingen opslaan voor de getekende kaart
                if (layerMap[y][x] > 0) {
                    mapTiles[y][x] = tiles.get(layerMap[y][x] - 1);
                }
            }
        }
    }

    // Methode om de laag te tekenen
    public void draw(Graphics2D g) {
        AffineTransform transform = new AffineTransform();
        int customTileSize = 32; // Aangepaste grootte voor tegels

        // Itereren over de tegelafbeeldingen en deze tekenen
        for (int y = 0; y < this.mapHeight; y++) {
            for (int x = 0; x < this.mapHeight; x++) {
                transform.translate(x * (customTileSize), y * (customTileSize)); // Transleren naar de juiste positie

                g.drawImage(mapTiles[y][x], transform, null); // Tegel tekenen op de huidige positie

                transform.setToTranslation(0, 0); // Herstellen van de transformatie
            }
        }
        layerMap = new int[0][0]; // Lege kaart om geheugen vrij te maken
    }

    // Methode om de laag met botsingsdetectie op te halen
    public int[][] getCollisionLayer() {
        return collisionLayer;
    }
}