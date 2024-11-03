package km.Projekt.state;

//L2 - STATE - interfejs ze stanami, które będą wykorzystywane

// L3 Interface Segregation
public interface State extends IStateArchive, IStatePublish, IStateEdit {
        public String getState();
}


