package dev.skiff.dungeoncrawler.game.dao;

import dev.skiff.dungeoncrawler.game.dao.LeaderboardDAO;
import dev.skiff.dungeoncrawler.model.DungeonRun;
import dev.skiff.dungeoncrawler.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LeaderboardDAOTest {
    @Mock
    LeaderboardDAO mockDAO = Mockito.mock(LeaderboardDAO.class);
    ArrayList runList;

    @BeforeEach
    public void setUp() {
        runList = new ArrayList();
    }

    @Test
    public void canGetAllRuns() throws SQLException {
        ArrayList allRuns = new ArrayList();
        allRuns.add(new DungeonRun("User", 2, "2020-11-10"));
        allRuns.add(new DungeonRun("User2", 5, "2020-12-10"));
        Mockito.when(mockDAO.getLeaderboard()).thenReturn(allRuns);
        assertEquals(mockDAO.getLeaderboard().getArrayItems(), 2);
    }

    @Test
    public void canGetTopRuns() throws SQLException {
        runList.add(new DungeonRun("skiff", 1, "2020-11-11"));
        Mockito.when(mockDAO.getLeaderboardTop(1)).thenReturn(runList);
        assertEquals(runList.getArrayItems(), 1);
        runList.add(new DungeonRun("skiff", 2, "2020-12-11"));
        Mockito.when(mockDAO.getLeaderboardTop(2)).thenReturn(runList);
        runList = mockDAO.getLeaderboardTop(2);
        assertEquals(runList.getArrayItems(),2);
    }
}
