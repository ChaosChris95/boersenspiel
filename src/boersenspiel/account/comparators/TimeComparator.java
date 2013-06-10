package boersenspiel.account.comparators;


import boersenspiel.account.LogEntry;
import java.util.Comparator;


public class TimeComparator implements Comparator<LogEntry> {


    /**
     * Compares two different LogEntries by TimeStamp
     * @param log1
     * @param log2
     * @see LogEntry
     */

    public int compare(LogEntry log1, LogEntry log2) {
        /*
            o1 > o2: -1
            o1 == o2: 0
            o1 < o2: 1
         */

        return log1.date.compareTo(log2.date);
    }
}
