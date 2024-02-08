package data;

import java.util.ArrayList;

public class Performance {
    private ArrayList<Artist> artists;
    private Stage stage;

    private int beginUur;
    private int beginMinuut;
    private int eindUur;
    private int eindMinuut;

    private String begintijd;
    private String eindtijd;


    public Performance(ArrayList<Artist> artists, Stage stage, int beginUur, int beginMinuut, int eindUur, int eindMinuut) {
        this.artists = artists;
        this.stage = stage;
        this.beginUur = beginUur;
        this.beginMinuut = beginMinuut;
        this.eindUur = eindUur;
        this.eindMinuut = eindMinuut;
    }

    public ArrayList<Artist> getArtists() {
        return artists;
    }

    public void setArtists(ArrayList<Artist> artists) {
        this.artists = artists;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public int getBeginUur() {
        return beginUur;
    }

    public void setBeginUur(int beginUur) {
        this.beginUur = beginUur;
    }

    public int getBeginMinuut() {
        return beginMinuut;
    }

    public void setBeginMinuut(int beginMinuut) {
        this.beginMinuut = beginMinuut;
    }

    public int getEindUur() {
        return eindUur;
    }

    public void setEindUur(int eindUur) {
        this.eindUur = eindUur;
    }

    public int getEindMinuut() {
        return eindMinuut;
    }

    public void setEindMinuut(int eindMinuut) {
        this.eindMinuut = eindMinuut;
    }

    public String getBegintijd() {
        return begintijd;
    }

    public void setBegintijd(String begintijd) {
        this.begintijd = begintijd;
    }

    public String getEindtijd() {
        return eindtijd;
    }

    public void setEindtijd(String eindtijd) {
        this.eindtijd = eindtijd;
    }
}
