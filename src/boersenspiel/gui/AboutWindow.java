package boersenspiel.gui;

import javafx.application.Application;
import javafx.beans.DefaultProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * User: Jan
 * Date: 10.06.13
 * Time: 11:56
 */

//@DefaultProperty(value="image")
//public class AboutWindow extends Node {
public class AboutWindow extends Application{

    private Stage stage;
    private Logger logger;
    private File fileURL;
    private ResourceBundle rs = ResourceBundle.getBundle("boersenspiel");

    public static void main(String[] args) {
        AboutWindow aboutWindow = new AboutWindow();
        Application.launch(args);
    }
    public AboutWindow(){
        logger = Logger.getLogger("AboutWindow");
    }

    public void start(Stage stage){
        stage.setTitle(rs.getString("About"));
        VBox vBox = new VBox();
        Label label = new Label("The legendary Börsenspiel! \n\n" +
                                "Creators: \n" +  "Jan Kowalke & \n" + "Bianca Bergmann \n\n" +
                                "Informatik 4.Semester \n" +
                                "Programmieren 2 \n \n" +
                                "Copyright 2013");

        /*try{
            fileURL = new File(ClassLoader.getSystemResource("./res/Icon.png").toURI());
        } catch (URISyntaxException e){
            logger.log(Level.SEVERE, "toURI failed", e);
        }*/
        Image image = new Image("./res/Icon.png");
        ImageView iv = new ImageView();
        iv.setImage(image);
        vBox.getChildren().addAll(iv, label);
        Scene scene = new Scene(vBox, 200, 200);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

}
