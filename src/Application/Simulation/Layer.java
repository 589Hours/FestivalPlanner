package Application.Simulation;

import javax.imageio.ImageIO;
import javax.json.JsonObject;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
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

    public Layer(JsonObject root, int mapWidth, int mapHeight, int layerNum, ArrayList<BufferedImage> tiles) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.layerNum = layerNum;
        this.tiles = tiles;

        layerMap = new int[mapHeight][mapWidth];

        for(int y = 0; y < this.mapHeight; y++)
        {
            for(int x = 0; x < this.mapWidth; x++)
            {
                layerMap[y][x] = root.getJsonArray("layers").getJsonObject(layerNum).getJsonArray("data").getInt((y * 128) + x);
            }
        }
    }

    public void draw(Graphics2D g) {
        AffineTransform transform = new AffineTransform();
        for (int y = 0; y < this.mapHeight; y++) {
            for (int x = 0; x < this.mapHeight; x++) {
                transform.translate(x*tileWidth, y*tileHeight);
                if (layerMap[y][x] > tiles.size()){
                    // Te hoog getal
                } else {
                    g.drawImage(tiles.get(layerMap[y][x]), transform, null);
                }
                transform.setToTranslation(0,0);
            }

        }
    }
}
