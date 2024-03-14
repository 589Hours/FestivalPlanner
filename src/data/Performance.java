package data;

import java.io.Serializable;

// Klasse om een optreden te vertegenwoordigen, implementeert Serializable zodat objecten kunnen worden omgezet in een stroom van bytes voor serialisatie
public class Performance implements Serializable {
    private Artist artist; // De artiest die optreedt
    private Stage stage; // Het podium waarop het optreden plaatsvindt

    private int beginHour; // Het uur waarop het optreden begint
    private int beginMinute; // De minuut waarop het optreden begint
    private int endHour; // Het uur waarop het optreden eindigt
    private int endMinute; // De minuut waarop het optreden eindigt

    // Constructor voor het maken van een optreden met opgegeven artiest, podium en begin- en eindtijd
    public Performance(Artist artist, Stage stage, int beginHour, int beginMinute, int endHour, int endMinute) {
        this.artist = artist; // Wijs de opgegeven artiest toe aan het optreden
        this.stage = stage; // Wijs het opgegeven podium toe aan het optreden
        this.beginHour = beginHour; // Wijs het opgegeven beginuur toe aan het optreden
        this.beginMinute = beginMinute; // Wijs de opgegeven beginminuut toe aan het optreden
        this.endHour = endHour; // Wijs het opgegeven einduur toe aan het optreden
        this.endMinute = endMinute; // Wijs de opgegeven eindminuut toe aan het optreden
    }

    // Getter voor de artiest van het optreden
    public Artist getArtist() {
        return artist; // Geeft de artiest van het optreden terug
    }

    // Setter voor de artiest van het optreden
    public void setArtist(Artist artist) {
        this.artist = artist; // Wijs de opgegeven artiest toe aan het optreden
    }

    // Getter voor het podium van het optreden
    public Stage getStage() {
        return stage; // Geeft het podium van het optreden terug
    }

    // Setter voor het podium van het optreden
    public void setStage(Stage stage) {
        this.stage = stage; // Wijs het opgegeven podium toe aan het optreden
    }

    // Getter voor het beginuur van het optreden
    public int getBeginHour() {
        return beginHour; // Geeft het beginuur van het optreden terug
    }

    // Setter voor het beginuur van het optreden
    public void setBeginHour(int beginHour) {
        this.beginHour = beginHour; // Wijs het opgegeven beginuur toe aan het optreden
    }

    // Getter voor de beginminuut van het optreden
    public int getBeginMinute() {
        return beginMinute; // Geeft de beginminuut van het optreden terug
    }

    // Setter voor de beginminuut van het optreden
    public void setBeginMinute(int beginMinute) {
        this.beginMinute = beginMinute; // Wijs de opgegeven beginminuut toe aan het optreden
    }

    // Getter voor het einduur van het optreden
    public int getEndHour() {
        return endHour; // Geeft het einduur van het optreden terug
    }

    // Setter voor het einduur van het optreden
    public void setEndHour(int endHour) {
        this.endHour = endHour; // Wijs het opgegeven einduur toe aan het optreden
    }

    // Getter voor de eindminuut van het optreden
    public int getEndMinute() {
        return endMinute; // Geeft de eindminuut van het optreden terug
    }

    // Setter voor de eindminuut van het optreden
    public void setEndMinute(int endMinute) {
        this.endMinute = endMinute; // Wijs de opgegeven eindminuut toe aan het optreden
    }

    // Methode om de begintijd van het optreden als een String te krijgen
    public String getBeginTime() {
        if (beginMinute < 10) {
            return (beginHour + ":0" + beginMinute); // Geeft de begintijd van het optreden terug als een String in het formaat "uu:mm"
        }
        return (beginHour + ":" + beginMinute); // Geeft de begintijd van het optreden terug als een String in het formaat "uu:mm"
    }

    // Methode om de eindtijd van het optreden als een String te krijgen
    public String getEndTime() {
        if (endMinute < 10) {
            return (endHour + ":0" + endMinute); // Geeft de eindtijd van het optreden terug als een String in het formaat "uu:mm"
        }
        return (endHour + ":" + endMinute); // Geeft de eindtijd van het optreden terug als een String in het formaat "uu:mm"
    }

    // Methode om de duur van het optreden in minuten te krijgen
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
        return totalMinutes; // Geeft de totale duur van het optreden terug in minuten
    }

    // Override van de toString-methode om een representatie van het optreden als een String terug te geven
    @Override
    public String toString() {
        return getArtist() + " at " + stage.getName();
    }
}