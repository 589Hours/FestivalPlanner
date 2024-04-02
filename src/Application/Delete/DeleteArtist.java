package Application.Delete;

import Application.FestivalBlockview;
import data.Artist;
import data.FestivalPlan;
import data.Performance;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Optional;

public class DeleteArtist {
    public DeleteArtist(FestivalPlan festivalPlan, FestivalBlockview festivalBlockview) {
        Stage stage = new Stage();
        ChoiceBox<data.Artist> artists = new ChoiceBox<>();
        artists.getItems().addAll(festivalPlan.getArtists());

        Button deleteArtist = new Button("Delete");
        Button cancelButton = new Button("Cancel");

        HBox buttons = new HBox();
        buttons.getChildren().addAll(deleteArtist, cancelButton);

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

        deleteArtist.setOnAction(event -> {
            if (artists.getItems().isEmpty()) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.getDialogPane().setContent(new Label("Please select an artist to delete!"));
                error.show();
            } else {
                Alert warning = new Alert(Alert.AlertType.WARNING);
                ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                warning.getButtonTypes().addAll(cancel);

                String artistName =  artists.getSelectionModel().getSelectedItem().getName();
                warning.setHeaderText("Are you sure you want to delete " + artistName);
                warning.setContentText("Deleting this " +artistName+ " will also delete Performances with them.");

                Optional<ButtonType> result = warning.showAndWait();

                if (result.get() == cancel) {
                    warning.close();
                } else {
                    Artist artist = artists.getSelectionModel().getSelectedItem();
                    festivalPlan.deleteArtist(artist);
                    System.out.println("deleted");
                    deletePerformanceWithArtist(festivalPlan, artist, festivalBlockview);
                    warning.close();
                    stage.close();
                }
            }
        });
    }

    private void deletePerformanceWithArtist(FestivalPlan festivalPlan, Artist artist, FestivalBlockview festivalBlockview) {
        ArrayList<Performance> tempPerformances = new ArrayList<>(festivalPlan.getPerformances());
        for (Performance performance : tempPerformances) {
            if (performance.getArtist().equals(artist)){
                festivalPlan.deletePerformance(performance);
                festivalBlockview.deleteBlock(performance);
            }
        }

    }
}
