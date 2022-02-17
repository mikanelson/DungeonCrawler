package dev.skiff.dungeoncrawler.game;

import dev.skiff.dungeoncrawler.dao.MonsterDAO;
import dev.skiff.dungeoncrawler.dao.WeaponDAO;
import dev.skiff.dungeoncrawler.game.entities.Player;
import dev.skiff.dungeoncrawler.model.Monster;
import dev.skiff.dungeoncrawler.model.Weapon;
import dev.skiff.dungeoncrawler.util.ArrayList;

import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class Controller {
    private int score;
    private ArrayList allWeapons;
    private ArrayList allMonsters;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Player getPlayer() {
        return this.p;
    }

    public void setPlayer(Player p) {
        this.p = p;
    }

    private Controller() {
        score = 0;
        s = new Scanner(System.in);
        p = new Player();
        rng = new Random();
        try {
            allWeapons = new WeaponDAO().getAllWeapons();
            allMonsters = new MonsterDAO().getAllMonsters();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void gameOver() {
        //TODO Update this to look nicer and have more functionality
        System.out.println("You died.");
    }

    public void setUp() {
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
        Monster enemy = (Monster) allMonsters.get(new Random().nextInt(allMonsters.getArrayItems()));
        int enemyDamage = enemy.getDamage();
        int pDamage = p.getDamage();
        while (p.getHealth() > 0 && enemy.getHealth() > 0) {
            enemy.takeDamage(pDamage);
            p.takeDamage(enemyDamage);
        }
    }
}
