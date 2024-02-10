package Application;

import data.Artist;
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

public class GUI extends Application {
    private ResizableCanvas canvas;
    FestivalBlockview festivalBlockview = new FestivalBlockview();
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane borderPane = new BorderPane();
        canvas = new ResizableCanvas(g -> festivalBlockview.draw(g, canvas), borderPane);



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
            FestivalTableview festivalTableview = new FestivalTableview();
            borderPane.setCenter(festivalTableview);
        });
        menuItem2.setOnAction(event -> {
            FestivalTableview festivalTableview = new FestivalTableview();
            borderPane.setCenter(canvas);
        });
        menuItem3.setOnAction(event -> {
            FestivalTableview festivalTableview = new FestivalTableview();
            borderPane.setCenter(festivalTableview);
        });
        menuItem4.setOnAction(event -> {
            FestivalTableview festivalTableview = new FestivalTableview();
            borderPane.setCenter(canvas);
        });

        borderPane.setTop(menuBar);


        //Dit is een test voor ArtistInfo, als dit een mergeConflict geeft kan deze verwijderd worden.
        Button button = new Button("Artist info check");
        HBox hBox = new HBox(button);
        borderPane.setCenter(hBox);

        button.setOnAction(event -> {
            ArtistInfo artistInfo = new ArtistInfo();
            Artist artist = new Artist("Ed Sheeran", 10000, "Pop");
            artist.setArtistInfo("Ed Sheeran is an british pop artist. He is mostly known for his hits 'Shape of you' and 'Perfect'. ");
            artistInfo.setArtist(artist);

            try {
                artistInfo.start(new Stage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        borderPane.setPrefSize(1700,800);
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Festival Planner");
        primaryStage.show();
    }
}
