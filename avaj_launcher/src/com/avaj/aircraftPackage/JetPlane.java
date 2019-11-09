package com.avaj.aircraftPackage;


public final class JetPlane extends Aircraft implements Flyable
{
    protected String actualWeather = null;
    private WeatherTower weatherTower = null;

    public JetPlane(String name1, Coordinates coordinates1)
    {
        super(name1, coordinates1);
        this.type = "JetPlane";
    }

    public void updateConditions()
    {
        if (this.weatherTower == null)
        {
            System.out.println("Error occurred during update, jetplane");
            return ;
        }
        String newWeather = weatherTower.getWeather(coordinates);

        if (actualWeather == null || !actualWeather.equals(newWeather))
            actualWeather = newWeather;
        if (actualWeather.equals("SUN"))
            this.log("Sunny day clear skies whoop!");
        else if (actualWeather.equals("SNOW"))
            this.log("Love it when it snow");
        else if (actualWeather.equals("RAIN"))
            this.log("It's raining. Better watch out for lightings");
        else if (actualWeather.equals("FOG"))
            this.log("I can hardly see ..");
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
            newCoords = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude() + 10, this.coordinates.getHeight() + 2);
        else if (this.actualWeather.equals("RAIN"))
            newCoords = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude() + 5, this.coordinates.getHeight());
        else if (this.actualWeather.equals("FOG"))
            newCoords = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude() + 1, this.coordinates.getHeight());
        else
            newCoords = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), this.coordinates.getHeight() - 7);
        move(newCoords);
    }
}
