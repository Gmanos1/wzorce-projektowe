package km.Projekt.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import km.Projekt.entity.mediator.Mediator;
import km.Projekt.entity.memento.NoteMemento;
import km.Projekt.entity.observer.Observer;
import km.Projekt.notesView.NoteStyle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@jakarta.persistence.Entity
@Table(name = "Notes")
public class Note implements Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    @NotEmpty(message = "Tytuł nie może być pusty")
    public String title;
    @NotEmpty(message = "Zawartość nie może być pusta")
    public String text;
    public Boolean isPublic = false;
    public String content;
    @Embedded //wbudowany obiekt w klasie Node
    public NoteStyle noteStyle; //współdzieony styl notatki L1 - FLYWEIGHT
    @ElementCollection
    public List<String> tags; //lista tagów dla notatki
    @ManyToOne
    public User user;

    @Override
    public void displayInfo() {
        System.out.println("Jestem notatka o id " + id.toString() + ", o tytule " + title);
    }

    public Note(NoteStyle noteStyle ) {
        this.noteStyle = noteStyle;
    }

    public void displayNote() { // L1 - FLYWEIGHT, wyświetlenie notatki
        System.out.println("Notatka: " + noteStyle);
    }

    // L2 - MEMENTO
    public NoteMemento saveToMemento() {
        return new NoteMemento(text);
    }

    public void restoreFromMemento(NoteMemento memento) {
        if (memento != null) {
            this.text = memento.getContent();
        }
    }

    @Override
    public String toString() {
        return "Notatka { content = " + text + "}";
    }


    // L2 - OBSERVER
    @Transient
    private final List<Observer> observers = new ArrayList<>();

    public void addObserver(Observer observer) { //dodawanie obserwatora
        observers.add(observer);
    }

//    public void removeObserver(Observer observer) { //usuwanie obserwatora
//        observers.remove(observer);
//    }

    public void notifyOfNewNote(String newText) { // -> zmiana mediatora
        if (isPublic) {
            notifyObserversOfNewNote("Nowa notatka: " + newText);
            mediator.notify(this, "publicNoteCreated");
        }
    }

    private void notifyObserversOfNewNote(String message) { //powiadomienie obserwatorów o zmianie
        if (isPublic) {
            for (Observer observer : observers) {
                observer.update(message);
            }
        }
    }

//    public void notifyObserversOfUpdatedNote(String newText) { //zmiana treści notatki i powiadowmienie obserwatorów
//        this.text = newText;
//        notifyObserversOfNewNote("Treść notatki została zaktualizowana: " + newText);
//    }


    // L2 - MEDIATOR
    @Transient
    private Mediator mediator;
    public Note(Mediator mediator) {
        this.mediator = mediator;
    }


}

