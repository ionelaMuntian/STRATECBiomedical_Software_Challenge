package com.example.stratec;

import com.example.stratec.Model.Planet;
import com.example.stratec.Model.SolarSystem;
import com.example.stratec.Service.ComputationsStageOne;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class StageOneController {

    @FXML
    private Button nextButton;

    @FXML
    private TextFlow textFlow;

    @FXML
    void nextButtonOnAction(ActionEvent event) {
        HelloApplication.switchScene("StageTwoPage.fxml", "Stage 2");
    }

    /**
     * Display planetary data inside TextFlow.
     */
    public void displayPlanetaryData(SolarSystem solarSystem) {
        System.out.println("✅ Stage 1: Velocity for each planet: 🚀\n\n");

        // Compute escape velocities
        ComputationsStageOne computations = new ComputationsStageOne();
        computations.computeVelocities(solarSystem);

        StringBuilder data = new StringBuilder();
        for (Planet planet : solarSystem.getPlanets()) {
            double escapeVelocityKmS = planet.getEscapeVelocity() / 1000.0;  // Convert m/s to km/s
            data.append(String.format("Planet: %s\nEscape Velocity: %.2f km/s\n\n",
                    planet.getName(), escapeVelocityKmS));
        }

        textFlow.getChildren().clear();
        textFlow.getChildren().add(new Text(data.toString()));
    }
}
