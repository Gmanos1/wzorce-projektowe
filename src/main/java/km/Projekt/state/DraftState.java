package km.Projekt.state;

//L2 - STATE - jeden ze stanów notatki

public class DraftState implements State {

    @Override
    public void edit(Note note) {
        System.out.println("Editing the draft note.");
    }

    @Override
    public void publish(Note note) {
        System.out.println("Publishing the note from draft to published state.");
        note.setState(new PublishedState());
    }

    @Override
    public void archive(Note note) {
        System.out.println("Draft cannot be archived directly.");
    }
}