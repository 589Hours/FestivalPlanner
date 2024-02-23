package Application;

import Application.Create.ArtistAdd;
import Application.Create.StageAdd;
import Application.Delete.DeleteArtist;
import Application.Delete.DeletePerformance;
import Application.Delete.DeleteStage;
import data.Artist;
import data.Performance;
import data.FestivalPlan;
import data.SaveToFile;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jfree.fx.ResizableCanvas;

import java.awt.geom.Point2D;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;

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
        Menu menu1 = new Menu("View");
        MenuItem menuItem1 = new MenuItem("Tableview");
        MenuItem menuItem2 = new MenuItem("Blockview");
        menu1.getItems().addAll(menuItem1, menuItem2);

        Menu menu2 = new Menu("Create");
        MenuItem menuItem3 = new MenuItem("Podium");
        MenuItem menuItem4 = new MenuItem("Artist");
        MenuItem menuItem5 = new MenuItem("Performance");

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
        Menu saveAndLoadMenu = new Menu("Save & Load");
        MenuItem saveAgenda = new MenuItem("Save");
        MenuItem loadAgenda = new MenuItem("Load");
        saveAndLoadMenu.getItems().addAll(saveAgenda, loadAgenda);


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

        saveAgenda.setOnAction(event -> {
            SaveToFile save = new SaveToFile(festivalPlan);
        });

        loadAgenda.setOnAction(event -> {
            try{
                FileInputStream fis = new FileInputStream("Saves/SaveFile.ser");
                ObjectInputStream ois = new ObjectInputStream(fis);
                festivalPlan = (FestivalPlan) ois.readObject();
            } catch (Exception e){
                System.out.println("file not found or class not found!");
            }
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

        deleteArtist.setOnAction(event -> {
            new DeleteArtist(festivalPlan);
        });

        deletePerformance.setOnAction(event -> {
            new DeletePerformance(festivalPlan);
        });

        // MouseClick
        canvas.setOnMousePressed(event -> {
            if (borderPane.getCenter() == canvas){
                Point2D point2D = new Point2D.Double(event.getX(), event.getY());
                if (festivalBlockview.checkClicked(point2D) != null) {
                    Alert info = new Alert(Alert.AlertType.INFORMATION);
                    info.setTitle(festivalBlockview.checkClicked(point2D).getArtist().getName());
                    info.setHeaderText(festivalBlockview.checkClicked(point2D).getArtist().getName());
                    info.setContentText("Podium: " + festivalBlockview.checkClicked(point2D).getStage().getName() + "\n" +
                            "Tijd: " + festivalBlockview.checkClicked(point2D).getBeginTime() + " - " +
                                    festivalBlockview.checkClicked(point2D).getEndTime() + "\n" +
                            "Genre: " + festivalBlockview.checkClicked(point2D).getArtist().getGenre() + "\n" +
                            "ArtiestBeschrijving: " + festivalBlockview.checkClicked(point2D).getArtist().getArtistInfo());
                    info.showAndWait();
                    System.out.println(festivalBlockview.checkClicked(point2D));
                }
            }
        });

        borderPane.setTop(menuBar);

        borderPane.setPrefSize(1700, 800);
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Festival Planner");
        primaryStage.getIcons().add(new Image("icons8-festival-64.png"));
        primaryStage.show();
    }

}
