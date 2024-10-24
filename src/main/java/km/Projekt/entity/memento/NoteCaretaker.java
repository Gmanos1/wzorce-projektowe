package km.Projekt.entity.memento;

import java.util.Stack;

public class NoteCaretaker {
    private Stack<NoteMemento> mementoStack = new Stack<>();

    public void saveState(NoteMemento memento) {
        mementoStack.push(memento);
    }

    public NoteMemento restoreState() {
        return mementoStack.isEmpty() ? null : mementoStack.pop();
    }
}
