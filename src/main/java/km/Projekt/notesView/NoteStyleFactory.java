package km.Projekt.notesView;

import km.Projekt.entity.strategy.BasicStyle;
import km.Projekt.entity.strategy.PriorityStyle;

import java.util.HashMap;
import java.util.Map;


// L1 - FLYWEIGHT
public class NoteStyleFactory { //zarządzanie współdzielonymi obiektami NodeStyle
    private static Map<String, NoteStyle> styles = new HashMap<>();

    public static NoteStyle getNoteStyle(String priority, boolean active) {
        String key = priority + active;

        if (!styles.containsKey(key)) {
            styles.put(key, new NoteStyle(priority, active, new BasicStyle()));
            System.out.println("Tworzenie nowej notatki"); //tworzenie nowego obiektu notatki


            // L2 - STRATEGY
            NoteStyle newBasicNote = new NoteStyle("Standard", true, new BasicStyle());
            NoteStyle newPriorityNote = new NoteStyle("Emergency", true, new PriorityStyle("Emergency"));
            System.out.println(newBasicNote.applyStyle("Example basic note"));
            System.out.println(newPriorityNote.applyStyle("Example priority note"));

        } else {
            System.out.println("Taka notatka już istnieje"); //zwrocenie informacji, jezeli dana notatka juz istnieje
        }

        return styles.get(key);
    }
}
