package dev.skiff.dungeoncrawler.dao;

import dev.skiff.dungeoncrawler.model.Weapon;
import dev.skiff.dungeoncrawler.util.ArrayList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static dev.skiff.dungeoncrawler.game.DungeonCrawler.conn;

public class WeaponDAO {
    public ArrayList getAllWeapons() throws SQLException {
        ArrayList allWeapons = new ArrayList();
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("Select * From Weapons");
        while(rs.next()){
            Weapon nextWeapon = new Weapon(rs.getString("name"), rs.getInt("damage"),
                    rs.getString("weapon_type"));
            allWeapons.add(nextWeapon);
        }
        rs.close();
        return allWeapons;
    }
}
