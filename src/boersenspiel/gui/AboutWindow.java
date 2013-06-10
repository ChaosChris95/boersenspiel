package boersenspiel.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * User: Jan
 * Date: 10.06.13
 * Time: 11:56
 */

public class AboutWindow extends Application{

    private Stage stage;

    public void start(Stage stage){
        stage.setTitle("About");
        Label label = new Label("The legendary Börsenspiel");
        Scene scene = new Scene(label, 200, 200);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
