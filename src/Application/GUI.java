package Application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GUI extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = new BorderPane();

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

        menuItem3.setOnAction(event -> {
            FestivalTableview festivalTableview = new FestivalTableview();
            borderPane.setCenter(festivalTableview);
        });

        borderPane.setTop(menuBar);


        Scene scene = new Scene(borderPane);
        primaryStage.setHeight(500);
        primaryStage.setWidth(750);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Festival Planner");
        primaryStage.show();
    }
}
