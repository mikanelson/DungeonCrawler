package dev.skiff.dungeoncrawler.model;

public class DungeonRun {
    private String playerName;
    private int score;
    private String date;

    public DungeonRun(String playerName, int score, String date) {
        this.playerName = playerName;
        this.score = score;
        this.date = date;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }

    public String getDate() {
        return date;
    }

    public String toString() {
        return "{Character: " + playerName + ", Score: " + score + ", Date: " + date + "}";
    }
}
