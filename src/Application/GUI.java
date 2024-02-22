package Application;

import data.FestivalPlan;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
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
        Menu menu1 = new Menu("Saturday");
        MenuItem menuItem1 = new MenuItem("Tableview");
        MenuItem menuItem2 = new MenuItem("Blockview");
        menu1.getItems().addAll(menuItem1, menuItem2);
        Menu menu2 = new Menu("Sunday");

        MenuItem menuItem3 = new MenuItem("Tableview");
        MenuItem menuItem4 = new MenuItem("Blockview");
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

        // MouseClick
        canvas.setOnMousePressed(event -> {
            if (borderPane.getCenter() == canvas){
                Point2D point2D = new Point2D.Double(event.getX(), event.getY());
                if (festivalBlockview.checkClicked(point2D) != null) {
                    System.out.println(festivalBlockview.checkClicked(point2D).getArtists().get(0).getName());
                }
            }
        });


        borderPane.setTop(menuBar);

        borderPane.setPrefSize(1700,800);
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Festival Planner");
        primaryStage.show();
    }
}
