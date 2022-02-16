package dev.skiff.dungeoncrawler.dao;

import dev.skiff.dungeoncrawler.util.ArrayList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.sql.SQLException;

public class WeaponDAOTest {
    @Test
    public void canGetAllWeapons() throws SQLException {
        WeaponDAO testDAO = new WeaponDAO();
        ArrayList weaponList = testDAO.getAllWeapons();
        assertEquals(weaponList.getArrayItems(), 2);
    }
}
