package simonRace.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void isObstacaledTest() {
        // Set up test players and game
        Player p1 = new Player("Player 1",2,4);
        Player p2 = new Player("Player 2",1,4);
        Game game = new Game(p1,p2);

        // Test meet fence
        String direction = "Forward";
        Boolean expectedResult = true; // because there is a fence at (1,4)
        Boolean actualResult = game.isObstacaled(p1, direction);
        assertEquals(expectedResult, actualResult);

        // Test meet onther player
        direction = "Back";
        expectedResult = true; // because there is no obstacle at (3,4)
        actualResult = game.isObstacaled(p2, direction);
        assertEquals(expectedResult, actualResult);

        // Test meet boarder and Left
        Player p3 = new Player("Player 3",5,0);
        Game game2 = new Game(p1,p3);
        direction = "L";
        expectedResult = true; // because there is no boarder
        actualResult = game2.isObstacaled(p3, direction);
        assertEquals(expectedResult, actualResult);

        // Test moving right and meet fire
        Player p4 = new Player("Player 4",3,2);
        Game game3 = new Game(p1,p4);
        direction = "R";
        expectedResult = true; // because there is fire player at (3,3)
        actualResult = game.isObstacaled(p4, direction);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void moveStartAreaTest(){
        Player p1 = new Player("Player 1",5,3);
        Player p2 = new Player("Player 2",3,0);
        Game game = new Game(p1,p2);

        Boolean expectedResult = true; // because there is fire player at (3,3)
        Boolean actualResult = game.moveStartArea(p2);

        assertEquals(expectedResult, actualResult);
        assertTrue(p2.getPosX() == 5 && p2.getPosY() < 5 && p2.getPosY() >= 0);

        p1.setPosX(1);
        actualResult = game.moveStartArea(p1);
        assertEquals(expectedResult,actualResult);
        assertTrue(p1.getPosX() == 5 && p1.getPosY() < 5 && p1.getPosY() >= 0);
    }

    @Test
    void moveTest() {
        Player p1 = new Player("p1",5,0);
        Player p2 = new Player("p2",4,2);
        Game game = new Game(p1,p2);


        String direction = "Forward";
        //check if player meet hole
        String expect = "Drop hole";
        String actual = game.move(p1,3,direction);
        assertEquals(expect, actual);

        //check if a player win
        p1.setPosY(1);
        expect = "p1 Win!";
        actual = game.move(p1,4,direction);
        assertEquals(expect, actual);

        //check move position
        p1.setPosY(1);
        p1.setPosX(5);
        expect = "3, 1";
        actual = game.move(p1,2, direction);
        assertEquals(expect, actual);

        direction = "L";
        //check move position
        p1.setPosY(2);
        p1.setPosX(5);
        expect = "5, 0";
        actual = game.move(p1,2, direction);
        assertEquals(expect, actual);

        //check reach board
        p1.setPosY(2);
        p1.setPosX(5);
        expect = "Obstacle 5, 0";
        actual = game.move(p1,3, direction);
        assertEquals(expect, actual);

        direction = "Back";
        //check reach board
        p1.setPosY(2);
        p1.setPosX(5);
        expect = "Obstacle 5, 2";
        actual = game.move(p1,3, direction);
        assertEquals(expect, actual);

        //check reach board
        p1.setPosY(1);
        p1.setPosX(1);
        expect = "4, 1";
        actual = game.move(p1,3, direction);
        assertEquals(expect, actual);

        direction = "R";
        //check move
        p1.setPosY(2);
        p1.setPosX(5);
        expect = "5, 3";
        actual = game.move(p1,1, direction);
        assertEquals(expect, actual);

        //check reach board
        p1.setPosY(4);
        p1.setPosX(1);
        expect = "Obstacle 1, 5";
        actual = game.move(p1,3, direction);
        assertEquals(expect, actual);

        direction = "Skip Turn";
        p1.setPosY(4);
        p1.setPosX(1);
        expect = "1, 4";
        actual = game.move(p1,3, direction);
        assertEquals(expect, actual);

        p1.setPosY(5);
        p1.setPosX(5);
        expect = "5, 5";
        actual = game.move(p1,3, direction);
        assertEquals(expect, actual);
    }

}