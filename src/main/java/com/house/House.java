package com.house;

import com.house.containers.ArrivalContainer;
import com.house.containers.DispatchContainer;
import com.house.containers.ElevatorContainer;
import com.logger.ApplicationLogger;

import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.Properties;

public class House {
    private List<Store> stores;
    private List<Passenger> passengers;
    private int storesNumber;
    private int passengersNumber;
    private int elevatorCapacity;
    private ElevatorContainer elevatorContainer;

    public House() {
        stores = new ArrayList<>();
        passengers = new ArrayList<>();
        elevatorContainer = new ElevatorContainer();
    }

    public void initHouse() {
        readInitialData();
        createPassengerList();
        createStores();
        distributePassengersOnFlores();
        populateDispatcherContainersOnFloors();
        getElevatorContainer().setCapacity(this.elevatorCapacity);
        showCreatedHouse();
    }

    //private methods are used for initialization
    private void readInitialData() {
        Properties property = new Properties();
        try {
            FileInputStream inputStream = new FileInputStream("src/main/resources/initialData.properties");
            property.load(inputStream);
        } catch (IOException e) {
            System.out.println("Not found .properties file");
        }
        storesNumber = Integer.parseInt(property.getProperty("storesNumber"));
        elevatorCapacity = Integer.parseInt(property.getProperty("elevatorCapacity"));
        passengersNumber = Integer.parseInt(property.getProperty("passengersNumber"));
    }
    private void createPassengerList() {
        int passengerCount = 1;
        for (int i = 0; i < passengersNumber; i++) {
            passengers.add(new Passenger(passengerCount++));
        }
    }
    private void createStores() {
        int storeCount = 1;
        for (int i = 0; i < storesNumber; i++) {
            stores.add(new Store(storeCount++));
        }
    }
    private void distributePassengersOnFlores() {
        for (Passenger p : passengers) {
            p.setDispatchStoreNumber(getRandomStore());
            setRandomArrivalFloorNumber(p);
            p.setDirection();
            p.setDispatureContainerForPassengerState(this);
        }
    }
    private int getRandomStore() {
        return (int) (1 + (Math.random() * storesNumber));
    }
    private void setRandomArrivalFloorNumber(Passenger p) {
        int randomStore = getRandomStore();
        while (randomStore == p.getDispatchStoreNumber()) {
            randomStore = getRandomStore();
        }
        p.setArrivalStoreNumber(randomStore);
    }
    private void showCreatedHouse() {
        String s = "House has: " + storesNumber + " stores and " + passengersNumber + " passengers";
        ApplicationLogger.logger.info(s);
        for (Passenger p : passengers) {
            ApplicationLogger.logger.info(p.toString());
        }
        for (int i = 0; i < storesNumber; i++) {
            ApplicationLogger.logger.info(stores.get(i).toString());
        }
    }
    private void populateDispatcherContainersOnFloors() {
        for (Passenger p : passengers) {
            int passengerDispatcherStore = p.getDispatchStoreNumber();
            DispatchContainer dispatcherContainer =
                    getDispatchContainerByNumberOfStore(passengerDispatcherStore);
            if (dispatcherContainer != null) {
                dispatcherContainer.addToContainer(p);
            }
        }
    }

    public DispatchContainer getDispatchContainerByNumberOfStore(int currentStore) {
        return stores.get(currentStore - 1).getDispatcherContainer();
    }

    public ArrivalContainer getArrivalContainerByNumberOfStore(int number) {
        return stores.get(number - 1).getArrivalContainer();
    }

    public Store getInstanceOfStoreByNumber(int number) {
        Store store = null;
        for (Store s : stores) {
            if (s.getNumber() == number) {
                store = s;
            }
        }
        return store;
    }

    public int getSizeOfArrivalContainerOnStore(int store) {
        return getStores().get(store).getArrivalContainer().getPassengers().size();
    }

    public ElevatorContainer getElevatorContainer() {
        return elevatorContainer;
    }

    public List<Store> getStores() {
        return stores;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public int getElevatorCapacity() {
        return elevatorCapacity;
    }

    public int getStoresNumber() {
        return storesNumber;
    }

    public int getPassengersNumber() {
        return passengersNumber;
    }
}
