package km.Projekt;


import km.Projekt.Command.Command;
import km.Projekt.Command.Logger;
import km.Projekt.Command.ShowMessageCommand;
import km.Projekt.state.NoteWithState;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication {
	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);

		//L2 - LOGGER - przykładowe użycie
		Logger loggerTest = new Logger("test", false);
		executeCommand(new ShowMessageCommand(loggerTest));

		//L2 - STATE - przykładowe użycie
		NoteWithState note = new NoteWithState();
		note.edit();
		note.archive();
		note.publish();
		note.archive();
	}

	private static void executeCommand(Command command) {
		command.execute();
	}
}