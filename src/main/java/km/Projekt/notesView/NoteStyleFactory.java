package km.Projekt.notesView;

import km.Projekt.entity.strategy.BasicStyle;
import km.Projekt.entity.strategy.NoteStyleStrategy;
import km.Projekt.entity.strategy.PriorityStyle;

import java.util.HashMap;
import java.util.Map;


// L1 - FLYWEIGHT
public class NoteStyleFactory { //zarządzanie współdzielonymi obiektami NodeStyle
    private static Map<String, NoteStyle> styles = new HashMap<>();

    public static NoteStyle getNoteStyle(String priority, boolean active) {
        String key = priority + active;

        if (!styles.containsKey(key)) {
            styles.put(key, createNoteStyleObject(priority, active, new BasicStyle()));
            displayMessageOnConsole("Tworzenie nowej notatki"); //tworzenie nowego obiektu notatki

            // L2 - STRATEGY
            strategyCreatingNotes();

        } else {
            displayMessageOnConsole("Taka notatka już istnieje"); //zwrocenie informacji, jezeli dana notatka juz istnieje
        }

        return styles.get(key);
    }

    private static void displayMessageOnConsole(String message) {
        System.out.println(message);
    }

    private static NoteStyle createNoteStyleObject(String styleName, boolean isActive, NoteStyleStrategy styleStrategy) {
        return new NoteStyle(styleName, isActive, styleStrategy);
    }

    private static void strategyCreatingNotes() {
        NoteStyle newBasicNote = createNoteStyleObject("Standard", true, new BasicStyle());
        NoteStyle newPriorityNote = createNoteStyleObject("Emergency", true, new PriorityStyle("Emergency"));
        displayMessages(newBasicNote, newPriorityNote);
    }

    private static void displayMessages(NoteStyle newBasicNote, NoteStyle newPriorityNote) {
        displayMessageOnConsole(newBasicNote.applyStyle("Example basic note"));
        displayMessageOnConsole(newPriorityNote.applyStyle("Example priority note"));
    }
}
