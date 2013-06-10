package boersenspiel.gui;

import boersenspiel.exceptions.NegativeValueException;
import boersenspiel.manager.AccountManagerImpl;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * User: Jan
 * Date: 10.06.13
 * Time: 11:00
 */
public class GetStockWindow extends Application{

    private Stage stage;
    private AccountManagerImpl accountManager;
    private String name;

    public GetStockWindow(){
        accountManager = AccountManagerImpl.getInstance();
    }

    public void start(Stage primaryStage) {

        primaryStage.setTitle("Get Stock");
        GridPane gridPane = new GridPane();
        final TextField textFieldStock = new TextField();
        Button gs = new Button("Get Stock");
        gs.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                name = textFieldStock.getText();
                name = textFieldStock.getText();
                accountManager.getStock(name);
            }
        });

        gridPane.add(textFieldStock, 0, 0);
        gridPane.add(gs, 1, 0);

        Scene scene = new Scene(gridPane, 205, 10);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
