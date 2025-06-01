package com.example.stratec;

import com.example.stratec.DataManipulation.ReadData;
import com.example.stratec.Model.Planet;
import com.example.stratec.Model.SolarSystem;
import com.example.stratec.Service.ComputationsStageFour;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

public class StageFourController {

    @FXML
    private Button computeButton;

    @FXML
    private TextField insertDays;

    @FXML
    private Button startSimulationButton;

    @FXML
    private TextFlow textFlow;

    private SolarSystem solarSystem;

    public void initialize(SolarSystem solarSystem)  {
        this.solarSystem = solarSystem;
    }
    @FXML
    void nextButtonOnAction(ActionEvent event) {
        HelloApplication.switchScene("StageFivePage.fxml", "Next Stage");
    }

    @FXML
    void startSimulationButtonOnAction(ActionEvent event) {
        try {
            int days = Integer.parseInt(insertDays.getText().trim());

            // Get angular positions
            List<Planet> planets = solarSystem.getPlanets();
            Map<String, Double> positions = ComputationsStageFour.computeAngularPositions(planets, days);

            // Display results in UI
            StringBuilder result = new StringBuilder("Angular Positions after " + days + " days:\n");
            for (Map.Entry<String, Double> entry : positions.entrySet()) {
                result.append(entry.getKey()).append(": ")
                        .append(String.format("%.2f", entry.getValue())).append(" degrees\n");
            }
            textFlow.getChildren().clear();
            textFlow.getChildren().add(new Text(result.toString()));

            // Print results to console in a user-friendly format
            System.out.println("\n\n✅ Stage 4: Angular Positions for each planet: 🚀\n");
            System.out.println("-----------------------------------");
            for (Map.Entry<String, Double> entry : positions.entrySet()) {
                System.out.printf("%-12s: %-15.2f degrees\n", entry.getKey(), entry.getValue());
            }
            System.out.println("-----------------------------------");
        } catch (NumberFormatException e) {
            textFlow.getChildren().clear();
            textFlow.getChildren().add(new Text("Invalid input! Please enter a valid number of days."));
            System.out.println("❌ Error: Invalid input! Please enter a valid number of days.");
        }
    }
}
