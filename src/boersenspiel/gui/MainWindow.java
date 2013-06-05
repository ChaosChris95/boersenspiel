package boersenspiel.gui;

import boersenspiel.exceptions.NegativeValueException;
import boersenspiel.exceptions.WrongNumberOfParametersException;
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

import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * User: Jan
 * Date: 30.05.13
 * Time: 10:15
 */

public class MainWindow extends Application {

    AccountManagerImpl accountManager;

    private MenuItem menuEditCrp;
    private MenuItem menuEditCtc;
    private MenuItem menuOptionsBot;
    private MenuItem menuOptionsCs;
    private MenuItem menuOptionsDs;
    private MenuItem menuInformationGs;
    private MenuItem menuInformationGas;
    private MenuItem menuInformationCs;
    private MenuItem menuLogShow;
    private MenuItem menuLogPrint;
    private MenuItem menuHelpAbout;
    private ResourceBundle rs = ResourceBundle.getBundle("boersenspiel");

    public static void main(String[] args){
        Application.launch(args);
    }

    public void start(Stage primaryStage){

        accountManager = AccountManagerImpl.getInstance();
        //Logger logger = Logger.getLogger("MainWindow");

        primaryStage.setTitle(rs.getString("programTitle"));

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
        menuEditCtc = new MenuItem("Change to Console");
        menuEditCtc.setOnAction(event);
        menuEdit.getItems().addAll(menuEditCrp);
        menuEdit.getItems().addAll(menuEditCtc);

        final Menu menuOptions = new Menu ("Options");
        menuOptionsBot = new MenuItem("Start Bot");
        menuOptionsBot.setOnAction(event);
        menuOptionsCs = new MenuItem("Create Share");
        menuOptionsCs.setOnAction(event);
        menuOptionsDs = new MenuItem("Delete Share");
        menuOptionsDs.setOnAction(event);
        menuOptions.getItems().addAll(menuOptionsBot);
        menuOptions.getItems().addAll(menuOptionsCs);
        menuOptions.getItems().addAll(menuOptionsDs);

        Menu menuInformation = new Menu ("Information");
        menuInformationGs = new MenuItem("Get Stock");
        menuInformationGs.setOnAction(event);
        menuInformationGas = new MenuItem("Get All Stock");
        menuInformationGas.setOnAction(event);
        menuInformationCs = new MenuItem("Get Cash");
        menuInformationCs.setOnAction(event);
        menuInformation.getItems().addAll(menuInformationGs);
        menuInformation.getItems().addAll(menuInformationGas);
        menuInformation.getItems().addAll(menuInformationCs);

        Menu menuLog = new Menu ("Log");
        menuLogShow = new MenuItem("Show Logs");
        menuLogShow.setOnAction(event);
        menuLogPrint = new MenuItem("Print Logs");
        menuLogPrint.setOnAction(event);
        menuLog.getItems().addAll(menuLogShow);
        menuLog.getItems().addAll(menuLogPrint);

        Menu menuHelp = new Menu ("Help");
        menuHelpAbout = new MenuItem("About");
        menuHelpAbout.setOnAction(event);
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
                    System.out.println("menuEditCrp");
                    //CreatePlayerWindow createPlayerWindow = new CreatePlayerWindow();
                }
                else if (event.getTarget() == menuEditCtc){
                    System.out.println("menuEditCtc");
                }
                else if (event.getTarget() == menuOptionsBot){
                    System.out.println("menuOptionsBot");
                }
                else if (event.getTarget() == menuOptionsCs){
                    System.out.println("menuOptionsCs");
                }
                else if (event.getTarget() == menuOptionsDs){
                    System.out.println("menuOptionsDs");
                }
                else if (event.getTarget() == menuInformationGs){
                    System.out.println("menuInformationGs");
                }
                else if (event.getTarget() == menuInformationGas){
                    System.out.println("menuInformationGas");
                }
                else if (event.getTarget() == menuInformationCs){
                    System.out.println("menuInformationCs");
                }
                else if (event.getTarget() == menuLogShow){
                    System.out.println("menuLogShow");
                }
                else if (event.getTarget() == menuLogPrint){
                    System.out.println("menuLogPrint");
                }
                else if (event.getTarget() == menuHelpAbout){
                    System.out.println("menuHelpAbout");
                }
            }
        };
        return event;
    }
}
