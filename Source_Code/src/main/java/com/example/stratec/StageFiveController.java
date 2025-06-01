package com.example.stratec;

import com.example.stratec.Model.Planet;
import com.example.stratec.Model.SolarSystem;
import com.example.stratec.Service.ComputationsStageFive;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

public class StageFiveController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button computeButton;

    @FXML
    private ComboBox<Planet> destPlanetOption;

    @FXML
    private Button nextButton;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private ComboBox<Planet> startPlanetOption;

    @FXML
    private TextArea textArea;

    private SolarSystem solarSystem;  // To store the passed SolarSystem

    // This method will be called to initialize the controller with a SolarSystem
    public void initialize(SolarSystem solarSystem) {
        this.solarSystem = solarSystem;

        startPlanetOption.getItems().clear();
        startPlanetOption.getItems().addAll(solarSystem.getPlanets());

        destPlanetOption.getItems().clear();
        destPlanetOption.getItems().addAll(solarSystem.getPlanets());

        startPlanetOption.setCellFactory(param -> new javafx.scene.control.ListCell<Planet>() {
            @Override
            protected void updateItem(Planet item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });

        destPlanetOption.setCellFactory(param -> new javafx.scene.control.ListCell<Planet>() {
            @Override
            protected void updateItem(Planet item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });

        startPlanetOption.setButtonCell(new javafx.scene.control.ListCell<Planet>() {
            @Override
            protected void updateItem(Planet item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });

        destPlanetOption.setButtonCell(new javafx.scene.control.ListCell<Planet>() {
            @Override
            protected void updateItem(Planet item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });
    }

    @FXML
    void computeButtonOnAction(ActionEvent event) {
        // Get the selected planets from the ComboBoxes
        Planet startPlanet = startPlanetOption.getSelectionModel().getSelectedItem();
        Planet destPlanet = destPlanetOption.getSelectionModel().getSelectedItem();

        // Check if both planets are selected
        if (startPlanet != null && destPlanet != null) {
            // Call the ComputationsStageFive service to compute the journey details
            ComputationsStageFive computations = new ComputationsStageFive();
            String journeyDetails = computations.computeJourneyDetails(startPlanet, destPlanet, solarSystem, 100*365);  // Assuming 100 years

            // Display the results in the TextArea
            textArea.setText(journeyDetails);

            // Print everything in the console
            System.out.println("\n\n✅ Stage 5: Journey Computation Results: 🚀\n\n");
            System.out.println(journeyDetails);
        } else {
            String errorMessage = "Please select both start and destination planets.";
            textArea.setText(errorMessage);
            System.out.println(errorMessage);
        }
    }

    @FXML
    void nextButtonOnAction(ActionEvent event) {
        HelloApplication.switchScene("StageSixPage.fxml", "Next Stage");
    }
}
