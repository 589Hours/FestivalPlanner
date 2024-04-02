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

    public Layer(JsonObject root, int mapWidth, int mapHeight, int layerNum, ArrayList<BufferedImage> tiles) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.layerNum = layerNum;
        this.tiles = tiles;

        layerMap = new int[mapHeight][mapWidth];
        mapTiles = new BufferedImage[mapHeight][mapWidth];
        collisionLayer = new int[mapHeight][mapWidth];

        for (int y = 0; y < this.mapHeight; y++) {
            for (int x = 0; x < this.mapWidth; x++) {
                layerMap[y][x] = root.getJsonArray("layers").getJsonObject(layerNum).getJsonArray("data").getInt((y * 128) + x);

                //save Collision data in a different Array since it doesn't have to be drawn
                    if (root.getJsonArray("layers").getJsonObject(layerNum).getString("name").equals("Collision")){
                        collisionLayer[y][x] = layerMap[y][x];
                        continue;
                    }
                //saving map tiles for the drawn map
                    if (layerMap[y][x] > 0) {
                        mapTiles[y][x] = tiles.get(layerMap[y][x] - 1);
                    }
            }
        }
    }

    public void draw(Graphics2D g) {
        AffineTransform transform = new AffineTransform();
        int customTileSize = 32;
        for (int y = 0; y < this.mapHeight; y++) {
            for (int x = 0; x < this.mapHeight; x++) {
                transform.translate(x * (customTileSize), y * (customTileSize));

                g.drawImage(mapTiles[y][x], transform, null);

                transform.setToTranslation(0, 0);
            }
        }
        layerMap = new int[0][0];
    }

    public int[][] getCollisionLayer() {
        return collisionLayer;
    }
}
