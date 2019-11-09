package com.avaj.aircraftPackage;

public class Coordinates {
    private final int longitude;
    private final int latitude;
    private final int height;

    public Coordinates(int longitude1, int latitude1, int height1)
    {
        this.longitude = longitude1;
        this.latitude = latitude1;
        this.height = height1;
    }

    public int getLongitude()
    {
        return (this.longitude);
    }

    public int getLatitude()
    {
        return (this.latitude);
    }

    public int getHeight()
    {
        return (this.height);
    }

}
