package km.Projekt.state;

//L2 - STATE - jeden ze stanów notatki

public class DraftState implements State {

    @Override
    public void edit(NoteWithState note) {
        System.out.println("Editing the draft note.");
    }

    @Override
    public void publish(NoteWithState note) {
        System.out.println("Publishing the note from draft to published state.");
        note.setState(new PublishedState());
    }

    @Override
    public void archive(NoteWithState note) {
        System.out.println("Draft cannot be archived directly.");
    }
    @Override
    public String getState() {
        return "Current state is draft";
    }
}