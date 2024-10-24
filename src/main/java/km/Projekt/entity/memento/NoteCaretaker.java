package km.Projekt.entity.memento;

import java.util.Stack;

public class NoteCaretaker {
    private Stack<NoteMemento> mementoStack = new Stack<>();

    public void saveState(NoteMemento memento) {
        System.out.println("SAVE STATE: " + memento);
        mementoStack.push(memento);
    }

    public NoteMemento restoreState() {
        System.out.println("RESTORE STATE: " + mementoStack);
        return mementoStack.isEmpty() ? null : mementoStack.pop();
    }
}
