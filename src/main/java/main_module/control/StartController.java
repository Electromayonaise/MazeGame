package main_module.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import main_module.Main;

public class StartController {

    @FXML
    public Button startBtn;

    @FXML
    public ComboBox chooseImplementation;

    @FXML
    public ComboBox chooseRepresentation;

    public boolean directed;

    public boolean adjacencyList;

    public void onBegin(ActionEvent actionEvent) {
        if (chooseImplementation.getValue() == null || chooseRepresentation.getValue() == null) {
            showAlert("Warning", "Please choose both an implementation and a representation.");
        }
        else {
            Stage stage = (Stage) startBtn.getScene().getWindow();
            stage.close();
            onImplementation(actionEvent);
            onRepresentation(actionEvent);
            Main.setBooleans(this.directed, this.adjacencyList);
            Main.openWindow("controller-view.fxml", "Bomber el man", 1152, 768);
        }

    }

    public void onImplementation(ActionEvent actionEvent){
        String implementation = chooseImplementation.getValue().toString();
        if(implementation.equals("Directed graph")){
            this.directed = true;
        }
        else if(implementation.equals("Undirected graph")){
            this.directed = false;
        }
    }

    public void onRepresentation(ActionEvent actionEvent){
        String representation = chooseRepresentation.getValue().toString();
        if(representation.equals("Adjacency matrix")){
            this.adjacencyList = false;
        }
        else if(representation.equals("Adjacency list")){
            this.adjacencyList = true;
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
