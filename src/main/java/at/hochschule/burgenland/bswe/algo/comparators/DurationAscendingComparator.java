package at.hochschule.burgenland.bswe.algo.comparators;

import at.hochschule.burgenland.bswe.algo.entities.Route;

import java.util.Comparator;

public class DurationAscendingComparator implements Comparator<Route> {

    @Override
    public int compare(Route route1, Route route2) {
        return Integer.compare(route1.getTotalDuration(), route2.getTotalDuration());
    }
}
