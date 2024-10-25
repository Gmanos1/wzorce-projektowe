
package km.Projekt.loggingCommand;

//L2 - COMMAND  - "polecenie", które wyświetla log tekstowy

public class ShowMessageCommand extends Command {

    public ShowMessageCommand(Logger logger) {
        super(logger);
    }

    @Override
    public void execute() {
        System.out.println("Showing message: " + logger.loggerMessage);
    }
}
