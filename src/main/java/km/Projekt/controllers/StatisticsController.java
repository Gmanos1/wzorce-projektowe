package km.Projekt.controllers;

import km.Projekt.entity.statistics.SessionStatistics;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StatisticsController {
    SessionStatistics sessionStatistics = SessionStatistics.getInstance();
    @GetMapping("/statistics")
    public String statisticsPage(Model m) {
        sessionStatistics.incrementNumberOfLogins();
        m.addAttribute("statistics", SessionStatistics.getInstance());
        return "statistics";
    }
}
