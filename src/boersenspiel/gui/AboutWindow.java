package boersenspiel.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
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
        VBox vBox = new VBox();
        Image image = new Image("./res/Icon.ico");
        Label label = new Label("The legendary Börsenspiel");
        ImageView iv = new ImageView();
        iv.setImage(image);
        vBox.getChildren().addAll(iv, label);
        Scene scene = new Scene(vBox, 200, 200);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
}
