
package km.Projekt.loggingCommand;

//L2 - COMMAND  - klasa abstrakcyjna dla "polecenia"

public abstract class Command {

    public Logger logger;

    Command(Logger logger) {
        this.logger = logger;
    }

    public abstract void execute();
}
