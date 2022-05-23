package application.pizzaking;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class PizzaKing extends Application {





    @Override
    public void start(Stage stage) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("PizzaOrderFXML.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Pizza Order");

        Image anotherIcon = new Image("file:E:\\testing\\PizzaKing\\src\\main\\pizzaimg.jpg");   //
        stage.getIcons().add(anotherIcon);
        stage.show();




    }

    public static void main(String[] args) {
        launch(args);
    }
}