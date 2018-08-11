package com.threads;

import com.house.House;
import com.house.Store;
import com.house.containers.ArrivalContainer;
import com.house.containers.DispatchContainer;
import com.house.containers.ElevatorContainer;
import com.house.containers.ElevatorController;
import com.logger.ApplicationLogger;
import com.validator.Validator;

public class ElevatorThread implements Runnable {

    private House house;
    private ElevatorController elevatorController;

    public ElevatorThread(House house) {
        this.house = house;
        this.elevatorController = new ElevatorController();
    }

    @Override
    public void run() {
        ApplicationLogger.logger.info("Lift started ");
        Validator validator = new Validator();
        while (!validator.isValid(house)) {
            try {
                askCabinPasengersToExit();
                Thread.sleep(200);
                askStorePassengersToEnter();
                Thread.sleep(200);
                moveOnNextFloor();
                showCabinAndNextFloorInfo();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (validator.isValid(house)) {
            ApplicationLogger.logger.info("Transportation finished correctly ");
            validator.showFinalHouse(house);
        } else {
            ApplicationLogger.logger.info("isAllDispatcherContainersEmpty "
                    + validator.isAllDispatcherContainersEmpty(house));
            ApplicationLogger.logger.info("isElevatorCabineEmpty "
                    + validator.isElevatorCabineEmpty(house));
            ApplicationLogger.logger.info("isAllPassengersInCompleteState"
                    + validator.isAllPassengersInCompleteState(house));
            ApplicationLogger.logger.info("isAllPassengersOnProperStore;"
                    + validator.isAllPassengersOnProperStore(house));

        }

    }

    private void askCabinPasengersToExit() {
        ElevatorContainer container = house.getElevatorContainer();
        ApplicationLogger.logger.info("Lift is on " + container.getCurrentStore() + " store, passengers can EXIT");
        if (container.getPassengers().size() == 0) return;
        Object o = container.getSynchronizedCabin();
        synchronized (o) {
            o.notifyAll();
        }
    }


    public void askStorePassengersToEnter() {
        ElevatorContainer container = house.getElevatorContainer();
        ApplicationLogger.logger.info("Lift is on " + container.getCurrentStore() + " store, passengers can ENTRY)");
        Store store = house.getInstanceOfStoreByNumber(container.getCurrentStore());
        Object o = store.getSynchronizedStore();
        synchronized (o) {
            ApplicationLogger.logger.info("locked store " + store.getNumber());
            int times = 0;
            while (times++ < determineNumberOfPassengersToEnterCabin()) {
                if (elevatorController.isPossibleToEntryCabine(container)
                        && !store.isAllPassengersFromStoreEnterCabin(house)) {// and pass store n dipatcer
                    System.out.println(" in notify lift to enter");
                    o.notify();
                }
            }
        }

    }

    private void moveOnNextFloor() {
        elevatorController.moveToNextStore(house.getElevatorContainer(), house.getStoresNumber());
    }

    private void showCabinAndNextFloorInfo() {
        ApplicationLogger.logger.info(" Information for next Store : ");
        house.getElevatorContainer().showCabin();
        int floor = house.getElevatorContainer().getCurrentStore();
        ArrivalContainer a = house.getArrivalContainerByNumberOfStore(floor);
        DispatchContainer d = house.getDispatchContainerByNumberOfStore(floor);
        a.showArrivalContainer();
        d.showDispatchContainer();
    }

    private int determineNumberOfPassengersToEnterCabin() {
        int cabinHasPassengersNumber = house.getElevatorContainer().getPassengers().size();
        int freeCabin = house.getElevatorCapacity() - cabinHasPassengersNumber;
        int waitingOnStore = house.getDispatchContainerByNumberOfStore(house.getElevatorContainer().
                getCurrentStore()).getPassengers().size();
        return Math.min(freeCabin, waitingOnStore);
    }
}

