package thecoolerduke.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import thecoolerduke.main.TheCoolerDuke;


/**
 * The main application handling the display and bot processing.
 */
public class UI extends Application {
    private TheCoolerDuke bot = new TheCoolerDuke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(UI.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBot(bot); // inject the Bot instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
