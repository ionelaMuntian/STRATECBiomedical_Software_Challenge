package com.example.stratec;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainController {

    @FXML
    private Button stageFive;

    @FXML
    private Button stageFour;

    @FXML
    private Button stageOne;

    @FXML
    private Button stageSix;

    @FXML
    private Button stageThree;

    @FXML
    private Button stageTwo;

    @FXML
    void stageFiveOnAction(ActionEvent event) {
        HelloApplication.switchScene("StageFivePage.fxml", "Main Page");
    }

    @FXML
    void stageFourOnAction(ActionEvent event) {
        HelloApplication.switchScene("StageFourPage.fxml", "Main Page");
    }

    @FXML
    void stageOneOnAction(ActionEvent event) {
        HelloApplication.switchScene("StageOnePage.fxml", "Main Page");

    }

    @FXML
    void stageSixOnAction(ActionEvent event) {
        HelloApplication.switchScene("StageSixPage.fxml", "Main Page");
    }

    @FXML
    void stageThreeOnAction(ActionEvent event) {
        HelloApplication.switchScene("StageThreePage.fxml", "Main Page");
    }

    @FXML
    void stageTwoOnAction(ActionEvent event) {
        HelloApplication.switchScene("StageTwoPage.fxml", "Main Page");
    }

}
