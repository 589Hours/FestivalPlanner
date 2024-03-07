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
    private int tileWidth = 32;
    private int tileHeight = 32;
    private int mapWidth;
    private int mapHeight;
    private int layerNum;

    private ArrayList<BufferedImage> tiles = new ArrayList<>();
    private BufferedImage layerTiledMap;
    private int[][] layerMap;
    private BufferedImage[][] mapTiles;

    public Layer(JsonObject root, int mapWidth, int mapHeight, int layerNum, ArrayList<BufferedImage> tiles) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.layerNum = layerNum;
        this.tiles = tiles;

        layerMap = new int[mapHeight][mapWidth];
        mapTiles = new BufferedImage[mapHeight][mapWidth];

        for (int y = 0; y < this.mapHeight; y++) {
            for (int x = 0; x < this.mapWidth; x++) {
                layerMap[y][x] = root.getJsonArray("layers").getJsonObject(layerNum).getJsonArray("data").getInt((y * 128) + x);
                if (layerMap[y][x] > tiles.size()) {
                    // Te hoog getal
                    int num = layerMap[y][x];
                    int realTiled = num & 0xFFFFFFF;
                    mapTiles[y][x] = tiles.get(realTiled-1);

                    int flippedBit = num >> 30;
                    boolean horizontallyFlipped = (flippedBit & 0x1) != 0;
                    boolean verticallyFlipped = (flippedBit & 0x2) != 0;
                    boolean diagonallyFlipped = (flippedBit & 0x4) != 0;

                } else {
                    if (layerMap[y][x] > 0) {
                        mapTiles[y][x] = tiles.get(layerMap[y][x] - 1);
                    }
                }
            }
        }
        createImage();
    }

    public void createImage(){
        AffineTransform transform = new AffineTransform();
        int customTileSize = 32;
        this.layerTiledMap = new BufferedImage(customTileSize*mapWidth, customTileSize*mapHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = this.layerTiledMap.createGraphics();
        for (int y = 0; y < this.mapHeight; y++) {
            for (int x = 0; x < this.mapHeight; x++) {
                transform.translate(x * (customTileSize), y * (customTileSize));

                g2d.drawImage(mapTiles[y][x], transform, null);

                transform.setToTranslation(0, 0);
            }
        }
        layerMap = new int[0][0];
    }
    public void draw(Graphics2D g) {
        g.drawImage(this.layerTiledMap, new AffineTransform(), null);
    }
}
