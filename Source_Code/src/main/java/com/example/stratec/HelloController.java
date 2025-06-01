package com.example.stratec;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HelloController {

    @FXML
    private Button startButton;

    @FXML
    void startButtonOnAction(ActionEvent event) {
        HelloApplication.switchScene("MainPage.fxml", "Main Page");
    }

}
