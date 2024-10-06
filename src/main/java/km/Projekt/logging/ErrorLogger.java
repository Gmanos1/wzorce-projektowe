package km.Projekt.logging;

// L1 - COMPOSITE - rozszerzenie klasy Logger - ErrorLogger
public class ErrorLogger extends Logger {
    private final boolean occuredError = true;

    public ErrorLogger(String message, boolean error) {
        super(message, error);
    }

    @Override
    public boolean isError() {
        return occuredError;
    }

}

