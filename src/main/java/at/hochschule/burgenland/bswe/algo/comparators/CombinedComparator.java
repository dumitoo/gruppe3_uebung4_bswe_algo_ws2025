package at.hochschule.burgenland.bswe.algo.comparators;

import at.hochschule.burgenland.bswe.algo.entities.Route;

import java.util.Comparator;

public class CombinedComparator implements Comparator<Route>{

    private final Comparator<Route> durationComparator = new DurationAscendingComparator();
    private final Comparator<Route> stopoversComparator = new StopoversAscendingComparator();
    private final Comparator<Route> priceComparator = new PriceAscendingComparator();


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

        return stopoversComparator.compare(route1, route2);
    }
}
