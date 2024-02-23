package Application.Create;

import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;

public class ArtistAdd {

    public ArtistAdd(){
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
    }
}
