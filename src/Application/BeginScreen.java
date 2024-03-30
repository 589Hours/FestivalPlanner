package Application;

import data.FestivalPlan;
import org.jfree.fx.FXGraphics2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BeginScreen extends Canvas {

    private FXGraphics2D fxGraphics2D;
    private BufferedImage text;

    public BeginScreen() {
        try {
            this.text = ImageIO.read(this.getClass().getResourceAsStream("/Title-Text.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public void draw(FXGraphics2D graphics) {
        this.fxGraphics2D = graphics;
        if (graphics != null) {
            drawBeginScreen(graphics);
        }
    }

    private void drawBeginScreen(FXGraphics2D g){
        g.drawImage(this.text, 300,0, null);
    }

    public FXGraphics2D getFxGraphics2D() {
        return fxGraphics2D;
    }
}
