package com.house;

import com.house.containers.Direction;
import com.house.containers.DispatchContainer;

public class Passenger {
    private int id;
    private PassengerStatePosition passengerStatePosition;
    private int dispatchStoreNumber;
    private int arrivalStoreNumber;
    private Direction direction;

    public Passenger(int id) {
        this.id = id;
        passengerStatePosition = new PassengerStatePosition();
    }

    public int getId() {
        return id;
    }

    public Direction getPassengerDirection() {
        return direction;
    }

    public int getDispatchStoreNumber() {
        return dispatchStoreNumber;
    }

    public void setDispatchStoreNumber(int dispatchStoreNumber) {
        this.dispatchStoreNumber = dispatchStoreNumber;
    }

    public int getArrivalStoreNumber() {
        return arrivalStoreNumber;
    }

    public void setArrivalStoreNumber(int arrivalStoreNumber) {
        this.arrivalStoreNumber = arrivalStoreNumber;
    }

    public PassengerStatePosition getPassengerStatePosition() {
        return passengerStatePosition;
    }

    public void setPassengerStatePosition(PassengerStatePosition passengerStatePosition) {
        this.passengerStatePosition = passengerStatePosition;
    }

    public void setDispatureContainerForPassengerState(House h) {
        DispatchContainer d = h.getDispatchContainerByNumberOfStore(getDispatchStoreNumber());
        getPassengerStatePosition().setContainer(d);
    }

    public void setDirection() {
        if (getDispatchStoreNumber() != getArrivalStoreNumber()) {
            if (getDispatchStoreNumber() > getArrivalStoreNumber())
                direction = Direction.DOWN;
            else direction = Direction.UP;
        }
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id + "; direction " + direction +
                "; " + passengerStatePosition.toString() + //Output: ... PassengerStatePosition{...} ...
                "; dispatcherStoreNumber=" + dispatchStoreNumber +
                "; arrivalStoreNumber=" + arrivalStoreNumber +
                '}';
    }
}
