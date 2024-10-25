package km.Projekt.state;

//L2 - STATE - interfejs ze stanami, które będą wykorzystywane

public interface State {
        void edit(Note note);
        void publish(Note note);
        void archive(Note note);
}


