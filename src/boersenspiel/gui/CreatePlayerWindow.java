package boersenspiel.gui;

import boersenspiel.exceptions.WrongNumberOfParametersException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import boersenspiel.exceptions.WrongNumberOfParametersException;

/**
 * User: Jan
 * Date: 30.05.13
 * Time: 18:25
 */

public class CreatePlayerWindow extends Application {

    private String name;
    private long cash;
    private Stage stage;

    public CreatePlayerWindow(){
    }

    public void start(Stage primaryStage){//throws WrongNumberOfParametersException{

        primaryStage.setTitle("Create Player");
        GridPane gridPane = new GridPane();
        TextField textFieldName = new TextField();
        TextField textFieldCash = new TextField();
        Button crp = new Button("Create Player");
        gridPane.add(textFieldName, 0, 0);
        gridPane.add(textFieldCash, 1, 0);
        gridPane.add(crp, 2, 0);

        /*
        String characters;
        characters = (textField.getCharacters()).toString();
        String[] parameters = characters.split(" ");
        if (parameters.length != 2){
            throw new WrongNumberOfParametersException("two parameters are needed");
        }
        name = parameters[0];
        cash = Long.parseLong(parameters[1]);
        */
        Scene scene = new Scene(gridPane, 370, 10);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public String getName (){
        return name;
    }

    public long getCash(){
        return cash;
    }

}