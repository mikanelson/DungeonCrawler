package dev.skiff.dungeoncrawler.dao;

import dev.skiff.dungeoncrawler.model.DungeonRun;
import dev.skiff.dungeoncrawler.util.ArrayList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static dev.skiff.dungeoncrawler.game.DungeonCrawler.conn;

public class LeaderboardDAO {
    public ArrayList getLeaderboard() throws SQLException {
        ArrayList allRuns = new ArrayList();
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("Select * From Weapons");
        while(rs.next()){
            DungeonRun nextRun = new DungeonRun(rs.getString("name"), rs.getInt("damage"),
                    rs.getString("weapon_type"));
            allRuns.add(nextRun);
        }
        rs.close();
        return allRuns;
    }
}
