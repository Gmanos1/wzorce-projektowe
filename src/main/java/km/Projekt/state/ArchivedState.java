package km.Projekt.state;

//L2 - STATE - jeden ze stanów notatki

public class ArchivedState implements State {

    @Override
    public void edit(NoteWithState note) {
        displayMessageOnConsole("Cannot edit an archived note.");
    }

    @Override
    public void publish(NoteWithState note) {
        displayMessageOnConsole("Cannot publish an archived note.");
    }

    @Override
    public void archive(NoteWithState note) {
        displayMessageOnConsole("The note is already archived.");
    }

    @Override
    public String getState() {
        return "Current state is archived";
    }

    private static void displayMessageOnConsole(String message) {
        System.out.println(message);
    }
}