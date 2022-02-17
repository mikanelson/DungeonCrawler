package dev.skiff.dungeoncrawler.dao;

import dev.skiff.dungeoncrawler.model.Monster;
import dev.skiff.dungeoncrawler.util.ArrayList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static dev.skiff.dungeoncrawler.game.DungeonCrawler.conn;

public class MonsterDAO {
    public ArrayList getAllMonsters() throws SQLException {
        ArrayList allMonsters = new ArrayList();
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM monsters");
        while (rs.next()) {
            Monster nextMonster = new Monster(rs.getString("monster_species"), rs.getInt("damage"),
                    rs.getInt("health"));
            allMonsters.add(nextMonster);
        }
        rs.close();
        return allMonsters;
    }

    public Monster getRandomMonster() throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("SELECT TOP(1) * FROM monsters ORDER BY CHECKSUM(NEWID())");
        Monster monster = null;
        while (rs.next()) {
            monster = new Monster(rs.getString("monster_species"), rs.getInt("damage"),
                    rs.getInt("health"));
        }
        rs.close();
        return monster;
    }
}
