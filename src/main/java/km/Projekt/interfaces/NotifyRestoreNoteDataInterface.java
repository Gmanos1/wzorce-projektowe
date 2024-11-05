package km.Projekt.interfaces;

import km.Projekt.entity.memento.NoteMemento;

public interface NotifyRestoreNoteDataInterface {
    void restoreNoteDataNotification(Integer id, NoteMemento oldValue);
}
