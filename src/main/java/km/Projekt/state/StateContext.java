package km.Projekt.state;

//L2 - STATE - klasa pomocnicza do zarządzania stanami

public class StateContext {
    private State state;

    public StateContext() {
        this.state = new DraftState(); // Initial state is Draft
    }

    public void setState(State state) {
        this.state = state;
    }

    public void edit(NoteWithState note) {
        state.edit(note);
    }

    public void publish(NoteWithState note) {
        state.publish(note);
    }

    public void archive(NoteWithState note) {
        state.archive(note);
    }
}
