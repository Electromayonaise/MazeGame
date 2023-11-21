package main_module.screens;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Represents the main menu screen with options for starting a new game or closing the application.
 */
public class MenuScreen {

    private VBox menuLayout;

    /**
     * Initializes the menu screen.
     */
    public MenuScreen() {
        initMenu();
    }

    /**
     * Initializes the layout and components of the menu.
     */
    private void initMenu() {
        menuLayout = new VBox(40);
        menuLayout.setAlignment(Pos.CENTER);

        Label title = new Label("Bomberman!");
        title.setStyle("-fx-font-size: 48px; -fx-font-weight: bold; -fx-background-color: yellow; -fx-text-fill: black; -fx-padding: 5px;");
        Button newGameButton = new Button("New Game");
        newGameButton.setStyle("-fx-font-size: 24px;");
        Button closeGameButton = new Button("Close Game");
        closeGameButton.setStyle("-fx-font-size: 24px;");

        menuLayout.getChildren().addAll(title, newGameButton, closeGameButton);
    }

    /**
     * Gets the layout of the menu.
     *
     * @return The VBox layout of the menu.
     */
    public VBox getMenuLayout() {
        return menuLayout;
    }

    /**
     * Sets the action to be executed when the "New Game" button is clicked.
     *
     * @param newGameAction The action to be executed.
     */
    public void setNewGameAction(Runnable newGameAction) {
        Button newGameButton = (Button) menuLayout.getChildren().get(1);
        newGameButton.setOnAction(event -> newGameAction.run());
    }

    /**
     * Sets the action to be executed when the "Close Game" button is clicked.
     *
     * @param closeGameAction The action to be executed.
     */
    public void setCloseGameAction(Runnable closeGameAction) {
        Button closeGameButton = (Button) menuLayout.getChildren().get(2);
        closeGameButton.setOnAction(event -> closeGameAction.run());
    }
}
