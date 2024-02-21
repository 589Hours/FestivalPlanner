package data;

import java.util.ArrayList;

public class Performance {
    private ArrayList<Artist> artists;
    private Stage stage;

    private int beginHour;
    private int beginMinute;
    private int endHour;
    private int endMinute;

    private String beginTime;
    private String endTime;


    public Performance(ArrayList<Artist> artists, Stage stage, int beginHour, int beginMinute, int endHour, int endMinute) {
        this.artists = artists;
        this.stage = stage;
        this.beginHour = beginHour;
        this.beginMinute = beginMinute;
        this.endHour = endHour;
        this.endMinute = endMinute;
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

    public int getBeginHour() {
        return beginHour;
    }

    public void setBeginHour(int beginHour) {
        this.beginHour = beginHour;
    }

    public int getBeginMinute() {
        return beginMinute;
    }

    public void setBeginMinute(int beginMinute) {
        this.beginMinute = beginMinute;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public int getEndMinute() {
        return endMinute;
    }

    public void setEndMinute(int endMinute) {
        this.endMinute = endMinute;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    public int getDuration(){
        // Geeft de duur van het optreden terug in minuten
        int totalMinutes = 0;
        if (this.beginMinute == this.endMinute){
            totalMinutes += (endHour - beginHour)*60;
        } else {
            totalMinutes += 30;
            totalMinutes += ((endHour - beginHour)-1)*60;
        }
        return totalMinutes;
    }
}
