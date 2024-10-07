package km.Projekt.entity;

public class UserFactory implements EntityFactory{
    @Override
    public Entity createEntity() {
        return new User();
    }
}
