package com.example.stratec.DataManipulation;

import com.example.stratec.Model.Planet;
import com.example.stratec.Model.Rocket;
import com.example.stratec.Model.SolarSystem;

import java.io.*;
import java.net.URISyntaxException;
import java.util.*;

public class ReadData {
    private static final double EARTH_KG = 5.972e24;
    private static final double AU_TO_METERS = 1.496e11;

    public static SolarSystem readPlanetsData() throws URISyntaxException {
        List<Planet> planets = new ArrayList<>();

        File planetaryDataFile = new File(ReadData.class.getResource("/com/example/stratec/Planetary_Data.txt").toURI());
        File solarSystemDataFile = new File(ReadData.class.getResource("/com/example/stratec/Solar_System_Data.txt").toURI());

        try (BufferedReader br = new BufferedReader(new FileReader(planetaryDataFile))) {

            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(": ");
                if (parts.length == 2) {
                    String name = parts[0].trim();
                    String[] attributes = parts[1].split(", ");

                    if (attributes.length == 2) {
                        double diameter = extractNumericValue(attributes[0], "km")*1000;//store in meters
                        double mass = extractMass(attributes[1]);
                        planets.add(new Planet(name, diameter, mass));
                    }
                }
            }

        } catch (IOException e) {
            System.err.println("Error reading planetary data: " + e.getMessage());
        }

        try (BufferedReader br = new BufferedReader(new FileReader(solarSystemDataFile))) {
            String line;
            Map<String, Planet> planetMap = new HashMap<>();
            for (Planet p : planets) {
                planetMap.put(p.getName(), p);
            }

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(": ");
                if (parts.length == 2) {
                    String name = parts[0].trim();
                    String[] attributes = parts[1].split(", ");

                    if (attributes.length == 2) {
                        int period = extractIntegerValue(attributes[0], "days");
                        double radius = extractNumericValue(attributes[1], "AU") * AU_TO_METERS; // Convert to meters

                        if (planetMap.containsKey(name)) {
                            Planet planet = planetMap.get(name);
                            planet.setOrbitalPeriod(period);
                            planet.setOrbitalRadius(radius);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading solar system data: " + e.getMessage());
        }

        return new SolarSystem(planets);
    }

    public static Rocket readRocketData() {
        int engines = 0;
        double acceleration = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("com/example/stratec/Rocket_Data.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(": ");
                if (parts.length == 2) {
                    if (parts[0].contains("Number of rocket engines")) {
                        engines = Integer.parseInt(parts[1].trim());
                    } else if (parts[0].contains("Acceleration per engine")) {
                        acceleration = extractNumericValue(parts[1], "m/s^2");
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading rocket data: " + e.getMessage());
        }

        return new Rocket(engines, acceleration);
    }

    private static double extractNumericValue(String text, String unit) {
        text = text.replace(unit, "").replaceAll("[^0-9.]", "").trim();
        return Double.parseDouble(text);
    }

    private static int extractIntegerValue(String text, String unit) {
        text = text.replaceAll("[^0-9]", "");
        return Integer.parseInt(text.trim());
    }

    private static double extractMass(String text) {
        text = text.replace("mass =", "").trim();

        if (text.contains("Earths")) {
            text = text.replace(" Earths", "").trim();
            return Double.parseDouble(text) * EARTH_KG;
        }

        if (text.contains("kg")) {
            text = text.replace(" kg", "").trim();

            // Handle scientific notation (6 * 10^24)
            if (text.matches(".*\\*.*10\\^.*")) {
                String[] parts = text.split("\\* 10\\^");
                double base = Double.parseDouble(parts[0].trim());
                double exponent = Double.parseDouble(parts[1].trim());
                return base * Math.pow(10, exponent);
            }

            return Double.parseDouble(text);
        }

        return Double.parseDouble(text);
    }
}
