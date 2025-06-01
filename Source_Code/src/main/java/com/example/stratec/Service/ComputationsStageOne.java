package com.example.stratec.Service;

import com.example.stratec.Model.Planet;
import com.example.stratec.Model.SolarSystem;

/** The first stage’s requirements are simple: write software that reads the given file and parses it to display the
 * planetary escape velocities for all of the given planets. */
public class ComputationsStageOne {
    private static final double G = 6.67 * Math.pow(10, -11);

    public void computeVelocities(SolarSystem solarSystem) {
        for (Planet planet : solarSystem.getPlanets()) {
            if (planet.getDiameter() == null || planet.getMass() == null) {
                System.out.println("Missing data for planet: " + planet.getName());
                continue;
            }

            double radius = planet.getDiameter() / 2; //  already in meters
            double mass = planet.getMass(); // Already in kg

            double escapeVelocity = 0;
            if (radius > 0 && mass > 0) {
                escapeVelocity = Math.sqrt((2 * G * mass) / radius);
                System.out.printf("Escape velocity for %s: %.2f m/s%n", planet.getName(), escapeVelocity);
            } else {
                System.out.println("Invalid data for planet: " + planet.getName());
            }
            planet.setEscapeVelocity(escapeVelocity);
        }
    }

}

