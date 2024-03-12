package Application.Create;

import data.Artist;
import data.FestivalPlan;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;

public class ArtistAdd {

    public ArtistAdd(FestivalPlan festivalPlan){
        Stage artistStage = new Stage(); // Hier wordt de venster aangemaakt waarin de gebruiker de artiestgegevens zal invoeren.

        TextField artistNameText = new TextField(); // Naam van de artiest.
        TextField artistGenreText = new TextField(); // Genre van de artiest.
        TextField artistPop = new TextField(); // Populariteit van de artiest.

        Label artistName = new Label("Please enter the name of the artist:"); // Label om de gebruiker te informeren over welke informatie hij moet invoeren.
        Label artistGenre = new Label("Please enter the genre in which this artist performs:"); // // Label om de gebruiker te informeren over welke informatie hij moet invoeren.
        Label artistPopularity = new Label("Please enter the expected for this artist ranging from 0-100"); // Label om de gebruiker te informeren over welke informatie hij moet invoeren.


        Button createButton = new Button("Create"); // Hiermee maakt de gebruiker een artiest aan.
        Button cancelButton = new Button("Cancel"); // Hiermee annuleer je het proces van het toevoegen van een artiest.

        HBox buttonsBox = new HBox(); // Hier worden de knoppen van de venster geplaatst.
        buttonsBox.setSpacing(10); // De knoppen worden 10 pixels apart geplaatst.
        buttonsBox.getChildren().addAll(createButton, cancelButton); // Alle knoppen worden toegevoegd aan de buttonsBox.

        Label artistDescription = new Label("Description of artist"); // Label om de gebruiker te informeren over welke informatie hij moet invoeren.
        TextArea description = new TextArea("Optional"); // Hierin kan de gebruiker een beschrijving van de artiest invoeren. ( Optioneel )

        VBox artistAdd = new VBox(); // Hier worden de labels en textfields van de venster geplaatst.
        artistAdd.setSpacing(10); // De labels en textfields worden 10 pixels apart geplaatst.
        artistAdd.getChildren().addAll(artistName, artistNameText, artistGenre, artistGenreText, artistPopularity, artistPop, artistDescription, description, buttonsBox); // Alle labels en textfields worden toegevoegd aan de artistAdd.


        Scene artistScene = new Scene(artistAdd); // Hier wordt de venster gemaakt.
        artistStage.setScene(artistScene); // Hier wordt de venster geplaatst.
        artistStage.setWidth(750); // Hier wordt de width van de venster geplaatst.
        artistStage.setHeight(500); // Hier wordt de height van de venster geplaatst.
        artistStage.setTitle("Artist"); // Hier wordt de titel van de venster geplaatst.
        artistStage.show(); // Hier wordt de venster getoond.

        createButton.setOnAction(event -> { // Hier wordt de createButton aangemaakt.
            if (artistNameText.getText().isEmpty() || artistGenreText.getText().isEmpty() || Integer.parseInt(artistPop.getText()) < 0 || Integer.parseInt(artistPop.getText()) > 100) { // Als de gebruiker geen gegevens heeft ingevuld, dan wordt er een alert gemaakt.
                Alert error = new Alert(Alert.AlertType.ERROR); // Hier wordt de alert gemaakt.
                error.getDialogPane().setContent(new Label("Either a textfield is left empty or the given popularity is not between 0-100")); // Hierin wordt de inhoud van de alert geplaatst.
                error.show(); // Hier wordt de alert getoond.
            } else {
                if (description.getText().equals("Optional")){ // Als de gebruiker geen beschrijving heeft ingevuld, dan wordt er geen beschrijving gemaakt.
                    festivalPlan.addArtist(new Artist(artistNameText.getText(), Integer.parseInt(artistPop.getText()), artistGenreText.getText())); // Hier wordt de artiest gemaakt.
                } else {
                    festivalPlan.addArtist(new Artist(artistNameText.getText(), Integer.parseInt(artistPop.getText()), artistGenreText.getText(), description.getText())); // Hier wordt de artiest gemaakt met een beschrijving.
                }
            }

            Alert confirmation = new Alert(Alert.AlertType.INFORMATION); // Hier wordt een alert gemaakt.
            confirmation.setHeaderText("Succes!"); // Hierin wordt de header geplaatst
            confirmation.setContentText("The Artist was succesfully added!"); // Hierin wordt de inhoud geplaatst.
            confirmation.showAndWait(); // Hier wordt de alert getoond.

            artistStage.close(); // Hier wordt de venster gesloten.
        });
        cancelButton.setOnAction(event -> { // Hier wordt de cancel button aangemaakt.
            artistStage.close(); // Hier wordt de venster gesloten.
        });
    }
}
