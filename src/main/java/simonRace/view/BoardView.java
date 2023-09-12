package simonRace.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import simonRace.entity.Board;

/**
 * The BoardView class represents the view of the board in the Simon's Race game.
 *
 * @author [Qiongyi Zhang]
 */
public class BoardView {

    /**
     * represent number of rows for board
     */
    public static final int ROWS = 6;

    /**
     * represent number of columns for board
     */
    public static final int COLS = 6;

    /**
     * width of board
     */
    public static final int WIDTH = 600;

    /**
     * height of board
     */
    public static final int HEIGHT = 600;

    /**
     * A 2D array representing the map for the Simon race game.
     */
    public int[][] map = simonRace.entity.Board.map;

    /**
     * an instance of GridPane
     */
    public GridPane board = new GridPane();

    /**
     * Construct of BoardView creating basic board for Simon's Race Game
     *
     */
    public BoardView(){
        // set fire image
        Image fire = new Image(getClass().getResource("/images/fire.png").toString());
        ImageView fireImage = new ImageView(fire);
        fireImage.setFitWidth(120);
        fireImage.setFitHeight(120);
        //set hole image
        Image hole = new Image(getClass().getResource("/images/hole.png").toString());
        ImageView holeImage = new ImageView(hole);
        holeImage.setFitWidth(120);
        holeImage.setFitHeight(120);
        //set fence image
        Image fence = new Image(getClass().getResource("/images/fence.png").toString());
        ImageView fenceImage = new ImageView(fence);
        fenceImage.setFitWidth(120);
        fenceImage.setFitHeight(120);


//        board = new GridPane();
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                Rectangle rectangle = new Rectangle(WIDTH / COLS, HEIGHT / ROWS);
                if (row == 0) {
                    rectangle.setFill(Color.GREEN);
                } else if (row == ROWS - 1) {
                    rectangle.setFill(Color.RED);
                } else{
//                    rectangle.setFill(Color.WHITE);
                    rectangle.setFill(Color.BLUE);
                    rectangle.setStroke(Color.BLACK);
                    if (map[row][col] == Board.FIRE){
                        rectangle.setFill(new ImagePattern(fire));
                    } else if (map[row][col] == Board.HOLE){
                        rectangle.setFill(new ImagePattern(hole));
                    } else if (map[row][col] == Board.FENCE) {
                        rectangle.setFill(new ImagePattern(fence));
                    }
                }
                board.add(rectangle, col, row);
            }
        }
    }
}
