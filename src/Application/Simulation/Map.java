package Application.Simulation;

import javax.imageio.ImageIO;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Map {
    private int width;
    private int height;
    private int tileWidth;
    private int tileHeight;
    private boolean nightMode = false;
    private ArrayList<Layer> layers = new ArrayList<>();
    private ArrayList<BufferedImage> tiles = new ArrayList<>();

    private ArrayList<BufferedImage> tilesets = new ArrayList<>();


    public Map(String fileName) {
        JsonReader reader = null;
        reader = Json.createReader(getClass().getResourceAsStream(fileName));
        JsonObject root = reader.readObject();
        String imageFileName;
        this.width = root.getInt("width");
        this.height = root.getInt("height");

        try {
            for (int i = 0; i < 7; i++) {
                imageFileName = "/"+ root.getJsonArray("tilesets").getJsonObject(i).getString("image");
                tilesets.add(ImageIO.read(getClass().getResourceAsStream(imageFileName)));
            }
            this.tileHeight = root.getJsonArray("tilesets").getJsonObject(0).getInt("tileheight");
            this.tileWidth = root.getJsonArray("tilesets").getJsonObject(0).getInt("tilewidth");


            for (BufferedImage tileset : tilesets) {
                for(int y = 0; y <= tileset.getHeight() - tileHeight; y += tileHeight)
                {
                    for(int x = 0; x <= tileset.getWidth() - tileWidth; x += tileWidth)
                    {
                        tiles.add(tileset.getSubimage(x, y, tileWidth, tileHeight));
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < 18; i++) {
            Layer layer = new Layer(root, width, height, i, tiles);
            layers.add(layer);
        };
        createEndImage();
        layers.clear();

        Layer nightLayer = new Layer(root, width, height, 18, tiles);
        layers.add(nightLayer);
        Layer nightDecoration = new Layer(root, width, height, 19, tiles);
        layers.add(nightDecoration);
        setUpNightImage();
    }


    private BufferedImage endImage;
    private BufferedImage nightLayer;

    private void createEndImage() {
        this.endImage = new BufferedImage(32*width, 32*height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = this.endImage.createGraphics();
        for (Layer layer : layers) {
            layer.draw(g);
        }
    }
    private void setUpNightImage() {
        this.nightLayer = new BufferedImage(32*width, 32*height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = this.nightLayer.createGraphics();
        for (Layer layer : layers) {
            layer.draw(g);
        }
    }

    public void draw(Graphics2D g, AffineTransform transform) {
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        g.drawImage(this.endImage, transform, null);
        nightMode = true;
        if (nightMode){
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
            g.drawImage(this.nightLayer, transform, null);
        }
    }
}
