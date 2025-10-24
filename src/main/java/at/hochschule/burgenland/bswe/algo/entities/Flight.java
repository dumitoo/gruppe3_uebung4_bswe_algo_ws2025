package at.hochschule.burgenland.bswe.algo.entities;

import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Flight {
  private int id;
  private String origin;
  private String destination;
  private String airline;
  private String flightNumber;
  private int duration;
  private double price;
  private LocalTime departureTime;
}
