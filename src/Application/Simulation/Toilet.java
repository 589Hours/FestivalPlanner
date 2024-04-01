package Application.Simulation;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Toilet {
    private boolean isOccupied;
    private boolean animationStarted;
    private double status;
    private double animationTimer;
    private ArrayList<BufferedImage> images;
    private BufferedImage tileset;
    private Point2D position;

    public Toilet(Point2D.Double position) {
        this.isOccupied = false;
        this.animationStarted = false;
        this.status = 0;
        this.animationTimer = 0;
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

    public boolean isAnimationStarted() {
        return animationStarted;
    }

    public void setAnimationStarted(boolean animationStarted) {
        this.animationStarted = animationStarted;
    }

    private void createImages() {
        for (int x = 0; x < 180; x+= 96) {
            for (int y = 0; y < 960; y += 240) {
                images.add(tileset.getSubimage(x, y, 100, 240));
            }
        }
    }

    public double getAnimationTimer() {
        return animationTimer;
    }

    public void setStatus(double status) {
        this.status = status;
    }

    public void setAnimationTimer(double animationTimer) {
        this.animationTimer = animationTimer;
    }

    public void update(double deltatime) {
        if (this.isOccupied) {
            this.status += deltatime;
        }
        if (this.animationStarted){
            this.animationTimer += deltatime;
            System.out.println((int)animationTimer);
        }
    }

    public void draw(Graphics2D graphics) {
        AffineTransform original = graphics.getTransform();
        graphics.scale(0.675, 0.675);

        // Openen van de deur
        if (this.status <= 1) {
            graphics.drawImage(images.get(0), (int) this.position.getX(), (int) this.position.getY(), null);
        } else if (this.status >= 1 && this.status < 2) {
            graphics.drawImage(images.get(1), (int) this.position.getX(), (int) this.position.getY(), null);
        } else if (this.status >= 2 && this.status < 3) {
            graphics.drawImage(images.get(2), (int) this.position.getX(), (int) this.position.getY(), null);
        } else if (this.status >= 3 && this.status < 5) {
            graphics.drawImage(images.get(3), (int) this.position.getX(), (int) this.position.getY(), null);
        }

        // Sluiten van de deur
        else if (this.status >= 5 && this.status < 6) {
            graphics.drawImage(images.get(4), (int) this.position.getX(), (int) this.position.getY(), null);
        } else if (this.status >= 6 && this.status < 7) {
            graphics.drawImage(images.get(5), (int) this.position.getX(), (int) this.position.getY(), null);
        } else if (this.status >= 7 && this.status < 8) {
            graphics.drawImage(images.get(6), (int) this.position.getX(), (int) this.position.getY(), null);
        } else if (this.status >= 8 && this.status < 9) {
            graphics.drawImage(images.get(7), (int) this.position.getX(), (int) this.position.getY(), null);
        } else if (this.status >= 9 && this.status < 20) {
            graphics.drawImage(images.get(7), (int) this.position.getX(), (int) this.position.getY(), null);
        }

        // Openen van de deur
        else if (this.status <= 20 && this.status < 21) {
            graphics.drawImage(images.get(0), (int) this.position.getX(), (int) this.position.getY(), null);
        } else if (this.status >= 21 && this.status < 22) {
            graphics.drawImage(images.get(1), (int) this.position.getX(), (int) this.position.getY(), null);
        } else if (this.status >= 22 && this.status < 23) {
            graphics.drawImage(images.get(2), (int) this.position.getX(), (int) this.position.getY(), null);
        } else if (this.status >= 23 && this.status < 25) {
            graphics.drawImage(images.get(3), (int) this.position.getX(), (int) this.position.getY(), null);
        }

        // Sluiten van de deur
        else if (this.status >= 25 && this.status < 26) {
            graphics.drawImage(images.get(4), (int) this.position.getX(), (int) this.position.getY(), null);
        } else if (this.status >= 26 && this.status < 27) {
            graphics.drawImage(images.get(5), (int) this.position.getX(), (int) this.position.getY(), null);
        } else if (this.status >= 27 && this.status < 28) {
            graphics.drawImage(images.get(6), (int) this.position.getX(), (int) this.position.getY(), null);
        } else if (this.status >= 28 && this.status < 29) {
            graphics.drawImage(images.get(7), (int) this.position.getX(), (int) this.position.getY(), null);
        } else {
            graphics.drawImage(images.get(7), (int) this.position.getX(), (int) this.position.getY(), null);
        }

        graphics.setTransform(original);
    }
}
