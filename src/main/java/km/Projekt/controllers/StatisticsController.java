package km.Projekt.controllers;

import km.Projekt.entity.statistics.SessionStatistics;
import km.Projekt.loggingCommand.Command;
import km.Projekt.loggingCommand.Logger;
import km.Projekt.loggingCommand.ShowMessageCommand;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatisticsController {
    SessionStatistics sessionStatistics = SessionStatistics.getInstance();
    @GetMapping("/statistics")
    public String statisticsPage(Model m) {
        sessionStatistics.incrementNumberOfLogins();

        // Command
        Logger logger = new Logger("przykladowa wiadomosc", false);
        ShowMessageCommand showMessageCommand = new ShowMessageCommand(logger);
        showMessageCommand.execute();
        m.addAttribute("statistics", SessionStatistics.getInstance());
        return "statistics";
    }
}
