package at.hochschule.burgenland.bswe.algo.comparators;

import at.hochschule.burgenland.bswe.algo.entities.Route;

import java.util.Comparator;

public class PriceAscendingComparator implements Comparator<Route> {

    @Override
    public int compare(Route route1, Route route2) {
        return Double.compare(route1.getTotalPrice(), route2.getTotalPrice());
    }
}
