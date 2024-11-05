package km.Projekt.interfaces;

import km.Projekt.entity.memento.NoteMemento;

public class RestoringNoteNotification extends AbstractRestoreNote {
    @Override
    public void restoreNoteDataNotification(Integer id, NoteMemento oldValue) {
        System.out.println("RESTORE NOTE: " + id + ", value: " + oldValue);
    }
}
