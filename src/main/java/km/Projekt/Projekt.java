package km.Projekt;


import km.Projekt.loggingCommand.Command;
import km.Projekt.loggingCommand.Logger;
import km.Projekt.loggingCommand.ShowMessageCommand;
import km.Projekt.state.Note;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Projekt {
	public static void main(String[] args) {
		SpringApplication.run(Projekt.class, args);

		//L2 - LOGGER - przykładowe użycie
		Logger loggerTest = new Logger("test", false);
		executeCommand(new ShowMessageCommand(loggerTest));

		//L2 - STATE - przykładowe użycie
		Note note = new Note();
		note.edit();
		note.archive();
		note.publish();
		note.archive();
	}

	private static void executeCommand(Command command) {
		command.execute();
	}
}