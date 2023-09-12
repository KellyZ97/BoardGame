module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens simonRace to javafx.fxml;
    exports simonRace;
}