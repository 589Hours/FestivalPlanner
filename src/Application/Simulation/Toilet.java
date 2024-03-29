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
    private double status;
    private ArrayList<BufferedImage> images;
    private BufferedImage tileset;
    private Point2D position;

    public Toilet(Point2D.Double position) {
        this.isOccupied = true;
        this.status = 0;
        this.images = new ArrayList<>();
        try {
            File file = new File("res/UsedTiles/Portable_Toilet.png");
            this.tileset = ImageIO.read(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        createImages();
        this.position = position;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    private void createImages() {
        for (int x = 0; x < 180; x+= 96) {
            for (int y = 0; y < 960; y += 240) {
                images.add(tileset.getSubimage(x, y, 100, 240));
            }
        }
    }

    public void update(double deltatime) {
        if (this.isOccupied) {
            this.status += deltatime;
        }
        if (this.status > 15) {
            this.status = 0;
        }
    }

    public void draw(Graphics2D graphics) {
        // Openen van de deur
        if (this.status <= 1) {
            graphics.drawImage(images.get(0), (int) this.position.getX(), (int) this.position.getY(), null);
        } else if (this.status >= 1 && this.status < 2) {
            graphics.drawImage(images.get(1), (int) this.position.getX(), (int) this.position.getY(), null);
        } else if (this.status >= 2 && this.status < 3) {
            graphics.drawImage(images.get(2), (int) this.position.getX(), (int) this.position.getY(), null);
        } else if (this.status >= 3 && this.status < 10) {
            graphics.drawImage(images.get(3), (int) this.position.getX(), (int) this.position.getY(), null);
        }

        // Sluiten van de deur
        else if (this.status >= 10 && this.status < 11) {
            graphics.drawImage(images.get(4), (int) this.position.getX(), (int) this.position.getY(), null);
        } else if (this.status >= 11 && this.status < 12) {
            graphics.drawImage(images.get(5), (int) this.position.getX(), (int) this.position.getY(), null);
        } else if (this.status >= 12 && this.status < 13) {
            graphics.drawImage(images.get(6), (int) this.position.getX(), (int) this.position.getY(), null);
        } else if (this.status >= 13 && this.status < 14) {
            graphics.drawImage(images.get(7), (int) this.position.getX(), (int) this.position.getY(), null);
        } else {
            graphics.drawImage(images.get(7), (int) this.position.getX(), (int) this.position.getY(), null);
        }
    }
}
