package km.Projekt.entity.mediator;

public interface Mediator {
    void notify(Object sender, String event); //przyjmuje nadawcę i zdarzenie; informuje o zdarzeniu
}

/*
 * Mediator.java
 * NoteMediator.java
 * Note.java - zmiana w ifie - wysyłamy info tylko do mediatora, a nie do loggera i notify
 * NoteController.java - zmiana tworzenia mediatora zamiast instancji loggera i notify
 */