package simonRace.entity;

/**
 * A class representing a player in the game.
 * <p>
 * Contains the player's name, position, and total number of steps taken.
 */

public class Player {
    private String name;
    private int posX;
    private int posY;
    private int allStep = 0;

    /**
     * Constructs Player object with the given name and position X and Y.
     *
     * @param name the name of the player
     * @param x the x position of the player
     * @param y the y position of the player
     */
    public Player(String name, int x, int y) {
        this.name = name;
        this.posX = x;
        this.posY = y;
    }

    /**
     * Returns the name of the player.
     *
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the x position of the player.
     *
     * @return the x position of the player
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Returns the y position of the player.
     *
     * @return the y position of the player
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Sets the x position of the player.
     *
     * @param posX the new x position of the player
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     * Sets the y position of the player.
     *
     * @param posY the new y position of the player
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }

    /**
     * Returns the total number of steps taken by the player.
     *
     * @return the total number of steps taken by the player
     */
    public int getAllStep() {
        return allStep;
    }

    /**
     * Increments the total number of steps taken by the player by 1.
     */
    public void addAllSteps() {
        this.allStep += 1;
    }
}

