package boersenspiel.gui;

import boersenspiel.exceptions.*;
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
import java.util.Currency;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimerTask;
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

    private Label consoleText;

    private Stage stage;
    private ResourceBundle rs = ResourceBundle.getBundle("boersenspiel");
    private Logger logger;
    private AccountManagerImpl accountManager;
    private ShareManagement shareManagement;
    private String player;
    private String title;
    private StringBuffer stringBuffer;

    public MainWindow(String name){
        logger = Logger.getLogger("MainWindow");
        stringBuffer = new StringBuffer();
        accountManager = AccountManagerImpl.getInstance();
        shareManagement = ShareManagement.getInstance();

        if (name == "Börsenspiel"){
            title = name;
        } else {
            player = name;
            title = name;
        }


    }

    public static void main(String[] args) {
        Application.launch(args);
    }


    public void start(Stage primaryStage){

        primaryStage.setTitle(title);

        /**
         * label with console-text
         */
        consoleText = new Label();
        stringBuffer.append(rs.getString("Output") + ":\n");
        consoleText.setText(stringBuffer.toString());

        /**
         * ScrollPane
         */
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
        final TextField textField2 = new TextField(rs.getString("Share1"));
        gridPane.add(textField2, 1, 1);
        final TextField textField3 = new TextField(rs.getString("ShareAmount"));
        gridPane.add(textField3, 2, 1);

        Button buttonBuy = new Button(rs.getString("Buy"));
        buttonBuy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String shareName = textField2.getText();
                int amount = Integer.parseInt(textField3.getText());
                try{
                    accountManager.buy(player, shareName, amount);
                    stringBuffer.append(rs.getString("Gamer") + " " + player + " "
                            + rs.getString("AMBuy") + " " + amount + " " + rs.getString("Of") + " " + shareName);
                    consoleText.setText(stringBuffer.toString());
                } catch (NegativeValueException e){
                    logger.log(Level.SEVERE, "NegativeValueException", e);
                } catch (PlayerDoesNotExistException e) {
                    e.printStackTrace();
                } catch (NotEnoughMoneyException e) {
                    e.printStackTrace();
                }
            }
        });
        gridPane.add(buttonBuy, 3, 1);

        Button buttonSell = new Button(rs.getString("Sell"));
        buttonSell.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String shareName = textField2.getText();
                int amount = Integer.parseInt(textField3.getText());
                try {
                    accountManager.sell(player, shareName, amount);
                } catch (NegativeValueException e) {
                    e.printStackTrace();
                } catch (PlayerDoesNotExistException e) {
                    e.printStackTrace();
                } catch (NotEnoughSharesException e) {
                    e.printStackTrace();
                }
                stringBuffer.append(rs.getString("Gamer")+ " " + player + " "
                        + rs.getString("AMSell") + " " + amount + " " + rs.getString("Of") + " " + shareName);
                consoleText.setText(stringBuffer.toString());
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

        final Menu menuEdit = new Menu (rs.getString("Edit"));
        menuEditCrp = new MenuItem(rs.getString("createPlayer"));
        menuEditCrp.setOnAction(event);
        menuEdit.getItems().addAll(menuEditCrp);

        final Menu menuOptions = new Menu (rs.getString("Option"));
        menuOptionsBot = new MenuItem(rs.getString("StartB"));
        menuOptionsBot.setOnAction(event);
        menuOptionsLd = new MenuItem("Deutsch");
        menuOptionsLd.setOnAction(event);
        menuOptionsLe = new MenuItem("English");
        menuOptionsLe.setOnAction(event);
        menuOptionsCs = new MenuItem(rs.getString("createShare"));
        menuOptionsCs.setOnAction(event);
        menuOptionsDs = new MenuItem(rs.getString("deleteShare"));
        menuOptionsDs.setOnAction(event);
        menuOptions.getItems().addAll(menuOptionsBot);
        menuOptions.getItems().addAll(menuOptionsCs);
        menuOptions.getItems().addAll(menuOptionsDs);
        menuOptions.getItems().addAll(menuOptionsLd);
        menuOptions.getItems().addAll(menuOptionsLe);

        Menu menuInformation = new Menu ("Information");
        menuInformationGs = new MenuItem(rs.getString("GetStock"));
        menuInformationGs.setOnAction(event);
        menuInformationGas = new MenuItem(rs.getString("GetAllStk"));
        menuInformationGas.setOnAction(event);
        menuInformationCs = new MenuItem(rs.getString("GetCash"));
        menuInformationCs.setOnAction(event);
        menuInformation.getItems().addAll(menuInformationGs);
        menuInformation.getItems().addAll(menuInformationGas);
        menuInformation.getItems().addAll(menuInformationCs);

        Menu menuLog = new Menu (rs.getString("Log"));
        Menu menuLogShow = new Menu (rs.getString("ShowLog"));
        menuLogShowShare = new MenuItem(rs.getString("ByShare"));
        menuLogShowShare.setOnAction(event);
        menuLogShowTime = new MenuItem(rs.getString("ByTime"));
        menuLogShowTime.setOnAction(event);
        menuLogShow.getItems().addAll(menuLogShowShare);
        menuLogShow.getItems().addAll(menuLogShowTime);
        Menu menuLogPrint = new Menu (rs.getString("PrintLog"));
        menuLogPrintShare = new MenuItem(rs.getString("ByShare"));
        menuLogPrintShare.setOnAction(event);
        menuLogPrintTime = new MenuItem(rs.getString("ByTime"));
        menuLogPrintTime.setOnAction(event);
        menuLogPrint.getItems().addAll(menuLogPrintShare);
        menuLogPrint.getItems().addAll(menuLogPrintTime);;
        menuLog.getItems().addAll(menuLogShow);
        menuLog.getItems().addAll(menuLogPrint);

        Menu menuHelp = new Menu (rs.getString("Help"));
        menuHelpAbout = new MenuItem(rs.getString("About"));
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
                    stage = new Stage();
                    CreatePlayerWindow cpw = new CreatePlayerWindow();
                    cpw.start(stage);
                }
                else if (event.getTarget() == menuOptionsBot){
                    if (player == null){
                        stringBuffer.append(rs.getString("NoPlayerCreate"));
                        consoleText.setText(stringBuffer.toString());
                    }
                    else{
                        accountManager.botPlayer(player);
                        stringBuffer.append(rs.getString("BotStart") + "\n");
                        consoleText.setText(stringBuffer.toString());
                    }
                }
                else if (event.getTarget() == menuOptionsCs){
                    stringBuffer.append("not implemented yet\n");
                    consoleText.setText(stringBuffer.toString());
                    //Does not make sense yet
                }
                else if (event.getTarget() == menuOptionsDs){
                    stringBuffer.append("not implemented yet\n");
                    consoleText.setText(stringBuffer.toString());
                    //Does not make sense yet
                }
                else if (event.getTarget() == menuOptionsLd){
                    try{
                        accountManager.setLocale("de");
                    } catch (LanguageNotFoundException e){
                        logger.log(Level.SEVERE, "Language not supported", e);
                    }
                    stringBuffer.append("Sprache jetzt Deutsch\n");
                    consoleText.setText(stringBuffer.toString());
                }
                else if (event.getTarget() == menuOptionsLe){
                    try{
                        accountManager.setLocale("en");
                    } catch (LanguageNotFoundException e){
                        logger.log(Level.SEVERE, "Language not supported", e);
                    }
                    stringBuffer.append("Language now English\n");
                    consoleText.setText(stringBuffer.toString());
                }
                else if (event.getTarget() == menuInformationGs){
                    String gs = accountManager.getStock(player) + "\n";

                    if (player == null){
                        stringBuffer.append(rs.getString("NoPlayerCreate"));
                        consoleText.setText(stringBuffer.toString());
                    }
                    else {
                        stringBuffer.append(gs);
                        consoleText.setText(stringBuffer.toString());
                    }

                }
                else if (event.getTarget() == menuInformationGas){
                    String gas = shareManagement.getSharesAndRates();
                    gas = gas.replace("<br>", "\n");
                    stringBuffer.append(gas);
                    consoleText.setText(stringBuffer.toString());
                }
                else if (event.getTarget() == menuInformationCs){
                    if(Locale.getDefault() == Locale.GERMAN) {
                        stringBuffer.append(accountManager.getCashAccountValue(player) + " " + Currency.getInstance("EUR").getSymbol());
                    } else {
                        stringBuffer.append(accountManager.getCashAccountValue(player) + " " + Currency.getInstance("GBP").getSymbol());
                    }
                    consoleText.setText(stringBuffer.toString());
                }
                else if (event.getTarget() == menuLogShowShare){
                    try{
                        accountManager.printPlain(player, 1);
                    } catch (PlayerDoesNotExistException e){
                        logger.log(Level.SEVERE, "FileNotFoundException", e);
                    } catch (IOException e){
                        logger.log(Level.SEVERE, "IOException", e);
                    }
                    stringBuffer.append("menuLogShowShare not implemented yet\n");
                    consoleText.setText(stringBuffer.toString());
                }
                else if (event.getTarget() == menuLogShowTime){
                    try{
                        accountManager.printPlain(player, 2);
                    } catch (PlayerDoesNotExistException e){
                        logger.log(Level.SEVERE, "FileNotFoundException", e);
                    } catch (IOException e){
                        logger.log(Level.SEVERE, "IOException", e);
                    }
                    stringBuffer.append("menuLogShowShare not implemented yet\n");
                    consoleText.setText(stringBuffer.toString());
                }
                else if (event.getTarget() == menuLogPrintShare){
                    PrintHtmlWindow phw = new PrintHtmlWindow(player, 1);
                    stage = new Stage();
                    //try{
                        phw.start(stage);
                    /*} catch(WrongParametersException e){
                        logger.log(Level.SEVERE, "Wrong sort option");
                    }*/
                    stringBuffer.append(rs.getString("Log")+ " " + rs.getString("File")+ " "  + phw.getPath() + rs.getString("Saved") +  ", "  + rs.getString("ByShare") + "\n");
                    consoleText.setText(stringBuffer.toString());
                }
                else if (event.getTarget() == menuLogPrintTime){
                    PrintHtmlWindow phw = new PrintHtmlWindow(player, 2);
                    stage = new Stage();
                    //try{
                        phw.start(stage);
                    /*} catch(WrongParametersException e){
                        logger.log(Level.SEVERE, "Wrong sort option");
                    }*/
                    stringBuffer.append(rs.getString("Log")+ " " + rs.getString("File")+ " "  + phw.getPath() + rs.getString("Saved") +  ", "  + rs.getString("ByTime") + "\n");
                    consoleText.setText(stringBuffer.toString());
                }
                else if (event.getTarget() == menuHelpAbout){
                    stage = new Stage();
                    AboutWindow aboutWindow = new AboutWindow();
                    aboutWindow.start(stage);
                }
            }
        };
        return event;
    }
}
