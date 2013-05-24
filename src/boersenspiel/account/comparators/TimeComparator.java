package boersenspiel.account.comparators;


import boersenspiel.account.LogEntry;
import java.util.Comparator;


public class TimeComparator implements Comparator<LogEntry> {
    @Override
    public int compare(LogEntry o1, LogEntry o2) {
        /*
            o1 > o2: -1
            o1 == o2: 0
            o1 < o2: 1
         */

        return o1.date.compareTo(o2.date);
    }
}
