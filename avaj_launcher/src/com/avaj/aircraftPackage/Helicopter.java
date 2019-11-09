package com.avaj.aircraftPackage;



public final class Helicopter extends Aircraft implements Flyable
{
    protected String actualWeather = null;
    private WeatherTower weatherTower = null;

    public Helicopter(String name1, Coordinates coordinates1)
    {
        super(name1, coordinates1);
        this.type = "Helicopter";
    }

    public void updateConditions()
    {
        if (this.weatherTower == null)
        {
            System.out.println("Error occurred during update, helicopter");
            return ;
        }
        String newWeather = weatherTower.getWeather(coordinates);

        if (actualWeather == null || !actualWeather.equals(newWeather))
            actualWeather = newWeather;
        if (actualWeather.equals("SUN"))
            this.log("This is hot");
        else if (actualWeather.equals("SNOW"))
            this.log(" My rotor is going to freeze!");
        else if (actualWeather.equals("RAIN"))
            this.log("This rain is distrcacting");
        else if (actualWeather.equals("FOG"))
            this.log("Oh the fog man is here");
        this.increaseCoords();
    }

    public void registerTower(WeatherTower weatherTower1)
    {
        weatherTower1.register(this);
        this.weatherTower = weatherTower1;
    }

    protected void abort()
    {
        this.log("landing.");
        this.weatherTower.unregister(this);
    }

    private void increaseCoords()
    {
        Coordinates newCoords;
        if (this.actualWeather.equals("SUN"))
            newCoords = new Coordinates(this.coordinates.getLongitude() + 10, this.coordinates.getLatitude(), this.coordinates.getHeight() + 2);
        else if (this.actualWeather.equals("RAIN"))
            newCoords = new Coordinates(this.coordinates.getLongitude() + 5, this.coordinates.getLatitude(), this.coordinates.getHeight());
        else if (this.actualWeather.equals("FOG"))
            newCoords = new Coordinates(this.coordinates.getLongitude() + 1, this.coordinates.getLatitude(), this.coordinates.getHeight());
        else
            newCoords = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), this.coordinates.getHeight() - 12);
        move(newCoords);
    }
}
