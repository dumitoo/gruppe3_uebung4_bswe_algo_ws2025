package at.hochschule.burgenland.bswe.algo.entities;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
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
}
