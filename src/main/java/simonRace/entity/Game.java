package simonRace.entity;

import simonRace.AlertDiologs;

import java.util.Random;

/**
 * A class representing basic logic of Game and help for testing the players movement
 *
 * @author [Qiongyi Zhang]
 */
public class Game {
    /**
     * player 1
     */
    public Player p1;

    /**
     * player 2
     */
    public Player p2;
//    private int step;
//    private String direction;
    /**
     * Game map(same as board)
     */
    public static final int[][] map = new int[][]{
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 2, 0},
            {0, 0, 0, 0, 0, 0},
            {3, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
    };

    /**
     * represent a instance of Dice
     */
    private Dice dice = new Dice();

    /**
     * construction of Game
     *
     * @param p1 player 1
     * @param p2 player 2
     */
    public Game(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
    }


    /**
     * move method shows the logic we use in GamePane but just used to help test
     *
     * @param p current Player will move
     * @param step left steps the player can move
     * @param direction directions move to
     * @return result shows the position of the player when the move end as format "posX, posY"; or shows the winning result, all the statue if meet Obstacles
     */
    public String move(Player p, int step, String direction) {
        String result = "";
        int curRow = p.getPosX();
        int curCol = p.getPosY();

        result = result + curRow + ", " + curCol;
        //check if the player move in hole
        if (map[curRow][curCol] == Board.HOLE) {
            // randomly go to another place
//            if (moveStartArea(p)){
//                move(p,step,"Skip Turn");
//            }
            return "Drop hole";
        }else if (curRow == 0) {
            return p.getName() + " Win!";
//            System.out.println(p.getName() + "Win!");
        } else {
            if (step > 0) {
                if (direction.equals("Skip Turn")) {
                    return result;
                }
                if (direction.equals("Forward")) {
                    if (isObstacaled(p, direction)) {
                        return "Obstacle " + result;
                    } else {
                        p.setPosX(curRow - 1);
                        p.addAllSteps();
                        return move(p, step - 1, direction);
                    }
                }
                if (direction.equals("Back")) {
                    if (isObstacaled(p, direction)) {
                        return "Obstacle " + result;
//                        move(p, step, Util.getInput("Input: S-Skip Turn, R-turn Right,L-Turn Left"));
                    } else {
                        p.setPosX(curRow + 1);
                        p.addAllSteps();
                        return move(p, step - 1, direction);
                    }
                }
                if (direction.equals("L")) {
                    if (isObstacaled(p, direction)) {
//                        nextPlayerTurn(p);
                        return "Obstacle " + result;
                    } else {
                        p.setPosY(curCol - 1);
                        p.addAllSteps();
                        return move(p, step - 1, direction);
                    }
                }
                if (direction.equals("R")) {
                    if (isObstacaled(p, direction)) {
//                        nextPlayerTurn(p);
                        return "Obstacle " + result;
                    } else {
                        p.setPosY(curCol + 1);
                        p.addAllSteps();
                        return move(p, step - 1, direction);
                    }
                }
            }
            return result;
        }
    }

    /**
     * isObstacaled method shows the logic we use in GamePane to check if the player will meet the hazard but just used to help test
     *
     * @param p current Player to be send to start area
     * @param direction current move direction
     * @return true if the player is relocated to the start area, otherwise rerun the method
     */
    public Boolean isObstacaled(Player p, String direction) {
        int curRow = p.getPosX();
        int curCol = p.getPosY();
        if (direction.equals("Forward")) {
            return ((map[curRow - 1][curCol] != 0 && map[curRow - 1][curCol] != Board.HOLE)
                    || (p.getName() == p1.getName() && (p2.getPosX() == curRow - 1 && p2.getPosY() == curCol)) ||
                    (p.getName() == p2.getName() && p1.getPosX() == curRow - 1 && p1.getPosY() == curCol));
        } else if (direction.equals("Back")) {
            return (curRow + 1 > 5 || (map[curRow + 1][curCol] != 0)
                    || (p.getName() == p1.getName() && p2.getPosX() == curRow + 1 && p2.getPosY() == curCol)
                    || (p.getName() == p2.getName() && p1.getPosX() == curRow + 1 && p1.getPosY() == curCol));
        } else if (direction.equals("L")) {
            return (curCol - 1 < 0 || (curCol - 1 > 0 && map[curRow][curCol - 1] != 0)
                    || (p.getName() == p1.getName() && p2.getPosY() == curCol - 1 && p2.getPosX() == curRow)
                    || (p.getName() == p2.getName() && p1.getPosY() == curCol - 1 && p1.getPosX() == curRow));
        } else if(direction.equals("R")) {
            return (curCol + 1 > 5 || (curCol + 1 < 5 && map[curRow][curCol + 1] != 0)
                    ||(p.getName() == p1.getName() && p2.getPosY() == curCol + 1 && p2.getPosX() == curRow)
                    ||(p.getName() == p2.getName() && p1.getPosY() == curCol + 1 && p1.getPosX() == curRow));
        }else{
            return false;
        }
    }

    /**
     * moveStartArea method shows the logic we use in GamePane but just used to help test
     *
     * @param p current Player to be send to start area
     * @return true if the player is relocated to the start area, otherwise rerun the method
     */
    public boolean moveStartArea(Player p) {
        Random random = new Random();
        int newLane = random.nextInt(6);
        p.setPosX(5);
        if(p.getName() == p1.getName()){
            if (p2.getPosX() == 5 && p2.getPosY() == newLane){
                return moveStartArea(p);
            }else{
                p.setPosY(newLane);
                return true;
            }
        } else {
            if (p1.getPosX() == 5 && p1.getPosY() == newLane){
                return moveStartArea(p);
            }else{
                p.setPosY(newLane);
                return true;
            }
        }
    }

}

