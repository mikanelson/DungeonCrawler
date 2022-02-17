package dev.skiff.dungeoncrawler.dao;

import dev.skiff.dungeoncrawler.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LeaderboardDAOTest {
    LeaderboardDAO testDAO = new LeaderboardDAO();
    ArrayList runList;

    @BeforeEach
    public void setUp() {
        runList = new ArrayList();
    }

    @Test
    public void canGetAllRuns() throws SQLException {
        runList = testDAO.getLeaderboard();
        assertEquals(runList.getArrayItems(), 2);
    }

    @Test
    public void canGetTopRuns() throws SQLException {
        runList = testDAO.getLeaderboardTop(1);
        assertEquals(runList.getArrayItems(), 1);
        runList = testDAO.getLeaderboardTop(2);
        assertEquals(runList.getArrayItems(),2);
    }
}
