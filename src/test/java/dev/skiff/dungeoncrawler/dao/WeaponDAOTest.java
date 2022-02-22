package dev.skiff.dungeoncrawler.dao;

import dev.skiff.dungeoncrawler.dao.WeaponDAO;
import dev.skiff.dungeoncrawler.model.Weapon;
import dev.skiff.dungeoncrawler.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.sql.SQLException;

public class WeaponDAOTest {
    @Test
    public void canGetAllWeapons() throws SQLException {
        WeaponDAO mockDAO = Mockito.mock(WeaponDAO.class);
        ArrayList weaponList = new ArrayList();
        weaponList.add(new Weapon("Knife", 10, "Knife"));
        weaponList.add(new Weapon("Sword", 10, "Sword"));
        weaponList.add(new Weapon("Staff", 10, "Staff"));
        Mockito.when(mockDAO.getAllWeapons()).thenReturn(weaponList);
        assertEquals(mockDAO.getAllWeapons().getArrayItems(), 3);
    }
}
