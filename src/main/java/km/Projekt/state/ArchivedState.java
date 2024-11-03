package km.Projekt.state;

//L2 - STATE - jeden ze stanów notatki

public class ArchivedState implements State {

    @Override
    public void edit(NoteWithState note) {
        System.out.println("Cannot edit an archived note.");
    }

    @Override
    public void publish(NoteWithState note) {
        System.out.println("Cannot publish an archived note.");
    }

    @Override
    public void archive(NoteWithState note) {
        System.out.println("The note is already archived.");
    }

    @Override
    public String getState() {
        return "Current state is archived";
    }
}