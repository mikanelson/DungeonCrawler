package dev.skiff.dungeoncrawler.game.dao;

import dev.skiff.dungeoncrawler.model.Monster;
import dev.skiff.dungeoncrawler.util.APIRequestUtil;
import dev.skiff.dungeoncrawler.util.ArrayList;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
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

    public Monster getRandomAPIMonster() throws IOException {
        String baseURL = "https://www.dnd5eapi.co/api/monsters/";
        String request = APIRequestUtil.getAPIJson(baseURL);
        JSONObject monsterJSON = null;
        try {
            monsterJSON = APIRequestUtil.getRandomJSONObject(APIRequestUtil.parseJSONString(request));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String monsterName = "rabbit";
        int monsterDamage = 5;
        int monsterHealth = 20;
        if (monsterJSON != null) {
            request = APIRequestUtil.getAPIJson(baseURL + monsterJSON.get("index"));
            monsterName = monsterJSON.get("name") + "";
            try {
                monsterJSON = APIRequestUtil.parseJSONString(request);
                float tempDamage = Float.parseFloat("" + monsterJSON.get("challenge_rating"));
                monsterDamage = Math.max(1, Math.round(tempDamage * 0.5f));
                monsterHealth = Integer.parseInt("" + monsterJSON.get("hit_points"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return new Monster(monsterName, monsterDamage, monsterHealth);
    }
}
