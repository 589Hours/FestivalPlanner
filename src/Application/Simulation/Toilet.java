package Application.Simulation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Toilet {
    private boolean isOccupied;
    private int status;
    private ArrayList<BufferedImage> images;
    private BufferedImage tileset;
    private Point2D position;

    public Toilet(Point2D.Double position) {
        this.isOccupied = false;
        this.status = 0;
        this.images = new ArrayList<>();
        try {
            this.tileset = ImageIO.read(new File("UsedTiles/Portable_Toilet.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.position = position;
    }

    private void createImages() {
        for (int x = 0; x < 2; x += 240) {
            for (int y = 0; y < 4; y += 144) {
                images.add(tileset.getSubimage(x, y, 240, 144));
            }
        }
    }

    public void update(int deltatime) {
        if (this.isOccupied) {
            this.status += deltatime;
        }
    }

    public void draw(Graphics2D graphics) {
        if (this.status < 1) {
            graphics.drawImage(images.get(0), (int) this.position.getX(), (int) this.position.getY(), null);
        }
    }
}
