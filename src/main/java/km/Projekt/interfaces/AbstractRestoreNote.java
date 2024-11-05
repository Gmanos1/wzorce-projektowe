package km.Projekt.interfaces;

import km.Projekt.entity.memento.NoteMemento;

public abstract class AbstractRestoreNote implements NotifyRestoreNoteDataInterface {
    public abstract void restoreNoteDataNotification(Integer id, NoteMemento oldValue);
}
