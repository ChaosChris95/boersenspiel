package boersenspiel.gui;

import boersenspiel.exceptions.LanguageNotFoundException;
import boersenspiel.exceptions.NegativeValueException;
import boersenspiel.exceptions.PlayerDoesNotExistException;
import boersenspiel.manager.AccountManagerImpl;
import boersenspiel.manager.ShareManagement;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * User: Jan
 * Date: 30.05.13
 * Time: 10:15
 */

public class MainWindow extends Application {

    private MenuItem menuEditCrp;
    private MenuItem menuOptionsBot;
    private MenuItem menuOptionsCs;
    private MenuItem menuOptionsDs;
    private MenuItem menuOptionsLd;
    private MenuItem menuOptionsLe;
    private MenuItem menuInformationGs;
    private MenuItem menuInformationGas;
    private MenuItem menuInformationCs;
    private MenuItem menuHelpAbout;
    private MenuItem menuLogShowShare;
    private MenuItem menuLogShowTime;
    private MenuItem menuLogPrintShare;
    private MenuItem menuLogPrintTime;

    private Stage stage;
    private ResourceBundle rs = ResourceBundle.getBundle("boersenspiel");
    private Logger logger;
    private AccountManagerImpl accountManager;
    private ShareManagement shareManagement;
    private String player;

    public MainWindow(){
        logger = Logger.getLogger("MainWindow");
        accountManager = AccountManagerImpl.getInstance();
        shareManagement = ShareManagement.getInstance();
        //
        try{
            accountManager.createPlayer("jan", 2000L);
        } catch (NegativeValueException e){
            logger.log(Level.SEVERE, "NegativeValueException", e);
        }
        player = "jan";
        //
    }

    public void start(Stage primaryStage){

        primaryStage.setTitle(rs.getString("programTitle"));// + " " + player);

        /**
         * label with console-text
         */
        Label consoleText = new Label();
        consoleText.setText("Hi");


        ScrollPane consoleScrollBar = new ScrollPane();
        consoleScrollBar.setContent(consoleText);
        //consoleScrollBar.setFitToWidth(true);

        /**
         * Buy Sell User Interface
         */
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.BOTTOM_RIGHT);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        /*TextField textField1 = new TextField("Name");
        gridPane.add(textField1, 0, 1);*/
        final TextField textField2 = new TextField("Share");
        gridPane.add(textField2, 1, 1);
        final TextField textField3 = new TextField("Amount");
        gridPane.add(textField3, 2, 1);

