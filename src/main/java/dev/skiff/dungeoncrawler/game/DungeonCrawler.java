package dev.skiff.dungeoncrawler.game;

import dev.skiff.dungeoncrawler.dao.LeaderboardDAO;
import dev.skiff.dungeoncrawler.dao.WeaponDAO;
import dev.skiff.dungeoncrawler.util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class DungeonCrawler {
    public static Connection conn = DBConnectionUtil.getConnection();

    public static void main(String[] args) {
        WeaponDAO wDAO = new WeaponDAO();
        LeaderboardDAO lDAO = new LeaderboardDAO();
        try {
            System.out.println(wDAO.getAllWeapons());
            System.out.println(lDAO.getLeaderboard());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
