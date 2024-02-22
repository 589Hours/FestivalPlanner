package data;

import java.io.Serializable;

public class Stage implements Serializable {
    private String name;

    public Stage(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