        Button buttonBuy = new Button("Buy");
        buttonBuy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String shareName = textField2.getText();
                int amount = Integer.parseInt(textField3.getText());
                try{    //TODO Exception no share with this name
                    accountManager.buy(player, shareName, amount);
                } catch (NegativeValueException e){
                    logger.log(Level.SEVERE, "NegativeValueException", e);
                }
            }
        });
        gridPane.add(buttonBuy, 3, 1);

        Button buttonSell = new Button("Sell");
        buttonSell.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String shareName = textField2.getText();
                int amount = Integer.parseInt(textField3.getText());
                accountManager.sell(player, shareName, amount);
            }
        });
        gridPane.add(buttonSell, 4, 1);

        /**
         * position all elements and show window
         */
        BorderPane border = new BorderPane();
        VBox menuBox = new VBox();
        menuBox.getChildren().addAll(setMenuBar(handleAction()));

        border.setTop(menuBox);
        border.setCenter(consoleScrollBar);
        border.setBottom(gridPane);

        Scene scene = new Scene(border, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
        scene.addEventHandler(Event.ANY, handleAction());
    }

    public MenuBar setMenuBar(EventHandler event){

        MenuBar menuBar = new MenuBar();

        final Menu menuEdit = new Menu ("Edit");
        menuEditCrp = new MenuItem("Create Player");
        menuEditCrp.setOnAction(event);
        menuEdit.getItems().addAll(menuEditCrp);

        final Menu menuOptions = new Menu ("Options");
        menuOptionsBot = new MenuItem("Start Bot");
        menuOptionsBot.setOnAction(event);
        menuOptionsLd = new MenuItem("Deutsch");
        menuOptionsLd.setOnAction(event);
        menuOptionsLe = new MenuItem("English");
        menuOptionsLe.setOnAction(event);
        menuOptionsCs = new MenuItem("Create Share");
        menuOptionsCs.setOnAction(event);
        menuOptionsDs = new MenuItem("Delete Share");
        menuOptionsDs.setOnAction(event);
        menuOptions.getItems().addAll(menuOptionsBot);
        menuOptions.getItems().addAll(menuOptionsCs);
        menuOptions.getItems().addAll(menuOptionsDs);
        menuOptions.getItems().addAll(menuOptionsLd);
        menuOptions.getItems().addAll(menuOptionsLe);

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
        Menu menuLogShow = new Menu ("Show Logs");
        menuLogShowShare = new MenuItem("sorted by share");
        menuLogShowShare.setOnAction(event);
        menuLogShowTime = new MenuItem("sorted by time");
        menuLogShowTime.setOnAction(event);
        menuLogShow.getItems().addAll(menuLogShowShare);
        menuLogShow.getItems().addAll(menuLogShowTime);
        Menu menuLogPrint = new Menu ("Print Logs");
        menuLogPrintShare = new MenuItem("sorted by share");
        menuLogPrintShare.setOnAction(event);
        menuLogPrintTime = new MenuItem("sorted by time");
        menuLogPrintTime.setOnAction(event);
        menuLogPrint.getItems().addAll(menuLogPrintShare);
        menuLogPrint.getItems().addAll(menuLogPrintTime);;
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
                    stage = new Stage();
                    CreatePlayerWindow cpw = new CreatePlayerWindow();
                    cpw.start(stage);
                    //player = cpw.getName(); //TODO does not work
                    System.out.println(player);
                }
                else if (event.getTarget() == menuOptionsBot){
                    System.out.println("menuOptionsBot");
                    accountManager.botPlayer(player);

                }
                else if (event.getTarget() == menuOptionsCs){
                    System.out.println("menuOptionsCs");
                    //Does not make sense yet
                }
                else if (event.getTarget() == menuOptionsDs){
                    System.out.println("menuOptionsDs");
                    //Does not make sense yet
                }
                else if (event.getTarget() == menuOptionsLd){
                    System.out.println("menuOptionsLd");
                    try{
                        accountManager.setLocale("de");
                    } catch (LanguageNotFoundException e){
                        logger.log(Level.SEVERE, "Wrong Language", e);
                    }
                }
                else if (event.getTarget() == menuOptionsLe){
                    System.out.println("menuOptionsLe");
                    try{
                        accountManager.setLocale("en");
                    } catch (LanguageNotFoundException e){
                        logger.log(Level.SEVERE, "Wrong Language", e);
                    }
                }
                else if (event.getTarget() == menuInformationGs){
                    System.out.println("menuInformationGs");
                    accountManager.getStock(player);
                }
                else if (event.getTarget() == menuInformationGas){
                    System.out.println("menuInformationGas");
                    logger.info(shareManagement.getSharesAndRates());
                }
                else if (event.getTarget() == menuInformationCs){
                    System.out.println("menuInformationCs");
                    accountManager.getCashAccountValue(player);
                }
                else if (event.getTarget() == menuLogShowShare){
                    System.out.println("menuLogShow");
                    try{
                        accountManager.printPlain(player, 1);   //TODO new Window 0 or 1 sort
                    } catch (PlayerDoesNotExistException e){
                        logger.log(Level.SEVERE, "FileNotFoundException", e);
                    } catch (IOException e){
                        logger.log(Level.SEVERE, "IOException", e);
                    }
                }
                else if (event.getTarget() == menuLogShowTime){
                    System.out.println("menuLogShow");
                    try{
                        accountManager.printPlain(player, 2);   //TODO new Window 0 or 1 sort
                    } catch (PlayerDoesNotExistException e){
                        logger.log(Level.SEVERE, "FileNotFoundException", e);
                    } catch (IOException e){
                        logger.log(Level.SEVERE, "IOException", e);
                    }
                }
                else if (event.getTarget() == menuLogPrintShare){    //TODO new Window 0 or 1 sort and filename
                    System.out.println("menuLogPrintShare");
                    PrintHtmlWindow phw = new PrintHtmlWindow(player, 1);
                    stage = new Stage();
                    phw.start(stage);
                }
                else if (event.getTarget() == menuLogPrintTime){    //TODO new Window 0 or 1 sort and filename
                    System.out.println("menuLogPrintTime");
                    PrintHtmlWindow phw = new PrintHtmlWindow(player, 2);
                    stage = new Stage();
                    phw.start(stage);
                }
                else if (event.getTarget() == menuHelpAbout){
                    System.out.println("menuHelpAbout");
                    stage = new Stage();
                    AboutWindow aboutWindow = new AboutWindow();
                    aboutWindow.start(stage);
                }
            }
        };
        return event;
    }
}
