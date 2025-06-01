package com.example.stratec.Service;

import com.example.stratec.Model.Planet;
import com.example.stratec.Model.SolarSystem;

public class ComputationsStageTwo {
    private static final double ACCELERATION = 40.0;

    public void computeEscapeTimeAndDistance(SolarSystem solarSystem) {
        for (Planet planet : solarSystem.getPlanets()) {
            double escapeVelocity = planet.getEscapeVelocity();

            if (escapeVelocity > 0) {
                // Compute time to reach escape velocity
                double timeToEscape = escapeVelocity / ACCELERATION;  // seconds

                // Compute distance traveled before reaching escape velocity
                double distanceTraveled = (ACCELERATION * Math.pow(timeToEscape, 2)) / 2;  // meters

                // Compute total distance from planet's center
                double totalDistance = (planet.getDiameter() / 2) + distanceTraveled;  // meters

                // Set values for the planet
                planet.setTimeEscape(timeToEscape);  // seconds
                planet.setDistanceEscape(totalDistance);  // meters

                // Output results
                System.out.println(planet.getName() + ": ");
                System.out.printf("Time to reach escape velocity: %.2f seconds%n", timeToEscape);
                System.out.printf("Distance traveled: %.2f meters%n", distanceTraveled);
                System.out.printf("Total distance from planet center: %.2f meters%n%n", totalDistance);
            } else {
                System.out.println("Invalid escape velocity for planet: " + planet.getName());
            }
        }
    }
}
