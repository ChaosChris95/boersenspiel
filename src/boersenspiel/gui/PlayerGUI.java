package boersenspiel.gui;

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
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * User: Peach
 * Date: 10.06.13
 * Time: 14:39
 */
public class PlayerGUI extends Thread {

    private static String player;
    private MenuItem menuEditCrp;
    private MenuItem menuEditCtc;
    private MenuItem menuOptionsBot;
    private MenuItem menuOptionsCs;
    private MenuItem menuOptionsDs;
    private MenuItem menuOptionsLd;
    private MenuItem menuOptionsLe;
    private MenuItem menuInformationGs;
    private MenuItem menuInformationGas;
    private MenuItem menuInformationCs;
    private MenuItem menuLogShowShare;
    private MenuItem menuLogShowTime;
    private MenuItem menuLogPrintShare;
    private MenuItem menuLogPrintTime;
    private MenuItem menuHelpAbout;


    private Stage stage;
    private ResourceBundle rs = ResourceBundle.getBundle("boersenspiel");
    private Logger logger;
    private AccountManagerImpl accountManager;
    private ShareManagement shareManagement;
    private StockPriceViewer stockPriceViewer;
    private int amount;
    private String shareName;


    public static void main(String[] args){
        PlayerGUI playerGUI = new PlayerGUI(player);
        Application.launch(args);
    }

    public PlayerGUI(String player){
        logger = Logger.getLogger("PlayerWindow");
        this.player = player;
        accountManager = AccountManagerImpl.getInstance();
        shareManagement = ShareManagement.getInstance();
    }

    public void start(Stage primaryStage){

        primaryStage.setTitle("Player: " + " " + this.player);

        Label consoleText = new Label("" +
                "Java ist eine objektorientierte Programmiersprache und eine eingetragene Marke des Unternehmens Sun Microsystems (2010 von Oracle aufgekauft). \nDie Programmiersprache ist ein Bestandteil der Java-Technologie – diese besteht grundsätzlich aus dem Java-Entwicklungswerkzeug (JDK) \nzum Erstellen von Java-Programmen und der Java-Laufzeitumgebung (JRE) zu deren Ausführung. \nDie Laufzeitumgebung selbst umfasst die virtuelle Maschine (JVM) und die mitgelieferten Bibliotheken."
                + "");

        ScrollPane consoleScrollBar = new ScrollPane();
        consoleScrollBar.setContent(consoleText);
        //consoleScrollBar.setFitToWidth(true);

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
                shareName = textField2.getText();
                amount = Integer.parseInt(textField3.getText());
                try{
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
                shareName = textField2.getText();
                amount = Integer.parseInt(textField3.getText());
                accountManager.sell(player, shareName, amount);
            }
        });
        gridPane.add(buttonSell, 4, 1);

        BorderPane border = new BorderPane();
        VBox menuBox = new VBox();

        menuBox.getChildren().addAll(setMenuBar(handleAction()));
        border.setTop(menuBox);
        border.setCenter(consoleScrollBar);
        border.setBottom(setGridPane(handleAction()));

        Scene scene = new Scene(border, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
        scene.addEventHandler(Event.ANY, handleAction());
    }

    public GridPane setGridPane (EventHandler event){

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.BOTTOM_RIGHT);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        /*TextField textField1 = new TextField("Name");
        gridPane.add(textField1, 0, 1);*/
        TextField textField2 = new TextField("Share");
        gridPane.add(textField2, 1, 1);
        TextField textField3 = new TextField("Amount");
        gridPane.add(textField3, 2, 1);
        Button buttonBuy = new Button("Buy");
        buttonBuy.setOnAction(event);
        gridPane.add(buttonBuy, 3, 1);
        Button buttonSell = new Button("Sell");
        buttonSell.setOnAction(event);
        gridPane.add(buttonSell, 4, 1);
        return gridPane;
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
                else if (event.getTarget() == menuEditCtc){
                    System.out.println("menuEditCtc");
                    //TODO start new instance
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
                else if (event.getTarget() == menuOptionsLd){
                    System.out.println("menuOptionsLd");
                }
                else if (event.getTarget() == menuOptionsLe){
                    System.out.println("menuOptionsLe");
                }
            }
        };
        return event;
    }
}