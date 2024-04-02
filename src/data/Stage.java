package data;

import java.io.Serializable;

public class Stage implements Serializable {
    private String name;
    private int stageNumber;

    public Stage(String name, int stageNumber) {
        this.name = name;
        this.stageNumber = stageNumber;
    }

    public int getStageNumber() {
        return stageNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }


}
