package data;

import java.io.Serializable;

public class Performance implements Serializable {
    private Artist artist;
    private Stage stage;

    private int beginHour;
    private int beginMinute;
    private int endHour;
    private int endMinute;



    public Performance(Artist artist, Stage stage, int beginHour, int beginMinute, int endHour, int endMinute) {
        this.artist = artist;
        this.stage = stage;
        this.beginHour = beginHour;
        this.beginMinute = beginMinute;
        this.endHour = endHour;
        this.endMinute = endMinute;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
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
        if (beginMinute < 10){
            return (beginHour + ":0" + beginMinute);
        }
        return (beginHour + ":" + beginMinute);
    }

    public String getEndTime() {
        if (endMinute < 10){
            return (endHour + ":0" + endMinute);
        }
        return (endHour + ":" + endMinute);
    }

    public int getDuration() {
        // Geeft de duur van het optreden terug in minuten
        int totalMinutes = 0;
        if (this.beginHour > this.endHour) {
            if (this.beginMinute == this.endMinute) {
                totalMinutes += ((endHour + 24) - beginHour) * 60;
            } else {
                if (this.beginMinute == 30) {
                    totalMinutes += 30;
                    totalMinutes += (((endHour + 24) - beginHour) - 1) * 60;
                } else {
                    totalMinutes += 30;
                    totalMinutes += (((endHour + 24) - beginHour)) * 60;
                }

            }
        } else {
            if (this.beginMinute == this.endMinute) {
                totalMinutes += (endHour - beginHour) * 60;
            } else {
                if (this.beginMinute == 30) {
                    totalMinutes += 30;
                    totalMinutes += ((endHour - beginHour) - 1) * 60;
                } else {
                    totalMinutes += 30;
                    totalMinutes += ((endHour - beginHour)) * 60;
                }

            }
        }
        return totalMinutes;
    }

    @Override
    public String toString() {
        return getArtist() + " at " + stage.getName();
    }
}
