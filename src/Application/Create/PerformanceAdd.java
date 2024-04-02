package Application.Create;

import data.Artist;
import data.FestivalPlan;
import data.Performance;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PerformanceAdd {
    public PerformanceAdd(FestivalPlan festivalPlan) { // Hier wordt de venster aangemaakt waarin de gebruiker de performancegegevens zal invoeren.
        Stage stage = new Stage(); // Hier wordt de venster aangemaakt waarin de gebruiker de performancegegevens zal invoeren.
        stage.setTitle("Creating performance"); // Hier wordt de titel van de venster geplaatst.

        Label artistLabel = new Label("Choose the artist: "); // Label om de gebruiker te informeren over welke informatie hij moet invoeren.
        ChoiceBox<Artist> artists = new ChoiceBox<>(); // Hier worden de artiesten geplaatst.
        artists.getItems().addAll(festivalPlan.getArtists()); // Alle artiesten worden toegevoegd aan de artists.

        Label stageLabel = new Label("\nChoose the Stage: "); // Label om de gebruiker te informeren over welke informatie hij moet invoeren.
        ChoiceBox<data.Stage> stages = new ChoiceBox<>(); // Hier worden de stages geplaatst.
        stages.getItems().addAll(festivalPlan.getStages()); // ALle stages worden toegevoegd aan de stages.

        Label beginTimeLabel = new Label("\nChoose the begin time"); // Label om de gebruiker te informeren over welke informatie hij moet invoeren.
        ChoiceBox<Integer> beginHourBox = new ChoiceBox<>(); // Hier worden de begin uren geplaatst.
        beginHourBox.getItems().addAll(12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 0, 1, 2); // Alle begin uren worden toegevoegd aan de beginHourBox.
        ChoiceBox<Integer> beginMinuteBox = new ChoiceBox<>();  // Hier worden de begin minuten geplaatst.
        beginMinuteBox.getItems().addAll(00, 30); // Alle begin minuten worden toegevoegd aan de beginMinuteBox.

        HBox beginButtonsHbox = new HBox(beginHourBox, new Label(":"), beginMinuteBox); // Hier worden de begin uren en minuten geplaatst.
        VBox beginVBox = new VBox(beginTimeLabel, beginButtonsHbox); // Hier worden de begin uren en minuten geplaatst.

        Label endTimeLabel = new Label("\nChoose the end time: "); // Label om de gebruiker te informeren over welke informatie hij moet invoeren.
        ChoiceBox<Integer> endHourBox = new ChoiceBox<>(); // Hier worden de eind uren geplaatst.
        endHourBox.getItems().addAll(12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 0, 1, 2); // Alle eind uren worden toegevoegd aan de endHourBox.
        ChoiceBox<Integer> endMinuteBox = new ChoiceBox<>(); // Hier worden de eind minuten geplaatst.
        endMinuteBox.getItems().addAll(00, 30); // Hele eind minuten worden toegevoegd aan de endMinuteBox.

        HBox endButtonsHbox = new HBox(endHourBox, new Label(":"), endMinuteBox); // Hier worden de eind uren en minuten geplaatst.
        VBox endVBox = new VBox(endTimeLabel, endButtonsHbox); // Hier worden de eind uren en minuten geplaatst.

        HBox timesHBox = new HBox(beginVBox, endVBox); // Hier worden de begin en eind uren geplaatst.
        timesHBox.setSpacing(40); // De tussenruimte tussen de begin en eind uren wordt aangemaakt.

        Button createButton = new Button("Create"); // Hier wordt de knop geplaatst waarmee de gebruiker de performance kan maken.
        Button cancelButton = new Button("Cancel"); // Hier wordt de knop geplaatst waarmee de gebruiker de performance kan annuleren.

        HBox buttonHbox = new HBox(createButton, cancelButton); // Hier worden de knoppen geplaatst.
        buttonHbox.setSpacing(10); // De tussenruimte tussen de knoppen wordt aangemaakt.

        VBox totalVBox = new VBox(artistLabel, artists, stageLabel, stages, timesHBox, new Label("\n\n"), buttonHbox); // Hier worden de labels, de artiesten, de stages, de begin en eind uren, de knoppen geplaatst.

        Scene scene = new Scene(totalVBox); // Hier wordt de scene aangemaakt
        stage.setScene(scene); // Hier wordt de scene toegevoegd aan de venster
        stage.setHeight(500); // Hier wordt de hoogte van de venster gedefineerd
        stage.setWidth(750); // Hier wordt de breedte van de venster gedefineerd
        stage.show(); // Hier wordt de venster getoond

        createButton.setOnAction(event -> { // Hier wordt de actie gedefinieerd wanneer de knop create wordt geklikt.
            Object beginHour = beginHourBox.getSelectionModel().getSelectedItem(); // Hier wordt de begin uur gedefineerd.
            Object beginMinute = beginMinuteBox.getSelectionModel().getSelectedItem(); // Hier wordt de begin minuut gedefineerd.
            Object endHour = endHourBox.getSelectionModel().getSelectedItem(); // Hier wordt de eind uur gedefineerd.
            Object endMinute = endMinuteBox.getSelectionModel().getSelectedItem(); // Hier wordt de eind minuut gedefineerd.
            Artist artist = artists.getSelectionModel().getSelectedItem(); // Hier wordt de artiest gedefineerd.
            data.Stage stage1 = stages.getSelectionModel().getSelectedItem(); // Hier wordt de stage gedefineerd.

            if (artist != null || stage1 != null || beginHour != null || beginMinute != null || endHour != null || endMinute != null) { // Hier wordt gecheckt of er een waarde is ingevuld.
                if (endHourBox.getSelectionModel().getSelectedItem() < beginHourBox.getSelectionModel().getSelectedItem() || beginHour == endHour) { // Hier wordt gecheckt of de eind uur groter is dan de begin uur of de begin en eind uur gelijk zijn.
                    if (endMinuteBox.getSelectionModel().getSelectedItem() < beginMinuteBox.getSelectionModel().getSelectedItem()) { //
                        festivalPlan.addPerformance(new Performance(artists.getSelectionModel().getSelectedItem(), stages.getSelectionModel().getSelectedItem(), endHourBox.getSelectionModel().getSelectedItem(), endMinuteBox.getSelectionModel().getSelectedItem(), beginHourBox.getSelectionModel().getSelectedItem(), beginMinuteBox.getSelectionModel().getSelectedItem())); // Hier wordt de performance gemaakt.
                    } else {
                        festivalPlan.addPerformance(new Performance(artists.getSelectionModel().getSelectedItem(), stages.getSelectionModel().getSelectedItem(), beginHourBox.getSelectionModel().getSelectedItem(), beginMinuteBox.getSelectionModel().getSelectedItem(), endHourBox.getSelectionModel().getSelectedItem(), endMinuteBox.getSelectionModel().getSelectedItem())); // Hier wordt de performance gemaakt.
                    }
                } else {
                    festivalPlan.addPerformance(new Performance(artists.getSelectionModel().getSelectedItem(), stages.getSelectionModel().getSelectedItem(), beginHourBox.getSelectionModel().getSelectedItem(), beginMinuteBox.getSelectionModel().getSelectedItem(), endHourBox.getSelectionModel().getSelectedItem(), endMinuteBox.getSelectionModel().getSelectedItem()));// Hier wordt de performance gemaakt.
                }

                Alert confirmation = new Alert(Alert.AlertType.INFORMATION); // Hier wordt een alert gemaakt.
                confirmation.setHeaderText("Succes!"); // Hier wordt de header gedefinieerd
                confirmation.setContentText("The performance was succesfully created!"); // Hier wordt de tekst gedefinieerd
                confirmation.showAndWait(); // Hier wordt de alert getoond.
                stage.close(); // Hier wordt de venster gesloten.
            } else {
                Alert error = new Alert(Alert.AlertType.ERROR); // Hier wordt een alert gemaakt.
                error.getDialogPane().setContent(new Label("Please select a option in every box!")); // Hier wordt de tekst gedefinieerd.

                error.show(); // Hier wordt de alert getoond.
            }

        });

        cancelButton.setOnAction(event -> { // Hier wordt de actie gedefinieerd wanneer de knop cancel wordt geklikt.
            stage.close(); // Hier wordt de venster gesloten.
        });
    }
}
