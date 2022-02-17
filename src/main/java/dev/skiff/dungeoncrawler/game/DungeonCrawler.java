package dev.skiff.dungeoncrawler.game;

import dev.skiff.dungeoncrawler.util.DBConnectionUtil;

import java.sql.Connection;

public class DungeonCrawler {
    public static Connection conn = DBConnectionUtil.getConnection();
    private static Controller controller = Controller.getInstance();
    public static void main(String[] args) {
        controller.printDeath();
        //runGame();
    }

    public static void runGame() {
        printTitle();
        controller.setUp();
        controller.fight();
    }

    private static void printTitle() {
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
