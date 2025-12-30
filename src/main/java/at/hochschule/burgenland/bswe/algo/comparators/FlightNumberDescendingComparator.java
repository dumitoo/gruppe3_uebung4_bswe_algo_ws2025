package at.hochschule.burgenland.bswe.algo.comparators;

import at.hochschule.burgenland.bswe.algo.entities.Flight;
import at.hochschule.burgenland.bswe.algo.entities.Route;

import java.util.Comparator;
import java.util.List;

public class FlightNumberDescendingComparator implements Comparator<Route> {
    @Override
    public int compare(Route route1, Route route2) {
        String flightNumber1 = getFirstFlightNumber(route1);
        String flightNumber2 = getFirstFlightNumber(route2);

        return flightNumber2.compareTo(flightNumber1);
    }

    private String getFirstFlightNumber(Route route) {
        List<Flight> flights = route.getFlights();

        if (flights == null || flights.isEmpty()) {
            return "";
        }

        return flights.get(0).getFlightNumber();
    }
}
