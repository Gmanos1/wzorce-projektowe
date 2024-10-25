package km.Projekt.entity.observer;

public interface Observer {
    void update(String message); // implementacja klas obserwatorów
}

/*
 * Observer.java
 * Note.java - podmiot obserwowany
 * Logger.java, Notifier.java - obserwatorzy
 * NoteController.java - po dodaniu notatki wysyłane jest powiadominie
 */