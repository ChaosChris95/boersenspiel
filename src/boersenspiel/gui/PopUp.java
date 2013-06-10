package boersenspiel.gui;

import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * User: Peach
 * Date: 10.06.13
 * Time: 15:38
 */
public class PopUp extends Application {

    public void popUp(String msg) {
        Stage stage = new Stage();
        HBox hBox = new HBox();
        Label label = new Label(msg);
        hBox.getChildren().add(label);
        hBox.setPadding(new Insets(25, 25, 25, 25));
        stage.setScene(new Scene(hBox));
        stage.setTitle("My modal window");
        stage.initModality(Modality.WINDOW_MODAL);
        JFXPanel primaryStage = null;
        stage.initOwner(primaryStage.getScene().getWindow());
        stage.show();
    }

    @Override
    public void start(Stage stage) throws Exception {

    }
}
