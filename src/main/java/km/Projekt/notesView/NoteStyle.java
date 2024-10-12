package km.Projekt.notesView;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

//L1 - FLYWEIGHT
@Getter
@Setter
@Embeddable
public class NoteStyle { //obiekt przechowujący współdzielone dane
    private String priority;
    private boolean active;

    public NoteStyle(String priority, boolean active) {
        this.priority = priority;
        this.active = active;
    }

    public NoteStyle() {}

    @Override
    public String toString() {
        return "Notatka: priorytet: " + priority + "; aktywna: " + active;
    }
}
