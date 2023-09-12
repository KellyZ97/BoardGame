package simonRace;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * A utility class that contains methods for displaying different types and messages of alert dialogs.
 *
 *
 * @author [Qiongyi Zhang]
 */
public class AlertDiologs {

    /**
     * Default constructor for the AlertDiologs class.
     */
    public AlertDiologs() {}

    /**
     * Displays an error alert dialog with given title and message.
     *
     * @param title the title of the alert dialog
     * @param msg the message to display in the alert dialog
     */
    public static void errorAlert(String title, String msg){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    /**
     * Displays an information alert dialog with given title and message.
     *
     * @param title the title of the alert dialog
     * @param msg the message to display in the alert dialog
     */
    public static void informAlert(String title, String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    /**
     * Displays a confirmation alert dialog with the given title and message. The user can choose
     * between three options: "Left", "Right", or "Stop".
     *
     * @return the user's selection, either "L" for "Left", "R" for "Right", or "Skip Turn" for "Stop"
     */
    public static String chooseDirection(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Movement Options");
        alert.setHeaderText("You have reached a obstacle, please choose a direction to move:");
        ButtonType leftButton = new ButtonType("Left");
        ButtonType rightButton = new ButtonType("Right");
        ButtonType stopButton = new ButtonType("Stop");
        alert.getButtonTypes().setAll(leftButton, rightButton, stopButton);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == leftButton) {
            return "L";
        } else if (result.get() == rightButton) {
            return "R";
        } else {
            return "Skip Turn";
        }
    }

}