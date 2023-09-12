package simonRace.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    @Test
    public void testPlayerConstructorAndGetter() {
        Player player = new Player("John", 3, 4);
        assertEquals("John", player.getName());
        assertEquals(3, player.getPosX());
        assertEquals(4, player.getPosY());
        assertEquals(0, player.getAllStep());
    }


    @Test
    public void testSetPosX() {
        Player player = new Player("John", 3, 4);
        player.setPosX(5);
        assertEquals(5, player.getPosX());
    }

    @Test
    public void testSetPosY() {
        Player player = new Player("John", 3, 4);
        player.setPosY(2);
        assertEquals(2, player.getPosY());
    }

    @Test
    public void testGetAllStep() {
        Player player = new Player("John", 3, 4);
        assertEquals(0, player.getAllStep());
    }

    @Test
    public void testAddAllSteps() {
        Player player = new Player("John", 3, 4);
        player.addAllSteps();
        player.addAllSteps();
        player.addAllSteps();
        assertEquals(3, player.getAllStep());
    }
}
