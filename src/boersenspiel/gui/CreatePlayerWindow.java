package boersenspiel.gui;

import boersenspiel.exceptions.WrongNumberOfParametersException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
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

//    public static void main (String[] args){
//        Application.launch(args);
//    }

    public void start(Stage stage) throws WrongNumberOfParametersException{
        stage.setTitle("Test");
        TextField textField = new TextField();
        String characters;
        characters = (textField.getCharacters()).toString();
        String[] parameters = characters.split(" ");
        if (parameters.length != 2){
            throw new WrongNumberOfParametersException("two parameters are needed");
        }
        name = parameters[0];
        cash = Long.parseLong(parameters[1]);
        Scene scene = new Scene(textField, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    public String getName (){
        return name;
    }

    public long getCash(){
        return cash;
    }

}
