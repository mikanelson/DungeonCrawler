package dev.skiff.dungeoncrawler.game;

import dev.skiff.dungeoncrawler.dao.LeaderboardDAO;
import dev.skiff.dungeoncrawler.dao.MonsterDAO;
import dev.skiff.dungeoncrawler.dao.WeaponDAO;
import dev.skiff.dungeoncrawler.game.entities.Player;
import dev.skiff.dungeoncrawler.model.DungeonRun;
import dev.skiff.dungeoncrawler.model.Monster;
import dev.skiff.dungeoncrawler.model.Weapon;
import dev.skiff.dungeoncrawler.util.ArrayList;

import java.io.IOException;
import java.sql.SQLException;
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
        DungeonCrawler.log.info("Game over.");
        printDeath();
        LeaderboardDAO lDAO = new LeaderboardDAO();
        try {
            lDAO.addRunToLeaderboard(p.getName(), this.score);
            ArrayList topRuns = lDAO.getLeaderboardTop(10);
            System.out.println("LEADERBOARD:");
            for (int i = 0; i < topRuns.getArrayItems(); i++) {
                DungeonRun run = (DungeonRun) topRuns.get(i);
                String format = "|%1$-20s|%2$-4s|%3$-10s|\n";
                System.out.format(format, run.getPlayerName(), run.getScore(), run.getDate());
            }
            DungeonCrawler.log.info("Player added to leaderboards.");
        } catch (SQLException e) {
            e.printStackTrace();
            DungeonCrawler.log.error("Error adding user to leaderboards.", e);
        }
        System.out.println("You defeated " + score + " monsters.");
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
            DungeonCrawler.log.info("Player quit game.");
            System.exit(0);
        } else {
            this.score = 0;
            runGame();
        }
    }

    public void setUp() {
        p = new Player();
        p.setName(s);
        chooseWeapon(generateWeaponChoices(), s);
    }

    public ArrayList generateWeaponChoices() {
        DungeonCrawler.log.info("Generating weapon choices.");
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
        DungeonCrawler.log.info("Equipping " + chosen.getName() + " to player.");
        System.out.println("You equip the " + chosen.getName() + ". Your damage is now " + damage + ".");
    }

    public void fight() {
        try {
            Monster enemy = new MonsterDAO().getRandomAPIMonster();
            int enemyDamage = enemy.getDamage();
            int pDamage = p.getDamage();
            DungeonCrawler.log.info("Fight start with " + enemy.getSpecies());
            while (p.getHealth() > 0 && enemy.getHealth() > 0) {
                enemy.takeDamage(pDamage);
                p.takeDamage(enemyDamage);
            }
        } catch (IOException e) {
            e.printStackTrace();
            DungeonCrawler.log.error("Error while fighting.", e);
        }
        getRoomChoice(s);
    }

    public void getRoomChoice(Scanner s) {
        System.out.println("What would you like to do next?");
        System.out.println("1. Heal");
        System.out.println("2. Fight");
        System.out.println("3. New Weapon");
        int choice;
        do {
            while (!s.hasNextInt()) {
                s.next();
            }
            choice = s.nextInt();
        } while (choice > 3 || choice <= 0);
        DungeonCrawler.log.info("Player choice: " + choice);
        switch (choice) {
            case 1:
                p.heal(Math.max(rng.nextInt(100), 15));
                fight();
                break;
            case 2:
                fight();
                break;
            case 3:
                chooseWeapon(generateWeaponChoices(), s);
                fight();
            default:
                fight();
        }
    }

    private void printDeath() {
        System.out.println(" __     __ ____   _    _   _____  _____  ______  _____   _ \n" +
                " \\ \\   / // __ \\ | |  | | |  __ \\|_   _||  ____||  __ \\ | |\n" +
                "  \\ \\_/ /| |  | || |  | | | |  | | | |  | |__   | |  | || |\n" +
                "   \\   / | |  | || |  | | | |  | | | |  |  __|  | |  | || |\n" +
                "    | |  | |__| || |__| | | |__| |_| |_ | |____ | |__| ||_|\n" +
                "    |_|   \\____/  \\____/  |_____/|_____||______||_____/ (_)\n" +
                "                                                           \n" +
                "                                                           ");
    }
}
