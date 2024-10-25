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
        if (sender instanceof Note && "publicNoteCreated".equals(event)) {
            String message = ((Note) sender).getText();

            logger.update("Logger: nowa notatka " + message);
            notifier.update("Notifier: powiadomienie - nowa notatka " + message);
        }
    }

}
