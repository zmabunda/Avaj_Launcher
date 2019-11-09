package com.avaj.aircraftPackage;

import java.util.ArrayList;
import com.avaj.simulator.MsgLog;

public abstract class Tower {
    private ArrayList<Flyable> observers = new ArrayList<Flyable>();

    public void register(Flyable flyable)
    {
        observers.add(flyable);
        MsgLog.log("Tower says: " + flyable.Display() + " registered to weather tower.");
    }

    public  void unregister(Flyable flyable)
    {
        observers.remove(flyable);
        MsgLog.log("Tower says: " + flyable.Display() + " unregistered from weather tower.");
    }

    protected void conditionsChanged()
    {
        for(int i = 0; i < observers.size(); i++)
        {
            observers.get(i).updateConditions();
        }
    }
}
