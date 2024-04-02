package Application.Simulation;

import data.FestivalPlan;

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
    private int[][] collisionLayer;
    private ArrayList<Layer> layers = new ArrayList<>();
    private ArrayList<BufferedImage> tiles = new ArrayList<>();
    private ArrayList<BufferedImage> tilesets = new ArrayList<>();
    private float nightOpacity;
    private Simulation sim;


    public Map(String fileName, FestivalPlan festivalPlan, Simulation simulation) {
        JsonReader reader = null;
        reader = Json.createReader(getClass().getResourceAsStream(fileName)); // JSON-lezer initialiseren
        JsonObject root = reader.readObject(); // Root JSON-object ophalen
        String imageFileName;
        nightOpacity = 0f;
        sim = simulation;
        this.width = root.getInt("width");
        this.height = root.getInt("height");

        try {
            // Tegelsets inladen
            for (int i = 0; i < 7; i++) {
                imageFileName = "/" + root.getJsonArray("tilesets").getJsonObject(i).getString("image");
                tilesets.add(ImageIO.read(getClass().getResourceAsStream(imageFileName)));
            }
            this.tileHeight = root.getJsonArray("tilesets").getJsonObject(0).getInt("tileheight"); // Tegelhoogte ophalen
            this.tileWidth = root.getJsonArray("tilesets").getJsonObject(0).getInt("tilewidth"); // Tegelbreedte ophalen

            // Tegelafbeeldingen creëren
            for (BufferedImage tileset : tilesets) {
                for (int y = 0; y <= tileset.getHeight() - tileHeight; y += tileHeight) {
                    for (int x = 0; x <= tileset.getWidth() - tileWidth; x += tileWidth) {
                        tiles.add(tileset.getSubimage(x, y, tileWidth, tileHeight));
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < 4 + festivalPlan.getStages().size() * 3; i++) {
            String type = root.getJsonArray("layers").getJsonObject(i).getString("type");
            String layerName = root.getJsonArray("layers").getJsonObject(i).getString("name");
            if (type.equals("tilelayer")){
                Layer layer = new Layer(root, width, height, i, tiles); // Nieuwe laag aanmaken
                if (layerName.equals("Collision")){
                    collisionLayer = layer.getCollisionLayer();
                }
                layers.add(layer); // Laag toevoegen aan de lijst
            }
        }

        createEndImage();
        layers.clear();

        // Nachtlaag toevoegen
        Layer nightLayer = new Layer(root, width, height, 19, tiles);
        layers.add(nightLayer);
        // Nachtdecoratielaag toevoegen
        Layer nightDecoration = new Layer(root, width, height, 20, tiles);
        layers.add(nightDecoration);
        setUpNightImage(); // Nachtmodusafbeelding instellen
    }

    private BufferedImage endImage; // Eindafbeelding van de kaart
    private BufferedImage nightLayer; // Nachtmodusafbeelding van de kaart

    // Methode om de eindafbeelding van de kaart te maken
    private void createEndImage() {
        this.endImage = new BufferedImage(32 * width, 32 * height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = this.endImage.createGraphics();
        for (Layer layer : layers) {
            layer.draw(g); // Elke laag tekenen op de eindafbeelding
        }
    }

    private void setUpNightImage() {
        this.nightLayer = new BufferedImage(32 * width, 32 * height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = this.nightLayer.createGraphics();
        for (Layer layer : layers) {
            layer.draw(g); // Elke laag tekenen op de nachtmodusafbeelding
        }
    }

    // Methode om de kaart te tekenen
    public void draw(Graphics2D g) {
        String timeString;
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        g.drawImage(this.endImage, new AffineTransform(), null);



        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, nightOpacity));
        g.drawImage(this.nightLayer, new AffineTransform(), null);

        if (sim.getMinutes() < 10) {
            timeString = sim.getHours() + ":0" + sim.getMinutes();
        } else {
            timeString = sim.getHours() + ":" + sim.getMinutes();
        }

        Font oldFont = g.getFont();

        g.setColor(Color.black);
        g.setFont(new Font("Arial", Font.PLAIN, 140));

        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        g.drawString(timeString, 2200, 600);

        g.setFont(oldFont);




    }


    public int[][] getCollisionLayer() {
        return this.collisionLayer;
    }
    public void updateOpacity() {
        if (sim.getHours() == 19) {
            if (nightOpacity >= 0.9f){
                nightOpacity = 0.7f;
            }
            nightOpacity = nightOpacity + 0.7f / 60f;
        }

        if (sim.getHours() == 6) {
            if (nightOpacity <= 0.1f){
                nightOpacity = 0.1f;
            }
            nightOpacity = nightOpacity - 0.7f / 60f;
        }

        if (sim.getHours() > 6 && sim.getHours() < 19) {
            nightOpacity = 0;
        }
    }
}
