package Application.Simulation;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Map {
    private int width;
    private int height;

    private ArrayList<Layer> layers = new ArrayList<>();

    public Map(String fileName) {
        JsonReader reader = Json.createReader(getClass().getResourceAsStream(fileName));;
        JsonObject root = reader.readObject();

        this.width = root.getInt("width");
        this.height = root.getInt("height");
        Layer layer = new Layer(root);
    }

    public void draw(Graphics2D g) {
        for (Layer layer : layers) {
            layer.draw(g);
        }
    }
}
