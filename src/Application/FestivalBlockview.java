package Application;

import data.Artist;
import data.FestivalPlan;
import data.Performance;
import org.jfree.fx.FXGraphics2D;
import sun.misc.Perf;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.HashMap;

public class FestivalBlockview extends Canvas {

    private FestivalPlan festivalPlan = new FestivalPlan();
    private HashMap<RoundRectangle2D, Performance> blocks = new HashMap<>();

    public void draw(FXGraphics2D graphics, FestivalPlan festivalPlan) {
        this.festivalPlan = festivalPlan;
        // Tekent de basiselementen zoals tijden met bijbehorende tijdvakken.
        drawBasics(graphics);

        // Tekent de alle podiums
        drawStages(graphics);

        // Tekent alle optredes
        drawPerformances(graphics);
    }


    public void drawBasics(FXGraphics2D graphics) {
        graphics.setColor(Color.BLACK);
        Rectangle2D rectangle1 = new Rectangle2D.Double(0, 0, 200, 30);
        graphics.drawString("Podiums", 70, 20);
        graphics.drawLine(200, 0, 200, 800);
        graphics.draw(rectangle1);
        for (int i = 0; i < 30; i++) {
            Rectangle2D rectangle2 = new Rectangle2D.Double((i * 50) + 200, 0, 50, 30);
            graphics.draw(rectangle2);
        }

        graphics.drawString("12:00", 205, 20);
        graphics.drawString("12:30", 255, 20);
        graphics.drawString("13:00", 305, 20);
        graphics.drawString("13:30", 355, 20);
        graphics.drawString("14:00", 405, 20);
        graphics.drawString("14:30", 455, 20);
        graphics.drawString("15:00", 505, 20);
        graphics.drawString("15:30", 555, 20);
        graphics.drawString("16:00", 605, 20);
        graphics.drawString("16:30", 655, 20);
        graphics.drawString("17:00", 705, 20);
        graphics.drawString("17:30", 755, 20);
        graphics.drawString("18:00", 805, 20);
        graphics.drawString("18:30", 855, 20);
        graphics.drawString("19:00", 905, 20);
        graphics.drawString("19:30", 955, 20);
        graphics.drawString("20:00", 1005, 20);
        graphics.drawString("20:30", 1055, 20);
        graphics.drawString("21:00", 1105, 20);
        graphics.drawString("21:30", 1155, 20);
        graphics.drawString("22:00", 1205, 20);
        graphics.drawString("22:30", 1255, 20);
        graphics.drawString("23:00", 1305, 20);
        graphics.drawString("23:30", 1355, 20);
        graphics.drawString("0:00", 1405, 20);
        graphics.drawString("0:30", 1455, 20);
        graphics.drawString("1:00", 1505, 20);
        graphics.drawString("1:30", 1555, 20);
        graphics.drawString("2:00", 1605, 20);
        graphics.drawString("2:30", 1655, 20);
    }

    public void drawStages(FXGraphics2D graphics) {
        graphics.setColor(Color.BLACK);
        for (int i = 0; i < festivalPlan.getStages().size(); i++) {
            graphics.drawLine(0, 100 + (75 * i), 1700, 100 + (75 * i));
            graphics.drawString(festivalPlan.getStages().get(i).getName(), 50, 70 + (75 * i));
        }
    }

    private void drawPerformances(FXGraphics2D graphics) {
        graphics.setColor(Color.BLACK);

        // Blocks
        for (Performance performance : festivalPlan.getPerformances()) {
            int width = 50 * (performance.getDuration() / 30);
            int height = 65;
            int x = 201;
            switch (performance.getBeginHour()) {
                case 13:
                    x += 100;
                    break;
                case 14:
                    x += 200;
                    break;
                case 15:
                    x += 300;
                    break;
                case 16:
                    x += 400;
                    break;
                case 17:
                    x += 500;
                    break;
                case 18:
                    x += 600;
                    break;
                case 19:
                    x += 700;
                    break;
                case 20:
                    x += 800;
                    break;
                case 21:
                    x += 900;
                    break;
                case 22:
                    x += 1000;
                    break;
                case 23:
                    x += 1100;
                    break;
                case 24:
                    x += 1200;
                    break;
                case 1:
                    x += 1300;
                    break;
                case 2:
                    x += 1400;
                    break;
            }
            if (performance.getBeginMinute() == 30) {
                x += 50;
            }

            int y = 31;
            switch (performance.getStage().getName()) {
                case "Hoofdpodium":
                    y = 32;
                    break;
                case "Testpodium1":
                    y = 104;
                    break;
                case "Testpodium2":
                    y = 178;
                    break;
                case "Testpodium3":
                    y = 253;
                    break;
                case "Testpodium4":
                    y = 328;
                    break;
            }
            String artist = performance.getArtist().getName();
            int popularity = performance.getArtist().getPopularity();
            String genre = performance.getArtist().getGenre();

            Color color = Color.getHSBColor(((float) (popularity / 3)/ 100), 1, 1);

            RoundRectangle2D roundRectangle = new RoundRectangle2D.Double(x, y, width, height, 5, 5);
            blocks.put(roundRectangle, performance);
            graphics.draw(roundRectangle);
            graphics.setColor(color);
            graphics.fill(roundRectangle);

            // Tekst
            graphics.setColor(Color.BLACK);
            graphics.drawString(artist, (x + 10), (y + 25));
            graphics.drawString(String.valueOf(popularity), (x + 10), (y + 50));
            graphics.drawString(genre, (x + 60), (y + 50));
        }
    }

    public Performance checkClicked(Point2D point2D) {
        for (RoundRectangle2D block : blocks.keySet()) {
            if (point2D.getX() > block.getX() && point2D.getX() < (block.getX() + block.getWidth()) &&
                    point2D.getY() > block.getY() && point2D.getY() < (block.getY() + block.getHeight())) {
                return blocks.get(block);
            }
        }
        return null;
    }
}
