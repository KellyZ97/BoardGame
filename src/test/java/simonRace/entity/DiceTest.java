package simonRace.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DiceTest {
    private Dice dice;

    @BeforeEach
    public void setup() {
        dice = new Dice();
    }

    @Test
    public void testRoll() {
        // Test that the roll method updates the status and die values
        dice.roll();
        assertNotNull(dice.getStatus());
        assertTrue(dice.getDieOne() > 0 && dice.getDieOne() <= 4);
    }

    @Test
    public void testGetStatus() {
        // Test that the getStatus method returns the correct status
        dice.roll();
        String status = dice.getStatus();
        assertTrue(status.equals("Forward") || status.equals("Back") || status.equals("Skip Turn"));
    }

    @Test
    public void testGetDieOne() {
        // Test that the getDieOne method returns the correct value
        dice.roll();
        int dieOne = dice.getDieOne();
        assertTrue(dieOne > 0 && dieOne <= 4);
    }
}