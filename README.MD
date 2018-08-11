# Elevator

We have building with stores and elevator with certain capacity. Initial data (number of stores, number of passengers, elevator capacity) placed in .property file.



## Flow of implementation:

Read initial data from file.
Create certain number of stores. Each store has arrival and dispatcher containers for passengers.
Create certain number of passengers (each have id, destination and dispatcher store, state (Not_Started, In_Progress, Completed). In each moment we should know at what container (dispatcher, arrival, elevator) passenger is placed. Randomly divide passengers among stores.
Create PassengerTransportationTask for each passenger.
Create ElevatorThread for elevator.
Start threads.



## ElevatorThread flow:

Elevator is moving among stores in cycle. Elevator stops at every store. Elevator cabin has limit capacity.
Elevator asks passengers to exit from elevator cabin when reach store
Elevator asks passengers to enter from elevator cabin.
Actions performed in cycle till the moment when will be true all following conditions:
- All dispatcher containers should be empty,
- Elevator cabin should be empty,
- All passengers should be in Complete state,
- Each passenger will be in proper arrival store (in each arrival store passengers destination property should be equal store number),
- Total number of passengers should be equal sum of passengers in all arrival containers



**Actions for starting, moving elevator, boarding and deboarding passengers should be logged with log4j.**
