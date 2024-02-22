package Application;

import Application.Create.ArtistAdd;
import Application.Create.StageAdd;
import Application.Delete.DeleteStage;
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

public class GUI extends Application {
    private FestivalPlan festivalPlan;
    private ResizableCanvas canvas;
    FestivalBlockview festivalBlockview = new FestivalBlockview();

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = new BorderPane();
        BorderPane buttonPlacement = new BorderPane();
        canvas = new ResizableCanvas(g -> festivalBlockview.draw(g), borderPane);


        festivalPlan = new FestivalPlan();

        MenuBar menuBar = new MenuBar();
        Menu viewMenu = new Menu("View");
        MenuItem viewTable = new MenuItem("Tableview");
        MenuItem viewBlock = new MenuItem("Blockview");
        viewMenu.getItems().addAll(viewTable, viewBlock);

        Menu createMenu = new Menu("Create");
        MenuItem createPodium = new MenuItem("Podium");
        MenuItem createArtist = new MenuItem("Artist");
        MenuItem createPerformance = new MenuItem("Performance");
        createMenu.getItems().addAll(createPodium, createArtist, createPerformance);

        Menu deleteMenu = new Menu("Delete");
        MenuItem  deleteStage = new MenuItem("Podium");
        MenuItem  deleteArtist = new MenuItem("Arist");
        MenuItem  deletePerformance = new MenuItem("Performance");
        deleteMenu.getItems().addAll(deleteStage, deleteArtist, deletePerformance);

        menuBar.getMenus().addAll(viewMenu, createMenu, deleteMenu);

        viewTable.setOnAction(event -> {
            FestivalTableview festivalTableview = new FestivalTableview(festivalPlan);
            borderPane.setCenter(festivalTableview);
        });
        viewBlock.setOnAction(event -> {
            borderPane.setCenter(canvas);
        });
        viewTable.setOnAction(event -> {
            FestivalTableview festivalTableview = new FestivalTableview(festivalPlan);
            borderPane.setCenter(festivalTableview);
        });
        createArtist.setOnAction(event -> {
            new ArtistAdd();
        });

        createPodium.setOnAction(event -> {
            new StageAdd(festivalPlan);
        });

        createPerformance.setOnAction(event -> {
//            new PerformanceAdd();
        });


        deleteStage.setOnAction(event -> {
            new DeleteStage(festivalPlan);
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
