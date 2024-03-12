package Application.Create;

import data.FestivalPlan;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StageAdd {
    public StageAdd(FestivalPlan data) {
        Stage stageAdd = new Stage(); // Maak een stage aan

        TextField stageNameText = new TextField(); // Maak een textfield aan
        stageNameText.setPromptText("Stage name"); // Stel de prompttekst in

        Button buttonCreate = new Button("Create"); // Maak een create button aan
        Button buttonCancel = new Button("Cancel"); // Maak een cancel button aan

        HBox buttons = new HBox(); // Maak een hbox aan
        buttons.getChildren().addAll(buttonCreate, buttonCancel); // Voeg de buttons toe aan de box

        VBox vBox = new VBox(); // Maak een vbox aan
        vBox.setSpacing(10); // Zet de spatie tussen de elementen


        vBox.getChildren().addAll(new Label("Input new stage name: "), stageNameText, buttons); // Voeg de elementen toe aan de vbox
        Scene stageScene = new Scene(vBox); // Maak de scene aan
        stageAdd.setHeight(500); // Zet de hoogte van de stage
        stageAdd.setWidth(750); // zet de breedte van de stage
        stageAdd.setScene(stageScene); // Zet de scene van de stage
        stageAdd.show(); // Toon de stage


        //button actions
        buttonCancel.setOnAction(event -> { // Als de cancel button wordt aangeklikt
            stageAdd.close(); // Sluit de stage af
        });

        buttonCreate.setOnAction(event ->  { // Als de create button wordt aangeklikt
            if (!(data.getStages().size() >= 5)) { // Als er minder dan 5 stages zijn
                if (stageNameText.getText().isEmpty()) { // Als de textfield leeg is
                    Alert alert = new Alert(Alert.AlertType.WARNING); // Maak een alert aan
                     alert.getDialogPane().setContent(new Label("The textfield is empty, please enter a name.")); // Stel de tekst in
                    alert.show(); // Toon de alert

                } else { // Als de textfield niet leeg is
                    data.addStage(new data.Stage(stageNameText.getText(), data.getStages().size() + 1)); // Voeg de stage toe aan de data

                    Alert confirmation = new Alert(Alert.AlertType.INFORMATION); // Maak een alert aan
                    confirmation.setHeaderText("Succes!"); // Stel de headertekst in
                    confirmation.setHeaderText("The stage with the name \"" + stageNameText.getText() + "\" was added succesfully!"); // Stel de bodytekst in
                    confirmation.showAndWait(); // Toon de alert
                    stageAdd.close(); // Sluit de stage af
                }
            } else { // Als er meer dan 5 stages zijn
                Alert alert = new Alert(Alert.AlertType.INFORMATION); // Maak een alert aan
                alert.getDialogPane().setContent(new Label("You can't have more than 5 stages")); // Stel de tekst in
                alert.show(); // Toon de alert
            }
        });
    }
}
