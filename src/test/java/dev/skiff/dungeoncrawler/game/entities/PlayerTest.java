package dev.skiff.dungeoncrawler.game.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {
    @Test
    public void canTakeDamage() {
        Player testPlayer = new Player("test", 1, 10);
        assertEquals(testPlayer.health, 10);
        testPlayer.takeDamage(5);
        assertEquals(testPlayer.health, 5);
    }
}
