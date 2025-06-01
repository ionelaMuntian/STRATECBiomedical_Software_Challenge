package com.example.stratec;

import com.example.stratec.Model.Planet;
import com.example.stratec.Model.SolarSystem;
import com.example.stratec.Service.ComputationStageThree;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.text.Text;

public class StageThreeController {

    @FXML
    private Button computeButton;

    @FXML
    private ComboBox<Planet> destPlanetOption;

    @FXML
    private Button nextButton;

    @FXML
    private Text raspuns1, raspuns2, raspuns3, raspuns4, raspuns5, raspuns6;

    @FXML
    private ComboBox<Planet> startPlanetOption;

    private SolarSystem solarSystem;

    public void init(SolarSystem solarSystem) {
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
    void nextButtonOnAction(ActionEvent event) {
        HelloApplication.switchScene("StageFourPage.fxml", "Next Stage");
    }
    @FXML
    void computeButtonOnAction(ActionEvent event) {
        Planet startPlanet = startPlanetOption.getValue();
        Planet destPlanet = destPlanetOption.getValue();

        // Check if both planets are selected
        if (startPlanet != null && destPlanet != null) {
            // Perform the computation for the journey parameters
            ComputationStageThree computations = new ComputationStageThree();
            computations.computeJourneyParameters(startPlanet, destPlanet, solarSystem);

            // Set the computed results in the Text fields with units (in kilometers)
            raspuns1.setText(String.format("%.2f seconds", computations.getTimeToCruisingVelocity()));

            // Convert distances from meters to kilometers
            raspuns2.setText(String.format("%.2f km", computations.getDistanceFromSurfaceAtCruising() / 1000.0));
            raspuns3.setText(String.format("%.2f seconds", computations.getCruisingTime()));

            // Convert distances from meters to kilometers
            raspuns4.setText(String.format("%.2f km", computations.getDistanceFromDestToStartDeceleration() / 1000.0));
            raspuns5.setText(String.format("%.2f seconds", computations.getDecelerationTime()));
            raspuns6.setText(String.format(computations.getTotalTravelTimeFormatted()));


            System.out.println("\n\n✅ Stage 3: Journey Computation Results: 🚀\n\n");

            System.out.println("----------------------------------");
            System.out.println("Start Planet: " + startPlanet.getName());
            System.out.println("Destination Planet: " + destPlanet.getName());
            System.out.println("Time to Cruising Velocity: " + String.format("%.2f seconds", computations.getTimeToCruisingVelocity()));
            System.out.println("Distance from Surface at Cruising Speed: " + String.format("%.2f km", computations.getDistanceFromSurfaceAtCruising() / 1000.0));
            System.out.println("Cruising Time: " + String.format("%.2f seconds", computations.getCruisingTime()));
            System.out.println("Distance from Destination to Start of Deceleration: " + String.format("%.2f km", computations.getDistanceFromDestToStartDeceleration() / 1000.0));
            System.out.println("Deceleration Time: " + String.format("%.2f seconds", computations.getDecelerationTime()));
            System.out.println("Total Travel Time: " +  computations.getTotalTravelTimeFormatted());
            System.out.println("----------------------------------");

        } else {
            raspuns1.setText("Please select both start and destination planets.");
        }
    }

    @FXML
    void destPlanetOptionOnAction(ActionEvent event) {
        Planet selectedDestPlanet = destPlanetOption.getValue();
        if (selectedDestPlanet != null) {
            // Perform actions, like enabling/disabling buttons or updating UI
            System.out.println("Destination planet selected: " + selectedDestPlanet.getName());
        }
    }

    @FXML
    void startPlanetOptionOnAction(ActionEvent event) {
        Planet selectedStartPlanet = startPlanetOption.getValue();
        if (selectedStartPlanet != null) {
            System.out.println("Start planet selected: " + selectedStartPlanet.getName());
        }
    }


}
