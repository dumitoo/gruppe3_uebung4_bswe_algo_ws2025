package at.hochschule.burgenland.bswe.algo.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Airport {
  private int id;
  private String iata;
  private String city;
  private String country;
  private double latitude;
  private double longitude;
}
