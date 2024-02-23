package Application;

import Application.Create.ArtistAdd;
import Application.Create.PerformanceAdd;
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

        Menu saveAndLoadMenu = new Menu("Save & Load");
        MenuItem saveAgenda = new MenuItem("Save");
        MenuItem loadAgenda = new MenuItem("Load");
        saveAndLoadMenu.getItems().addAll(saveAgenda, loadAgenda);

        menuBar.getMenus().addAll(viewMenu, createMenu, deleteMenu, saveAndLoadMenu);





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
            new PerformanceAdd(festivalPlan);
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
