package km.Projekt.logging;


// Logger do logów
// L1 - COMPOSITE - klasa, nie zawiera pod-obiektów
public class Logger implements LoggerCompositeInterface {
    public String loggerMessage;
    public boolean loggerError;

    Logger(String message, boolean error) {
        this.loggerMessage = message;
        this.loggerError = error;
    }

    @Override
    public void logMessage(String message) {
        if (this.loggerError) {
            loggerMessage = "";
        }
    }

    @Override
    public boolean isError() {
        return false;
    }

    @Override
    public String textPosition(String position) {
        return  "new position " + position;
    }
}

