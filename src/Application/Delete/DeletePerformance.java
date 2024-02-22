package Application.Delete;

import data.Artist;
import data.FestivalPlan;
import data.Performance;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

public class DeletePerformance {
    public DeletePerformance(FestivalPlan festivalPlan) {
        Stage stage = new Stage();
        ChoiceBox<Performance> performances = new ChoiceBox<>();
        performances.getItems().addAll(festivalPlan.getPerformances());

        Button deleteStage = new Button("Delete");
        Button cancelButton = new Button("Cancel");

        HBox buttons = new HBox();
        buttons.getChildren().addAll(deleteStage, cancelButton);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(performances, buttons);
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
            if (performances.getItems().isEmpty()) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.getDialogPane().setContent(new Label("Please select a performance to delete!"));
                error.show();
            } else {
                Alert warning = new Alert(Alert.AlertType.WARNING);
                ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                warning.getButtonTypes().addAll(cancel);

                warning.getDialogPane().setContent(new Label("Are you sure you want to delete " + performances.getSelectionModel().getSelectedItem()));

                Optional<ButtonType> result = warning.showAndWait();

                if (result.get() == cancel) {
                    warning.close();
                } else {
                    festivalPlan.deletePerformance(performances.getSelectionModel().getSelectedItem());
                    System.out.println("deleted");
                    warning.close();
                    stage.close();
                }
            }
        });
    }
}
