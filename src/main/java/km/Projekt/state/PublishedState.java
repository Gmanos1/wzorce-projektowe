package km.Projekt.state;

//L2 - STATE - jeden ze stanów notatki

public class PublishedState implements State {

    @Override
    public void edit(NoteWithState note) {
        displayMessageOnConsole("Cannot edit a published note.");
    }

    @Override
    public void publish(NoteWithState note) {
        displayMessageOnConsole("The note is already published.");
    }

    @Override
    public void archive(NoteWithState note) {
        displayMessageOnConsole("Archiving the published note.");
        archiveSetState(note);
    }
    private static void archiveSetState(NoteWithState note) {
        note.setState(new ArchivedState());
    }

    @Override
    public String getState() {
        return "Current state is published";
    }

    private static void displayMessageOnConsole(String message) {
        System.out.println(message);
    }
}