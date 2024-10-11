package km.Projekt.notesView;

import java.util.HashMap;
import java.util.Map;


// L1 - FLYWEIGHT
public class NoteStyleFactory { //zarządzanie współdzielonymi obiektami NodeStyle
    private static Map<String, NoteStyle> styles = new HashMap<>();

    public static NoteStyle getNoteStyle(String priority, boolean active) {
        String key = priority + active;

        if (!styles.containsKey(key)) {
            styles.put(key, new NoteStyle(priority, active));
            System.out.println("Tworzenie nowej notatki"); //tworzenie nowego obiektu notatki
        } else {
            System.out.println("Taka notatka już istnieje"); //zwrocenie informacji, jezeli dana notatka juz istnieje
        }

        return styles.get(key);
    }
}
