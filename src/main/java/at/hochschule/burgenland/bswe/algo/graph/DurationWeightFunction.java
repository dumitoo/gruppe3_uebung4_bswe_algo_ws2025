package at.hochschule.burgenland.bswe.algo.graph;

import at.hochschule.burgenland.bswe.algo.entities.Flight;

public class DurationWeightFunction implements WeightFunction {
    @Override
    public double getWeight(Flight flight) {
        return flight.getDuration();
    }
}