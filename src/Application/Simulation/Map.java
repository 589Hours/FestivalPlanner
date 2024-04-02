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
    private int width; // Breedte van de kaart
    private int height; // Hoogte van de kaart
    private int tileWidth; // Breedte van een tegel
    private int tileHeight; // Hoogte van een tegel
    private boolean nightMode = false; // Nachtmodus status
    private ArrayList<Layer> layers = new ArrayList<>(); // Lijst van kaartlagen
    private ArrayList<BufferedImage> tiles = new ArrayList<>(); // Lijst van tegelafbeeldingen

    private ArrayList<BufferedImage> tilesets = new ArrayList<>(); // Lijst van tegelsets

    // Constructor voor de Map
    public Map(String fileName, PathFinder pathFinder) {
        JsonReader reader = null;
        reader = Json.createReader(getClass().getResourceAsStream(fileName)); // JSON-lezer initialiseren
        JsonObject root = reader.readObject(); // Root JSON-object ophalen
        String imageFileName;
        this.width = root.getInt("width"); // Breedte van de kaart ophalen
        this.height = root.getInt("height"); // Hoogte van de kaart ophalen

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
                for(int y = 0; y <= tileset.getHeight() - tileHeight; y += tileHeight)
                {
                    for(int x = 0; x <= tileset.getWidth() - tileWidth; x += tileWidth)
                    {
                        tiles.add(tileset.getSubimage(x, y, tileWidth, tileHeight)); // Subafbeelding toevoegen
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Lagen initialiseren
        for (int i = 0; i < 19; i++) {
            String type = root.getJsonArray("layers").getJsonObject(i).getString("type");
            String layerName = root.getJsonArray("layers").getJsonObject(i).getString("name");
            if (type.equals("tilelayer")){
                Layer layer = new Layer(root, width, height, i, tiles); // Nieuwe laag aanmaken
                if (layerName.equals("Collision")){
                    pathFinder.setCollisionLayer(layer.getCollisionLayer()); // Botsingslaag instellen
                }
                layers.add(layer); // Laag toevoegen aan de lijst
            }
        };
        createEndImage(); // Eindafbeelding creëren
        layers.clear(); // Lijst van lagen wissen

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
        this.endImage = new BufferedImage(32*width, 32*height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = this.endImage.createGraphics();
        for (Layer layer : layers) {
            layer.draw(g); // Elke laag tekenen op de eindafbeelding
        }
    }

    // Methode om de nachtmodusafbeelding van de kaart in te stellen
    private void setUpNightImage() {
        this.nightLayer = new BufferedImage(32*width, 32*height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = this.nightLayer.createGraphics();
        for (Layer layer : layers) {
            layer.draw(g); // Elke laag tekenen op de nachtmodusafbeelding
        }
    }

    // Methode om de kaart te tekenen
    public void draw(Graphics2D g) {
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        g.drawImage(this.endImage, new AffineTransform(), null); // Eindafbeelding tekenen

        nightMode = true; // Nachtmodus activeren
        if (nightMode){
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
            g.drawImage(this.nightLayer, new AffineTransform(), null); // Nachtmodusafbeelding tekenen
        }
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f)); // Alfa terugzetten naar normaal
    }
}
