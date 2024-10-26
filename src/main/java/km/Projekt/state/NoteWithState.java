package km.Projekt.state;

//L2 - STATE - klasa bazowa obiektu note

public class NoteWithState {
    private StateContext stateContext;

    public NoteWithState() {
        this.stateContext = new StateContext();
    }

    public void edit() {
        stateContext.edit(this);
    }

    public void publish() {
        stateContext.publish(this);
    }

    public void archive() {
        stateContext.archive(this);
    }

    public void setState(State newState) {
        this.stateContext.setState(newState);
    }
}
