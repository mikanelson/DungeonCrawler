package dev.skiff.dungeoncrawler.game.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlayerTest {
    Player testPlayer;

    @BeforeEach
    public void setUp() {
        testPlayer = new Player("test", 1, 10);
    }

    @Test
    public void canTakeDamage() {
        assertEquals(testPlayer.health, 10);
        testPlayer.takeDamage(5);
        assertEquals(testPlayer.health, 5);
    }

    @Test
    public void canChangeNameCorrectly() {
        assertTrue(testPlayer.getName().equalsIgnoreCase("test"));
        testPlayer.setName(new Scanner("daniel"));
        assertEquals(testPlayer.getName(), "daniel");
    }
}
