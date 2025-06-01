package com.example.stratec;

import com.example.stratec.Model.Planet;
import com.example.stratec.Model.SolarSystem;
import com.example.stratec.Service.ComputationsStageTwo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class StageTwoController {

    @FXML
    private Button nextButton;

    @FXML
    private TextFlow textFlow;

    @FXML
    private TextFlow textFlow2;

    @FXML
    void nextButtonOnAction(ActionEvent event) {
        HelloApplication.switchScene("StageThreePage.fxml", "Page Three");
    }

    public void displayEscapeCalculations(SolarSystem solarSystem) {
        System.out.println("✅ Stage 2: Computing escape time and distance for all planets 🚀\n\n");

        StringBuilder data = new StringBuilder();
        StringBuilder formulas = new StringBuilder();
        ComputationsStageTwo computations = new ComputationsStageTwo();

        // Compute escape time and distance for each planet in the solar system
        computations.computeEscapeTimeAndDistance(solarSystem);

        for (Planet planet : solarSystem.getPlanets()) {
            // Convert distance from meters to kilometers
            double distanceInKm = planet.getDistanceEscape() / 1000.0;

            data.append(String.format("Planet: %s\nTime to reach escape velocity T: %.2f seconds\nDistance traveled: %.2f km\n\n",
                    planet.getName(), planet.getTimeEscape(), distanceInKm));
        }

        // General formulas for time and distance calculations
        formulas.append("To compute the time required to reach escape velocity:\n")
                .append("\n")
                .append("    t = vₑ / a\n")
                .append("\n")
                .append("where:\n")
                .append("    t  = time to reach escape velocity (seconds)\n")
                .append("    vₑ = escape velocity (m/s)\n")
                .append("    a  = acceleration (m/s²)\n")
                .append("\n")
                .append("To compute the distance traveled before reaching escape velocity:\n")
                .append("\n")
                .append("    d = v₀ * t + (1/2) * a * t²\n")
                .append("\n")
                .append("where:\n")
                .append("    d  = distance traveled (meters)\n")
                .append("    v₀ = initial velocity (m/s), typically 0 for launch\n")
                .append("    t  = time computed from the first formula\n")
                .append("    a  = acceleration (m/s²)\n");

        textFlow.getChildren().clear();
        textFlow.getChildren().add(new Text(data.toString()));

        textFlow2.getChildren().clear();
        textFlow2.getChildren().add(new Text(formulas.toString()));
    }
}
