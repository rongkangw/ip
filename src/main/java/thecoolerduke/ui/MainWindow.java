package thecoolerduke.ui;

import java.util.Objects;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import thecoolerduke.main.TheCoolerDuke;
import thecoolerduke.ui.components.DialogBox;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private TheCoolerDuke bot;

    private final Image userImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/User.png")));
    private final Image botImage = new Image(
            Objects.requireNonNull(this.getClass().getResourceAsStream("/images/Bot.png")));

    /**
     * Initialises the bot with its startup sequence.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    // Injects the Bot instance
    public void setBot(TheCoolerDuke d) {
        bot = d;

        dialogContainer.getChildren().addAll(
                DialogBox.getBotDialog(bot.run(), botImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        String response = bot.processResponse(input);

        /*
        Closes application upon seeing "bye" command.
        See note in TheCoolerDuke.processResponse() for more information.
         */
        if (input.equals("bye")) {
            Platform.exit();
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBotDialog(response, botImage)
        );
        userInput.clear();
    }
}
