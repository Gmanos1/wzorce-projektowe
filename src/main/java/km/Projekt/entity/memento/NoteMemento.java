package km.Projekt.entity.memento;

public class NoteMemento {
    private final String text;

    public NoteMemento(String text) {
        this.text = text;
    }

    public String getContent() {
        System.out.println("Memento getContent: " + text);
        return text;
    }
}

/*
 * L2 - MEMENTO - zapis i przywracanie stanu obiektu
 * NoteMemento.java - przechowywanie stanu notatki
 * NoteCaretaker.java - zarządzanie stanem notatki
 * NoteController.java - editNote; undoEdit
 * Note.java - saveToMemento; restoreFromMemento
 * editnote.html - dodanie przycisku przywrócenia stanu
 * */