package Application;

import data.FestivalPlan;
import org.jfree.fx.FXGraphics2D;
import org.jfree.fx.ResizableCanvas;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class FestivalBlockview extends Canvas {

    private FestivalPlan festivalPlan = new FestivalPlan();

    public void draw(FXGraphics2D graphics){
        // Tekent de basiselementen zoals tijden met bijbehorende tijdvakken.
        drawBasics(graphics);

        drawStages(graphics);
    }

    public void drawBasics(FXGraphics2D graphics){
        Rectangle2D rectangle1 = new Rectangle2D.Double(0,0, 200, 30);
        graphics.drawString("Podiums", 70,20);
        graphics.drawLine(200, 0, 200, 800);
        graphics.draw(rectangle1);
        for (int i = 0; i < 30; i++) {
            Rectangle2D rectangle2 = new Rectangle2D.Double((i*50)+200,0, 50, 30);
            graphics.draw(rectangle2);
        }

        graphics.drawString("12:00", 205,20);
        graphics.drawString("12:30", 255,20);
        graphics.drawString("13:00", 305,20);
        graphics.drawString("13:30", 355,20);
        graphics.drawString("14:00", 405,20);
        graphics.drawString("14:30", 455,20);
        graphics.drawString("15:00", 505,20);
        graphics.drawString("15:30", 555,20);
        graphics.drawString("16:00", 605,20);
        graphics.drawString("16:30", 655,20);
        graphics.drawString("17:00", 705,20);
        graphics.drawString("17:30", 755,20);
        graphics.drawString("18:00", 805,20);
        graphics.drawString("18:30", 855,20);
        graphics.drawString("19:00", 905,20);
        graphics.drawString("19:30", 955,20);
        graphics.drawString("20:00", 1005,20);
        graphics.drawString("20:30", 1055,20);
        graphics.drawString("21:00", 1105,20);
        graphics.drawString("21:30", 1155,20);
        graphics.drawString("22:00", 1205,20);
        graphics.drawString("22:30", 1255,20);
        graphics.drawString("23:00", 1305,20);
        graphics.drawString("23:30", 1355,20);
        graphics.drawString("0:00", 1405,20);
        graphics.drawString("0:30", 1455,20);
        graphics.drawString("1:00", 1505,20);
        graphics.drawString("1:30", 1555,20);
        graphics.drawString("2:00", 1605,20);
        graphics.drawString("2:30", 1655,20);
    }

    public void drawStages(FXGraphics2D graphics){
        for (int i = 0; i < festivalPlan.getStages().size(); i++) {
            graphics.drawLine(0, 100 + (75*i), 1700, 100 + (75*i));
            graphics.drawString(festivalPlan.getStages().get(i).getName(), 50, 70 + (75*i));
        }
    }
}
