package km.Projekt.entity.memento;

public class NoteMemento {
    private String content;

    public NoteMemento(String content) {
        this.content = content;
    }

    public String getSavedContent() {
        return content;
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