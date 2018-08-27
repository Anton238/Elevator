package com.threads;

import com.house.*;
import com.house.containers.ArrivalContainer;
import com.logger.ApplicationLogger;

public class PassengerTransportationThread extends Thread {

    private Passenger passenger;
    private House house;

    public PassengerTransportationThread(Passenger passenger, House house) {
        this.passenger = passenger;
        this.house = house;
    }

    @Override
    public void run() {
        ApplicationLogger.logger.info(this.toString());
        ApplicationLogger.logger.info("Passenger{id=" + passenger.getId() + "} task started ");
        try {
            waitOnInitialStore();
            Thread.sleep(200);
            waitInElevatorContainer();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void waitOnInitialStore() {
        ApplicationLogger.logger.info("Passenger{id=" + passenger.getId() + "} on wait in Store ");
        Store initialStore = house.getInstanceOfStoreByNumber(passenger.getDispatchStoreNumber());
        ApplicationLogger.logger.info("Passenger{id=" + passenger.getId() + "} waiting store (locked store) " + initialStore.getNumber());
        Object o = initialStore.getSynchronizedStore();
        synchronized (o) {
            while (needToWaitOnFloor(house, passenger)) {
                try {
                    ApplicationLogger.logger.info("Passenger{id=" + passenger.getId() + "} on wait in Store while  " + this.getState());
                    o.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            house.getElevatorContainer().addToContainer(passenger);
            initialStore.getDispatcherContainer().getPassengers().remove(passenger);
            passenger.setPassengerStatePosition(new PassengerStatePosition(house.getElevatorContainer(),
                    PassengerState.IN_PROGRESS));
            ApplicationLogger.logger.info("Passenger{id=" + passenger.getId() + "} moved TO cabin");
        }
    }


    private boolean needToWaitOnFloor(House house, Passenger p) {
        ApplicationLogger.logger.info("Passenger{id=" + p.getId() + "} is " + p.getPassengerDirection() + ", elevator = " +
                house.getElevatorContainer().getElevatorDirection());
        ApplicationLogger.logger.info("Free cabin: " + house.getElevatorContainer().hasFreePlacesInCabin());

        return !(house.getElevatorContainer().hasFreePlacesInCabin() &&
                house.getElevatorContainer().getCurrentStore() == p.getDispatchStoreNumber()
                && isPassengerElevatorContainerEqualDirection());
    }

    private boolean isPassengerElevatorContainerEqualDirection() {
        if (passenger.getDispatchStoreNumber() == 1 && house.getElevatorContainer().getCurrentStore() == 1)
            return true;
        if (passenger.getDispatchStoreNumber() == house.getStoresNumber()
                && house.getElevatorContainer().getCurrentStore() == house.getStoresNumber())
            return true;

        return passenger.getPassengerDirection() == house.getElevatorContainer().getElevatorDirection();
    }


    private void waitInElevatorContainer() {
        ApplicationLogger.logger.info("Passenger{id=" + passenger.getId() + "} in waitInElevatorContainer");
        Object o = house.getElevatorContainer().getSynchronizedCabin();
        synchronized (o) {
            while (passenger.getArrivalStoreNumber() != house.getElevatorContainer().getCurrentStore()) {
                try {
                    ApplicationLogger.logger.info("Passenger{id=" + passenger.getId() + "} in waitInElevatorContainer while "
                            + getState());
                    o.wait();
                    ApplicationLogger.logger.info("Passenger{id=" + passenger.getId() + "} in waitInElevatorContainer while "
                            + getState());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int currentStore = house.getElevatorContainer().getCurrentStore();
            if (passenger.getArrivalStoreNumber() == currentStore) {
                house.getElevatorContainer().getPassengers().remove(passenger);
                ArrivalContainer arrivalContainer = house.getArrivalContainerByNumberOfStore(currentStore);
                arrivalContainer.addToContainer(passenger);
                passenger.setPassengerStatePosition(new PassengerStatePosition(arrivalContainer, PassengerState.COMPLETED));
            }
            ApplicationLogger.logger.info("Passenger{id=" + passenger.getId() + "} passenger moved FROM cabin");
        }
    }

    @Override
    public String toString() {
        return "PassengerTransportationThread{" +
                "passengerId=" + passenger.getId() +
                "; state=" + this.getState() +
                '}';
    }
}
