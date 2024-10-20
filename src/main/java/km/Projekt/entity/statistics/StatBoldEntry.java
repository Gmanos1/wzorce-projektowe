package km.Projekt.entity.statistics;

//L2 template
public class StatBoldEntry extends  StatEntry{
    public final String isBold = "bold";

    public StatBoldEntry(String statName, int statValue) {
        super(statName, statValue);
    }
    @Override
    public String toString() {
        return super.toString() + String.format("bold: true");
    }
}
