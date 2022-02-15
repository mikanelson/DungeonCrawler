package dev.skiff.dungeoncrawler.game;

import dev.skiff.dungeoncrawler.dao.LeaderboardDAO;
import dev.skiff.dungeoncrawler.dao.MonsterDAO;
import dev.skiff.dungeoncrawler.dao.WeaponDAO;
import dev.skiff.dungeoncrawler.util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class DungeonCrawler {
    public static Connection conn = DBConnectionUtil.getConnection();

    public static void main(String[] args) {
        WeaponDAO wDAO = new WeaponDAO();
        LeaderboardDAO lDAO = new LeaderboardDAO();
        MonsterDAO mDAO = new MonsterDAO();
        try {
            System.out.println(wDAO.getAllWeapons());
            System.out.println(lDAO.getLeaderboard());
            System.out.println(mDAO.getAllMonsters());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
