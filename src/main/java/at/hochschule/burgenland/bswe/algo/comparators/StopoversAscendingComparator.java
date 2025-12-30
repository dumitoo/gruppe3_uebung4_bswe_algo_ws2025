package at.hochschule.burgenland.bswe.algo.comparators;

import at.hochschule.burgenland.bswe.algo.entities.Route;

import java.util.Comparator;

public class StopoversAscendingComparator implements Comparator<Route> {

    @Override
    public int compare(Route route1, Route route2) {
        return Integer.compare(route1.getStopovers(), route2.getStopovers());
    }
}
