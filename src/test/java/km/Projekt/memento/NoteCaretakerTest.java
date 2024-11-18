package km.Projekt.memento;

import km.Projekt.entity.memento.NoteCaretaker;
import km.Projekt.entity.memento.NoteMemento;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NoteCaretakerTest {

    @Test
    public void testSaveMemento() {
        NoteCaretaker caretaker = new NoteCaretaker();
        NoteMemento memento = new NoteMemento("Test content");

        caretaker.saveMemento(memento);
        assertEquals(1, caretaker.getMementoStack().size());
    }

    @Test
    public void testRestoreMemento() {
        NoteCaretaker caretaker = new NoteCaretaker();
        NoteMemento memento = new NoteMemento("Test content");

        caretaker.saveMemento(memento);
        NoteMemento restoredMemento = caretaker.restoreMemento();
        assertEquals("Test content", restoredMemento.getContent());
    }

    @Test
    public void testRestoreStateEmptyStack() {
        NoteCaretaker caretaker = new NoteCaretaker();
        assertNull(caretaker.restoreMemento());
    }

    @Test
    public void testMultipleSaves() {
        NoteCaretaker caretaker = new NoteCaretaker();

        caretaker.saveMemento(new NoteMemento("Content 1"));
        caretaker.saveMemento(new NoteMemento("Content 2"));
        assertEquals(2, caretaker.getMementoStack().size());
    }

    @Test
    public void testRestoreOrder() {
        NoteCaretaker caretaker = new NoteCaretaker();

        caretaker.saveMemento(new NoteMemento("Content 1"));
        caretaker.saveMemento(new NoteMemento("Content 2"));
        assertEquals("Content 2", caretaker.restoreMemento().getContent());
    }
}
