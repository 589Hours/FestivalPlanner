package Application.Simulation;

import javax.imageio.ImageIO;
import javax.json.JsonObject;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Array;
import java.util.ArrayList;

public class Layer {
    private int tileWidth = 32; // Breedte van een tegel
    private int tileHeight = 32; // Hoogte van een tegel
    private int mapWidth; // Breedte van de kaart
    private int mapHeight; // Hoogte van de kaart
    private int layerNum; // Nummer van de laag

    private ArrayList<BufferedImage> tiles = new ArrayList<>(); // Lijst van tegelafbeeldingen
    private int[][] layerMap; // Kaart die de tegels opslaat
    private BufferedImage[][] mapTiles; // 2D-array van afbeeldingen voor de kaarttegels
    private int[][] collisionLayer; // Kaartlaag voor botsingsdetectie

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