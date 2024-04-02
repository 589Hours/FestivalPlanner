package Application.Edit;

import data.Artist;
import data.FestivalPlan;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EditArtist {
    public EditArtist(FestivalPlan festivalPlan) {
        Stage stage = new Stage();
        ChoiceBox<Artist> artists = new ChoiceBox<>();
        artists.getItems().addAll(festivalPlan.getArtists());

        Button editStage = new Button("Edit");
        Button cancelButton = new Button("Cancel");

        HBox buttons = new HBox();
        buttons.getChildren().addAll(editStage, cancelButton);

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

        editStage.setOnAction(event -> {
            if (artists.getItems().isEmpty()) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.getDialogPane().setContent(new Label("Please select an artist to edit!"));
                error.show();
            } else {
                stage.close();
                Stage artistStage = new Stage();
                TextField artistNameText = new TextField();
                TextField artistGenreText = new TextField();
                TextField artistPop = new TextField();
                TextArea description = new TextArea("");

                Label artistName = new Label("Please enter the new name of the artist:");
                Label artistGenre = new Label("Please enter the new genre in which this artist performs:");
                Label artistPopularity = new Label("Please enter the expected popularity for this artist ranging from 0-100");

                Button editButton = new Button("Edit");
                Button cancelButtonPopup = new Button("Cancel");

                HBox buttonsBox = new HBox();
                buttonsBox.setSpacing(10);
                buttonsBox.getChildren().addAll(editButton, cancelButtonPopup);

                Label artistDescription = new Label("Description of artist");

                if (artists.getSelectionModel().getSelectedItem() != null) {
                    description = new TextArea(artists.getSelectionModel().getSelectedItem().getArtistInfo());
                    artistNameText.setText(artists.getSelectionModel().getSelectedItem().getName());
                    artistGenreText.setText(artists.getSelectionModel().getSelectedItem().getGenre());
                    artistPop.setText(String.valueOf(artists.getSelectionModel().getSelectedItem().getPopularity()));
                }


                VBox artistAdd = new VBox();
                artistAdd.setSpacing(10);
                artistAdd.getChildren().addAll(artistName, artistNameText, artistGenre, artistGenreText, artistPopularity, artistPop, artistDescription, description, buttonsBox);
                if (artists.getSelectionModel().getSelectedItem() != null) {
                    TextArea finalDescription = description;
                    editButton.setOnAction(event1 -> {
                        if (!(artistNameText.getText().isEmpty() || artistGenreText.getText().isEmpty() || Integer.parseInt(artistPop.getText()) < 0 || Integer.parseInt(artistPop.getText()) > 100)) {
                            artists.getSelectionModel().getSelectedItem().setName(artistNameText.getText());
                            artists.getSelectionModel().getSelectedItem().setGenre(artistGenreText.getText());
                            artists.getSelectionModel().getSelectedItem().setPopularity(Integer.parseInt(artistPop.getText()));
                            artists.getSelectionModel().getSelectedItem().setArtistInfo(finalDescription.getText());

                            Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
                            confirmation.setHeaderText("Succes!");
                            confirmation.setContentText("The artist was successfully edited");
                            confirmation.showAndWait();

                            artistStage.close();

                        } else {
                            Alert error = new Alert(Alert.AlertType.ERROR);
                            error.getDialogPane().setContent(new Label("Either a textfield is left empty or the given popularity is not between 0-100"));
                            error.show();
                        }

                    });

                    cancelButtonPopup.setOnAction(event1 -> {
                        artistStage.close();
                    });

                    Scene artistScene = new Scene(artistAdd);
                    artistStage.setScene(artistScene);
                    artistStage.setWidth(750);
                    artistStage.setHeight(500);
                    artistStage.setTitle("Artist");
                    artistStage.show();
                }
            }
        });
    }
}
