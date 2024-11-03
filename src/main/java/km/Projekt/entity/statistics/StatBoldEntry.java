package km.Projekt.entity.statistics;

//L2 template
public class StatBoldEntry extends  StatEntry implements IBoldEntry{
    public boolean isBold = true;

    public StatBoldEntry(String statName, int statValue) {
        super(statName, statValue);
    }
    @Override
    public String toString() {
        return super.toString() + String.format("bold: true");
    }

    // L3 Interface Segregation
    @Override
    public void setBoldProperty(boolean value) {
        this.isBold = value;
    }
}
