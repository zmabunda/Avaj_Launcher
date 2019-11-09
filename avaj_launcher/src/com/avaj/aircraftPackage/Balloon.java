package com.avaj.aircraftPackage;


public final class Balloon extends Aircraft implements Flyable
{
    protected String actualWeather = null;
    private WeatherTower weatherTower = null;

    public Balloon(String name1, Coordinates coordinates1)
    {
        super(name1, coordinates1);
        this.type = "Baloon";
    }

    public void updateConditions()
    {
        if (this.weatherTower == null)
        {
            System.out.println("Error occurred during update, balloon");
            return ;
        }
        String newWeather = weatherTower.getWeather(coordinates);

        if (actualWeather == null || !actualWeather.equals(newWeather))
            actualWeather = newWeather;
        if (actualWeather.equals("SUN"))
            this.log("Gosh bluelight!!, bring my shades");
        else if (actualWeather.equals("SNOW"))
            this.log("It's snowing. We're gonna crash");
        else if (actualWeather.equals("RAIN"))
            this.log("Ever tasted some rain water? open your mouth");
        else if (actualWeather.equals("FOG"))
            this.log("Are we inside a cloud or what ? it's smoky up here");
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
            newCoords = new Coordinates(this.coordinates.getLongitude() + 2, this.coordinates.getLatitude(), this.coordinates.getHeight() + 4);
        else if (this.actualWeather.equals("RAIN"))
            newCoords = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), this.coordinates.getHeight() - 5);
        else if (this.actualWeather.equals("FOG"))
            newCoords = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), this.coordinates.getHeight() - 3);
        else
            newCoords = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), this.coordinates.getHeight() - 15);
        move(newCoords);
    }
}