package simonRace;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import simonRace.view.BoardView;
import simonRace.view.GameInfo;
import simonRace.entity.Board;
import simonRace.entity.Player;
import simonRace.entity.Dice;
import javafx.collections.FXCollections;



import java.util.Optional;
import java.util.Random;

import static simonRace.ScoresHandle.*;

/**
 * GamePane is a JavaFX application that represents a game and logic of the game.
 * <p>
 * The game includes hazards such as fire and holes that can send the player back to the starting position, and fences that the player must avoid. The player who reaches the end area of the board first wins the game.
 * The game can be started by pressing the "Start Game" button, which will make player 1 to roll the dice and then can click move button to make a move.
 * The current player and the game status is displayed in the left pane, along with a list of the top 10 scores(steps). The game board is displayed in the right pane, with the players' game pieces represented by images.
 *
 * @author [Qiongyi Zhang]
 */
public class GamePane extends Application {
    public Player p1;
    public Player p2;

    /**
     * Player object representing the current player
     */
    public Player curPlayer;

    /**
     * Boolean value representing whether the game has started
     */
    public Boolean isGameStart;

    /**
     * GameInfo object which displays game information
     */
    public GameInfo leftPane = new GameInfo();

    /**
     * Dice object representing the dice used in the game
     */
    public Dice dice = new Dice();


    public static BoardView boardView = new BoardView();
    public static GridPane gridPane = boardView.board;

    private ListView<String> recordList;

    private static final int WIDTH = BoardView.WIDTH;
    private static final int HEIGHT = BoardView.HEIGHT;

    /**
     * 2D array representing the game map, with get from Board Class
     */
    public int[][] map = Board.map;

    private static final int FIRE = Board.FIRE;
    private static final int HOLE = Board.HOLE;
    private static final int FENCE = Board.FENCE;

