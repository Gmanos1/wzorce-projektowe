package km.Projekt.functional;

import km.Projekt.entity.Note;

@FunctionalInterface
public interface NoteMementoFunctional {
    String restoreNotification(Note mementoNote);
}
