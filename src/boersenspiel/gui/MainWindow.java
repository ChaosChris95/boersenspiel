package boersenspiel.gui;

import boersenspiel.exceptions.NegativeValueException;
import boersenspiel.interfaces.AccountManager;
import boersenspiel.manager.AccountManagerImpl;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.TextField;
import java.util.logging.Logger;

/**
 * User: Jan
 * Date: 30.05.13
 * Time: 10:15
 */

public class MainWindow extends Application {

    AccountManagerImpl accountManager;

    public static void main(String[] args){
        //accountManager = AccountManagerImpl.getInstance();
        Application.launch(args);
    }

    /*public MainWindow (String[] args){
        accountManager = AccountManagerImpl.getInstance();
        Application.launch(args);
    }*/

    public void start(Stage primaryStage){

        accountManager = AccountManagerImpl.getInstance();
        final Logger logger = Logger.getLogger("MainWindow");

        primaryStage.setTitle("Börsenspiel");
        Label rightLabel = new Label("Console");
        Label leftLabel = new Label ("TickerTask");

        rightLabel.setAlignment(Pos.CENTER_RIGHT);
        Button buyButton = new Button("Buy");
        Button sellButton = new Button("Sell");
        sellButton.setAlignment(Pos.BOTTOM_RIGHT);
        buyButton.setAlignment(Pos.BOTTOM_RIGHT);
        ChoiceBox choicePlayer = new ChoiceBox();
        ChoiceBox choiceShare = new ChoiceBox();
        ChoiceBox choiceAmount = new ChoiceBox();

        MenuBar menuBar = new MenuBar();

        final Menu menuEdit = new Menu ("Edit");
        final MenuItem menuEditCrp = new MenuItem("Create Player");
        final MenuItem menuEditCtc = new MenuItem("Change to Console");
        menuEdit.getItems().addAll(menuEditCrp);
        menuEdit.getItems().addAll(menuEditCtc);

        final Menu menuOptions = new Menu ("Options");
        final MenuItem menuOptionsBot = new MenuItem("Start Bot");
        final MenuItem menuOptionsCs = new MenuItem("Create Share");
        final MenuItem menuOptionsDs = new MenuItem("Delete Share");
        menuOptions.getItems().addAll(menuOptionsBot);
        menuOptions.getItems().addAll(menuOptionsCs);
        menuOptions.getItems().addAll(menuOptionsDs);

        Menu menuInformation = new Menu ("Information");
        final MenuItem menuInformationGs = new MenuItem("Get Stock");
        final MenuItem menuInformationGas = new MenuItem("Get All Stock");
        final MenuItem menuInformationCs = new MenuItem("Get Cash");
        menuInformation.getItems().addAll(menuInformationGs);
        menuInformation.getItems().addAll(menuInformationGas);
        menuInformation.getItems().addAll(menuInformationCs);

        Menu menuLog = new Menu ("Log");
        final MenuItem menuLogShow = new MenuItem("Show Logs");
        final MenuItem menuLogPrint = new MenuItem("Print Logs");
        menuLog.getItems().addAll(menuLogShow);
        menuLog.getItems().addAll(menuLogPrint);

        Menu menuHelp = new Menu ("Help");
        final MenuItem menuHelpAbout = new MenuItem("About");
        menuHelp.getItems().addAll(menuHelpAbout);

        menuBar.getMenus().addAll(
        menuEdit, menuOptions, menuInformation, menuLog, menuHelp
        );

        Scene scene = new Scene(new VBox(), 800, 600);
        ((VBox) scene.getRoot()).getChildren().addAll(menuBar);
        primaryStage.setScene(scene);
        primaryStage.show();

        scene.addEventHandler(Event.ANY, new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                Frame frame = new Frame();
                TextField textField = new TextField();
                //class TextField extends TextInputControl{

                if (event.getSource() == menuEditCrp){
                    CreatePlayerWindow createPlayerWindow = new CreatePlayerWindow();
                    String name = createPlayerWindow.getName();
                    long cash = createPlayerWindow.getCash();

                    //createPlayerWindow.start();
                    try {
                        accountManager.createPlayer(name, cash);
                    } catch (NegativeValueException e) {
                        logger.info("CashAccount value must be >0");
                    }

                }
                else if (event.getSource() == menuEditCtc){

                }
                else if (event.getSource() == menuOptionsBot){

                }
                else if (event.getSource() == menuOptionsCs){

                }
                else if (event.getSource() == menuOptionsDs){

                }
                else if (event.getSource() == menuInformationGs){

                }
                else if (event.getSource() == menuInformationGas){

                }
                else if (event.getSource() == menuInformationCs){

                }
                else if (event.getSource() == menuLogShow){

                }
                else if (event.getSource() == menuLogPrint){

                } else {

                }
            }
        });

        /*HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.BOTTOM_RIGHT);
        final Button button = new Button("Close");
        hBox.getChildren().add(button);

        primaryStage.setScene(scene);
        primaryStage.show();
*/

        /*GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        Label label = new Label("Current Value:");
        gridPane.add(label, 0, 1);
        final TextField textField = new TextField("Test!");
        gridPane.add(textField,1,1);
        HBox hBox = new HBox(10);
        hBox.setAlignment(Pos.BOTTOM_RIGHT);
        final Button button = new Button("Close");
        hBox.getChildren().add(button);
        gridPane.add(hBox,1,4);

        Scene scene = new Scene(gridPane, 300, 275);
        primaryStage.setScene(scene);
        primaryStage.show();
*/
    }
}
