package com.house.containers;

import com.logger.ApplicationLogger;

public class ElevatorController {
    public void moveToNextStore(ElevatorContainer elevatorContainer, int numberOfFloors) {
        int elevatorStore = elevatorContainer.getCurrentStore();
        int nextStore = nextStore(elevatorContainer, numberOfFloors);
        elevatorContainer.setCurrentStore(nextStore);
        ApplicationLogger.logger.info("Elevator cabin is moving from " + elevatorStore
                + " store to the " + nextStore + " store. Direction: " + elevatorContainer.getElevatorDirection());
    }

    public int nextStore(ElevatorContainer elevatorContainer, int numberOfFloors) {
        int elevatorCurrentFloor = elevatorContainer.getCurrentStore();
        int nextfloor = elevatorCurrentFloor;
        if (elevatorCurrentFloor == 1) {
            elevatorContainer.setElevatorDirection(Direction.UP);
            nextfloor++;
            return nextfloor;
        }
        if (elevatorCurrentFloor == numberOfFloors) {
            elevatorContainer.changeDirection();
            nextfloor--;
            return nextfloor;
        }


        if (elevatorContainer.getElevatorDirection() == Direction.UP) {
            if (elevatorCurrentFloor < numberOfFloors)
                nextfloor++;
        } else {
            if (elevatorCurrentFloor < numberOfFloors && elevatorCurrentFloor > 1)
                nextfloor--;
        }
        return nextfloor;
    }

    public boolean isPossibleToEntryCabine(ElevatorContainer container) {
        return container.hasFreePlacesInCabin();
    }


}
