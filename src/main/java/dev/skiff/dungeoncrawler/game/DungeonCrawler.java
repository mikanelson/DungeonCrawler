package dev.skiff.dungeoncrawler.game;

import dev.skiff.dungeoncrawler.dao.WeaponDAO;
import dev.skiff.dungeoncrawler.util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class DungeonCrawler {
    public static Connection conn = DBConnectionUtil.getConnection();

    public static void main(String[] args) {
        WeaponDAO wDAO = new WeaponDAO();
        try {
            System.out.println(wDAO.getAllWeapons());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
