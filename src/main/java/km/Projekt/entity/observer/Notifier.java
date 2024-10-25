package km.Projekt.entity.observer;

public class Notifier implements Observer {
    @Override
    public void update(String message) {
        System.out.println("Notifier: Powiadomienie - " + message);
    }
}
