package dev.skiff.dungeoncrawler.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnectionUtil {
    private static Connection conn;

    public static Connection getConnection() {
        try (FileInputStream propertiesInput = new FileInputStream("C:\\Users\\mikan\\IdeaProjects" +
                "\\dungeoncrawler\\src\\main\\java\\dev\\skiff\\dungeoncrawler\\util\\db.properties")) {
            Properties props = new Properties();
            props.load(propertiesInput);

            String url = (String) props.get("url");
            String username = (String) props.get("user");
            String password = (String) props.get("pass");

            if (conn == null) {
                try {
                    conn = DriverManager.getConnection(url, username, password);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        return conn;
    }
}
