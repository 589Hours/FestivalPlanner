package Application.Simulation;

import javax.imageio.ImageIO;
import javax.json.JsonObject;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Layer {
    private int tileWidth;
    private int tileHeight;
    private int layerNum;

    private ArrayList<BufferedImage> tiles = new ArrayList<>();
    private BufferedImage layerTiledMap;
    private int[][] layerMap;

    public Layer(JsonObject root, int mapWidth, int mapHeight, int layerNum) {
        this.layerNum = layerNum;

        //TODO: overal de juiste locatie in de json file neerzetten


        layerMap = new int[mapHeight][mapWidth];
        for(int y = 0; y < mapHeight; y++)
        {
            for(int x = 0; x < mapWidth; x++)
            {
                layerMap[y][x] = root.getJsonArray("map").getJsonArray(y).getInt(x);
            }
        }
    }

    public void draw(Graphics2D g) {
        for (int y = 0; y < layerTiledMap.getHeight(); y += tileHeight) {
            for (int x = 0; x < layerTiledMap.getWidth(); x += tileWidth) {
                g.drawImage(tiles.get(layerMap[y][x]), AffineTransform.getTranslateInstance(x * tileWidth, y * tileHeight), null);
            }
        }
    }
}
