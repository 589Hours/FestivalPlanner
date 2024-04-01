package Application.Simulation;

import data.Performance;

import java.util.Comparator;

public class CustomHourComparator implements Comparator<Performance> {

    @Override
    public int compare(Performance p1, Performance p2) {
        int byHour = Integer.compare(p1.getBeginHour(), p2.getBeginHour());
        if(byHour == 0) {
            return Integer.compare(p1.getBeginMinute(), p2.getBeginMinute());
        }
        return byHour;
    }
}
