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

import java.util.Optional;

public class DeletePerformance {
    public DeletePerformance(FestivalPlan festivalPlan, FestivalBlockview festivalBlockview) {
        Stage stage = new Stage(); // Maakt een nieuwe stage
        ChoiceBox<Performance> performances = new ChoiceBox<>(); // Maakt een choicebox
        performances.getItems().addAll(festivalPlan.getPerformances()); // Voeg alle performances toe

        Button deleteStage = new Button("Delete"); // Maakt een delete button
        Button cancelButton = new Button("Cancel"); // Maakt een cancel button

        HBox buttons = new HBox(); // Maakt een hbox
        buttons.getChildren().addAll(deleteStage, cancelButton); // Vult de hbox met de buttons

        VBox vBox = new VBox(); // Maakt een vbox
        vBox.getChildren().addAll(performances, buttons); // Vult de vbox met de choicebox en de buttons
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

        deleteStage.setOnAction(event -> { // Als de delete button wordt aangeklikt
            if (performances.getItems().isEmpty()) { // Als er geen performances zijn geselecteerd
                Alert error = new Alert(Alert.AlertType.ERROR); // Maak een error
                error.getDialogPane().setContent(new Label("Please select a performance to delete!")); // Zet de error tekst
                error.show(); // Toon de error
            } else { // Als er performances zijn geselecteerd
                Alert warning = new Alert(Alert.AlertType.WARNING);// Maak een warning
                ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE); // Maak een cancel button
                warning.getButtonTypes().addAll(cancel); // Voeg de cancel button toe

                warning.getDialogPane().setContent(new Label("Are you sure you want to delete " + performances.getSelectionModel().getSelectedItem())); // Zet de warning tekst

                Optional<ButtonType> result = warning.showAndWait(); // Toon de warning en wacht op antwoord

                if (result.get() == cancel) { // Als de cancel button wordt aangeklikt
                    warning.close(); // Sluit de warning af
                } else { // Als de delete button wordt aangeklikt
                    festivalPlan.deletePerformance(performances.getSelectionModel().getSelectedItem()); // Verwijder de performance uit de festivalplan
                    festivalBlockview.deleteBlock(performances.getSelectionModel().getSelectedItem()); // Verwijder de performance uit de festivalblockview
                    System.out.println("deleted"); // Print deleted
                    warning.close(); // Sluit de warning
                    stage.close(); // Sluit de stages af
                }
            }
        });
    }
}
