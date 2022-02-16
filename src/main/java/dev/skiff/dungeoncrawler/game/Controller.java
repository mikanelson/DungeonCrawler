package dev.skiff.dungeoncrawler.game;

import dev.skiff.dungeoncrawler.dao.MonsterDAO;
import dev.skiff.dungeoncrawler.dao.WeaponDAO;
import dev.skiff.dungeoncrawler.game.entities.Player;
import dev.skiff.dungeoncrawler.util.ArrayList;

import java.sql.SQLException;

public class Controller {
    private int score;
    private ArrayList allWeapons;
    private ArrayList allMonsters;

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    private static Controller instance;

    private Controller() {
        score = 0;
        try {
            allWeapons = new WeaponDAO().getAllWeapons();
            allMonsters = new MonsterDAO().getAllMonsters();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void gameOver() {
        System.out.println("You died.");
    }
}
