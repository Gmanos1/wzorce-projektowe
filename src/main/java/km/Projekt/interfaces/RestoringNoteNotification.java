package km.Projekt.interfaces;

import km.Projekt.entity.memento.NoteMemento;

public class RestoringNoteNotification extends AbstractRestoreNote {
    @Override
    public void restoreNoteDataNotification(Integer id, NoteMemento oldValue) {
        displayMessageOnConsole("RESTORE NOTE: " + id + ", value: " + oldValue);
    }

    private static void displayMessageOnConsole(String message) {
        System.out.println(message);
    }
}
