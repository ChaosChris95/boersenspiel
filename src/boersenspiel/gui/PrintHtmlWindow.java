package boersenspiel.gui;

import boersenspiel.exceptions.PlayerDoesNotExistException;
import boersenspiel.interfaces.AccountManager;
import boersenspiel.manager.AccountManagerImpl;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * User: Jan
 * Date: 10.06.13
 * Time: 12:11
 */

public class PrintHtmlWindow extends Application{

    private int option = 0;
    private String title = "Save";
    private AccountManagerImpl accountManager;
    private String player;
    Logger logger = Logger.getLogger("PrintHtmlWindow");

    public PrintHtmlWindow(String player){
        this.player = player;
        accountManager = AccountManagerImpl.getInstance();
    }

    public void start(Stage stage){
        stage.setTitle("Print HTML");
        VBox vBox = new VBox();
        Label label = new Label("Choose your type of sort");

        Button time = new Button("Time");
        time.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                option = 0;
            }
        });

        Button share = new Button("Share");
        share.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                option = 1;
            }
        });
        vBox.getChildren().addAll(label, time, share);
        //showFileDialog(title, false);
        Scene scene = new Scene(vBox, 200, 200);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        try{
            accountManager.printHtml(player, showFileDialog(title, false).getAbsolutePath(), option);//"dummy", 1);
        } catch (PlayerDoesNotExistException e){
            logger.log(Level.SEVERE, "FileNotFoundException", e);
        }
    }

    public File showFileDialog(String title, boolean open) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        String currentDir = System.getProperty("user.dir") + File.separator;
        File file = new File(currentDir);
        fileChooser.setInitialDirectory(file);
        return open ? fileChooser.showOpenDialog(null) : fileChooser.showSaveDialog(null);
    }


}
