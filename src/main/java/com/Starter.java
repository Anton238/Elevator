package com;

import com.house.House;
import com.house.Passenger;
import com.threads.ElevatorThread;
import com.threads.PassengerTransportationThread;

public class Starter {
    public static void main(String[] args) {
        House house = new House(); //initialization of House, Store< Passengers and other
        house.initHouse();


        for (Passenger p : house.getPassengers()) { //running passenger threads
            Thread passengerThread = new PassengerTransportationThread(p, house);
            passengerThread.start();
        }

        ElevatorThread elevatorThread = new ElevatorThread(house); //running elevator thread
        elevatorThread.run();
    }
}


