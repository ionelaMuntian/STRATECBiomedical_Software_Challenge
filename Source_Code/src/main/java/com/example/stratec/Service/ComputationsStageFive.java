package com.example.stratec.Service;

import com.example.stratec.Model.Planet;
import com.example.stratec.Model.SolarSystem;
import java.util.Map;
/**
 * We’ll consider a starting time of t0 + 100 years since the beginning of our solar system simulation. That means
 that one hundred years have passed since the planets were all perfectly aligned and they’re now arranged in
 various positions around the Sun. We’ll be searching for optimal transfer windows, or periods when journeys
 between planets are as easy and as quick as possible.
 For this stage, the user will again choose two planets, a starting planet and a destination planet. Your application
 must then return the same information that it did for stage three and the start of the first optimal transfer
 window.

 * */

public class ComputationsStageFive {
    private static final double ACCELERATION = 40.0;  // Rocket acceleration in m/s^2
    private static final double G = 6.67430e-11;  // Gravitational constant, in m^3 kg^-1 s^-2
    private static final double SUN_MASS = 1.989e30;  // Mass of the Sun in kg

    public String computeJourneyDetails(Planet startPlanet, Planet destPlanet, SolarSystem solarSystem, int daysSinceStart) {
        // Calculate the optimal transfer window first
        String transferWindow = calculateOptimalTransferWindow(startPlanet, destPlanet, daysSinceStart);
        double timeToLaunch = extractTimeFromTransferWindow(transferWindow); // Parse the launch time

        // Get the current angular positions of all planets at the optimal launch time
        Map<String, Double> angularPositions = ComputationsStageFour.computeAngularPositions(solarSystem.getPlanets(), (int)timeToLaunch);

        // Get the angular positions of the start and destination planets at the optimal time
        double startPlanetAngle = Math.toRadians(angularPositions.get(startPlanet.getName()));
        double destPlanetAngle = Math.toRadians(angularPositions.get(destPlanet.getName()));

        // Compute the distance between the two planets based on their orbital radii and angles at the launch time
        double distanceBetweenPlanets = calculateDistanceBetweenPlanets(startPlanet, destPlanet, startPlanetAngle, destPlanetAngle);

        // Get the escape velocities of the planets
        double startPlanetEscapeVelocity = startPlanet.getEscapeVelocity();
        double destPlanetEscapeVelocity = destPlanet.getEscapeVelocity();

        // Calculate cruising velocity (same as escape velocity for simplicity)
        double cruisingVelocity = Math.max(startPlanetEscapeVelocity, destPlanetEscapeVelocity);

        // Time to reach cruising velocity
        double timeToCruisingVelocity = cruisingVelocity / ACCELERATION;

        // Distance from surface when reaching cruising velocity
        double distanceFromSurfaceAtCruising = (ACCELERATION * Math.pow(timeToCruisingVelocity, 2)) / 2;

        // Time spent cruising (linear distance)
        double cruisingDistance = distanceBetweenPlanets - (2 * distanceFromSurfaceAtCruising);
        double cruisingTime = cruisingDistance / cruisingVelocity;

        // Deceleration to zero at destination
        double timeToDecelerate = cruisingVelocity / ACCELERATION;
        double distanceFromSurfaceAtDeceleration = (ACCELERATION * Math.pow(timeToDecelerate, 2)) / 2;

        // Total time to decelerate to zero at the destination
        double totalTimeToDestination = cruisingTime + timeToCruisingVelocity + timeToDecelerate;

        // Format total time in seconds
        long totalTimeInSeconds = Math.round(totalTimeToDestination);

        // Convert total time to days, hours, minutes, and seconds
        String formattedTime = formatTime(totalTimeInSeconds);

        // Return the formatted results
        return String.format("Journey Details:\n\n" +
                        "Start Planet: %s\nDestination Planet: %s\n\n" +
                        "Cruising Velocity: %.2f m/s\n" +
                        "Time to Cruising Velocity: %.2f seconds\n" +
                        "Distance from Start Planet's Surface at Cruising Velocity: %.2f km\n" +
                        "Cruising Time: %.2f seconds\n" +
                        "Distance from Destination Planet's Surface at Deceleration: %.2f km\n" +
                        "Time to Decelerate to Zero: %.2f seconds\n\n" +
                        "Total Travel Time: %s\n" +
                        "Optimal Transfer Window: %s",
                startPlanet.getName(), destPlanet.getName(),
                cruisingVelocity, timeToCruisingVelocity, distanceFromSurfaceAtCruising / 1000.0, cruisingTime,
                distanceFromSurfaceAtDeceleration / 1000.0, timeToDecelerate,
                formattedTime, transferWindow);
    }

    private double calculateDistanceBetweenPlanets(Planet startPlanet, Planet destPlanet, double startPlanetAngle, double destPlanetAngle) {
        // Use Law of Cosines to calculate the distance between two planets
        double r1 = startPlanet.getOrbitalRadius();
        double r2 = destPlanet.getOrbitalRadius();
        double deltaTheta = Math.abs(startPlanetAngle - destPlanetAngle);

        // Calculate the distance between the planets at the given time
        return Math.sqrt(r1 * r1 + r2 * r2 - 2 * r1 * r2 * Math.cos(deltaTheta));
    }

    private String formatTime(long totalTimeInSeconds) {
        // Convert seconds to days, hours, minutes, seconds
        long days = totalTimeInSeconds / 86400;
        long hours = (totalTimeInSeconds % 86400) / 3600;
        long minutes = (totalTimeInSeconds % 3600) / 60;
        long seconds = totalTimeInSeconds % 60;

        return String.format("%d days, %d hours, %d minutes, %d seconds", days, hours, minutes, seconds);
    }

    private String calculateOptimalTransferWindow(Planet startPlanet, Planet destPlanet, int daysSinceStart) {
        // Get the orbital periods of the planets in Earth days
        double P1 = startPlanet.getOrbitalPeriod();  // Orbital period of the start planet (in days)
        double P2 = destPlanet.getOrbitalPeriod();   // Orbital period of the destination planet (in days)

        // Calculate the synodic period
        double synodicPeriod = Math.abs(1 / P1 - 1 / P2);

        // Compute the time between the 100 days and the optimal time to launch
        double optimalLaunchTime = synodicPeriod * (Math.round((daysSinceStart) / synodicPeriod));

        // Calculate the difference between the current time and the next optimal launch window
        double timeToOptimalLaunch = optimalLaunchTime - daysSinceStart;

        return String.format("In %.2f days (from current day)", timeToOptimalLaunch);
    }

    private double extractTimeFromTransferWindow(String transferWindow) {
        // Extract time to launch (in days) from the transfer window message
        String[] parts = transferWindow.split(" ");
        return Double.parseDouble(parts[1]);
    }
}
