package com.example.stratec.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SolarSystem {
    private List<Planet> planets;

    public SolarSystem(List<Planet> planets) {
        this.planets=planets;
    }

    public List<Planet> getPlanets() {
        return planets;
    }

    public void setPlanets(List<Planet> planets) {
        this.planets = planets;
    }
}
