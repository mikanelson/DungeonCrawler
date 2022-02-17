package dev.skiff.dungeoncrawler.game;

import dev.skiff.dungeoncrawler.dao.LeaderboardDAO;
import dev.skiff.dungeoncrawler.dao.MonsterDAO;
import dev.skiff.dungeoncrawler.dao.WeaponDAO;
import dev.skiff.dungeoncrawler.game.entities.Player;
import dev.skiff.dungeoncrawler.model.Monster;
import dev.skiff.dungeoncrawler.model.Weapon;
import dev.skiff.dungeoncrawler.util.ArrayList;

import java.sql.SQLException;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

import static dev.skiff.dungeoncrawler.game.DungeonCrawler.runGame;

public class Controller {
    private int score;
    private ArrayList allWeapons;
    private Player p;
    private static Controller instance;
    private Scanner s;
    Random rng;

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public void incrementScore() {
        this.score++;
    }

    private Controller() {
        score = 0;
        s = new Scanner(System.in);
        p = new Player();
        rng = new Random();
        try {
            allWeapons = new WeaponDAO().getAllWeapons();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void gameOver() {
        //TODO Update this to look nicer
        System.out.println("You died.");
        LeaderboardDAO lDAO = new LeaderboardDAO();
        try {
            lDAO.addRunToLeaderboard(p.getName(), this.score);
            ArrayList topRuns = lDAO.getLeaderboardTop(10);
            System.out.println("LEADERBOARD:");
            for (int i = 0; i < topRuns.getArrayItems(); i++) {
                System.out.println(topRuns.get(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Play again?");
        ArrayList validChoices = new ArrayList();
        validChoices.add("y");
        validChoices.add("yes");
        validChoices.add("no");
        validChoices.add("n");
        String choice;
        do {
            choice = s.next().toLowerCase();
        } while (!validChoices.contains(choice));
        if (choice.equals("n") || choice.equals("no")) {
            System.out.println("Thank you for playing.");
            System.exit(0);
        } else {
            runGame();
        }
    }

    public void setUp() {
        p = new Player();
        p.setName(s);
        chooseWeapon(generateWeaponChoices(), s);
    }

    public ArrayList generateWeaponChoices() {
        int wLength = allWeapons.getArrayItems();
        ArrayList temp = new ArrayList();
        while (temp.getArrayItems() < 3) {
            Weapon w = (Weapon)allWeapons.get(rng.nextInt(wLength));
            if (!temp.contains(w)) {
                temp.add(w);
            }
        }
        return temp;
    }

    public void chooseWeapon(ArrayList weapons, Scanner s) {
        System.out.println("Which weapon would you like?");
        int totalWeapons = weapons.getArrayItems();
        for (int i = 0; i < totalWeapons; i++) {
            System.out.println((i + 1) + ": " + weapons.get(i));
        }
        int choice;
        do {
            while (!s.hasNextInt()) {
                s.next();
            }
            choice = s.nextInt();
        } while (choice > totalWeapons || choice <= 0);
        Weapon chosen = (Weapon) weapons.get(choice - 1);
        int damage = chosen.getDamage();
        p.setDamage(damage);
        System.out.println("You equip the " + chosen.getName() + ". Your damage is now " + damage + ".");
    }

    public void fight() {
        try {
            Monster enemy = new MonsterDAO().getRandomMonster();
            int enemyDamage = enemy.getDamage();
            int pDamage = p.getDamage();
            while (p.getHealth() > 0 && enemy.getHealth() > 0) {
                enemy.takeDamage(pDamage);
                p.takeDamage(enemyDamage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
