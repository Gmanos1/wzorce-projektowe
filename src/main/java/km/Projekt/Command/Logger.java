package km.Projekt.Command;

//L2 - COMMAND  - klasa bazowa logu tekstowego


public class Logger {
    public String loggerMessage;
    public boolean loggerError;

    public Logger(String message, boolean error) {
        this.loggerMessage = message;
        this.loggerError = error;
    }
}
