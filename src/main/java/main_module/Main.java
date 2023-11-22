package main_module;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        openWindow("start-screen.fxml","Bomberman", 625, 475);
    }

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

    public static void main(String[] args) {
        launch();
    }
}