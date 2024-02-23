package data;
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
