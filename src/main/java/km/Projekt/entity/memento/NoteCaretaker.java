package km.Projekt.entity.memento;

import lombok.Getter;
import lombok.Setter;

import java.util.Stack;

@Getter
@Setter
public class NoteCaretaker {
    public final Stack<NoteMemento> mementoStack = new Stack<>();

    public void saveMemento(NoteMemento memento) {
        System.out.println("Saved memento: " + memento);
        mementoStack.push(memento);
    }

    public NoteMemento restoreMemento() {
        if (!mementoStack.isEmpty()) {
            System.out.println("Memento stack: " + mementoStack);
            return mementoStack.pop();
        }

        return null;
    }
}