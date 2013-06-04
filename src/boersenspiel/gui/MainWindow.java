package boersenspiel.gui;

import boersenspiel.manager.AccountManagerImpl;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.logging.Logger;

/**
 * User: Jan
 * Date: 30.05.13
 * Time: 10:15
 */

public class MainWindow extends Application {

    AccountManagerImpl accountManager;

    private MenuItem menuEditCrp;

    public static void main(String[] args){
        Application.launch(args);
    }

    public void start(Stage primaryStage){

        accountManager = AccountManagerImpl.getInstance();
        final Logger logger = Logger.getLogger("MainWindow");

        primaryStage.setTitle("Börsenspiel");

        Scene scene = new Scene(new VBox(), 800, 600);
        ((VBox) scene.getRoot()).getChildren().addAll(setMenuBar(handleAction()));
        primaryStage.setScene(scene);
        primaryStage.show();

        scene.addEventHandler(Event.ANY, handleAction());
    }

    public MenuBar setMenuBar(EventHandler event){

        MenuBar menuBar = new MenuBar();

        final Menu menuEdit = new Menu ("Edit");
        menuEditCrp = new MenuItem("Create Player");
        menuEditCrp.setOnAction(event);
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
        return menuBar;
    }

    public EventHandler handleAction(){
        EventHandler event = new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                if (event.getTarget() == menuEditCrp){
                    System.out.println("Hallo!");
                    /*CreatePlayerWindow createPlayerWindow = new CreatePlayerWindow();
                    try {
                        createPlayerWindow.start(new Stage());
                    } catch (WrongNumberOfParametersException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                    String name = createPlayerWindow.getName();
                    long cash = createPlayerWindow.getCash();

                    //createPlayerWindow.start();
                    try {
                        accountManager.createPlayer(name, cash);
                    } catch (NegativeValueException e) {
                        logger.info("CashAccount value must be >0");
                    }*/

                /*else if (event.getSource() == menuEditCtc){

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

                }*/
                }
            }
        };
        return event;
    }
}
