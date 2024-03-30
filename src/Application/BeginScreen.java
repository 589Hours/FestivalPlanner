package Application;

import data.Performance;
import org.jfree.fx.FXGraphics2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BeginScreen extends Canvas {

    private FXGraphics2D fxGraphics2D;
    private BufferedImage festivalPlanner;
    private BufferedImage tableView;
    private BufferedImage blockView;
    private BufferedImage startSimulation;

    public BeginScreen() {
        try {
            this.festivalPlanner = ImageIO.read(this.getClass().getResourceAsStream("/FestivalPlanner.png"));
            this.tableView = ImageIO.read(this.getClass().getResourceAsStream("/Tableview.png"));
            this.blockView = ImageIO.read(this.getClass().getResourceAsStream("/BlockView.png"));
            this.startSimulation = ImageIO.read(this.getClass().getResourceAsStream("/StartSimulation.png"));
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
        g.drawImage(this.festivalPlanner, 300,0, null);
        AffineTransform original = g.getTransform();
        g.scale(0.5, 0.5);
        g.drawImage(this.tableView, 1100,400, null);
        g.drawImage(this.blockView, 1100,700, null);
        g.scale(1.5, 1.5);
        g.drawImage(this.startSimulation, 575,650, null);

        g.setTransform(original);
    }

    public FXGraphics2D getFxGraphics2D() {
        return fxGraphics2D;
    }

    public String checkClicked(Point2D point2D) {
        if (point2D.getX() > 560 && point2D.getX() < 1060 && point2D.getY() > 222 && point2D.getY() < 322){
            return "TableView";
        } else if (point2D.getX() > 560 && point2D.getX() < 1060 && point2D.getY() > 370 && point2D.getY() < 470){
            return "BlockView";
        } else if (point2D.getX() > 467 && point2D.getX() < 1167 && point2D.getY() > 520 && point2D.getY() < 620){
            return "StartSimulation";
        }
        return null;
    }
}
