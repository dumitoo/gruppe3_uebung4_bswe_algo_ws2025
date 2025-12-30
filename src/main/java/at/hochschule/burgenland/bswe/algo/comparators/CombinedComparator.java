package at.hochschule.burgenland.bswe.algo.comparators;

import at.hochschule.burgenland.bswe.algo.entities.Route;

import java.util.Comparator;

public class CombinedComparator implements Comparator<Route>{

    private final Comparator<Route> airlineComparator = new AirlineDescendingComparator();
    private final Comparator<Route> durationComparator = new DurationAscendingComparator();
    private final Comparator<Route> stopoversComparator = new StopoversAscendingComparator();
    private final Comparator<Route> priceComparator = new PriceAscendingComparator();
    private final Comparator<Route> flighNumberComparator = new FlightNumberDescendingComparator();


    @Override
    public int compare(Route route1, Route route2) {
        int result = priceComparator.compare(route1, route2);
        if (result != 0) {
            return result;
        }

        result = durationComparator.compare(route1, route2);
        if (result != 0) {
            return result;
        }

        result = stopoversComparator.compare(route1, route2);
            if (result != 0) {
                return result;
            }

        result = airlineComparator.compare(route1, route2);
            if (result != 0) {
                return result;
            }

        return flighNumberComparator.compare(route1, route2);
    }
}
