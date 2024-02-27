package data;
import Application.GUI;
import javafx.scene.control.Alert;

import java.io.*;

public class SaveToFile {
    private FestivalPlan festivalPlan;
    private File agendaFile;

    public SaveToFile(FestivalPlan festivalPlan){
        this.festivalPlan = festivalPlan;
        saveFile();
    }

    private void saveFile() {
        try {
            FileOutputStream fos = new FileOutputStream("Saves/SaveFile.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(festivalPlan);
            oos.close();
            GUI.succesPopup("File successfully saved!");
        } catch (IOException e) {
            fileNotSavedNotification();
        }
    }
    private void fileNotSavedNotification(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("File could not be saved!");
        alert.setContentText("Something went wrong and the file could not be saved.");
        alert.showAndWait();
    }
}
