package main_module.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import main_module.Main;

public class StartController {

    @FXML
    public Button startBtn;

    public void onBegin(ActionEvent actionEvent) {
        Stage stage = (Stage) startBtn.getScene().getWindow();
        stage.close();
        Main.openWindow("controller-view.fxml", "Bomber el man", 1152,768);
    }

}
