package com.avaj.aircraftPackage;


public interface Flyable {
    void updateConditions();
    void registerTower(WeatherTower weatherTower);
    String Display();
}
