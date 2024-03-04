package Application.Simulation;

import javax.imageio.ImageIO;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Map {
    private int width;
    private int height;
    private int tileWidth;
    private int tileHeight;

    private ArrayList<Layer> layers = new ArrayList<>();
    private ArrayList<BufferedImage> tiles = new ArrayList<>();

//    private BufferedImage tileSet;


    public Map(String fileName) {
        JsonReader reader = null;
        reader = Json.createReader(getClass().getResourceAsStream(fileName));
        JsonObject root = reader.readObject();

        this.width = root.getInt("width");
        this.height = root.getInt("height");


        try {
            String imageFileName = "/"+ root.getJsonArray("tilesets").getJsonObject(0).getString("image");
            System.out.println(imageFileName);
            BufferedImage tilemap = ImageIO.read(getClass().getResourceAsStream(fileName));

            tileHeight = root.getJsonObject("tilemap").getJsonObject("tile").getInt("height");
            tileWidth = root.getJsonObject("tilemap").getJsonObject("tile").getInt("width");

            for(int y = 0; y < tilemap.getHeight(); y += tileHeight)
            {
                for(int x = 0; x < tilemap.getWidth(); x += tileWidth)
                {
                    tiles.add(tilemap.getSubimage(x, y, tileWidth, tileHeight));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Layer layer = new Layer(root, width, height, 1);
        layers.add(layer);
    }


    public void draw(Graphics2D g) {
        for (Layer layer : layers) {
            layer.draw(g);
        }
    }
}
