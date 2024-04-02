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
        Stage stage = new Stage(); // Maakt een nieuwe stage
        ChoiceBox<data.Artist> artists = new ChoiceBox<>(); // Maakt een choicebox
        artists.getItems().addAll(festivalPlan.getArtists()); // Vult de choicebox met alle artiesten uit de festivalplan

        Button deleteArtist = new Button("Delete"); // Maakt een delete button
        Button cancelButton = new Button("Cancel"); // Maakt een cancel button

        HBox buttons = new HBox(); // Maakt een hbox
        buttons.getChildren().addAll(deleteArtist, cancelButton); // Vult de hbox met de buttons

        VBox vBox = new VBox(); // Maakt een vbox
        vBox.getChildren().addAll(artists, buttons); // Vult de vbox met de choicebox en de buttons
        vBox.setSpacing(10); // Zet de spatie tussen de elementen

        Scene scene = new Scene(vBox); // Maakt de scene aan
        stage.setScene(scene); // Zet de scene van de stage
        stage.setHeight(500); // Zet de hoogte van de stage
        stage.setWidth(750); // Zet de breedte van de stage
        stage.show(); // Toon de stage

        //buttons
        cancelButton.setOnAction(event -> { // Als de cancel button wordt aangeklikt
            stage.close(); // Sluit de stage af
        });

        deleteArtist.setOnAction(event -> { // Als de delete button wordt aangeklikt
            if (artists.getItems().isEmpty()) { // Als er geen artiesten zijn geselecteerd
                Alert error = new Alert(Alert.AlertType.ERROR); // Maak een error
                error.getDialogPane().setContent(new Label("Please select an artist to delete!")); // Zet de error tekst
                error.show(); // Toon de error
            } else { // Als er artiesten zijn geselecteerd
                Alert warning = new Alert(Alert.AlertType.WARNING); // Maak een warning
                ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE); // Maak een cancel button
                warning.getButtonTypes().addAll(cancel); // Voeg de cancel button toe aan de warning

                String artistName =  artists.getSelectionModel().getSelectedItem().getName(); // Zet de artistnaam in een variabele
                warning.setHeaderText("Are you sure you want to delete " + artistName); // Zet de headertekst van de warning
                warning.setContentText("Deleting this " +artistName+ " will also delete Performances with them."); // Zet de tekst van de warning

                Optional<ButtonType> result = warning.showAndWait(); // Toon de warning en wacht op een antwoord

                if (result.get() == cancel) { // Als de cancel button wordt aangeklikt
                    warning.close(); // Sluit de warning af
                } else { // Als de delete button wordt aangeklikt
                    Artist artist = artists.getSelectionModel().getSelectedItem(); // Zet de geselecteerde artist in een variabele
                    festivalPlan.deleteArtist(artist); // Verwijder de artist uit de festivalplan
                    System.out.println("deleted"); // Print deleted
                    deletePerformanceWithArtist(festivalPlan, artist, festivalBlockview); // Verwijder alle performances met de geselecteerde artist
                    warning.close(); // Sluit de warning af
                    stage.close(); // Sluit de stage af
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
