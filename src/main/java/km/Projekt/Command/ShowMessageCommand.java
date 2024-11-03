
package km.Projekt.Command;

//L2 - COMMAND  - "polecenie", które wyświetla log tekstowy

public class ShowMessageCommand extends Command {

    private Logger logger;
    public ShowMessageCommand(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void execute() {
        System.out.println("Showing message: " + logger.loggerMessage);
    }
}
