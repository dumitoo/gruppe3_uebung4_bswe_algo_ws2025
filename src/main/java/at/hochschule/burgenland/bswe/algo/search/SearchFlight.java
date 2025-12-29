package at.hochschule.burgenland.bswe.algo.search;

import at.hochschule.burgenland.bswe.algo.entities.Flight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchFlight {
  private final HashMap<String, List<Flight>> byOrigin = new HashMap<>();
  private final HashMap<String, List<Flight>> byDestination = new HashMap<>();
  private final HashMap<String, List<Flight>> byAirline = new HashMap<>();
  private final HashMap<String, Flight> byFlightNumber = new HashMap<>();

  public SearchFlight(List<Flight> flights) {
    for (Flight flight : flights) {
      indexOrigin(flight);
      indexDestination(flight);
      indexAirline(flight);
      indexFlightNumber(flight);
    }
  }

  private void indexOrigin(Flight flight) {
    String origin = flight.getOrigin().trim().toUpperCase();

    if (!byOrigin.containsKey(origin)) {
      byOrigin.put(origin, new ArrayList<>());
    }

    byOrigin.get(origin).add(flight);
  }

  private void indexDestination(Flight flight) {
    String destination = flight.getDestination().trim().toUpperCase();

    if (!byDestination.containsKey(destination)) {
      byDestination.put(destination, new ArrayList<>());
    }

    byDestination.get(destination).add(flight);

  }

  private void indexAirline(Flight flight) {
    String airline = flight.getAirline().trim();

    if (!byAirline.containsKey(airline)) {
      byAirline.put(airline, new ArrayList<>());
    }

    byAirline.get(airline).add(flight);

  }

  private void indexFlightNumber(Flight flight) {
    String flightNumber = flight.getFlightNumber().trim().toUpperCase();

    if (!flightNumber.isEmpty()) {
      byFlightNumber.put(flightNumber, flight);
    }
  }

  public List<Flight> searchByOrigin(String origin) {
    return byOrigin.getOrDefault(origin.trim().toUpperCase(), List.of());
  }


  public List<Flight> searchByDestination(String destination) {
    return byDestination.getOrDefault(destination.trim().toUpperCase(), List.of());
  }

  public List<Flight> searchByAirline(String airline) {
    return byAirline.getOrDefault(airline.trim(), List.of());
  }

  public Flight searchByFlightNumber(String flightNumber) {
    return byFlightNumber.get(flightNumber.trim().toUpperCase());
  }

}
