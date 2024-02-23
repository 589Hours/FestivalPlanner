package Application.Create;

import data.Artist;
import data.FestivalPlan;
import data.Performance;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PerformanceAdd {
    public PerformanceAdd(FestivalPlan festivalPlan) {
        Stage stage = new Stage();

        Label artistLabel = new Label("Choose the artist: ");
        ChoiceBox<Artist> artists = new ChoiceBox<>();
        artists.getItems().addAll(festivalPlan.getArtists());

        Label stageLabel = new Label("\nChoose the Stage: ");
        ChoiceBox<data.Stage> stages = new ChoiceBox<>();
        stages.getItems().addAll(festivalPlan.getStages());

        Label beginTimeLabel = new Label("\nChoose the begin time");
        ChoiceBox<Integer> beginHourBox = new ChoiceBox<>();
        beginHourBox.getItems().addAll(12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 0, 1, 2);
        ChoiceBox<Integer> beginMinuteBox = new ChoiceBox<>();
        beginMinuteBox.getItems().addAll(00, 30);

        HBox beginButtonsHbox = new HBox(beginHourBox, new Label(":"), beginMinuteBox);
        VBox beginVBox = new VBox(beginTimeLabel, beginButtonsHbox);

        Label endTimeLabel = new Label("\nChoose the end time: ");
        ChoiceBox<Integer> endHourBox = new ChoiceBox<>();
        endHourBox.getItems().addAll(12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 0, 1, 2);
        ChoiceBox<Integer> endMinuteBox = new ChoiceBox<>();
        endMinuteBox.getItems().addAll(00, 30);

        HBox endButtonsHbox = new HBox(endHourBox, new Label(":"), endMinuteBox);
        VBox endVBox = new VBox(endTimeLabel, endButtonsHbox);

        HBox timesHBox = new HBox(beginVBox, endVBox);
        timesHBox.setSpacing(40);

        Button createButton = new Button("Create");
        Button cancelButton = new Button("Cancel");

        HBox buttonHbox = new HBox(createButton, cancelButton);
        buttonHbox.setSpacing(10);

        VBox totalVBox = new VBox(artistLabel, artists, stageLabel, stages, timesHBox, new Label("\n\n"), buttonHbox);

        Scene scene = new Scene(totalVBox);
        stage.setScene(scene);
        stage.setHeight(500);
        stage.setWidth(750);
        stage.show();

        createButton.setOnAction(event -> {
            boolean artistSelected = artists.getSelectionModel().getSelectedItem() == null;
            boolean stageSelected = stages.getSelectionModel().getSelectedItem() == null;

            if (artists.getSelectionModel().getSelectedItem() != null || stages.getSelectionModel().getSelectedItem() != null || beginHourBox.getSelectionModel().getSelectedItem() != null || endHourBox.getSelectionModel().getSelectedItem() != null || endHourBox.getSelectionModel().getSelectedItem() != null || endMinuteBox.getSelectionModel().getSelectedItem() != null) {
                if (endHourBox.getSelectionModel().getSelectedItem() < beginHourBox.getSelectionModel().getSelectedItem()) {
                    if (endMinuteBox.getSelectionModel().getSelectedItem() > beginMinuteBox.getSelectionModel().getSelectedItem()) {
                        festivalPlan.addPerformance(new Performance(artists.getSelectionModel().getSelectedItem(), stages.getSelectionModel().getSelectedItem(), endHourBox.getSelectionModel().getSelectedItem(), endMinuteBox.getSelectionModel().getSelectedItem(), beginHourBox.getSelectionModel().getSelectedItem(), beginMinuteBox.getSelectionModel().getSelectedItem()));
                    } else {
                        festivalPlan.addPerformance(new Performance(artists.getSelectionModel().getSelectedItem(), stages.getSelectionModel().getSelectedItem(), beginHourBox.getSelectionModel().getSelectedItem(), beginMinuteBox.getSelectionModel().getSelectedItem(), endHourBox.getSelectionModel().getSelectedItem(), endMinuteBox.getSelectionModel().getSelectedItem()));
                    }
                } else {
                    festivalPlan.addPerformance(new Performance(artists.getSelectionModel().getSelectedItem(), stages.getSelectionModel().getSelectedItem(), beginHourBox.getSelectionModel().getSelectedItem(), beginMinuteBox.getSelectionModel().getSelectedItem(), endHourBox.getSelectionModel().getSelectedItem(), endMinuteBox.getSelectionModel().getSelectedItem()));
                }
            } else {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.getDialogPane().setContent(new Label("Please select a option in every box!"));
            }
            stage.close();

        });

        cancelButton.setOnAction(event -> {
            stage.close();
        });
    }
}
