package Application.Delete;

import data.FestivalPlan;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

public class DeleteArtist {
    public DeleteArtist(FestivalPlan festivalPlan) {
        Stage stage = new Stage();
        ChoiceBox<data.Artist> artists = new ChoiceBox<>();
        artists.getItems().addAll(festivalPlan.getArtists());

        Button deleteStage = new Button("Delete");
        Button cancelButton = new Button("Cancel");

        HBox buttons = new HBox();
        buttons.getChildren().addAll(deleteStage, cancelButton);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(artists, buttons);
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
            if (artists.getItems().isEmpty()) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.getDialogPane().setContent(new Label("Please select an artist to delete!"));
                error.show();
            } else {
                Alert warning = new Alert(Alert.AlertType.WARNING);
                ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                warning.getButtonTypes().addAll(cancel);

                warning.getDialogPane().setContent(new Label("Are you sure you want to delete " + artists.getSelectionModel().getSelectedItem().getName()));

                Optional<ButtonType> result = warning.showAndWait();

                if (result.get() == cancel) {
                    warning.close();
                } else {
                    festivalPlan.deleteArtist(artists.getSelectionModel().getSelectedItem());
                    System.out.println("deleted");
                    warning.close();
                    stage.close();
                }
            }
        });
    }
}
