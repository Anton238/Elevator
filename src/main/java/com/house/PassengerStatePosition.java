package com.house;

import com.house.containers.Container;


public class PassengerStatePosition {

    private Container container;
    private PassengerState passengerState;

    public PassengerStatePosition() {
        this.passengerState = PassengerState.NOT_STARTED;
    }

    public PassengerStatePosition(Container container, PassengerState passengerState) {
        this.container = container;
        this.passengerState = passengerState;
    }

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public PassengerState getPassengerState() {
        return passengerState;
    }

    @Override
    public String toString() {
        return "PassengerStatePosition{" +
                "container=" + (container.getClass().toString()) +
                "; state=" + passengerState +
                '}';

    }
}
