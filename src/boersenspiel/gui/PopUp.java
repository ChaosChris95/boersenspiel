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
public class PopUp extends Thread {

    private Stage stage;
    private String msg;

    public static void main(String[] args) {
        Application.launch(args);
    }

    public PopUp(String message) throws Exception {
        this.msg = message;
        start(stage);
    }

    public void start(Stage primaryStage) throws Exception {
        final Stage stage = new Stage();
        HBox hBox = new HBox();
        Label label = new Label(msg);
        hBox.getChildren().add(label);
        hBox.setPadding(new Insets(25, 25, 25, 25));
        stage.setScene(new Scene(hBox));
        stage.setTitle("Fehlermeldung");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(primaryStage.getScene().getWindow());
        stage.show();
    }

}
