package Application.Simulation;

import data.FestivalPlan;
import data.Performance;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class Visitor {

    private PathFinder pathFinder;
    private Point2D position;
    private Point2D targetPosition;
    private double angle;
    private double speed;
    private double currentDistance = Integer.MAX_VALUE;
    private BufferedImage image;
    private Tile currentTile;
    private ArrayList<Performance> planning;

    public Visitor(Point2D position, PathFinder pathFinder, double speed, FestivalPlan festivalPlan) {
        try {
            this.image = ImageIO.read(this.getClass().getResourceAsStream("/Visitors/MV_Graveyard_Zombies_Skeleton.png"));
            this.image = image.getSubimage(0, 0, image.getWidth() / 11, image.getHeight() / 6);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.pathFinder = pathFinder;
        this.position = position;
        this.currentTile = pathFinder.getTileFromPosition(new Point2D.Double(126, 64));
        this.targetPosition = position;
        this.speed = speed;
        this.angle = 0;
        this.planning = new ArrayList<>();
        Random random = new Random();
        int totalPopularity = 0;
        int lastBeginMin = 0;
        int lastBeginHour = 0;
        ArrayList<Performance> sameTimePerformances = new ArrayList<>();
        ArrayList<Performance> sortedPerformance = new ArrayList<>(festivalPlan.getPerformances());
        Collections.sort(sortedPerformance, new CustomHourComparator());

        for (Performance performance : sortedPerformance) {
            if (totalPopularity == 0) {
                totalPopularity = performance.getArtist().getPopularity();
                sameTimePerformances.add(performance);
                lastBeginMin = performance.getBeginMinute();
                lastBeginHour = performance.getBeginHour();
            } else if (performance.getBeginMinute() == lastBeginMin && performance.getBeginHour() == lastBeginHour) {
                totalPopularity += performance.getArtist().getPopularity();
                sameTimePerformances.add(performance);
            } else {
                int randomInt = random.nextInt(totalPopularity);

                switch (sameTimePerformances.size()) {
                    case 1:
                        planning.add(sameTimePerformances.get(0));
                        break;
                    case 2:
                        if (sameTimePerformances.get(0).getArtist().getPopularity() >= randomInt) {
                            planning.add(sameTimePerformances.get(0));
                            break;
                        }
                        planning.add(sameTimePerformances.get(1));
                        break;
                    case 3:
                        if (sameTimePerformances.get(0).getArtist().getPopularity() >= randomInt) {
                            planning.add(sameTimePerformances.get(0));
                            break;
                        }
                        if (sameTimePerformances.get(1).getArtist().getPopularity() + sameTimePerformances.get(0).getArtist().getPopularity() > randomInt) {
                            planning.add(sameTimePerformances.get(1));
                            break;
                        }
                        planning.add(sameTimePerformances.get(2));
                        break;
                    case 4:
                        if (sameTimePerformances.get(0).getArtist().getPopularity() >= randomInt) {
                            planning.add(sameTimePerformances.get(0));
                            break;
                        }
                        if (sameTimePerformances.get(1).getArtist().getPopularity() + sameTimePerformances.get(0).getArtist().getPopularity() > randomInt) {
                            planning.add(sameTimePerformances.get(1));
                            break;
                        }
                        if (sameTimePerformances.get(2).getArtist().getPopularity() + sameTimePerformances.get(1).getArtist().getPopularity() + sameTimePerformances.get(0).getArtist().getPopularity() > randomInt) {
                            planning.add(sameTimePerformances.get(2));
                            break;
                        }
                        planning.add(sameTimePerformances.get(3));
                        break;
                    case 5:
                        if (sameTimePerformances.get(0).getArtist().getPopularity() >= randomInt) {
                            planning.add(sameTimePerformances.get(0));
                            break;
                        }
                        if (sameTimePerformances.get(1).getArtist().getPopularity() + sameTimePerformances.get(0).getArtist().getPopularity() > randomInt) {
                            planning.add(sameTimePerformances.get(1));
                            break;
                        }
                        if (sameTimePerformances.get(2).getArtist().getPopularity() + sameTimePerformances.get(1).getArtist().getPopularity() + sameTimePerformances.get(0).getArtist().getPopularity() > randomInt) {
                            planning.add(sameTimePerformances.get(2));
                            break;
                        }
                        if(sameTimePerformances.get(3).getArtist().getPopularity() + sameTimePerformances.get(2).getArtist().getPopularity() + sameTimePerformances.get(1).getArtist().getPopularity() + sameTimePerformances.get(0).getArtist().getPopularity() > randomInt) {
                            planning.add(sameTimePerformances.get(3));
                            break;
                        }
                        planning.add(sameTimePerformances.get(4));
                        break;
                }

                sameTimePerformances.clear();
                sameTimePerformances.add(performance);
                totalPopularity = performance.getArtist().getPopularity();
            }
//            totalPopularity += performance.getArtist().getPopularity();
        }
        for (Performance performance1 : planning) {
            System.out.println(performance1);
        }
        System.out.println("\n");

    }

    public void update(ArrayList<Visitor> visitors) {

        if (position.distance(targetPosition) < 20) {
            for (Tile tile : currentTile.getNeighbours()) {

                if (this.pathFinder.path.get(tile) == null)
                    continue;

                int newDistance = this.pathFinder.path.get(tile);

                if (newDistance < currentDistance) {
                    currentDistance = newDistance;

                    double x = tile.getPointX();
                    double y = tile.getPointY();

                    this.targetPosition = new Point2D.Double(x, y);
                    this.currentTile = pathFinder.getTileFromPosition(new Point2D.Double(x / 32, y / 32));

                    //break when a closer tile is found
                    break;
                }
            }
        }

        double newAngle = Math.atan2(this.targetPosition.getY() - this.position.getY(), this.targetPosition.getX() - this.position.getX());

        double angleDifference = angle - newAngle;

        while (angleDifference > Math.PI) {
            angleDifference -= 2 * Math.PI;
        }

        while (angleDifference < -Math.PI) {
            angleDifference += 2 * Math.PI;
        }

        if (angleDifference < -0.1) {
            angle += 0.1;
        } else if (angleDifference > 0.1) {
            angle -= 0.1;
        } else {
            angle = newAngle;
        }

        Point2D newPosition = new Point2D.Double(
                position.getX() + speed * Math.cos(angle) * targetPosition.getX(),
                position.getY() + speed * Math.sin(angle) * targetPosition.getY()
        );

        boolean collision = false;

        for (Visitor visitor : visitors) {
            if (visitor != this) {
                //32 so the heads can collide (giving the 2.5D effect we want)
                if (visitor.position.distance(newPosition) <= 32) {
                    collision = true;
                }
            }
        }

        if (!collision) {
            this.position = newPosition;
        } else {
            this.angle += 0.2;
        }
    }

    public void draw(Graphics2D graphics2D) {
        AffineTransform transform = new AffineTransform();

        transform.translate(position.getX() - image.getWidth() / 2, position.getY() - image.getHeight() / 1.5);

        graphics2D.setColor(Color.red);
//        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        graphics2D.draw(new Ellipse2D.Double(this.targetPosition.getX(), this.targetPosition.getY(), 10, 10));
        graphics2D.drawImage(this.image, transform, null);
        graphics2D.setColor(Color.green);
        graphics2D.fill(new Ellipse2D.Double(this.position.getX(), this.position.getY(), 10, 10));
        graphics2D.setColor(Color.black);
    }

    public void setTargetPosition(Point2D targetPosition) {
        this.targetPosition = targetPosition;
    }

    public PathFinder getPath() {
        return pathFinder;
    }

}
