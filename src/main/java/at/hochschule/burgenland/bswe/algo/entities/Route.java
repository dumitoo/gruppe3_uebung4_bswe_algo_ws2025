package at.hochschule.burgenland.bswe.algo.entities;

import lombok.*;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Route {
    private int id;
    private List<Flight> flights;
    private int totalDuration;
    private double totalPrice;
    private int stopovers;

    public Route(List<Flight> flights, int totalDuration, double totalPrice, int stopovers) {
        this.flights = flights;
        this.totalDuration = totalDuration;
        this.totalPrice = totalPrice;
        this.stopovers = stopovers;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return totalDuration == route.totalDuration && Double.compare(totalPrice, route.totalPrice) == 0 && stopovers == route.stopovers && Objects.equals(flights, route.flights);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flights, totalDuration, totalPrice, stopovers);
    }
}
