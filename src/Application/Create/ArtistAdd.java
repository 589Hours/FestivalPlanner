package Application.Create;

import data.Artist;
import data.FestivalPlan;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ArtistAdd {

    public ArtistAdd(FestivalPlan festivalPlan){
        Stage artistStage = new Stage();

        TextField artistNameText = new TextField();
        TextField artistGenreText = new TextField();
        TextField artistPop = new TextField();

        Label artistName = new Label("Please enter the name of the artist:");
        Label artistGenre = new Label("Please enter the genre in which this artist performs:");
        Label artistPopularity = new Label("Please enter the expected for this artist ranging from 0-100");


        Button createButton = new Button("Create");
        Button cancelButton = new Button("Cancel");

        HBox buttonsBox = new HBox();
        buttonsBox.setSpacing(10);
        buttonsBox.getChildren().addAll(createButton, cancelButton);

        Label artistDescription = new Label("Description of artist");
        TextArea description = new TextArea("Optional");

        VBox artistAdd = new VBox();
        artistAdd.setSpacing(10);
        artistAdd.getChildren().addAll(artistName, artistNameText, artistGenre, artistGenreText, artistPopularity, artistPop, artistDescription, description, buttonsBox);


        Scene artistScene = new Scene(artistAdd);
        artistStage.setScene(artistScene);
        artistStage.setWidth(750);
        artistStage.setHeight(500);
        artistStage.setTitle("Artist");
        artistStage.show();

        createButton.setOnAction(event -> {
            if (artistNameText.getText().isEmpty() || artistGenreText.getText().isEmpty() || Integer.parseInt(artistPop.getText()) < 0 || Integer.parseInt(artistPop.getText()) > 100) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.getDialogPane().setContent(new Label("Either a textfield is left empty or the given popularity is not between 0-100"));
                error.show();
            } else {
                for (Artist artist : festivalPlan.getArtists()) {
                    if (artist.getName().equals(artistNameText.getText())){
                        Alert error = new Alert(Alert.AlertType.ERROR);
                        error.getDialogPane().setContent(new Label("This artist already exists!"));
                        error.show();
                        return;
                    }
                }
                if (description.getText().equals("Optional")){
                    festivalPlan.addArtist(new Artist(artistNameText.getText(), Integer.parseInt(artistPop.getText()), artistGenreText.getText()));
                } else {
                    festivalPlan.addArtist(new Artist(artistNameText.getText(), Integer.parseInt(artistPop.getText()), artistGenreText.getText(), description.getText()));
                }
            }

            Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
            confirmation.setHeaderText("Succes!");
            confirmation.setContentText("The Artist was succesfully added!");
            confirmation.showAndWait();

            artistStage.close();
        });
        cancelButton.setOnAction(event -> {
            artistStage.close();
        });
    }
}
