package km.Projekt.state;

//L2 - STATE - interfejs ze stanami, które będą wykorzystywane

public interface State {
        void edit(NoteWithState note);
        void publish(NoteWithState note);
        void archive(NoteWithState note);
}


