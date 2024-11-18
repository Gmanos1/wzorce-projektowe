package km.Projekt.entity.mediator;

import km.Projekt.entity.Note;
import km.Projekt.entity.observer.LoggerObserver;
import km.Projekt.entity.observer.Notifier;

public class NoteMediator implements Mediator {
    private final LoggerObserver logger;
    private final Notifier notifier;

    public NoteMediator(LoggerObserver logger, Notifier notifier) {
        this.logger = logger;
        this.notifier = notifier;
    }

    @Override
    public void notify(Object sender, String event) {
        if (event == null) {
            if (logger != null) {
                logger.update(null);
            }
            if (notifier != null) {
                notifier.update(null);
            }
            return;
        }

        if (sender instanceof Note && "publicNoteCreated".equals(event)) {
            String message = ((Note) sender).getText();
            System.out.println("Notify method invoked with event: " + event);

            if (logger != null) {
                logger.update("Logger: nowa notatka " + message);
            }

            if (notifier != null) {
                System.out.println("Notify method invoked with event: " + event);
                notifier.update("Notifier: powiadomienie - nowa notatka " + message);
            }
        } else {
            System.out.println("Warunki nie zostały spełnione.");
        }
    }

}
