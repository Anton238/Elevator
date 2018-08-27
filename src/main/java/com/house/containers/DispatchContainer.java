package com.house.containers;

import com.house.Passenger;
import com.logger.ApplicationLogger;

public class DispatchContainer extends Container {
    public void showDispatchContainer() {
        ApplicationLogger.logger.info("DispatchContainer has: ");
        if (containerPassengers.size() == 0)
            ApplicationLogger.logger.info(" 0 Passengers");
        else
            for (Passenger p : containerPassengers)
                ApplicationLogger.logger.info(p.toString());

    }

    @Override
    public String toString() {
        return "DispatchContainer";
    }
}
