package km.Projekt.entity.statistics;

//L2 template
public class StatColorEntry extends StatEntry{
    public final String color;

    public StatColorEntry(String statName, int statValue, String color){
        super(statName, statValue);
        this.color = color;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", kolor: %s", color);
    }
}
