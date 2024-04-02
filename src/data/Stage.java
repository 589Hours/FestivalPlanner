package data;

import java.io.Serializable;


// De klasse Stage implementeert het Serializable-interface, waardoor objecten van deze klasse kunnen worden omgezet in een stroom van bytes voor serialisatie.
public class Stage implements Serializable {
    private String name; // De naam van het podium
    private int stageNumber; // Het podiumnummer

    // Constructor voor het maken van een podium met opgegeven naam en nummer
    public Stage(String name, int stageNumber) {
        this.name = name; // Wijs de opgegeven naam toe aan het podium
        this.stageNumber = stageNumber; // Wijs het opgegeven nummer toe aan het podium
    }

    public int getStageNumber() {
        return stageNumber; // Geeft het podiumnummer terug
    }

    // Methode om de naam van het podium op te vragen
    public String getName() {
        return name; // Geeft de naam van het podium terug
    }

    // Methode om de naam van het podium in te stellen
    public void setName(String name) {
        this.name = name; // Wijs de opgegeven naam toe aan het podium
    }

    // Override van de toString-methode om de naam van het podium terug te geven bij het converteren naar een String
    @Override
    public String toString() {
        return getName(); // Geeft de naam van het podium terug
    }
}

