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
            System.out.println(lDAO.getLeaderboardTop(1));
            System.out.println(mDAO.getAllMonsters());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("  _______                 _               __    ____         _        \n" +
                " |__   __|               | |             / _|  / __ \\       | |       \n" +
                "    | |  ___   _ __ ___  | |__     ___  | |_  | |  | | _ __ | |  __ _ \n" +
                "    | | / _ \\ | '_ ` _ \\ | '_ \\   / _ \\ |  _| | |  | || '__|| | / _` |\n" +
                "    | || (_) || | | | | || |_) | | (_) || |   | |__| || |   | || (_| |\n" +
                "    |_| \\___/ |_| |_| |_||_.__/   \\___/ |_|    \\____/ |_|   |_| \\__,_|\n" +
                "                                                                      \n" +
                "                                                                      ");
    }
}
