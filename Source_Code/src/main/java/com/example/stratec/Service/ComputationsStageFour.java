package com.example.stratec.Service;

import com.example.stratec.Model.Planet;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * Your application should accept a random time input from the user, given in days, and return or display the
 * angular positions for each of the planets on that chosen day.
 * */
public class ComputationsStageFour {

    public static Map<String, Double> computeAngularPositions(List<Planet> planets, int days) {
        Map<String, Double> positions = new HashMap<>();

        for (Planet planet : planets) {
            if (planet.getOrbitalPeriod() != null && planet.getOrbitalPeriod() > 0) {
                double angle = (days / (double) planet.getOrbitalPeriod() * 360) % 360;
                positions.put(planet.getName(), angle);
            } else {
                positions.put(planet.getName(), 0.0);
            }
        }
        return positions;
    }
}
