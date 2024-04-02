package data;
import Application.GUI;
import javafx.scene.control.Alert;

import java.io.*;

// Klasse om het FestivalPlan object naar een bestand op te slaan
public class SaveToFile {
    private FestivalPlan festivalPlan;

    // Constructor om een nieuw SaveToFile object te maken en het FestivalPlan object op te slaan
    public SaveToFile(FestivalPlan festivalPlan){
        this.festivalPlan = festivalPlan; // Wijs het opgegeven FestivalPlan object toe aan het SaveToFile object
        saveFile(); // Roep de methode aan om het bestand op te slaan
    }

    // Methode om het FestivalPlan object naar een bestand op te slaan
    private void saveFile() {
        try {
            FileOutputStream fos = new FileOutputStream("Saves/SaveFile.ser"); // Maak een FileOutputStream om het bestand te schrijven
            ObjectOutputStream oos = new ObjectOutputStream(fos); // Maak een ObjectOutputStream om objecten naar het bestand te schrijven
            oos.writeObject(festivalPlan); // Schrijf het FestivalPlan object naar het bestand
            oos.close(); // Sluit de ObjectOutputStream
            GUI.succesPopup("File successfully saved!"); // Toon een popup om te bevestigen dat het bestand succesvol is opgeslagen
        } catch (IOException e) { // Vang IOException op
            fileNotSavedNotification(); // Roep de methode aan om een melding te tonen dat het bestand niet kon worden opgeslagen
        }
    }

    // Methode om een melding te tonen dat het bestand niet kon worden opgeslagen
    private void fileNotSavedNotification(){
        Alert alert = new Alert(Alert.AlertType.ERROR); // Maak een melding van het type ERROR
        alert.setHeaderText("File could not be saved!"); // Stel de titel van de melding in
        alert.setContentText("Something went wrong and the file could not be saved."); // Stel de inhoud van de melding in
        alert.showAndWait(); // Toon de melding en wacht tot deze wordt gesloten
    }
}
