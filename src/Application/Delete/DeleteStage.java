package Application.Delete;

import Application.FestivalBlockview;
import data.FestivalPlan;
import data.Performance;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Optional;

public class DeleteStage {
    public DeleteStage(FestivalPlan festivalPlan, FestivalBlockview festivalBlockview) {
        Stage stage = new Stage(); // Maakt een nieuwe stage
        ChoiceBox<data.Stage> stages = new ChoiceBox<>(); // Maakt een nieuwe Choicebox
        stages.getItems().addAll(festivalPlan.getStages()); // Vul de choicebox met alle stages uit festivalplan

        Button deleteStage = new Button("Delete"); // Maakt een delete button
        Button cancelButton = new Button("Cancel"); // Maakt een cancel button

        HBox buttons = new HBox(); // Maakt een hbox
        buttons.getChildren().addAll(deleteStage, cancelButton); // Vul de hbox met de buttons

        VBox vBox = new VBox(); // Maakt een vbox
        vBox.getChildren().addAll(stages, buttons); // Vul de vbox met de choicebox en de buttons
        vBox.setSpacing(10); // Zet de spatie tussen de elementen

        Scene scene = new Scene(vBox); // Maakt de scene aan
        stage.setScene(scene); // Zet de scene van die stage
        stage.setHeight(500); // Zet de hoogte van de stage
        stage.setWidth(750); // Zet de breedte van de stage
        stage.show(); // Toon de stage

        //buttons
        cancelButton.setOnAction(event -> { // Als de cancel button wordt aangeklikt
            stage.close(); // Sluit de stages af
        });

        deleteStage.setOnAction(event -> { // Als de delete button wordt aangeklikt
            if(stages.getItems().isEmpty()) { // Als er geen stages zijn geselecteerd
                Alert error = new Alert(Alert.AlertType.ERROR); // Maak een error
                error.getDialogPane().setContent(new Label("Please select an stage to delete!")); // Zet de error tekst
                error.show(); // Toon de error
            } else { // Als er stages zijn geselecteerd
                Alert warning = new Alert(Alert.AlertType.WARNING); // Maak een warning
                ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE); // Maak een cancel button
                warning.getButtonTypes().addAll(cancel); // Vul de warning met de cancel button

                String stageName = stages.getSelectionModel().getSelectedItem().getName(); // Zet de stage naam
                warning.setHeaderText("Are you sure you want to delete " + stageName); // Zet de warning header tekst
                warning.setContentText("Deleting this stage will also delete every performance on it!"); // Zet de warning content tekst

                Optional<ButtonType> result = warning.showAndWait(); // Toon de warning en wacht op de gebruiker

                if(result.get() == cancel) { // Als de cancel button wordt aangeklikt
                    warning.close(); // Sluit de warning af
                } else { // Als de gebruiker de delete button aanklikt
                    data.Stage festivalStage = stages.getSelectionModel().getSelectedItem(); // Zet de stage
                    festivalPlan.deleteStage(festivalStage); // Verwijder de stage uit festivalplan
                    deletePerformancesOnStage(festivalPlan, festivalStage, festivalBlockview); // Verwijder alle performances op de stage
                    warning.close(); // Sluit de warning af
                    stage.close(); // Sluit de stage af
                }
            }
        });
    }

    private void deletePerformancesOnStage(FestivalPlan festivalPlan, data.Stage festivalStage, FestivalBlockview festivalBlockview) { // Verwijder alle performances op een stage
        ArrayList<Performance> temporaryPerformances = new ArrayList<>(festivalPlan.getPerformances()); // Maak een tijdelijke arraylist met alle performances
        for (Performance performance : temporaryPerformances) { // Loop door alle performances
            if (performance.getStage().equals(festivalStage)){ // Als de performance op de stage gelijk is aan de stage die we willen verwijderen
                festivalPlan.deletePerformance(performance); // Verwijder de performance uit festivalplan
                festivalBlockview.deleteBlock(performance); // Verwijder de performance uit de blockview
            }
        }
    }
}

