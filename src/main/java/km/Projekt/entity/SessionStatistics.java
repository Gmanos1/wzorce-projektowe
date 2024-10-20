package km.Projekt.entity;

import lombok.Getter;

// L1 singleton statistics
@Getter
public class SessionStatistics {
    private static volatile SessionStatistics instance;
    private int numberOfLogins = 0;
    private int numberOfProfileViews = 0;
    private int numberOfAddedNotes = 0;
    private int numberOfDeletedNotes = 0;
    private int numberOfViewedNotes = 0;
    private SessionStatistics(){};

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
    public void incrementNumberOfLogins() {
        this.numberOfLogins++;
    }
    public void incrementNumberOfProfileViews() {
        this.numberOfProfileViews++;
    }
    public void incrementNumberOfAddedNotes(){
        this.numberOfAddedNotes++;
    }
    public void incrementNumberOfDeletesNotes(){
        this.numberOfDeletedNotes++;
    }
    public void incrementNumberOfViewedNotes() {
        this.numberOfViewedNotes++;
    }
}
