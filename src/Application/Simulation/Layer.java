package Application.Simulation;

import javax.imageio.ImageIO;
import javax.json.JsonObject;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Layer {
    private int tileWidth;
    private int tileHeight;
    private ArrayList<BufferedImage> tiles = new ArrayList<>();

    public Layer(JsonObject root) {
        try {
            BufferedImage layerTiledMap = ImageIO.read(getClass().getResourceAsStream(root.getJsonObject("tilemap").getString("file")));

            tileHeight = root.getJsonObject("tilemap").getJsonObject("tile").getInt("height");
            tileWidth = root.getJsonObject("tilemap").getJsonObject("tile").getInt("width");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D g) {
    }
}
