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

    public Layer(JsonObject root, int mapWidth, int mapHeight, int layerNum, String fileName) {
        this.layerNum = layerNum;

        try {
            String imageFileName = "/"+ root.getJsonArray("tilesets").getJsonObject(layerNum).getString("image");
            System.out.println(imageFileName);
            BufferedImage tilemap = ImageIO.read(getClass().getResourceAsStream(fileName));

            tileHeight = root.getJsonArray("layers").getJsonObject(layerNum).getInt("height");
            tileWidth = root.getJsonArray("layers").getJsonObject(layerNum).getInt("height");

            for(int y = 0; y < mapHeight; y += tileHeight)
            {
                for(int x = 0; x < mapWidth; x += tileWidth)
                {
//                    tiles.add(tilemap.getSubimage(x, y, tileWidth, tileHeight));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        layerMap = new int[mapHeight][mapWidth];
        for(int y = 0; y < mapHeight; y++)
        {
            for(int x = 0; x < mapWidth; x++)
            {
//                layerMap[y][x] = root.getJsonArray("map").getJsonArray(y).getInt(x);
            }
        }
    }

    public void draw(Graphics2D g) {
    }
}
