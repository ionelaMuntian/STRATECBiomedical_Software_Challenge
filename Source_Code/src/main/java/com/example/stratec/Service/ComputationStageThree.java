package com.example.stratec.Service;

import com.example.stratec.Model.Planet;
import com.example.stratec.Model.SolarSystem;

/**
 * The output for this stage must include:
 *  How long it will take the rocket to reach its cruising velocity;
 *  How far away from the starting planet’s surface it will be when it reaches that velocity;
 *  How long it will cruise at the nominal velocity;
 *  How far away from the destination planet’s surface it has to be when it starts its deceleration burn.
 * Remember, planets’ positions are given by their centers;
 *  How long it will take the rocket to decelerate to zero when it reaches the destination planet’s surface.
 *  The total travel time in seconds. To spare the user’s eyesight, this travel time should also be displayed in
 * days, hours, minutes and seconds. For the outer planets, travel times in seconds will be hard to
 * comprehend.
 *
 * */
public class ComputationStageThree {
    private static final double ACCELERATION = 40.0;  // Same acceleration as Stage Two

    private double timeToCruisingVelocity;
    private double distanceFromSurfaceAtCruising;
    private double cruisingTime;
    private double distanceFromDestToStartDeceleration;
    private double decelerationTime;
    private double totalTravelTime;

    public void computeJourneyParameters(Planet startPlanet, Planet destPlanet, SolarSystem solarSystem) {

        // Get escape velocities
        double startPlanetEscapeVelocity = startPlanet.getEscapeVelocity();  // m/s
        double destPlanetEscapeVelocity = destPlanet.getEscapeVelocity();  // m/s

        // Select the higher escape velocity for cruising speed
        double cruisingVelocity = Math.max(startPlanetEscapeVelocity, destPlanetEscapeVelocity);  // m/s

        // Calculate distance between planets in meters
        double distanceBetweenPlanets = Math.abs(startPlanet.getOrbitalRadius() - destPlanet.getOrbitalRadius());

        // Calculate time to reach cruising velocity using the same method as Stage Two
        timeToCruisingVelocity = cruisingVelocity / ACCELERATION;  // seconds

        // Calculate distance traveled before reaching cruising velocity (same formula as Stage Two)
        distanceFromSurfaceAtCruising = (ACCELERATION * Math.pow(timeToCruisingVelocity, 2)) / 2;

        //distanceFromSurfaceAtCruising += startPlanet.getDiameter() / 2;  // Add radius of the planet

        // Calculate cruising time
        double cruisingDistance = distanceBetweenPlanets - (2 * distanceFromSurfaceAtCruising);
        cruisingTime = cruisingDistance / cruisingVelocity;  // seconds

        // Calculate distance from destination to start deceleration
        distanceFromDestToStartDeceleration = distanceFromSurfaceAtCruising;

        // Calculate deceleration time (same as acceleration phase)
        decelerationTime = cruisingVelocity / ACCELERATION;  // seconds

        // Total travel time in seconds
        totalTravelTime = timeToCruisingVelocity + cruisingTime + decelerationTime;
    }

    // Getters for the calculated values
    public double getTimeToCruisingVelocity() {
        return timeToCruisingVelocity;
    }

    public double getDistanceFromSurfaceAtCruising() {
        return distanceFromSurfaceAtCruising;
    }

    public double getCruisingTime() {
        return cruisingTime;
    }

    public double getDistanceFromDestToStartDeceleration() {
        return distanceFromDestToStartDeceleration;
    }

    public double getDecelerationTime() {
        return decelerationTime;
    }

    public String getTotalTravelTimeFormatted() {
        long totalTimeInSeconds = (long) totalTravelTime;
        long days = totalTimeInSeconds / (24 * 3600);
        long hours = (totalTimeInSeconds % (24 * 3600)) / 3600;
        long minutes = (totalTimeInSeconds % 3600) / 60;
        long seconds = totalTimeInSeconds % 60;

        return String.format("%d days %d hours %d minutes %d seconds", days, hours, minutes, seconds);
    }
}