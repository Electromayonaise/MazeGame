/**
 * The Main class serves as the entry point for the Bomberman Game.
 * It extends the JavaFX Application class and contains methods for initializing
 * and launching the graphical user interface.

 */
package main_module;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The Main class extends the JavaFX Application and serves as the entry point for
 * the Bomberman application. It initializes the graphical user interface and launches
 * the start screen.
 */
public class Main extends Application {

    /**
     * A boolean indicating whether the Bomberman game is directed or not.
     */
    public static boolean directed;

    /**
     * A boolean indicating whether the Bomberman game is represented using an adjacency list.
     */
    public static boolean adjacencyList;

    /**
     * The start method is called when the application is launched. It opens the start screen
     * using the openWindow method.
     *
     * @param stage The primary stage for the application.
     * @throws IOException If an error occurs while loading the FXML file.
     */
    @Override
    public void start(Stage stage) throws IOException {
        openWindow("start-screen.fxml","Bomberman", 625, 475);
    }

    /**
     * Opens a new window with the specified FXML file, title, width, and height.
     *
     * @param fxml   The path to the FXML file for the new window.
     * @param title  The title of the new window.
     * @param width  The width of the new window.
     * @param height The height of the new window.
     */
    public static void openWindow(String fxml, String title, int width, int height){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml));
            Scene scene = new Scene(fxmlLoader.load(), width, height);
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }

    /**
     * Sets the boolean values for directed and adjacencyList based on the provided parameters.
     *
     * @param directed      A boolean indicating whether the Bomberman game is directed.
     * @param adjacencyList A boolean indicating whether the Bomberman game is represented using an adjacency list.
     */
    public static void setBooleans(boolean directed, boolean adjacencyList){
        Main.directed = directed;
        Main.adjacencyList = adjacencyList;
    }

    /**
     * The main method is the entry point for the Bomberman game.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        launch();
    }
}
