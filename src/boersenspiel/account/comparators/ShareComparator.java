package boersenspiel.account.comparators;


import boersenspiel.account.LogEntry;

import java.util.Comparator;

public class ShareComparator implements Comparator<LogEntry> {
    @Override
    public int compare(LogEntry o1, LogEntry o2) {
         /*
            o1 > o2: -1
            o1 == o2: 0
            o1 < o2: 1
         */
         return o1.share.compareTo(o2.share);

    }
}
