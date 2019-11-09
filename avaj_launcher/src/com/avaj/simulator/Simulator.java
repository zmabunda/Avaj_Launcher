package com.avaj.simulator;

import com.avaj.factory.AircraftFactory;
import com.avaj.aircraftPackage.Flyable;
import com.avaj.aircraftPackage.WeatherTower;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Simulator {
    public static void main(String[] args) {

        if (args.length <= 0)
        {
            System.out.println("Please enter one argument.");
            System.exit(2);
        }
        else
        {
            System.out.println("Input file : " + args[0]);
        }

        try
        {
            MsgLog.setOutputFile("simulation.txt");
        } catch (IOException e)
        {
            System.out.println("Error : Can't open file simulation .txt for writing.");
            return ;
        }

        WeatherTower tower = new WeatherTower();
        ArrayList<Flyable> flyables = new ArrayList<>();
        try
        {
            FileReader readfile = new FileReader(args[0]);
            BufferedReader br = new BufferedReader(readfile);

            String linereader = br.readLine();
            int nbr_simulations;
            try
            {
                nbr_simulations = Integer.parseInt(linereader);
            } catch (NumberFormatException e)
            {
                throw new SimulatorException("The number you entered is not valid");
            }
            String current_line;
            
            while ((current_line = br.readLine()) != null)
            {
                String array[] = current_line.split(" ");
                if (array.length != 5)
                    throw new SimulatorException("You  must have at least five fields in each line.");
                try
                {
                    flyables.add(AircraftFactory.newAircraft(array[0], array[1], Integer.parseInt(array[2]), Integer.parseInt(array[3]), Integer.parseInt(array[4])));
                }
                catch (NumberFormatException e)
                {
                    throw new SimulatorException ("Format error : Expected a number [" + current_line + "]");
                }
            }

            for (Flyable flyable: flyables)
                flyable.registerTower(tower);

            for (int i = 0; i < nbr_simulations; i++)
                tower.changeWeather();
        }
        catch (IOException e)
        {
            System.out.println("Error in the Input/Output.");
        }
        catch (SimulatorException e)
        {
            System.out.println(e.getMessage());
        }
    }
}