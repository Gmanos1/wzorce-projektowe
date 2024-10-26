package km.Projekt.state;

//L2 - STATE - jeden ze stanów notatki

public class PublishedState implements State {

    @Override
    public void edit(NoteWithState note) {
        System.out.println("Cannot edit a published note.");
    }

    @Override
    public void publish(NoteWithState note) {
        System.out.println("The note is already published.");
    }

    @Override
    public void archive(NoteWithState note) {
        System.out.println("Archiving the published note.");
        note.setState(new ArchivedState());
    }
}