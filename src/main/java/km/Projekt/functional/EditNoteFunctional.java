package km.Projekt.functional;

import km.Projekt.entity.Note;

@FunctionalInterface
public interface EditNoteFunctional {
    boolean editNoteSuccessful(Note originalNote, Note editedNote);
}
