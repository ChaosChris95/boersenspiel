package boersenspiel.gui;

import boersenspiel.exceptions.NegativeValueException;
import boersenspiel.exceptions.WrongNumberOfParametersException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
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

    public CreatePlayerWindow(){
        accountManager = AccountManagerImpl.getInstance();
    }

    public void start(Stage primaryStage) {//throws NegativeValueException{

        primaryStage.setTitle("Create Player");
        GridPane gridPane = new GridPane();
        final TextField textFieldName = new TextField();
        final TextField textFieldCash = new TextField();
        Button crp = new Button("Create Player");
        crp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                name = textFieldName.getText();
                cash = Long.parseLong(textFieldCash.getText());
                try{
                    accountManager.createPlayer(name, cash);
                } catch (NegativeValueException e){} //TODO how catch it?

                //System.out.println("name = " + name);
                //System.out.println("cash = " + cash);
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

    public String getName(){
        return name;
    }

}