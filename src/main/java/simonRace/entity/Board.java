package simonRace.entity;

/**
 * The Board class represents the basic game board in the GamePane application.
 *
 * It contains constants for the different types of hazards on the board, as well as a 2D array representing the game map.
 *
 * @author [Qinogyi Zhang]
 */
public class Board {
    /**
     * stoic number represent fire in 2D array map
     */
    public static final int FIRE = 1;

    /**
     * stoic number represent hole in 2D array map
     */
    public static final int HOLE = 3;

    /**
     * stoic number represent fence in 2D array map
     */
    public static final int FENCE = 2;

    /**
     * 2D array of integer. 0 represent empty space, 1 represent fire, 2 represent fence, 3 represent hole
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
     * Default constructor for the AlertDiologs class.
     */
    public Board() {}

}
