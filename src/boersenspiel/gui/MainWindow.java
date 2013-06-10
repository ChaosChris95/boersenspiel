package boersenspiel.gui;

import boersenspiel.exceptions.NegativeValueException;
import boersenspiel.exceptions.PlayerDoesNotExistException;
import boersenspiel.manager.AccountManagerImpl;
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

    private Stage stage;
    private ResourceBundle rs = ResourceBundle.getBundle("boersenspiel");
    private Logger logger;
    private AccountManagerImpl accountManager;
    private String player;
    private int amount;
    private String shareName;
    //TODO instances with players

    public static void main(String[] args){
        MainWindow mainWindow = new MainWindow();
        Application.launch(args);
    }

    public MainWindow(){
        logger = Logger.getLogger("MainWindow");
        accountManager = AccountManagerImpl.getInstance();
        final String player = "jan";
    }

    public void start(Stage primaryStage){

        primaryStage.setTitle(rs.getString("programTitle"));// + " " + player);

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
                    stage = new Stage();
                    CreatePlayerWindow cpw = new CreatePlayerWindow();
                    cpw.start(stage);
                    player = cpw.getName(); //TODO does not work

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
                    stage = new Stage();
                    GetStockWindow getStockWindow = new GetStockWindow();   //does not work as expected!
                    getStockWindow.start(stage);
                }
                else if (event.getTarget() == menuInformationGas){
                    System.out.println("menuInformationGas");
                    accountManager.getStock(player);
                }
                else if (event.getTarget() == menuInformationCs){
                    System.out.println("menuInformationCs");
                    accountManager.getCashAccountValue(player);
                }
                else if (event.getTarget() == menuLogShow){
                    System.out.println("menuLogShow");
                    try{
                        accountManager.printPlain(player, 1);   //TODO new Window 0 or 1 sort
                    } catch (PlayerDoesNotExistException e){
                        logger.log(Level.SEVERE, "FileNotFoundException", e);
                    } catch (IOException e){
                        logger.log(Level.SEVERE, "IOException", e);
                    }
                }
                else if (event.getTarget() == menuLogPrint){    //TODO new Window 0 or 1 sort and filename
                    System.out.println("menuLogPrint");
                    PrintHtmlWindow phw = new PrintHtmlWindow(player);
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
