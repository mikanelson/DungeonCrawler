package dev.skiff.dungeoncrawler.dao;

import dev.skiff.dungeoncrawler.util.ArrayList;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MonsterDAOTest {
    @Test
    public void canGetAllMonsters() throws SQLException {
        MonsterDAO testDAO = new MonsterDAO();
        ArrayList monsterList = testDAO.getAllMonsters();
        assertEquals(monsterList.getArrayItems(), 2);
    }
}
