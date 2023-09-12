package simonRace.entity;

import java.util.Random;

/**
 * A class representing a 4-sided dice.
 *
 *  @author [Qinogyi Zhang]
 */
public class Dice {
    private String status;
    private int dieOne, dieTwo;
    private Random random;

    /**
     * Using a default constructor
     */
    public Dice(){}

    /**
     * Rolls the dice and get the direction value of the dice.
     * <p>
     * The direction of the dice can be one of the following:
     * "Forward" (for the values 1 or 2), "Back" (for the value 3), or "Skip Turn" (for the value 4).
     */
    public void roll() {
        if (random == null) {
            random = new Random();
        }

        dieOne = random.nextInt(4) + 1;
        dieTwo = random.nextInt(4) + 1;

        switch (dieTwo) {
            case 1:
            case 2:
                status = "Forward";
                break;
            case 3:
                status = "Back";
                break;
            case 4:
                status = "Skip Turn";
                break;
            default:
                throw new IllegalArgumentException("unexpected number in 4-side dice");
        }

    }

    /**
     * Get the direction of the dice
     * <p>
     * The direction of the dice can be one of the following:
     * "Forward" (for the values 1 or 2), "Back" (for the value 3), or "Skip Turn" (for the value 4).
     *
     * @return string of directions
     */
    public String getStatus() {
        return status;
    }


    /**
     * Get the result of the diceOne
     *
     * @return diceOne represent steps
     */
    public int getDieOne() {
        return dieOne;
    }
}
