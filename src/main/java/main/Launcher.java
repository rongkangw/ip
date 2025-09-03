package main;

import javafx.application.Application;
import ui.UI;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(UI.class, args);
    }
}
