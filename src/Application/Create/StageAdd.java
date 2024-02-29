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
        Stage stageAdd = new Stage();

        TextField stageNameText = new TextField();
        stageNameText.setPromptText("Stage name");

        Button buttonCreate = new Button("Create");
        Button buttonCancel = new Button("Cancel");

        HBox buttons = new HBox();
        buttons.getChildren().addAll(buttonCreate, buttonCancel);

        VBox vBox = new VBox();
        vBox.setSpacing(10);


        vBox.getChildren().addAll(new Label("Input new stage name: "), stageNameText, buttons);
        Scene stageScene = new Scene(vBox);
        stageAdd.setHeight(500);
        stageAdd.setWidth(750);
        stageAdd.setScene(stageScene);
        stageAdd.show();


        //button actions
        buttonCancel.setOnAction(event -> {
            stageAdd.close();
        });

        buttonCreate.setOnAction(event ->  {
            if (!(data.getStages().size() >= 5)) {
                if (stageNameText.getText().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.getDialogPane().setContent(new Label("The textfield is empty, please enter a name."));
                    alert.show();

                } else {
                    data.addStage(new data.Stage(stageNameText.getText(), data.getStages().size() + 1));

                    Alert confirmation = new Alert(Alert.AlertType.INFORMATION);
                    confirmation.setHeaderText("Succes!");
                    confirmation.setHeaderText("The stage with the name \"" + stageNameText.getText() + "\" was added succesfully!");
                    confirmation.showAndWait();
                    stageAdd.close();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.getDialogPane().setContent(new Label("You can't have more than 5 stages"));
                alert.show();
            }
        });
    }
}
