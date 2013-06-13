package boersenspiel.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import boersenspiel.exceptions.NegativeValueException;
import boersenspiel.manager.AccountManagerImpl;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * User: Jan
 * Date: 30.05.13
 * Time: 18:25
 */

public class CreatePlayerWindow extends Application {

    private String name;
    private long cash;
    private Stage stage;
    private AccountManagerImpl accountManager;
    Logger logger;
    private ResourceBundle rs = ResourceBundle.getBundle("boersenspiel");

    public CreatePlayerWindow() {
        accountManager = AccountManagerImpl.getInstance();
        logger = Logger.getLogger("CreatePlayerWindow");
    }

    public void start(final Stage primaryStage) {

        primaryStage.setTitle(rs.getString("createPlayer"));
        GridPane gridPane = new GridPane();
        final TextField textFieldName = new TextField();
        final TextField textFieldCash = new TextField();
        Button crp = new Button(rs.getString("createPlayer"));
        crp.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                name = textFieldName.getText();
                cash = Long.parseLong(textFieldCash.getText());
                try {
                    accountManager.createPlayer(name, cash);
                    stage = new Stage();
                    MainWindow mainWindow = new MainWindow(name);
                    mainWindow.start(stage);
                    primaryStage.close();
                } catch (NegativeValueException e) {
                    logger.log(Level.SEVERE, rs.getString("AMNegativ"));
                }
            }
        });

        gridPane.add(textFieldName, 0, 0);
        gridPane.add(textFieldCash, 1, 0);
        gridPane.add(crp, 2, 0);

        Scene scene = new Scene(gridPane, 370, 10);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    /*public void popupMsgWindow(String msg) {
        Stage stage = new Stage();
        HBox hBox = new HBox();
        Label label = new Label(msg);
        hBox.getChildren().add(label);
        hBox.setPadding(new Insets(25, 25, 25, 25));
        stage.setScene(new Scene(hBox));
        stage.setTitle("Fehlermeldung");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(stage.getScene().getWindow());
        stage.show();
    } */

}