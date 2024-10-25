package km.Projekt.notesView;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;
import km.Projekt.entity.strategy.NoteStyleStrategy;
import lombok.Getter;
import lombok.Setter;

//L1 - FLYWEIGHT
@Getter
@Setter
@Embeddable
public class NoteStyle { //obiekt przechowujący współdzielone dane
    private String priority;
    private boolean active;

    @Transient
    private NoteStyleStrategy noteStyleStrategy; //przypisanie stylu notatce

    public NoteStyle(String priority, boolean active, NoteStyleStrategy noteStyleStrategy) {
        this.priority = priority;
        this.active = active;
        this.noteStyleStrategy = noteStyleStrategy;
    }

    public NoteStyle() {
    }

    @Override
    public String toString() {
        return "Notatka: priorytet: " + priority + "; aktywna: " + active;
    }

// L2 - STRATEGY
    public String applyStyle(String noteContent) {
        return noteStyleStrategy.applyStyle(noteContent);
    }
}