package dev.skiff.dungeoncrawler.game;

import dev.skiff.dungeoncrawler.game.entities.Player;

public class Controller {
    private int score;

    public static Controller getInstance() {
        if (instance == null)
            instance = new Controller();
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
    }

    public void gameOver() {
        System.out.println("You died.");
    }
}
