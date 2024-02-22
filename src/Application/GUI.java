package Application;

import data.Artist;
import data.Performance;
import data.FestivalPlan;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.jfree.fx.ResizableCanvas;

import java.awt.geom.Point2D;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GUI extends Application {
    private FestivalPlan festivalPlan;
    private ResizableCanvas canvas;
    FestivalBlockview festivalBlockview = new FestivalBlockview();

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = new BorderPane();
        canvas = new ResizableCanvas(g -> festivalBlockview.draw(g), borderPane);


        festivalPlan = new FestivalPlan();

        MenuBar menuBar = new MenuBar();
        Menu menu1 = new Menu("View");
        MenuItem menuItem1 = new MenuItem("Tableview");
        MenuItem menuItem2 = new MenuItem("Blockview");
        menu1.getItems().addAll(menuItem1, menuItem2);
        Menu menu2 = new Menu("Create");
        MenuItem menuItem3 = new MenuItem("Podium");
        MenuItem menuItem4 = new MenuItem("Artist");
        MenuItem menuItem5 = new MenuItem("Performance");
        menu2.getItems().addAll(menuItem3, menuItem4, menuItem5);
        menuBar.getMenus().addAll(menu1, menu2);

        menuItem1.setOnAction(event -> {
            FestivalTableview festivalTableview = new FestivalTableview(festivalPlan);
            borderPane.setCenter(festivalTableview);
        });
        menuItem2.setOnAction(event -> {
            borderPane.setCenter(canvas);
        });
        menuItem3.setOnAction(event -> {
            FestivalTableview festivalTableview = new FestivalTableview(festivalPlan);
            borderPane.setCenter(festivalTableview);
        });
        menuItem4.setOnAction(event -> {
            borderPane.setCenter(canvas);
        });

        // MouseClick
        canvas.setOnMousePressed(event -> {
            if (borderPane.getCenter() == canvas){
                Point2D point2D = new Point2D.Double(event.getX(), event.getY());
                if (festivalBlockview.checkClicked(point2D) != null) {
                    System.out.println(festivalBlockview.checkClicked(point2D).getArtist().getName());
                }
            }
        });


        borderPane.setTop(menuBar);


        //Dit is een test voor ArtistInfo, als dit een mergeConflict geeft kan deze verwijderd worden.
        Button button = new Button("Artist info check");
        HBox hBox = new HBox(button);
        borderPane.setCenter(hBox);

        button.setOnAction(event -> {
            ArtistInfo artistInfo = new ArtistInfo();
            Artist artist = new Artist("Ed Sheeran", 10000, "Pop");
            artist.setArtistInfo("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad\nminim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit\nin voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia\ndeserunt mollit anim id est laborum.");
            data.Stage stage = new data.Stage("Alpha");
            artistInfo.setArtist(new Performance(artist, stage, 19, 0, 20, 0));

            try {
                artistInfo.start(new Stage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        borderPane.setPrefSize(1700, 800);
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Festival Planner");
        primaryStage.show();
    }
}
