package Application.Simulation;

import data.FestivalPlan;
import data.Performance;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Visitor {
    private String name;
    private int age;
    private PathFinder prevPathFinder;
    private PathFinder pathFinder;
    private Point2D position;
    private Point2D targetPosition;
    private double angle;
    private double speed;
    private double currentDistance = Integer.MAX_VALUE;
    private BufferedImage spriteSheet;
    private Tile currentTile;
    private ArrayList<BufferedImage> characterDown = new ArrayList<>();
    private ArrayList<BufferedImage> characterLeft = new ArrayList<>();
    private ArrayList<BufferedImage> characterRight = new ArrayList<>();
    private ArrayList<BufferedImage> characterUp = new ArrayList<>();
    private int imageWidth;
    private int imageHeight;
    private double animationCounter;
    private double newAngle;
    private double drinkCounter;
    private boolean isInToilet;
    private boolean isGoingToToilet;
    private Toilet currentToilet;
    private ArrayList<Performance> planning;
    private double foodcounter;

    public Visitor(Point2D position, PathFinder pathFinder, double speed, FestivalPlan festivalPlan, String name, int age) {
        try {
            this.spriteSheet = ImageIO.read(this.getClass().getResourceAsStream("/Visitors/MV_Graveyard_Zombies_Skeleton.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Padzoeker en positie instellen
        this.pathFinder = pathFinder;
        this.position = position;
        this.currentTile = this.pathFinder.getTileFromPosition(new Point2D.Double(126, 64));
        this.targetPosition = position;
        this.speed = speed;
        // Hoek initialiseren op 0
        this.angle = 0;
        this.imageWidth = 48;
        this.imageHeight = 100;
        CreateImages();
        this.animationCounter = 0;
        this.newAngle = 0;
        this.drinkCounter = Math.random() * 50;
        this.isInToilet = false;
        this.isGoingToToilet = false;
        this.currentToilet = null;
        this.foodcounter = Math.random()*100;
        this.name = name;
        this.age = age;
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
                        randomInt = random.nextInt(100);
                        if (sameTimePerformances.get(0).getArtist().getPopularity() > randomInt) {
                            planning.add(sameTimePerformances.get(0));
                        }
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
                        if (sameTimePerformances.get(3).getArtist().getPopularity() + sameTimePerformances.get(2).getArtist().getPopularity() + sameTimePerformances.get(1).getArtist().getPopularity() + sameTimePerformances.get(0).getArtist().getPopularity() > randomInt) {
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
        }

    }

    private void CreateImages() {
        for (int x = 0; x < 3; x++) {
            this.characterDown.add(spriteSheet.getSubimage(x * this.imageWidth, 0, this.imageWidth, this.imageHeight));
            this.characterLeft.add(spriteSheet.getSubimage(x * this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight));
            this.characterRight.add(spriteSheet.getSubimage(x * this.imageWidth, 2 * this.imageHeight, this.imageWidth, this.imageHeight));
            this.characterUp.add(spriteSheet.getSubimage(x * this.imageWidth, 3 * this.imageHeight, this.imageWidth, this.imageHeight));
        }
    }

    public void update(ArrayList<Visitor> visitors, double deltaTime, boolean emergency) {

        this.animationCounter += (5 * deltaTime);
        if ((int) this.animationCounter >= 3) {
            this.animationCounter = 0;
        }

        this.drinkCounter += deltaTime*Math.random();
        if (this.foodcounter > 0) {
            this.foodcounter -= deltaTime * Math.random();
        }

        newAngle = Math.atan2(this.targetPosition.getY() - this.position.getY(), this.targetPosition.getX() - this.position.getX());

        double angleDifference = angle - newAngle;
        // Hoekverschil binnen bereik van -pi tot pi houden
        while (angleDifference > Math.PI) {
            angleDifference -= 2 * Math.PI;
        }
        while (angleDifference < -Math.PI) {
            angleDifference += 2 * Math.PI;
        }

        // Hoek aanpassen op basis van het hoekverschil
        if (angleDifference < -0.1) {
            angle += 0.1;
        } else if (angleDifference > 0.1) {
            angle -= 0.1;
        } else {
            angle = newAngle;
        }

        // Nieuwe positie berekenen op basis van snelheid en hoek
        Point2D newPosition = new Point2D.Double(
                position.getX() + speed * Math.cos(angle) * 1500,
                position.getY() + speed * Math.sin(angle) * 1500
        );

        boolean collision = false; // Variabele om botsing te controleren

        // Controleren op botsingen met andere bezoekers
        for (Visitor visitor : visitors) {
            if (visitor != this && !emergency) {
                //32 so the heads can collide (giving the 2.5D effect we want)
                if (visitor.position.distance(newPosition) <= 16) {
                    collision = true;
                }
            }
        }

        if (position.distance(targetPosition) < 20) {
            double x = 0, y = 0;
            int newDistance;
            if (this.currentDistance < 5 && !emergency || collision) {
                int randomTile = (int) Math.round(Math.random()*this.currentTile.getNeighbours().size()-1);
                if (randomTile < 0){
                    randomTile = 0;
                }
                Tile randomNeighbour = this.currentTile.getNeighbours().get(randomTile);

                //you have to check it is not collision tile otherwise a new path won't work as null pointer.
                if (pathFinder.path.get(randomNeighbour) == null)
                    return;

                newDistance = pathFinder.path.get(randomNeighbour);

                currentDistance = newDistance;

                x = randomNeighbour.getPointX();
                y = randomNeighbour.getPointY();
            } else {
                for (Tile tile : currentTile.getNeighbours()) {
                    if (this.pathFinder.path.get(tile) == null)
                        continue;

                    newDistance = this.pathFinder.path.get(tile);

                    if (newDistance < currentDistance) {
                        currentDistance = newDistance;

                        x = tile.getPointX();
                        y = tile.getPointY();

                        //break when a closer tile is found
                        break;
                    }
                }
            }

            this.targetPosition = new Point2D.Double(x, y);
            this.currentTile = pathFinder.getTileFromPosition(new Point2D.Double(x / 32, y / 32));
        }

        if (!collision) {
            this.position = newPosition; // Positie bijwerken naar de nieuwe positie
        } else {
            this.angle += 0.2; // Hoek verhogen als er een botsing is
        }
    }

    // Methode om de bezoeker op het scherm te tekenen
    public void draw(Graphics2D graphics2D) {
        AffineTransform transform = new AffineTransform();

        transform.translate(position.getX() - this.imageWidth / 2.25, position.getY() - this.imageHeight / 1.25);

        if (this.newAngle >= Math.PI / 3 && this.newAngle <= Math.PI * 2 / 3) {
            graphics2D.drawImage(this.characterDown.get((int) this.animationCounter), transform, null);
        } else if (this.newAngle <= -Math.PI / 3 && this.newAngle >= -Math.PI * 2 / 3) {
            graphics2D.drawImage(this.characterUp.get((int) this.animationCounter), transform, null);
        } else if (this.newAngle > -Math.PI / 3 && this.newAngle < Math.PI / 3) {
            graphics2D.drawImage(this.characterRight.get((int) this.animationCounter), transform, null);
        } else {
            graphics2D.drawImage(this.characterLeft.get((int) this.animationCounter), transform, null);
        }


        graphics2D.setColor(Color.black);
    }

    public boolean isClickedOnMe(Point2D point) {
        if (point.getX() > position.getX() - this.imageWidth / 2.25 && point.getX() < position.getX() - this.imageWidth / 2.25 + this.imageWidth
                && point.getY() > position.getY() - this.imageHeight / 1.25 && point.getY() < position.getY() - this.imageHeight / 1.25 + this.imageHeight) {
            return true;
        }
        return false;
    }

    public Tile getCurrentTile() {
        return currentTile;
    }

    public PathFinder getPathFinder() {
        return pathFinder;
    }

    public double getDrinkCounter() {
        return drinkCounter;
    }

    public Toilet getCurrentToilet() {
        return currentToilet;
    }

    public void setCurrentToilet(Toilet currentToilet) {
        this.currentToilet = currentToilet;
    }

    public void setDrinkCounter(double drinkCounter) {
        this.drinkCounter = drinkCounter;
    }

    public ArrayList<Performance> getPlanning() {
        return planning;
    }

    public void setPathFinder(PathFinder pathFinder) {
        this.pathFinder = pathFinder;
        this.currentTile = pathFinder.getTileFromPosition(new Point2D.Double(
                Math.round(this.position.getX() / 32),
                Math.round(this.position.getY() / 32)
        ));

//        get the distanceValue from current Tile in another pathfinder
        if (this.pathFinder.path.get(this.currentTile) == null) {
            for (Tile neighbour : this.currentTile.getNeighbours()) {
                if (this.pathFinder.path.get(neighbour) != null){
                    this.currentTile = neighbour;
                    break;
                }
            }
        }

        int newPathCurrentDistanceValue = this.pathFinder.path.get(this.currentTile);
        this.currentDistance = newPathCurrentDistanceValue;

//        initialize the first target tile so the npc moves
        for (Tile neighbour : this.currentTile.getNeighbours()) {
            if (this.pathFinder.path.get(neighbour) == null)
                continue;

            int newPathDistance = this.pathFinder.path.get(neighbour);
            if (newPathDistance < newPathCurrentDistanceValue) {
                this.currentDistance = newPathDistance;

                double x = neighbour.getPointX();
                double y = neighbour.getPointY();

                this.targetPosition = new Point2D.Double(x, y);
                this.currentTile = pathFinder.getTileFromPosition(new Point2D.Double(x / 32, y / 32));

                //break when a closer tile is found
                break;
            }
        }
    }

    public boolean isInToilet() {
        return isInToilet;
    }

    public void setInToilet(boolean inToilet) {
        isInToilet = inToilet;
    }

    public boolean isGoingToToilet() {
        return isGoingToToilet;
    }

    public void setGoingToToilet(boolean goingToToilet) {
        isGoingToToilet = goingToToilet;
    }

    public PathFinder getPrevPathFinder() {
        return prevPathFinder;
    }

    public void setPrevPathFinder(PathFinder prevPathFinder) {
        this.prevPathFinder = prevPathFinder;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public PathFinder getPath() {
        return pathFinder;
    }


    public double getFoodcounter() {
        return foodcounter;
    }

    public void setFoodcounter(double foodcounter) {
        this.foodcounter = foodcounter;
    }
}
