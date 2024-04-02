package Application.Edit;

import data.FestivalPlan;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class EditStage {
    public EditStage(FestivalPlan festivalPlan) {
        Stage stage = new Stage();// maakt een nieuwe stage
        ChoiceBox<data.Stage> stages = new ChoiceBox<>(); // Maakt een nieuwe choicebox
        stages.getItems().addAll(festivalPlan.getStages()); // Vult de choicebox met alle stages uit festivalPlan

        Button editStage = new Button("Edit"); // Maakt een edit button
        Button cancelButton = new Button("Cancel"); // Maakt een cancel button

        HBox buttons = new HBox(); // Maakt een hbox
        buttons.getChildren().addAll(editStage, cancelButton); // Vult de hbox met de buttons

        VBox vBox = new VBox(); // Maakt een vbox
        vBox.getChildren().addAll(stages, buttons); // Vult de vbox met de choicebox en de buttons
        vBox.setSpacing(10); // Zet de spatie tussen de elementen

        Scene scene = new Scene(vBox); // Maakt de scene aan
        stage.setScene(scene); // Zet de scene van de stage
        stage.setHeight(500); // Zet de hoogte van de stage
        stage.setWidth(750); // Zet de breedte van de stage
        stage.show(); // Toont de stage

        //buttons
        cancelButton.setOnAction(event -> { // Als de cancel button wordt aangeklikt
            stage.close(); // Sluit de stage
        });

        editStage.setOnAction(event -> { // Handel de actie af wanneer op de bewerkingsknop wordt geklikt
            if (stages.getItems().isEmpty()) { // Controleer of de lijst met podia leeg is
                Alert error = new Alert(Alert.AlertType.ERROR); // Toon een foutmelding
                error.getDialogPane().setContent(new Label("Selecteer alstublieft een podium om te bewerken!")); // Stel de foutmelding in
                error.show(); // Toon de foutmelding
            } else { // Als de lijst met podia niet leeg is
                stage.close(); // Sluit het huidige venster
                Stage artistStage = new Stage(); // Creëer een nieuw venster voor het bewerken van podia
                TextField stageName = new TextField(); // Maak een tekstveld voor de podiumnaam

                Label stageNameLabel = new Label("Voer alstublieft de nieuwe podiumnaam in."); // Label voor het tekstveld

                Button editButton = new Button("Bewerk"); // Knop voor bewerken
                Button cancelButtonPopup = new Button("Annuleren"); // Knop voor annuleren

                HBox buttonsBox = new HBox(); // Horizontale box voor knoppen
                buttonsBox.setSpacing(10); // spatie tussen knoppen
                buttonsBox.getChildren().addAll(editButton, cancelButtonPopup); // Voeg knoppen toe aan de box

                VBox artistAdd = new VBox(); // Verticale box voor de interface
                artistAdd.setSpacing(10); // spatie tussen elementen
                artistAdd.getChildren().addAll(stageNameLabel, stageName, buttonsBox); // Voeg label, tekstveld en knoppen toe

                cancelButtonPopup.setOnAction(event1 -> { // Handel de actie af wanneer op de annuleringsknop wordt geklikt
                    artistStage.close(); // Sluit het venster
                });

                editButton.setOnAction(event1 -> { // Handel de actie af wanneer op de bewerkingsknop wordt geklikt
                    if (stageName.getText().isEmpty()) { // Controleer of het tekstveld leeg is
                        Alert alert = new Alert(Alert.AlertType.WARNING); // Toon een waarschuwing
                        alert.getDialogPane().setContent(new Label("Het tekstveld is leeg, voer alstublieft een naam in.")); // Stel de waarschuwing in
                        alert.show(); // Toon de waarschuwing
                    } else { // Als het tekstveld niet leeg is
                        stages.getSelectionModel().getSelectedItem().setName(stageName.getText()); // Bewerk de geselecteerde podiumnaam

                        Alert confirmation = new Alert(Alert.AlertType.INFORMATION); // Toon een bevestiging
                        confirmation.setHeaderText("Succes!"); // Stel de kop van de bevestiging in
                        confirmation.setContentText("Het podium is succesvol bewerkt!"); // Stel de inhoud van de bevestiging in
                        confirmation.showAndWait(); // Toon de bevestiging en wacht tot deze wordt gesloten
                        artistStage.close(); // Sluit het venster voor het bewerken van podia
                    }
                });

                Scene stageScene = new Scene(artistAdd); // Maak een scene voor het venster
                artistStage.setScene(stageScene); // Stel de scene in voor het venster
                artistStage.setWidth(750); // Stel de breedte van het venster in
                artistStage.setHeight(500); // Stel de hoogte van het venster in
                artistStage.setTitle("Artiest"); // Stel de titel van het venster in
                artistStage.show(); // Toon het venster
            }
        });
    }
}
