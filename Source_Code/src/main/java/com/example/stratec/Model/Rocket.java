package com.example.stratec.Model;

public class Rocket {
    private Integer noEngines;
    private Double acceleration;

    public Rocket(Integer noEngines, Double acceleration) {
        this.noEngines = noEngines;
        this.acceleration = acceleration;
    }

    public Integer getNoEngines() {
        return noEngines;
    }

    public void setNoEngines(Integer noEngines) {
        this.noEngines = noEngines;
    }

    public Double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Double acceleration) {
        this.acceleration = acceleration;
    }
}
