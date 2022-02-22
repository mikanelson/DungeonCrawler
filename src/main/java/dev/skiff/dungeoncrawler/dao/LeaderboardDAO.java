package dev.skiff.dungeoncrawler.dao;

import dev.skiff.dungeoncrawler.model.DungeonRun;
import dev.skiff.dungeoncrawler.util.ArrayList;

import java.sql.*;
import java.time.LocalDateTime;

import static dev.skiff.dungeoncrawler.game.DungeonCrawler.conn;

public class LeaderboardDAO {
    public ArrayList getLeaderboard() throws SQLException {
        ArrayList allRuns = new ArrayList();
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM leaderboards");
        while (rs.next()) {
            DungeonRun nextRun = new DungeonRun(rs.getString("player_name"), rs.getInt("score"),
                    rs.getString("run_date"));
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
                    rs.getString("run_date"));
            topList.add(nextRun);
        }
        rs.close();
        return topList;
    }

    public void addRunToLeaderboard(String playerName, int score) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("INSERT INTO leaderboards (score, player_name, run_date)"
                + " VALUES (?,?,?)");
        int parameterIndex = 0;
        statement.setInt(++parameterIndex, score);
        statement.setString(++parameterIndex, playerName);
        String now = LocalDateTime.now().toString();
        statement.setString(++parameterIndex, now.substring(0,10));
        statement.executeUpdate();
    }
}
