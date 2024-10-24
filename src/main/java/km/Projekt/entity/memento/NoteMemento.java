package km.Projekt.entity.memento;

public class NoteMemento {
    private final String content;
    private final String title;

    public NoteMemento(String content, String title) {
        this.content = content;
        this.title = title;
    }

    public String getSavedContent() {
        return content;
    }
}

/*
* L2 - MEMENTO - zapis i przywracanie stanu obiektu
* NoteMemento.java - przechowywanie stanu notatki
* NoteCaretaker.java - zarządzanie stanem notatki
* NoteController.java - showEditFormWithMemento; undoEdit -
* Note.java - saveToMemento; restoreFromMemento
* editnote.html - dodanie przycisku przywrócenia stanu
* */