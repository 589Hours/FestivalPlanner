package Application.Edit;

import data.Artist;
import data.FestivalPlan;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.sound.sampled.Line;
import java.util.Optional;

public class EditArtist {
    public EditArtist(FestivalPlan festivalPlan) {
        Stage stage = new Stage(); // Maak een nieuw venster
        ChoiceBox<Artist> artists = new ChoiceBox<>(); // Keuzebox voor artiesten
        artists.getItems().addAll(festivalPlan.getArtists()); // Voeg artiesten toe aan de keuzebox

        Button editStage = new Button("Edit"); // Bewerkingsknop
        Button cancelButton = new Button("Cancel"); // Annuleringsknop

        HBox buttons = new HBox(); // Horizontale box voor knoppen
        buttons.getChildren().addAll(editStage, cancelButton); // Voeg knoppen toe aan de box

        VBox vBox = new VBox(); // Verticale box voor de interface
        vBox.getChildren().addAll(artists, buttons); // Voeg keuzebox en knoppen toe aan de box
        vBox.setSpacing(10); // Ruimte tussen elementen

        Scene scene = new Scene(vBox); // Scene voor het venster
        stage.setScene(scene); // Stel de scene in voor het venster
        stage.setHeight(500); // Stel de hoogte van het venster in
        stage.setWidth(750); // Stel de breedte van het venster in
        stage.show(); // Toon het venster

           // buttons
        cancelButton.setOnAction(event -> { // Handel de actie af wanneer op de annuleringsknop wordt geklikt
            stage.close(); // Sluit het venster
        });

        editStage.setOnAction(event -> { // Handel de actie af wanneer op de bewerkingsknop wordt geklikt
            if (artists.getItems().isEmpty()) { // Controleer of de keuzebox leeg is
                Alert error = new Alert(Alert.AlertType.ERROR); // Toon een foutmelding
                error.getDialogPane().setContent(new Label("Selecteer alstublieft een artiest om te bewerken!")); // Stel de foutmelding in
                error.show(); // Toon de foutmelding
            } else { // Als de keuzebox niet leeg is
                stage.close(); // Sluit het huidige venster
                Stage artistStage = new Stage(); // Creëer een nieuw venster voor het bewerken van artiesten
                TextField artistNameText = new TextField(); // Tekstveld voor de naam van de artiest
                TextField artistGenreText = new TextField(); // Tekstveld voor het genre van de artiest
                TextField artistPop = new TextField(); // Tekstveld voor de populariteit van de artiest
                TextArea description = new TextArea(""); // Tekstgebied voor de beschrijving van de artiest

                Label artistName = new Label("Voer alstublieft de nieuwe naam van de artiest in:"); // Label voor de naam van de artiest
                Label artistGenre = new Label("Voer het nieuwe genre in waarin deze artiest optreedt:"); // Label voor het genre van de artiest
                Label artistPopularity = new Label("Voer de verwachte populariteit voor deze artiest in, variërend van 0-100"); // Label voor de populariteit van de artiest

                Button editButton = new Button("Bewerk"); // Bewerkingsknop
                Button cancelButtonPopup = new Button("Annuleren"); // Annuleringsknop

                HBox buttonsBox = new HBox(); // Horizontale box voor knoppen
                buttonsBox.setSpacing(10); // Ruimte tussen knoppen
                buttonsBox.getChildren().addAll(editButton, cancelButtonPopup); // Voeg knoppen toe aan de box

                Label artistDescription = new Label("Beschrijving van de artiest"); // Label voor de beschrijving van de artiest

                if (artists.getSelectionModel().getSelectedItem() != null) { // Controleer of een artiest is geselecteerd
                    description = new TextArea(artists.getSelectionModel().getSelectedItem().getArtistInfo()); // Gebruik de beschrijving van de geselecteerde artiest
                    artistNameText.setText(artists.getSelectionModel().getSelectedItem().getName()); // Gebruik de naam van de geselecteerde artiest
                    artistGenreText.setText(artists.getSelectionModel().getSelectedItem().getGenre()); // Gebruik het genre van de geselecteerde artiest
                    artistPop.setText(String.valueOf(artists.getSelectionModel().getSelectedItem().getPopularity())); // Gebruik de populariteit van de geselecteerde artiest
                }

                VBox artistAdd = new VBox(); // Verticale box voor de interface van de artiest
                artistAdd.setSpacing(10); // Ruimte tussen elementen
                artistAdd.getChildren().addAll(artistName, artistNameText, artistGenre, artistGenreText, artistPopularity, artistPop, artistDescription, description, buttonsBox); // Voeg elementen toe aan de box
                if (artists.getSelectionModel().getSelectedItem() != null) { // Controleer of een artiest is geselecteerd
                    TextArea finalDescription = description; // Definieer de beschrijving van de artiest
                    editButton.setOnAction(event1 -> { // Handel de actie af wanneer op de bewerkingsknop wordt geklikt
                        if (!(artistNameText.getText().isEmpty() || artistGenreText.getText().isEmpty() || Integer.parseInt(artistPop.getText()) < 0 || Integer.parseInt(artistPop.getText()) > 100)) { // Controleer of de invoer correct is
                            artists.getSelectionModel().getSelectedItem().setName(artistNameText.getText()); // Bewerk de naam van de geselecteerde artiest
                            artists.getSelectionModel().getSelectedItem().setGenre(artistGenreText.getText()); // Bewerk het genre van de geselecteerde artiest
                            artists.getSelectionModel().getSelectedItem().setPopularity(Integer.parseInt(artistPop.getText())); // Bewerk de populariteit van de geselecteerde artiest
                            artists.getSelectionModel().getSelectedItem().setArtistInfo(finalDescription.getText()); // Bewerk de beschrijving van de geselecteerde artiest

                            Alert confirmation = new Alert(Alert.AlertType.INFORMATION); // Toon een bevestiging
                            confirmation.setHeaderText("Succes!"); // Stel de kop van de bevestiging in
                            confirmation.setContentText("De artiest is succesvol bewerkt"); // Stel de inhoud van de bevestiging in
                            confirmation.showAndWait(); // Toon de bevestiging en wacht tot deze wordt gesloten

                            artistStage.close(); // Sluit het venster voor het bewerken van artiesten
                        } else { // Als de invoer onjuist is
                            Alert error = new Alert(Alert.AlertType.ERROR); // Toon een foutmelding
                            error.getDialogPane().setContent(new Label("Een tekstveld is leeg gelaten of de opgegeven populariteit ligt niet tussen 0-100")); // Stel de foutmelding in
                            error.show(); // Toon de foutmelding
                        }

                    });

                    cancelButtonPopup.setOnAction(event1 -> { // Handel de actie af wanneer op de annuleringsknop wordt geklikt
                        artistStage.close(); // Sl
                    });
                    Scene artistScene = new Scene(artistAdd); // Maak een scene voor het venster voor het bewerken van artiesten
                    artistStage.setScene(artistScene); // Stel de scene in voor het venster voor het bewerken van artiesten
                    artistStage.setWidth(750); // Stel de breedte van het venster in
                    artistStage.setHeight(500); // Stel de hoogte van het venster in
                    artistStage.setTitle("Artist"); // Stel de titel van het venster in
                    artistStage.show(); // Toon het venster
                }
            }
        });
    }
}
