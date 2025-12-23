package at.hochschule.burgenland.bswe.algo.graph;

import at.hochschule.burgenland.bswe.algo.entities.Flight;

@FunctionalInterface
public interface WeightFunction {
    double getWeight(Flight flight);
}