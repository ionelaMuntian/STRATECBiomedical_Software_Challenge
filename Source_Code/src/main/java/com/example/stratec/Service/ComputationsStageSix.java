package com.example.stratec.Service;

import com.example.stratec.Model.Planet;
import com.example.stratec.Model.SolarSystem;

import java.util.Map;
/**
 * The goals, constraints and assumptions for this stage are almost identical to those in stage five, except for one: the
 * solar system will no longer freeze once a rocket journey is underway. This means that, once the user has chosen
 * the starting planet and the destination planet, the optimal transfer window must be computed knowing that
 * planets may travel into the rocket’s path while the rocket is on its way to its destination.
 *
 * */

public class ComputationsStageSix {
    private static final double ACCELERATION = 40.0;
    private static final double G = 6.67430e-11;
    private static final double SUN_MASS = 1.989e30;

    public String computeJourneyDetails(Planet startPlanet, Planet destPlanet, SolarSystem solarSystem, int daysSinceStart) {
        // Get the current angular positions of all planets at the given time
        Map<String, Double> angularPositions = ComputationsStageFour.computeAngularPositions(solarSystem.getPlanets(), daysSinceStart);

        // Get the angular positions of the start and destination planets
        double startPlanetAngle = Math.toRadians(angularPositions.get(startPlanet.getName()));
        double destPlanetAngle = Math.toRadians(angularPositions.get(destPlanet.getName()));

        // Calculate the current distance between the planets
        double distanceBetweenPlanets = calculateDistanceBetweenPlanets(startPlanet, destPlanet, startPlanetAngle, destPlanetAngle);

        // Calculate the optimal transfer window (Hohmann Transfer)
        double optimalTransferTime = calculateOptimalTransferTime(startPlanet, destPlanet);
        double optimalLaunchTime = daysSinceStart + optimalTransferTime;

        // Calculate the planetary positions at the optimal launch time
        Map<String, Double> optimalPositions = ComputationsStageFour.computeAngularPositions(solarSystem.getPlanets(), (int) optimalLaunchTime);

        // Recalculate the distance based on the optimal positions
        double optimalStartPlanetAngle = Math.toRadians(optimalPositions.get(startPlanet.getName()));
        double optimalDestPlanetAngle = Math.toRadians(optimalPositions.get(destPlanet.getName()));

        double optimalDistanceBetweenPlanets = calculateDistanceBetweenPlanets(startPlanet, destPlanet, optimalStartPlanetAngle, optimalDestPlanetAngle);

        // Calculate escape velocities and cruising velocities
        double startPlanetEscapeVelocity = startPlanet.getEscapeVelocity();
        double destPlanetEscapeVelocity = destPlanet.getEscapeVelocity();
        double cruisingVelocity = Math.max(startPlanetEscapeVelocity, destPlanetEscapeVelocity);

        // Calculate the time and distance parameters for the journey
        double timeToCruisingVelocity = cruisingVelocity / ACCELERATION;
        double distanceFromSurfaceAtCruising = (ACCELERATION * Math.pow(timeToCruisingVelocity, 2)) / 2;
        double cruisingDistance = optimalDistanceBetweenPlanets - (2 * distanceFromSurfaceAtCruising);
        double cruisingTime = cruisingDistance / cruisingVelocity;
        double timeToDecelerate = cruisingVelocity / ACCELERATION;
        double distanceFromSurfaceAtDeceleration = (ACCELERATION * Math.pow(timeToDecelerate, 2)) / 2;

        // Total time to destination (including acceleration and deceleration)
        double totalTimeToDestination = cruisingTime + timeToCruisingVelocity + timeToDecelerate;
        long totalTimeInSeconds = Math.round(totalTimeToDestination);
        String formattedTime = formatTime(totalTimeInSeconds);

        // Calculate the arrival time in terms of days since the start
        int arrivalTime = daysSinceStart + (int) (totalTimeInSeconds / 86400);

        // Get the updated planetary positions at arrival time
        Map<String, Double> updatedPositions = ComputationsStageFour.computeAngularPositions(solarSystem.getPlanets(), arrivalTime);

        // Format the updated position details
        StringBuilder updatedPositionDetails = new StringBuilder();
        for (Map.Entry<String, Double> entry : updatedPositions.entrySet()) {
            updatedPositionDetails.append(String.format("   %s: (%.2f degrees)\n", entry.getKey(), entry.getValue()));
        }

        // Return the formatted journey details
        return String.format("Journey Details:\n\n" +
                        "Start Planet: %s\nDestination Planet: %s\n\n" +
                        "Cruising Velocity: %.2f m/s\n" +
                        "Time to Cruising Velocity: %.2f seconds\n" +
                        "Distance from Start Planet's Surface at Cruising Velocity: %.2f km\n" +
                        "Cruising Time: %.2f seconds\n" +
                        "Distance from Destination Planet's Surface at Deceleration: %.2f km\n" +
                        "Time to Decelerate to Zero: %.2f seconds\n\n" +
                        "Total Travel Time: %s\n" +
                        "Updated Position at Arrival:\n%s\n\n" +
                        "Optimal Launch Window: %.2f days from start",
                startPlanet.getName(), destPlanet.getName(),
                cruisingVelocity, timeToCruisingVelocity, distanceFromSurfaceAtCruising / 1000.0, cruisingTime,
                distanceFromSurfaceAtDeceleration / 1000.0, timeToDecelerate,
                formattedTime, updatedPositionDetails.toString(), optimalTransferTime);
    }

    private double calculateDistanceBetweenPlanets(Planet startPlanet, Planet destPlanet, double startPlanetAngle, double destPlanetAngle) {
        double r1 = startPlanet.getOrbitalRadius();
        double r2 = destPlanet.getOrbitalRadius();
        double deltaTheta = Math.abs(startPlanetAngle - destPlanetAngle);
        return Math.sqrt(r1 * r1 + r2 * r2 - 2 * r1 * r2 * Math.cos(deltaTheta));
    }

    private double calculateOptimalTransferTime(Planet startPlanet, Planet destPlanet) {
        // Use Hohmann Transfer to calculate the optimal transfer time
        double r1 = startPlanet.getOrbitalRadius();
        double r2 = destPlanet.getOrbitalRadius();

        // Calculate the semi-major axis of the elliptical orbit
        double a = (r1 + r2) / 2;

        // Calculate the orbital period of the transfer orbit (using Kepler's 3rd law)
        double period = 2 * Math.PI * Math.sqrt(Math.pow(a, 3) / (G * SUN_MASS));

        // The transfer time is half of the orbital period (since it's a Hohmann transfer)
        return period / 2 / 86400; // Convert from seconds to days
    }

    private String formatTime(long totalTimeInSeconds) {
        long days = totalTimeInSeconds / 86400;
        long hours = (totalTimeInSeconds % 86400) / 3600;
        long minutes = (totalTimeInSeconds % 3600) / 60;
        long seconds = totalTimeInSeconds % 60;
        return String.format("%d days, %d hours, %d minutes, %d seconds", days, hours, minutes, seconds);
    }
}
