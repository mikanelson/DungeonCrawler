package dev.skiff.dungeoncrawler.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ControllerTest {
    @Test
    public void canGetInstance() {
        Controller test = Controller.getInstance();
        Assertions.assertNotNull(test);
    }
}
