package Application;

import data.Artist;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class ArtistInfo extends Application {
    private Artist artist;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Label label = new Label(this.artist.getArtistInfo());
        primaryStage.setTitle("Artist info");

        FlowPane mainPane = new FlowPane();
        mainPane.getChildren().add(label);

        Scene scene = new Scene(mainPane);
//        scene.

        primaryStage.setScene(scene);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.show();
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
