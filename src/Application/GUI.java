package Application;

import data.Artist;
import data.Performance;
import data.FestivalPlan;
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
