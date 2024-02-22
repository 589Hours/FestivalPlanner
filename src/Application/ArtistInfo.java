package Application;

import data.Artist;
import data.Performance;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Optional;


public class ArtistInfo extends Application {
    private Performance performance;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Label artistName = new Label(this.performance.getArtist().getName());
        artistName.setFont(new Font(25));

        //artistInfo label
        Label aritstInfoLabel = new Label("\n" + this.performance.getArtist().getArtistInfo());

        VBox vBox = new VBox(artistName, generateTimesLabel(), aritstInfoLabel);
        Scene scene = new Scene(vBox);

        primaryStage.setTitle("Artist info");
        primaryStage.setMinWidth(750);
        primaryStage.setMaxWidth(750);
        primaryStage.setScene(scene);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.show();
    }

    public void setArtist(Performance performance) {
        this.performance = performance;
    }

    private Label generateTimesLabel() {
        String times = performance.getBeginHour() + ":";
        if (performance.getBeginMinute() < 10) {
            times = times + "0";
        }
        times = times + performance.getBeginMinute() + "-" + performance.getEndHour() + ":";
        if (performance.getEndMinute() < 10) {
            times = times + "0";
        }
        times = times + performance.getEndMinute();
        return new Label(performance.getStage().getName() + " - " + times);
    }
}
