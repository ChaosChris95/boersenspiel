package boersenspiel.account.comparators;


import boersenspiel.account.LogEntry;

import java.util.Comparator;

public class ShareComparator implements Comparator<LogEntry> {

    /**
     * Compares two different LogEntries by ShareName
     * @param log1 first LogEntry
     * @param log2 second LogEntry
     * @see LogEntry
     */

    public int compare(LogEntry log1, LogEntry log2) {
         /*
            o1 > o2: -1
            o1 == o2: 0
            o1 < o2: 1
         */
         return log1.share.compareTo(log2.share);

    }
}
