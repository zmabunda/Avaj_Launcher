package com.avaj.factory;

import com.avaj.aircraftPackage.Flyable;
import com.avaj.aircraftPackage.Coordinates;
import  com.avaj.aircraftPackage.JetPlane;
import  com.avaj.aircraftPackage.Helicopter;
import  com.avaj.aircraftPackage.Balloon;
import com.avaj.simulator.SimulatorException;


public class AircraftFactory {
    public static Flyable newAircraft(String type, String name, int latitude, int longitude, int height) throws SimulatorException
    {
        if (latitude < 0 || longitude < 0 || height < 0)
            throw new SimulatorException("Error : Coordinates must be positive numbers.");
        else if (height > 100)
            height = 100;
        Coordinates coordinates = new Coordinates(latitude, longitude, height);
        if (type.equals("Baloon"))
        {
            return new Balloon(name, coordinates);
        }
        else if (type.equals("JetPlane"))
        {
            return new JetPlane(name, coordinates);
        }
        else if (type.equals("Helicopter"))
        {
            return new Helicopter(name, coordinates);
        }
        else
        {
            throw new SimulatorException("Syntax error : Unknown type [" + type + "]");
        }
    }
}
