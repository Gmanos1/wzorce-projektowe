package km.Projekt.state;

//L2 - STATE - jeden ze stanów notatki

public class DraftState implements State {

    @Override
    public void edit(NoteWithState note) {
        System.out.println("Editing the draft note.");
    }

    @Override
    public void publish(NoteWithState note) {
        displayMessageOnConsole("Publishing the note from draft to published state.");
        publishSetState(note);
    }

    private static void publishSetState(NoteWithState note) {
        note.setState(new PublishedState());
    }

    @Override
    public void archive(NoteWithState note) {
        displayMessageOnConsole("Draft cannot be archived directly.");
    }
    @Override
    public String getState() {
        return "Current state is draft";
    }

    private static void displayMessageOnConsole(String message) {
        System.out.println(message);
    }
}