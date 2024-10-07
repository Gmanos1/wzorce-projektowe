package km.Projekt.logging;

// L1 - COMPOSITE - rozszerzenie klasy Logger - MessageLogger

public class MessageLogger extends Logger {
    public String messageContext = "new message content";
    
    public MessageLogger(String message, boolean error) {
        super(message, error);
    }

    @Override
    public void logMessage(String message) {
        System.out.println("MessageLogger: " + messageContext);
    }

    @Override
    public boolean isError() {
        return false;
    }
}
