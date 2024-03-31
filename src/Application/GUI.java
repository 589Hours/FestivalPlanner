package Application;

import Application.Create.ArtistAdd;
import Application.Create.PerformanceAdd;
import Application.Create.StageAdd;
import Application.Delete.DeleteArtist;
import Application.Delete.DeletePerformance;
import Application.Delete.DeleteStage;
import Application.Edit.EditArtist;
import Application.Edit.EditStage;
import Application.Simulation.Simulation;
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
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Optional;

public class GUI extends Application {
    private FestivalPlan festivalPlan;
    private ResizableCanvas canvas;
    private String saveFilePath;
    FestivalBlockview festivalBlockview = new FestivalBlockview();
    boolean started;


    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = new BorderPane();
        BorderPane buttonPlacement = new BorderPane();
        canvas = new ResizableCanvas(g -> festivalBlockview.draw(g, festivalPlan), borderPane);
        started = false;

        festivalPlan = new FestivalPlan();
        saveFilePath = "Saves/SaveFile.ser";
        try{

            FileInputStream fis = new FileInputStream(saveFilePath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            festivalPlan = (FestivalPlan) ois.readObject();
        } catch (Exception e){
            System.out.println("file not found or class not found!");
        }

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

        Menu editMenu = new Menu("Edit");
        MenuItem editStage = new MenuItem("Podium");
        MenuItem editArtist = new MenuItem("Artist");
        editMenu.getItems().addAll(editStage, editArtist);

        Menu deleteMenu = new Menu("Delete");
        MenuItem  deleteStage = new MenuItem("Podium");
        MenuItem  deleteArtist = new MenuItem("Artist");
        MenuItem  deletePerformance = new MenuItem("Performance");
        deleteMenu.getItems().addAll(deleteStage, deleteArtist, deletePerformance);

        Menu saveAndLoadMenu = new Menu("Save & Load");
        MenuItem saveAgenda = new MenuItem("Save");
        MenuItem loadAgenda = new MenuItem("Load");
        MenuItem clearAgenda = new MenuItem("Clear");
        saveAndLoadMenu.getItems().addAll(saveAgenda, loadAgenda, clearAgenda);

        menuBar.getMenus().addAll(viewMenu, createMenu, editMenu, deleteMenu, saveAndLoadMenu);

        viewTable.setOnAction(event -> {
            FestivalTableview festivalTableview = new FestivalTableview(festivalPlan);
            borderPane.setCenter(festivalTableview);
        });
        viewBlock.setOnAction(event -> {
            festivalBlockview.draw(festivalBlockview.getFxGraphics2D(), festivalPlan);
            borderPane.setCenter(canvas);
        });
        createArtist.setOnAction(event -> {
            new ArtistAdd(festivalPlan);
        });

        saveAgenda.setOnAction(event -> {
            SaveToFile save = new SaveToFile(festivalPlan);
        });

        loadAgenda.setOnAction(event -> {
            try{
                FileInputStream fis = new FileInputStream(saveFilePath);
                ObjectInputStream ois = new ObjectInputStream(fis);
                festivalPlan = (FestivalPlan) ois.readObject();
                succesPopup("File successfully loaded!");
            } catch (Exception e){
                fileNotFoundNotification(1);
                System.out.println("file not found or class not found!");
            }
        });

        clearAgenda.setOnAction(event -> {
            Alert warning = new Alert(Alert.AlertType.WARNING);
            ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            warning.getButtonTypes().addAll(cancel);

            warning.getDialogPane().setContent(new Label("Are you sure you want to clear the agenda? \nThis will make you lose your save file and all data! "));

            Optional<ButtonType> result = warning.showAndWait();

            if (result.get() == cancel) {
                warning.close();
            } else {
                this.festivalPlan = new FestivalPlan();
                festivalBlockview.deleteAllBlocks();
                festivalBlockview.draw(festivalBlockview.getFxGraphics2D(), festivalPlan);
                try {
                    PrintWriter printWriter = new PrintWriter(saveFilePath);
                    printWriter.write("");
                    printWriter.close();
                } catch (IOException e){
                    fileNotFoundNotification(2);
                }
                succesPopup("Successfully cleared the agenda and save file");
                warning.close();
            }
        });

        createPodium.setOnAction(event -> {
            new StageAdd(festivalPlan);
        });

        createPerformance.setOnAction(event -> {
            new PerformanceAdd(festivalPlan);
        });


        editStage.setOnAction(event -> {
            new EditStage(festivalPlan);
        });

        editArtist.setOnAction(event -> {
            new EditArtist(festivalPlan);
        });


        deleteStage.setOnAction(event -> {
            new DeleteStage(festivalPlan, festivalBlockview);
        });

        deleteArtist.setOnAction(event -> {
            new DeleteArtist(festivalPlan, festivalBlockview);
        });

        deletePerformance.setOnAction(event -> {
            new DeletePerformance(festivalPlan,festivalBlockview);
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

        Button simulationButton = new Button("Start simulation");
        borderPane.setBottom(simulationButton);

        simulationButton.setOnAction(event ->  {
            try {
                new Simulation().init(festivalPlan);
                started = true;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });


        borderPane.setPrefSize(1700, 800);
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Festival Planner");
        primaryStage.getIcons().add(new Image("icons8-festival-64.png"));
        primaryStage.show();
    }

    private void fileNotFoundNotification(int errorType) {
        Alert fileNotFound = new Alert(Alert.AlertType.ERROR);
        if (errorType == 1){
            fileNotFound.setContentText("Could not find a file to load!");
        } else {
            fileNotFound.setContentText("Could not find a file to clear!");
        }
        fileNotFound.showAndWait();
    }

    public static void succesPopup(String contextString){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Succes!");
        alert.setContentText(contextString);
        alert.showAndWait();
    }
}
