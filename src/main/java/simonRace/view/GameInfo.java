package simonRace.view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * A class representing the game information panel in the UI.
 * <p>
 * Contains labels and text fields for displaying the names of the players, the current player,
 * and the values of the dice. Also contains buttons for starting the game and moving.
 */
public class GameInfo extends VBox{
    /**
     * player 1 name label
     */
    public static Label player1Name = new Label("Player 1:");
    /**
     * show player one name
     */
    public Text getPlayerOneName = new Text("");
    /**
     * player 2 name label
     */
    public static Label player2Name = new Label("Player 2:");
    /**
     * show player 2 name
     */
    public Text getPlayerTwoName = new Text("");

    /**
     * current player label
     */
    public static Label curPlayerLab = new Label("Current Player:");
    /**
     * current player name
     */
    public Text txtPlayer = new Text();

    /**
     * DiceOne label
     */
    public static Label DiceOneLab = new Label("Dice One (steps):");
    /**
     * current dice one value (represent steps)
     */
    public Text txtDiceOne = new Text();

    /**
     * DiceTwo label
     */
    public static Label diceTwoLab = new Label("Dice Two (Direction):");
    /**
     * current dice one value (represent directions)
     */
    public Text txtDiceTwo = new Text();

    /**
     * left steps label
     */
    public static Label leftSteps = new Label("left Steps");
    /**
     * current left steps value
     */
    public Text leftStepsNum = new Text();

    /**
     * button clicked to start game
     */
    public Button startG = new Button("Start Game");
    /**
     * button clicked to move player
     */
    public Button move = new Button("Move");

    /**
     * label for top 10 secores
     */
    public static Label top10List = new Label("Top 10 Record (Using least steps to win)");

    /**
     * Constructs a new GameInfo object and adds the labels, text fields, and buttons.
     */
    public GameInfo(){
        txtPlayer.setId("curPlayer");
        txtDiceOne.setId("diceOne");
        txtDiceTwo.setId("diceTwo");

        startG.setId("btnStart");
        this.getChildren().addAll(player1Name,getPlayerOneName,player2Name,getPlayerTwoName,
                curPlayerLab,txtPlayer,DiceOneLab,txtDiceOne,diceTwoLab,txtDiceTwo, leftSteps, leftStepsNum,
                startG, move, top10List);
    }

}
