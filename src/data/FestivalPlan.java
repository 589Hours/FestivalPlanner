package data;

import java.util.ArrayList;

public class FestivalPlan {
    private ArrayList<Performance> performances;
    private ArrayList<Artist> artists;
    private ArrayList<Stage> stages;

    public FestivalPlan() {
        this.performances = new ArrayList<>();
        this.artists = new ArrayList<>();
        this.stages = new ArrayList<>();
        addTestData();
    }

    private void addTestData() {
        // Artiesten
        ArrayList<Artist> artist1 = new ArrayList<>();
        artist1.add(new Artist("Ed Sheeran", 100, "Pop"));

        ArrayList<Artist> artist2 = new ArrayList<>();
        artist2.add(new Artist("Gorillaz", 125, "Indie"));

        ArrayList<Artist> artist3 = new ArrayList<>();
        artist2.add(new Artist("Suzan en Freek", 150, "Rock"));

        ArrayList<Artist> artist4 = new ArrayList<>();
        artist2.add(new Artist("Kraantje Pappie", 175, "Rap"));

        ArrayList<Artist> artist5 = new ArrayList<>();
        artist2.add(new Artist("Bizzey", 200, "Country"));

        ArrayList<Artist> artist6 = new ArrayList<>();
        artist2.add(new Artist("The Weekend", 225, "Rock"));

        ArrayList<Artist> artist7 = new ArrayList<>();
        artist2.add(new Artist("Snelle", 250, "Pop"));

        ArrayList<Artist> artist8 = new ArrayList<>();
        artist2.add(new Artist("Armin van Buuren", 275, "Metal"));


        // Podiums
        Stage stage = new Stage("Hoofdpodium");
        Stage stage2 = new Stage("Testpodium1");
        Stage stage3 = new Stage("Testpodium2");
        Stage stage4 = new Stage("Testpodium3");
        Stage stage5 = new Stage("Testpodium4");
        stages.add(stage);
        stages.add(stage2);
        stages.add(stage3);
        stages.add(stage4);
        stages.add(stage5);

        // Performances
        Performance performance = new Performance(artist1, stage, 8, 0, 9,0);
        Performance performance1 = new Performance(artist2, stage2, 10, 0, 12, 0);
        Performance performance2 = new Performance(artist3, stage, 9, 30, 11, 30);
        Performance performance3 = new Performance(artist4, stage3, 8, 30, 11, 30);
        Performance performance4 = new Performance(artist5, stage4, 9, 0, 11, 0);
        Performance performance5 = new Performance(artist6, stage5, 12, 0, 13, 0);
        Performance performance6 = new Performance(artist7, stage2, 14, 0, 16, 0);
        Performance performance7 = new Performance(artist8, stage, 16, 0, 19, 0);
        Performance performance8 = new Performance(artist1, stage3, 20, 0, 23, 0);
        Performance performance9 = new Performance(artist2, stage4, 22, 0, 2, 0);
        performances.add(performance);
        performances.add(performance1);
        performances.add(performance2);
        performances.add(performance3);
        performances.add(performance4);
        performances.add(performance5);
        performances.add(performance6);
        performances.add(performance7);
        performances.add(performance8);
        performances.add(performance9);
    }

    public ArrayList<Performance> getPerformances() {
        return performances;
    }

    public void setPerformances(ArrayList<Performance> performances) {
        this.performances = performances;
    }

    public ArrayList<Artist> getArtists() {
        return artists;
    }

    public void setArtists(ArrayList<Artist> artists) {
        this.artists = artists;
    }

    public ArrayList<Stage> getStages() {
        return stages;
    }

    public void setStages(ArrayList<Stage> stages) {
        this.stages = stages;
    }

    public void addPerformance(Performance performance){
        this.performances.add(performance);
    }
    public void addArtist(Artist artist){
        this.artists.add(artist);
    }
    public void addStage(Stage stage){
        this.stages.add(stage);
    }
}
