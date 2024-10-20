package km.Projekt.entity;

// L1 Factory design pattern
public class UserFactory implements EntityFactory{
    @Override
    public Entity createEntity() {
        return new User();
    }
}
