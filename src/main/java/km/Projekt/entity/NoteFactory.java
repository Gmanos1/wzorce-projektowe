package km.Projekt.entity;

public class NoteFactory implements EntityFactory{

    @Override
    public Entity createEntity() {
        return new Note();
    }
}
