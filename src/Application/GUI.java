package Application;

import data.FestivalPlan;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.jfree.fx.ResizableCanvas;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

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

        Menu menu1 = new Menu("Saturday");
        MenuItem menuItem1 = new MenuItem("Tableview");
        MenuItem menuItem2 = new MenuItem("Blockview");

        Menu menu2 = new Menu("Sunday");
        MenuItem menuItem3 = new MenuItem("Tableview");
        MenuItem menuItem4 = new MenuItem("Blockview");

        menu1.getItems().addAll(menuItem1, menuItem2);
        menu2.getItems().addAll(menuItem3, menuItem4);
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

        borderPane.setTop(menuBar);

        Button artistButton = new Button("Add Artist");
        buttonPlacement.setRight(artistButton);
        borderPane.setBottom(buttonPlacement);

        artistButton.setOnAction(event -> {
            Stage artistStage = new Stage();
            ComboBox stages = new ComboBox();
            ComboBox timeStamps = new ComboBox();
            ArrayList<String> times = new ArrayList<>();
            Collections.addAll(times,"12:00","12:30","13:00","13:30","14:00","14:30","15:00","15:30","16:00","16:30","17:00","17:30","18:00","18:30","19:00","19:30","20:00","20:30","21:00","21:30","22:00","22:30","23:00","23:30","00:00","00:30","01:00","01:30","02:00");

            stages.getItems().addAll(new Label("Main stage"));
            for (String time : times){
                timeStamps.getItems().add(new Label(time));
            }

            VBox vBox = new VBox(10);


            vBox.getChildren().add(new Label("Artist Name"));
            vBox.getChildren().add(new TextField());
            vBox.getChildren().add(new Label("Stage where this artist will perform"));
            vBox.getChildren().add(stages);
            vBox.getChildren().add(new Label("Choose timeslot for the performance"));
            vBox.getChildren().add(timeStamps);

            Scene artistScene = new Scene(vBox);

            artistStage.setHeight(500);
            artistStage.setWidth(750);
            artistStage.setTitle("Artist Information");
            artistStage.setScene(artistScene);
            artistStage.show();
        });

        borderPane.setPrefSize(1700,800);
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Festival Planner");
        primaryStage.show();
    }

}
