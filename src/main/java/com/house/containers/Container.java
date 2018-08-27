package com.house.containers;

import com.house.Passenger;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Container {
    List<Passenger> containerPassengers;

    Container() {
        containerPassengers = new CopyOnWriteArrayList<>();
    }

    public List<Passenger> getPassengers() {
        return containerPassengers;
    }

    public void addToContainer(Passenger p) {
        containerPassengers.add(p);
    }

    public boolean isEmpty() {
        return containerPassengers.size() == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Container has: ");
        if (containerPassengers.size() == 0)
            sb.append(" 0 Passengers");
        else
            for (Passenger p : containerPassengers)
                sb.append(p.toString()).append("\n");

        return sb.toString();
    }
}
