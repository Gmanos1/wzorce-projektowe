package km.Projekt.entity.statistics;

//L2 template
public abstract class StatEntry {
    public String statName;
    public int statValue;

    public StatEntry(String statName, int statValue) {
        this.statName = statName;
        this.statValue = statValue;
    }
    @Override
    public String toString() {
        return String.format("Statystyka: %s, wartość: %d", statName, statValue);
    }
}
