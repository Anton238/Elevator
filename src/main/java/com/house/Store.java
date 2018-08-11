package com.house;

import com.house.containers.DispatchContainer;
import com.house.containers.ArrivalContainer;
import com.house.containers.ElevatorContainer;

public class Store {
    private int number;
    private DispatchContainer dispatcherContainer;
    private ArrivalContainer arrivalContainer;
    final private Object synchronizedStore = new Object();


    public Object getSynchronizedStore() {
        return synchronizedStore;
    }

    public Store(int number) {
        this.number = number;
        dispatcherContainer = new DispatchContainer();
        arrivalContainer = new ArrivalContainer();
    }

    public int getNumber() {
        return number;
    }

    public DispatchContainer getDispatcherContainer() {
        return dispatcherContainer;
    }

    public ArrivalContainer getArrivalContainer() {
        return arrivalContainer;
    }

    public boolean isAllPassengersFromStoreEnterCabin(House house){
        int currentStore = house.getElevatorContainer().getCurrentStore();
        DispatchContainer dispatcherContainer = house.getDispatchContainerByNumberOfStore(currentStore);
        if(dispatcherContainer.getPassengers().size() == 0)
            return true;
        for(Passenger p : dispatcherContainer.getPassengers()){
            if(p.getDispatchStoreNumber() == currentStore
                    && !(p.getPassengerStatePosition().getContainer() instanceof ElevatorContainer)){
                return false;
            }
        }
        return true;
    }


    @Override
    public String toString() {
        return "Store{" +
                "number=" + number +
                "; dispatcherContainer=" + dispatcherContainer.getPassengers().toString() +
                "; arrivalContainer=" + arrivalContainer.getPassengers().toString() +
                '}';
    }
}
