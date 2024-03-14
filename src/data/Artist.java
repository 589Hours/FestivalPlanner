package data;

import java.io.Serializable;

// Klasse om een artiest te vertegenwoordigen, implementeert Serializable zodat objecten kunnen worden omgezet in een stroom van bytes voor serialisatie
public class Artist implements Serializable {
    private String name; // Naam van de artiest
    private int popularity; // Populariteit van de artiest
    private String genre; // Genre van de artiest
    private String artistInfo; // Informatie over de artiest

    // Constructor voor het maken van een artiest met opgegeven naam, populariteit en genre
    public Artist(String name, int popularity, String genre) {
        this.name = name; // Wijs de opgegeven naam toe aan de artiest
        this.popularity = popularity; // Wijs de opgegeven populariteit toe aan de artiest
        this.genre = genre; // Wijs het opgegeven genre toe aan de artiest
        this.artistInfo = ""; // Initialiseer de informatie over de artiest als leeg
    }

    // Constructor voor het maken van een artiest met opgegeven naam, populariteit, genre en artiestinformatie
    public Artist(String name, int popularity, String genre, String artistInfo){
        this.name = name; // Wijs de opgegeven naam toe aan de artiest
        this.popularity = popularity; // Wijs de opgegeven populariteit toe aan de artiest
        this.genre = genre; // Wijs het opgegeven genre toe aan de artiest
        this.artistInfo = artistInfo; // Wijs de opgegeven artiestinformatie toe aan de artiest
    }

    // Getter voor de naam van de artiest
    public String getName() {
        return name; // Geeft de naam van de artiest terug
    }

    // Setter voor de naam van de artiest
    public void setName(String name) {
        this.name = name; // Wijs de opgegeven naam toe aan de artiest
    }

    // Getter voor de populariteit van de artiest
    public int getPopularity() {
        return popularity; // Geeft de populariteit van de artiest terug
    }

    // Setter voor de populariteit van de artiest
    public void setPopularity(int popularity) {
        this.popularity = popularity; // Wijs de opgegeven populariteit toe aan de artiest
    }

    // Getter voor het genre van de artiest
    public String getGenre() {
        return genre; // Geeft het genre van de artiest terug
    }

    // Setter voor het genre van de artiest
    public void setGenre(String genre) {
        this.genre = genre; // Wijs het opgegeven genre toe aan de artiest
    }

    // Getter voor de artiestinformatie
    public String getArtistInfo() {
        return artistInfo; // Geeft de artiestinformatie terug
    }

    // Setter voor de artiestinformatie
    public void setArtistInfo(String artistInfo) {
        this.artistInfo = artistInfo; // Wijs de opgegeven artiestinformatie toe aan de artiest
    }

    // Override van de toString-methode om de naam van de artiest terug te geven als een String
    @Override
    public String toString() {
        return getName(); // Geeft de naam van de artiest terug
    }
}