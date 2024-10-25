package km.Projekt.entity.observer;

public class LoggerObserver implements Observer {
    @Override
    public void update(String message) {
        System.out.println("Logger: " + message);
    }
}
