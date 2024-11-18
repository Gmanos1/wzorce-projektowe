package km.Projekt.controllers;

import km.Projekt.entity.statistics.SessionStatistics;
import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StatisticsControllerTest {

    @Test
    public void testSessionStatisticsUpdate() {
        SessionStatistics statistics = new SessionStatistics();

        statistics.incrementNumberOfAddedNotes();
        assertEquals(1, statistics.getNumberOfAddedNotes().statValue);
    }

    @Test
    public void testGetStatisticsPageReturnsCorrectView() {
        StatisticsController controller = new StatisticsController();
        Model model = mock(Model.class);
        String viewName = controller.statisticsPage(model);

        assertEquals("statistics", viewName, "Zwracana wartość powinna być równa 'statistics'.");
    }

    @Test
    public void testStatisticsAddedToModel() {
        StatisticsController controller = new StatisticsController();
        Model model = mock(Model.class);

        controller.statisticsPage(model);

        verify(model, times(1)).addAttribute(eq("statistics"), any());
    }

    @Test
    public void testSessionStatisticsInitialization() {
        SessionStatistics statistics = new SessionStatistics();

        assertNotNull(statistics, "Inicjalizacja SessionStatistics");
    }

    @Test
    public void testStatisticsPageWithEmptyModel() {
        StatisticsController controller = new StatisticsController();
        Model model = mock(Model.class);

        controller.statisticsPage(model);

        verify(model, never()).addAttribute(eq("nonexistentAttribute"), any());
    }
}
