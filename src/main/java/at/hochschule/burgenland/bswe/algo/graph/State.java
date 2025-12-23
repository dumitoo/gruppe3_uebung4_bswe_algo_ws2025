package at.hochschule.burgenland.bswe.algo.graph;

import at.hochschule.burgenland.bswe.algo.entities.Flight;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class State {
    private String airport;

    private List<Flight> path;

    private double cost;

    private int stopovers;
}