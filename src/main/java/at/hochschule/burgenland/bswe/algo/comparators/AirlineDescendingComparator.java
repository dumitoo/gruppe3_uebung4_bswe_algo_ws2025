package at.hochschule.burgenland.bswe.algo.comparators;

import at.hochschule.burgenland.bswe.algo.entities.Flight;
import at.hochschule.burgenland.bswe.algo.entities.Route;

import java.util.Comparator;
import java.util.List;

public class AirlineDescendingComparator implements Comparator<Route> {

    @Override
    public int compare(Route route1, Route route2) {
        String airline1 = getFirstAirline(route1);
        String airline2 = getFirstAirline(route2);

        return airline2.compareTo(airline1);
    }

    private String getFirstAirline(Route route) {
        List<Flight> flights = route.getFlights();

        if (flights == null || flights.isEmpty()) {
            return "";
        }

        return flights.get(0).getAirline();
    }
}
