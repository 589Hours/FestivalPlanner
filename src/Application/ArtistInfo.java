package Application;

import data.Artist;
import javafx.application.Application;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;



public class ArtistInfo extends Application {
    Artist artist;


    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox mainStage = new VBox();
        Label label = new Label("Ed Sheeran");

        mainStage.getChildren().add(label);
        primaryStage.setTitle("Artist info");
        primaryStage.show();
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
