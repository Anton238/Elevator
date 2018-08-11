package com;

import com.house.House;
import com.house.Passenger;
import com.threads.ElevatorThread;
import com.threads.PassengerTransportationThread;

public class Starter {
    public static void main(String[] args) {
        House house = new House();
        house.initHouse();


        for (Passenger p : house.getPassengers()) {
            Thread passengerThread = new PassengerTransportationThread(p, house);
            passengerThread.start();
        }

        ElevatorThread elevatorThread = new ElevatorThread(house);
        elevatorThread.run();
    }
}
