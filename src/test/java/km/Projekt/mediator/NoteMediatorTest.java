package km.Projekt.mediator;

import km.Projekt.entity.Note;
import km.Projekt.entity.mediator.NoteMediator;
import km.Projekt.entity.observer.LoggerObserver;
import km.Projekt.entity.observer.Notifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class NoteMediatorTest {

    @Test
    public void testNotifyLogsEvent() {
        LoggerObserver logger = mock(LoggerObserver.class);
        Notifier notifier = mock(Notifier.class);
        NoteMediator mediator = new NoteMediator(logger, notifier);
        Note note = new Note();
        note.setText("test");

        mediator.notify(note, "publicNoteCreated");

        verify(logger).update("Logger: nowa notatka test");
    }

    @Test
    public void testNotifySendsNotification() {
        LoggerObserver logger = mock(LoggerObserver.class);
        Notifier notifier = mock(Notifier.class);
        NoteMediator mediator = new NoteMediator(logger, notifier);
        Note note = new Note();
        note.setText("test");

        mediator.notify(note, "publicNoteCreated");

        verify(notifier).update("Notifier: powiadomienie - nowa notatka test");
    }

    @Test
    public void testMediatorInitialization() {
        LoggerObserver logger = mock(LoggerObserver.class);
        Notifier notifier = mock(Notifier.class);
        NoteMediator mediator = new NoteMediator(logger, notifier);

        assertNotNull(mediator, "Inicjalizacja mediatora nieprawidłowa");
    }

    @Test
    public void testNotifyCalledOnce() {
        LoggerObserver logger = mock(LoggerObserver.class);
        Notifier notifier = mock(Notifier.class);
        NoteMediator mediator = new NoteMediator(logger, notifier);
        Note note = new Note();
        note.setText("test");

        mediator.notify(note, "publicNoteCreated");

        verify(logger).update("Logger: nowa notatka test");
        verify(notifier).update("Notifier: powiadomienie - nowa notatka test");
    }

    @Test
    public void testNotifyWithNullEvent() {
        LoggerObserver logger = mock(LoggerObserver.class);
        Notifier notifier = mock(Notifier.class);
        NoteMediator mediator = new NoteMediator(logger, notifier);
        Note note = new Note();
        note.setText("test");

        mediator.notify(note, null);

        verify(logger).update(null);
        verify(notifier).update(null);
    }

}
