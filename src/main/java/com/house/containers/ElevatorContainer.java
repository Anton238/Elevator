package com.house.containers;

import com.house.Passenger;
import com.logger.ApplicationLogger;

public class ElevatorContainer extends Container {
    private int capacity;
    private int currentStore;
    private Direction elevatorDirection;
    final public Object synchronizedCabin = new Object();


    public Object getSynchronizedCabin() {
        return synchronizedCabin;
    }

    public ElevatorContainer() {
        currentStore = 1;
        elevatorDirection = Direction.UP;
    }

    public ElevatorContainer(int capacity) {
        this.capacity = capacity;
        currentStore = 1;
        elevatorDirection = Direction.UP;
    }

    public boolean hasFreePlacesInCabin() {
        return capacity - containerPassengers.size() > 0;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCurrentStore() {
        return currentStore;
    }

    public void setCurrentStore(int currentStore) {
        this.currentStore = currentStore;
    }

    public Direction getElevatorDirection() {
        return elevatorDirection;
    }

    public void setElevatorDirection(Direction elevatorDirection) {
        this.elevatorDirection = elevatorDirection;
    }

    public void showCabin() {
        ApplicationLogger.logger.info("Cabin has: ");
        if (containerPassengers.size() == 0)
            ApplicationLogger.logger.info(" 0 Passengers");
        else
            for (Passenger p : containerPassengers)
                ApplicationLogger.logger.info(p.toString());
    }

    public void changeDirection() {
        if (getElevatorDirection() == Direction.UP)
            setElevatorDirection(Direction.DOWN);
        else
            setElevatorDirection(Direction.UP);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
