package km.Projekt.controllers;

import km.Projekt.Command.Command;
import km.Projekt.Command.ExecuteCommand;
import km.Projekt.entity.statistics.SessionStatistics;
import km.Projekt.Command.Logger;
import km.Projekt.Command.ShowMessageCommand;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StatisticsController {
    SessionStatistics sessionStatistics = SessionStatistics.getInstance();
    @GetMapping("/statistics")
    public String statisticsPage(Model m) {
        sessionStatistics.incrementNumberOfLogins();

        // Command
        Logger logger = new Logger("Order 66", false);

        // L3 Liskov
        List<Command> executeCommandOrder = new ArrayList<>();
        executeCommandOrder.add(new ShowMessageCommand(logger));
        executeCommandOrder.add(new ExecuteCommand());

        for (Command cmd : executeCommandOrder) {
            cmd.execute();
        }

        m.addAttribute("statistics", SessionStatistics.getInstance());
        return "statistics";
    }
}
