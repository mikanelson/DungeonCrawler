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
        ResultSet rs = statement.executeQuery("SELECT * FROM leaderboards");
        while (rs.next()) {
            DungeonRun nextRun = new DungeonRun(rs.getString("player_name"), rs.getInt("score"),
                    rs.getDate("run_date").toString());
            allRuns.add(nextRun);
        }
        rs.close();
        return allRuns;
    }

    public ArrayList getLeaderboardTop(int number) throws SQLException {
        ArrayList topList = new ArrayList();
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("SELECT TOP(" + number + ") * FROM leaderboards ORDER BY score desc");
        while (rs.next()) {
            DungeonRun nextRun = new DungeonRun(rs.getString("player_name"), rs.getInt("score"),
                    rs.getDate("run_date").toString());
            topList.add(nextRun);
        }
        rs.close();
        return topList;
    }
}
