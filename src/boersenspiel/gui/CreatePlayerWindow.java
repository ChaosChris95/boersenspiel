package boersenspiel.gui;

import boersenspiel.exceptions.NegativeValueException;
import boersenspiel.exceptions.PlayerAlreadyExistsException;
import boersenspiel.exceptions.WrongNumberOfParametersException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import boersenspiel.manager.AccountManagerImpl;

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

    public CreatePlayerWindow() {
        accountManager = AccountManagerImpl.getInstance();
    }

    public void start(Stage primaryStage) {//throws NegativeValueException{

        final Stage[] stage = {primaryStage};
        final Stage[] stageNew = new Stage[1];
        stage[0].setTitle("Create Player");
        GridPane gridPane = new GridPane();
        final TextField textFieldName = new TextField();
        final TextField textFieldCash = new TextField();
        Button crp = new Button("Create Player");
        final PopUp popUp = new PopUp();
        crp.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                name = textFieldName.getText();
                cash = Long.parseLong(textFieldCash.getText());
                try {
                    accountManager.createPlayer(name, cash);
                } catch (NegativeValueException e) {

                    System.exit(0);
                }

                stage[0].close();
                stageNew[0] = new Stage();
                PlayerGUI playerGUI = new PlayerGUI(name);
                playerGUI.run();
                playerGUI.start(stage[0]);
            }
        });

        gridPane.add(textFieldName, 0, 0);
        gridPane.add(textFieldCash, 1, 0);
        gridPane.add(crp, 2, 0);

        Scene scene = new Scene(gridPane, 370, 10);
        stage[0].setResizable(false);
        stage[0].setScene(scene);
        stage[0].show();


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