    private ImageView player1image;
    private ImageView player2image;


    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The start method sets up the initial state of the game, including creating the Player objects, drawing the game map, and displaying the top 10 records.
     * It also sets up the game window, including the left pane and the right pane containing the game board.
     @param primaryStage the primary stage for the application
     */
    @Override
    public void start(Stage primaryStage) {
//        drawBoard();
        initialGame();
        setAction();

        SplitPane splitPane = new SplitPane();

        splitPane.getItems().addAll(leftPane, gridPane);
        Scene scene = new Scene(splitPane, WIDTH*1.5, HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * The initialGame method sets up the initial state of the game, including creating the Player objects, drawing the game map, and
     * displaying the top 10 records.
     */
    public void initialGame(){
        // Set the names for player 1 and player 2
        setNames();

        // If either player's name is empty, prompt the user to enter a valid name
        String p1name = leftPane.getPlayerOneName.getText();
        String p2name = leftPane.getPlayerTwoName.getText();
        if (p1name.isEmpty() || p2name.isEmpty()){initialGame();}

        // Create the Player objects for player 1 and player 2
        p1 = new Player(p1name, 5, 0);
        p2 = new Player(p2name, 5, 3);

        // Load the images for player 1 and player 2's game pieces
        Image player1 = new Image(getClass().getResource("/images/player1.png").toString());
        Image player2 = new Image(getClass().getResource("/images/player2.png").toString());
        player1image = new ImageView(player1);
        player2image = new ImageView(player2);
        player1image.setFitHeight(70);
        player1image.setFitWidth(70);
        player2image.setFitHeight(70);
        player2image.setFitWidth(70);

        // Add the player images to the gridPane at the starting positions for player 1 and player 2
        gridPane.add(player1image, 0, 5);
        gridPane.setHalignment(player1image, HPos.CENTER);
        gridPane.add(player2image, 3, 5);
        gridPane.setHalignment(player2image, HPos.CENTER);

        // When game not start yet, Set isGameStart to false and not disable the statG button
        isGameStart = false;
        leftPane.startG.setDisable(isGameStart);

        recordList = new ListView<>(FXCollections.observableArrayList(showTop10()));
        leftPane.getChildren().add(recordList);
    }

    /**
     * The setAction method sets up the actions for the buttons in the left pane.
     */
    public void setAction(){
        leftPane.startG.setOnAction(actionEvent -> {
            isGameStart = true;
            play(p1);
            leftPane.startG.setDisable(true);
        });
        leftPane.move.setOnAction(actionEvent -> {
            if (isGameStart) {move(curPlayer);}
        });
    }

    /**
     * The setNames method prompts the user to enter the names of player 1 and player 2 using text input dialogs.
     * <p>
     * If either input is empty or the same as the other player's name, an error message is displayed and the user is prompted to enter a valid name again.
     * <p>
     * If the inputs are valid, the names are set for the players.
     */
    private void setNames() {
        if (leftPane.getPlayerOneName.getText().isEmpty()){setPlayerOneName();}
        if (leftPane.getPlayerTwoName.getText().isEmpty()){setPlayerTwoName();}
    }

    /**
     * This method prompts the user to enter the name of player 1 using a text input dialog.
     * <p>
     * If the input is empty or the same as the name of player 1, an error message is displayed and the user is prompted to enter a valid name again.
     * <p>
     * If the input is valid, the name is set for player.
     */
    public void setPlayerOneName() {
        // Show a text input dialog to enter player one name
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Enter Player One Name");
        dialog.setHeaderText(null);
        dialog.setContentText("Please enter the name of player one:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String playerOneName = result.get();
            if (playerOneName.isEmpty()) {
                // Show an error message if the input is empty
                AlertDiologs.errorAlert("Error","Name can not be empty.");
                setPlayerOneName();
            } else if(playerOneName.equals(leftPane.getPlayerTwoName.getText())){
                // Show an error message if the input is same as player one
                AlertDiologs.errorAlert("Error","Please enter a different player name with player 2");
            } else {
                leftPane.getPlayerOneName.setText(playerOneName);
            }
        }
    }

    /**
     * This method prompts the user to enter the name of player 2 using a text input dialog.
     * <p>
     * If the input is empty or the same as the name of player 2, an error message is displayed and the user is prompted to enter a valid name again.
     * <p>
     * If the input is valid, the name is set for player.
     */
    public void setPlayerTwoName() {
        // Show a text input dialog to enter player one name
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Enter Player Two Name");
        dialog.setHeaderText(null);
        dialog.setContentText("Please enter the name of player Two:");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            String playerName = result.get();
            if (playerName.isEmpty()) {
                // Show an error message if the input is empty
                AlertDiologs.errorAlert("Error","Name can not be empty.");
                setPlayerTwoName();
            } else if(playerName.equals(leftPane.getPlayerOneName.getText())){
                // Show an error message if the input is same as player one
                AlertDiologs.errorAlert("Error","Please enter a different player name with player 1");
                setPlayerTwoName();
            }else {
                leftPane.getPlayerTwoName.setText(playerName);
            }
        }
    }



    /**
     * The move method moves a player's game piece on the board.
     * <p>
     * If the player lands on a HOLE, they are sent back to the starting position and their turn ends.
     * <p>
     * If the player lands on the opposite end of the board, they win the game and the game ends.
     * <p>
     * If the player's next step is on the Fence or Fire, they will choose to stop their turn or move Left or Right.
     * Player can only choose to move to side way one time.
     * <p>
     * When the Player move to the top of the board, the Game end and save the record and announce the winner.
     *
     * @param p the player whose game piece is being moved
     */
    public void move(Player p) {
        String direction = leftPane.txtDiceTwo.getText();
        //get left steps;
        int leftStep = Integer.parseInt(leftPane.leftStepsNum.getText());
        // move image views on gridpane
        int curRow = p.getPosX();
        int curCol = p.getPosY();
        // update position of players images
        if (p.getName() == p1.getName()) {
            gridPane.setColumnIndex(player1image, curCol);
            gridPane.setRowIndex(player1image, curRow);
        } else {
            gridPane.setColumnIndex(player2image, curCol);
            gridPane.setRowIndex(player2image, curRow);
        }
        // check if win
        if (curRow == 0) {
            AlertDiologs.informAlert("Win", p.getName() + " win with " + p.getAllStep() + " steps.");

            //write to file
            saveRecord(p.getName(), p.getAllStep());

            //clear GameInfo and restart a game
            leftPane.getPlayerOneName.setText("");
            leftPane.getPlayerTwoName.setText("");
            leftPane.getChildren().remove(recordList);
            gridPane.getChildren().remove(player1image);
            gridPane.getChildren().remove(player2image);
            initialGame();

        } else {
            if (leftStep > 0) {
                //check if the player move in hole
//                if (map[curRow][curCol] == HOLE) {
//                    // randomly go to another place
//                    AlertDiologs.informAlert("Drop into Hole", "You are droped into a hole, and will return to the start area");
//                    if (moveStartArea(p)){
//                        move(p);
//                    };
//                }
                if (direction.equals("Skip Turn")) {
                    AlertDiologs.informAlert("Skip Turn", "You rolled or chose to skip your turn");
                    nextPlayerTurn(p);
                }
                if (direction.equals("Forward")) {
                    if (isObstacaled(p)) {
                        String d = AlertDiologs.chooseDirection();
                        leftPane.txtDiceTwo.setText(d);
                        move(p);
                    } else {
                        p.setPosX(curRow - 1);
                        leftStep--;
                        leftPane.leftStepsNum.setText(String.valueOf(leftStep));
                        p.addAllSteps();
                        move(p);
                    }
                }
                if (direction.equals("Back")) {
                    if (isObstacaled(p)) {
                        String d = AlertDiologs.chooseDirection();
                        leftPane.txtDiceTwo.setText(d);
                        move(p);
                    } else {
                        p.setPosX(curRow + 1);
                        leftStep--;
                        leftPane.leftStepsNum.setText(String.valueOf(leftStep));
                        p.addAllSteps();
                        move(p);
                    }
                }
                if (direction.equals("L")) {
                    if (isObstacaled(p)) {
                        nextPlayerTurn(p);
                    } else {
                        p.setPosY(curCol - 1);
                        p.addAllSteps();
                        leftStep--;
                        leftPane.leftStepsNum.setText(String.valueOf(leftStep));
                        move(p);
                    }
                }
                if (direction.equals("R")) {
                    if (isObstacaled(p)) {
                        nextPlayerTurn(p);
                    } else {
                        p.setPosY(curCol + 1);
                        p.addAllSteps();
                        leftStep--;
                        leftPane.leftStepsNum.setText(String.valueOf(leftStep));
                        move(p);
                    }
                }
            }
            nextPlayerTurn(p);
        }
    }


    /**
     * The moveStartArea method moves a player to the starting area of the board.
     * <p>
     * This method is used when a player lands on a Hole and generate the players lane position randomly.
     *
     * @param p the player whose game piece is being moved
     */
    private boolean moveStartArea(Player p) {
        Random random = new Random();
        int newLane = random.nextInt(6);
        leftPane.leftStepsNum.setText("0");
        if(p.getName() == p1.getName()){
            if (p2.getPosX() == 5 && p2.getPosY() == newLane){
                moveStartArea(p);
            }else{
                p.setPosY(newLane);
                p.setPosX(5);
                return true;
            }
        } else {
            if (p1.getPosX() == 5 && p1.getPosY() == newLane){
                moveStartArea(p);
            }else{
                p.setPosX(5);
                p.setPosY(newLane);
                return true;
            }
        }
        return moveStartArea(p);
    }


    /**
     * The isObstacaled method checks if a player is blocked by a fence, fire or another player.
     *
     * @param p the player is current player to be checked
     * @return true if the player's game piece is blocked by a fence, false and nother player, otherwise return false
     */
    public Boolean isObstacaled(Player p) {
//        String direction = leftPane.txtDiceTwo.getText();
//        int curRow = p.getPosX();
//        int curCol = p.getPosY();
//        if (direction.equals("Forward")) {
//            return ((map[curRow - 1][curCol] != 0 && map[curRow - 1][curCol] != HOLE)
//                    || (p.getName() == p1.getName() && (p2.getPosX() == curRow - 1 && p2.getPosY() == curCol)) ||
//                    (p.getName() == p2.getName() && p1.getPosX() == curRow - 1 && p1.getPosY() == curCol));
//        } else if (direction.equals("Back")) {
//            return (curRow + 1 > 5 || (map[curRow + 1][curCol] != 0)
//                    || (p.getName() == p1.getName() && p2.getPosX() == curRow + 1 && p2.getPosY() == curCol)
//                    || (p.getName() == p2.getName() && p1.getPosX() == curRow + 1 && p1.getPosY() == curCol));
//        } else if (direction.equals("L")) {
//            return (curCol - 1 < 0 || (curCol - 1 > 0 && map[curRow][curCol - 1] != 0)
//                    || (p.getName() == p1.getName() && p2.getPosY() == curCol - 1 && p2.getPosX() == curRow)
//                    || (p.getName() == p2.getName() && p1.getPosY() == curCol - 1 && p1.getPosX() == curRow));
//        } else if(direction.equals("R")) {
//            return (curCol + 1 > 5 || (curCol + 1 < 5 && map[curRow][curCol + 1] != 0)
//                    ||(p.getName() == p1.getName() && p2.getPosY() == curCol + 1 && p2.getPosX() == curRow)
//                    ||(p.getName() == p2.getName() && p1.getPosY() == curCol + 1 && p1.getPosX() == curRow));
//        }else{
            return false;
//        }
    }

    /**
     * The nextPlayerTurn method switches the current player's turn to the other player.
     *
     * @param p the current player
     */
    public void nextPlayerTurn(Player p) {
        if (p == p1) {
            play(p2);
        } else {
            play(p1);
        }
    }

    /**
     * The play method starts a player's turn by rolling the dice and updating the game information to show the current player, dice roll results, and remaining steps.
     *
     * @param p the player whose turn it is
     */
    public void play(Player p) {
        dice.roll();
        leftPane.txtDiceOne.setText(Integer.toString(dice.getDieOne()));
        leftPane.txtDiceTwo.setText(dice.getStatus());
        leftPane.txtPlayer.setText(p.getName());
        leftPane.leftStepsNum.setText(leftPane.txtDiceOne.getText());
        curPlayer = p;
    }

}

