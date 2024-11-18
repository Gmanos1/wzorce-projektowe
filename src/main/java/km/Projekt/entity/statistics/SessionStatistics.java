package km.Projekt.entity.statistics;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Array;
import java.util.ArrayList;

// L1 singleton statistics
// L2 statistics entry as a template

@Getter
@Setter
public class SessionStatistics {
    private static volatile SessionStatistics instance;
    public final StatColorEntry numberOfLogins = new StatColorEntry("numberOfLogins", 0, "red");
    public final StatColorEntry numberOfProfileViews = new StatColorEntry("numberOfProfileViews", 0, "green");
    public final StatColorEntry numberOfAddedNotes = new StatColorEntry("numberOfAddedNotes", 0, "blue");
    public final StatColorEntry numberOfDeletedNotes = new StatColorEntry("numberOfDeletedNotes", 0, "yellow");
    public final StatBoldEntry numberOfViewedNotes = new StatBoldEntry("numberOfViewedNotes", 0);
    public SessionStatistics(){};

    public static SessionStatistics getInstance() {
        SessionStatistics result = instance;
        if (result != null) {
            return result;
        }
        synchronized(SessionStatistics.class) {
            if (instance == null) {
                instance = new SessionStatistics();
            }
            return instance;
        }
    }

    // L3 Liskov
    public void processEntries(){
        ArrayList<StatEntry> arrayList = new ArrayList<StatEntry>();
        arrayList.add(numberOfLogins);
        arrayList.add(numberOfProfileViews);
        arrayList.add(numberOfAddedNotes);
        arrayList.add(numberOfDeletedNotes);
        arrayList.add(numberOfViewedNotes);

        for(StatEntry statEntry : arrayList) {
            System.out.println(statEntry.toString());
        }
    }
    //
    public void incrementNumberOfLogins() {
        this.numberOfLogins.statValue++;
    }
    public void incrementNumberOfProfileViews() {
        this.numberOfProfileViews.statValue++;
    }
    public void incrementNumberOfAddedNotes(){
        this.numberOfAddedNotes.statValue++;
    }
    public void incrementNumberOfDeletesNotes(){
        this.numberOfDeletedNotes.statValue++;
    }
    public void incrementNumberOfViewedNotes() {
        this.numberOfViewedNotes.statValue++;
    }

    public StatColorEntry getNumberOfLogins() {
        return this.numberOfLogins;
    }
    public StatColorEntry getNumberOfProfileViews() {
        return this.numberOfProfileViews;
    }
    public StatColorEntry getNumberOfAddedNotes(){
        return this.numberOfAddedNotes;
    }
    public StatColorEntry getNumberOfDeletedNotes(){
        return this.numberOfDeletedNotes;
    }
    public StatBoldEntry getNumberOfViewedNotes() {
        return this.numberOfViewedNotes;
    }
}
