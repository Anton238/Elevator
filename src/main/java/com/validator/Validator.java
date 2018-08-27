package com.validator;

import com.house.House;
import com.house.Passenger;
import com.house.PassengerState;
import com.house.Store;
import com.house.containers.ArrivalContainer;

import java.util.List;

public class Validator {

    public boolean isValid(House house) {
        return isAllDispatcherContainersEmpty(house) &&
                isElevatorCabineEmpty(house) &&
                isAllPassengersInCompleteState(house) &&
                isAllPassengersOnProperStore(house) &&
                isAllPassengersOnArrivalContainer(house);
    }

    public boolean isAllDispatcherContainersEmpty(House house) {
        List<Store> stores = house.getStores();
        for (Store s : stores) {
            if (!s.getDispatcherContainer().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public boolean isElevatorCabineEmpty(House house) {
        return house.getElevatorContainer().isEmpty();

    }

    public boolean isAllPassengersInCompleteState(House house) {
        List<Passenger> passengers = house.getPassengers();
        for (Passenger p : passengers) {
            if (p.getPassengerStatePosition().getPassengerState() != PassengerState.COMPLETED)
                return false;
        }
        return true;
    }

    public boolean isAllPassengersOnProperStore(House house) {
        for (int i = 1; i <= house.getStoresNumber(); i++) {
            ArrivalContainer a = house.getArrivalContainerByNumberOfStore(i);
            for (Passenger p : a.getPassengers())
                if (p.getArrivalStoreNumber() != i) return false;
        }
        return true;
    }


    public boolean isAllPassengersOnArrivalContainer(House house) {
        int numberOfPassengersInArrContainers = 0;
        for (int i = 0; i < house.getStoresNumber(); i++)
            numberOfPassengersInArrContainers += house.getSizeOfArrivalContainerOnStore(i);

        return house.getPassengersNumber() == numberOfPassengersInArrContainers;
    }

    public void showFinalHouse(House house) {
        System.out.println("\n\nPassengers Final: ");
        for (Passenger p : house.getPassengers()) {
            System.out.println(p.toString());
        }
    }
}
