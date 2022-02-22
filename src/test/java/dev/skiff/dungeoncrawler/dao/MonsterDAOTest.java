package dev.skiff.dungeoncrawler.dao;

import dev.skiff.dungeoncrawler.dao.MonsterDAO;
import dev.skiff.dungeoncrawler.model.Monster;
import dev.skiff.dungeoncrawler.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MonsterDAOTest {
    @Test
    public void canGetAllMonsters() throws SQLException {
        ArrayList monsters = new ArrayList();
        monsters.add(new Monster("werebear", 10, 200));
        monsters.add(new Monster("bunny", 1, 5));
        MonsterDAO mockDao = Mockito.mock(MonsterDAO.class);
        Mockito.when(mockDao.getAllMonsters()).thenReturn(monsters);
        ArrayList monsterList = mockDao.getAllMonsters();
        assertEquals(monsterList.getArrayItems(), 2);
    }

    @Test
    public void monsterCanTakeDamage() {
        Monster testMonster = new Monster("test", 1, 10);
        testMonster.takeDamage(5);
        Assertions.assertEquals(testMonster.getHealth(), 5);
    }

    @Test
    public void monsterHealthCannotDropBelowZero() {
        Monster testMonster = new Monster("test", 1, 10);
        testMonster.takeDamage(20);
        Assertions.assertEquals(testMonster.getHealth(), 0);
    }
}
