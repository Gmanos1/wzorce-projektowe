package km.Projekt;


import km.Projekt.loggingCommand.Command;
import km.Projekt.loggingCommand.Logger;
import km.Projekt.loggingCommand.ShowMessageCommand;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Projekt {
	public static void main(String[] args) {
		SpringApplication.run(Projekt.class, args);

		//L2 - LOGGER - przykładowe użycie
		Logger loggerTest = new Logger("test", false);
		executeCommand(new ShowMessageCommand(loggerTest));

	}

	private static void executeCommand(Command command) {
		command.execute();
	}
}