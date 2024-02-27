package Application.Edit;

import data.Artist;
import data.FestivalPlan;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

public class EditStage {
    public EditStage(FestivalPlan festivalPlan) {
        Stage stage = new Stage();
        ChoiceBox<data.Stage> stages = new ChoiceBox<>();
        stages.getItems().addAll(festivalPlan.getStages());

        Button editStage = new Button("Edit");
        Button cancelButton = new Button("Cancel");

        HBox buttons = new HBox();
        buttons.getChildren().addAll(editStage, cancelButton);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(stages, buttons);
        vBox.setSpacing(10);

        Scene scene = new Scene(vBox);
        stage.setScene(scene);
        stage.setHeight(500);
        stage.setWidth(750);
        stage.show();

        //buttons
        cancelButton.setOnAction(event -> {
            stage.close();
        });

        editStage.setOnAction(event -> {
            if(stages.getItems().isEmpty()) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.getDialogPane().setContent(new Label("Please select a stage to edit!"));
                error.show();
            } else {
                stage.close();
                Alert warning = new Alert(Alert.AlertType.CONFIRMATION);
                ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                Stage artistStage = new Stage();
                TextField stageName = new TextField();

                Label stageNameLabel = new Label("Please enter the new stage name.");

                Button editButton = new Button("Edit");
                Button cancelButtonPopup = new Button("Cancel");

                HBox buttonsBox = new HBox();
                buttonsBox.setSpacing(10);
                buttonsBox.getChildren().addAll(editButton, cancelButtonPopup);

                VBox artistAdd = new VBox();
                artistAdd.setSpacing(10);
                artistAdd.getChildren().addAll(stageNameLabel, stageName, buttonsBox);

                cancelButtonPopup.setOnAction(event1 -> {
                    stage.close();
                    artistStage.close();
                });


                editButton.setOnAction(event1 -> {
                    if(stageName.getText().isEmpty()) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.getDialogPane().setContent(new Label("The textfield is empty, please enter a name."));
                        alert.show();
                    } else {
                        stage.close();
                        stages.getSelectionModel().getSelectedItem().setName(stageName.getText());
                        artistStage.close();
                    }
                });


                Scene artistScene = new Scene(artistAdd);
                artistStage.setScene(artistScene);
                artistStage.setWidth(750);
                artistStage.setHeight(500);
                artistStage.setTitle("Artist");
                artistStage.show();
            }
        });
    }
}
