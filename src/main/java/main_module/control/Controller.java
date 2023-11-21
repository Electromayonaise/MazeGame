package main_module.control;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import main_module.BaseScreen;
import main_module.screens.MenuScreen;
import main_module.screens.ScreenA;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;
import javafx.scene.layout.Pane;

/**
 * Controller class that manages the interaction between the user interface and the game logic.
 */
public class Controller implements Initializable {

    @FXML
    private Canvas canvas;
    @FXML
    private GraphicsContext gc;

    private BaseScreen screenA;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Canvas camerCanvas;

    private AtomicBoolean endGame = new AtomicBoolean(false);

    private MenuScreen menuScreen;

    /**
     * Initializes the controller.
     *
     * @param url            The location used to resolve relative paths for the root object.
     * @param resourceBundle The resources specific to this controller.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.gc = canvas.getGraphicsContext2D();

        initMenu();
    }

    /**
     * Starts the game thread, handling game updates and screen rendering.
     */
    public void startGameThread() {

        initActions();

        new Thread(() -> {
            while (!endGame.get()) {
                Platform.runLater(() -> {
                    endGame.set(screenA.update());
                    screenA.paint();
                });
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            Platform.runLater(() -> {
                Image gameOverImage = new Image(((System.getProperty("user.dir") + "/src/main/resources/main_module/animations/player/GameOver.png")), false);

                double imageWidth = 300;
                double x = 450;
                double y = 150;

                gc.drawImage(gameOverImage, x, y, imageWidth, gameOverImage.getHeight());
            });

            try {
                Thread.sleep(5000); // Display "GameOver" for 5 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Platform.runLater(() -> {
                initMenu(); // Display the menu again
                endGame.set(false);
            });
        }).start();
    }

    /**
     * Initializes the user input actions.
     */
    public void initActions() {
        screenA = new ScreenA(canvas,false,false);

        canvas.setOnKeyReleased(event -> {
            screenA.setOnKeyReleased(event);
        });

        canvas.setOnKeyPressed(event -> {
            screenA.setOnKeyPressed(event);
        });
    }

    /**
     * Initializes the main menu.
     */
    private void initMenu() {
        menuScreen = new MenuScreen();
        Pane menuPane = new Pane(menuScreen.getMenuLayout());
        menuPane.setStyle("-fx-background-color: navy;");

        double centerX = 450;
        double centerY = 255;
        menuPane.setLayoutX(centerX);
        menuPane.setLayoutY(centerY);

        menuScreen.setNewGameAction(() -> {
            anchorPane.getChildren().remove(menuPane); // Hide the menu
            startGameThread();
        });

        menuScreen.setCloseGameAction(() -> {
            // Close the application
            System.exit(0);
        });

        anchorPane.getChildren().add(menuPane);
    }
}
