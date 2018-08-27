package com.house.containers;

import com.house.Passenger;
import com.logger.ApplicationLogger;

public class ArrivalContainer extends Container {
    public void showArrivalContainer() {
        ApplicationLogger.logger.info("ArrivalContainer has: ");
        if(containerPassengers.size() == 0)
            ApplicationLogger.logger.info(" 0 Passengers");
        else
            for (Passenger p : containerPassengers)
                ApplicationLogger.logger.info(p.toString());
    }

    @Override
    public String toString() {
        return "ArrivalContainer";
    }
}
