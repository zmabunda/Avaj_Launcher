package com.avaj.aircraftPackage;

import com.avaj.simulator.MsgLog;

public abstract class Aircraft {
        protected long id;
        protected String name;
        protected Coordinates coordinates;
        protected String type;

        private static long idCounter = 0;

        protected Aircraft(String name1, Coordinates coordinates1)
        {
                this.name = name1;
                this.coordinates = coordinates1;
                this.id = this.nextId();
        }

        private long nextId()
        {
                Aircraft.idCounter++;
                return Aircraft.idCounter - 1;
        }

        protected void log(String message)
        {
                String toLog = Display() + ": " + message;
                MsgLog.log(toLog);
        }

        public String Display()
        {
                return this.type + '#' + this.name + '(' + this.id + ")";
        }

        protected void move(Coordinates coords)
        {
                int	newLatitude;
                int	newLongitude;
                int	newHeight;

                if (coords.getHeight() > 100)
                        newHeight = 100;
                else if (coords.getHeight() <= 0)
                {
                        this.coordinates = new Coordinates(coords.getLongitude(), coords.getLatitude(), 0);
                        abort();
                        return ;
                }
                else
                        newHeight = coords.getHeight();

                if (coords.getLatitude() < 0)
                        newLatitude = 0;
                else
                        newLatitude = coords.getLatitude();

                if (coords.getLongitude() < 0)
                        newLongitude = 0;
                else
                        newLongitude = coords.getLongitude();

                this.coordinates = new Coordinates(newLongitude, newLatitude, newHeight);
        }

        protected abstract void abort();
}
