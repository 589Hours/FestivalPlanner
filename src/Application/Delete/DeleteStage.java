package Application.Delete;

import data.FestivalPlan;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Optional;

public class DeleteStage {
    public DeleteStage(FestivalPlan festivalPlan) {
        Stage stage = new Stage();
        ChoiceBox<data.Stage> stages = new ChoiceBox<>();
        stages.getItems().addAll(festivalPlan.getStages());

        Button deleteStage = new Button("Delete");
        Button cancelButton = new Button("Cancel");

        HBox buttons = new HBox();
        buttons.getChildren().addAll(deleteStage, cancelButton);

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

        deleteStage.setOnAction(event -> {
            if(stages.getItems().isEmpty()) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.getDialogPane().setContent(new Label("Please select an stage to delete!"));
                error.show();
            } else {
                Alert warning = new Alert(Alert.AlertType.WARNING);
                ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                warning.getButtonTypes().addAll(cancel);

                warning.getDialogPane().setContent(new Label("Are you sure you want to delete " + stages.getSelectionModel().getSelectedItem().getName()) );

                Optional<ButtonType> result = warning.showAndWait();

                if(result.get() == cancel) {
                    warning.close();
                } else {
                    festivalPlan.deleteStage(stages.getSelectionModel().getSelectedItem());
                    warning.close();
                    stage.close();
                }
            }
        });
    }
}

