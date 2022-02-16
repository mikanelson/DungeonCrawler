package dev.skiff.dungeoncrawler.dao;

import dev.skiff.dungeoncrawler.util.ArrayList;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LeaderboardDAOTest {
    @Test
    public void canGetAllRuns() throws SQLException {
        LeaderboardDAO testDAO = new LeaderboardDAO();
        ArrayList runList = testDAO.getLeaderboard();
        assertEquals(runList.getArrayItems(), 2);
    }

    @Test
    public void canGetTopRuns() throws SQLException {
        LeaderboardDAO testDAO = new LeaderboardDAO();
        ArrayList runList = testDAO.getLeaderboardTop(1);
        assertEquals(runList.getArrayItems(), 1);
        runList = testDAO.getLeaderboardTop(2);
        assertEquals(runList.getArrayItems(),2);
    }
}
