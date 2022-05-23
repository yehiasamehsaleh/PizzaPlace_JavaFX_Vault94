module application.pizzaking {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;


    opens application.pizzaking to javafx.fxml;
    exports application.pizzaking;
}