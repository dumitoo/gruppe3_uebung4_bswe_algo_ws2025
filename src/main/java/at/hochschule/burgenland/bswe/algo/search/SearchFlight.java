package at.hochschule.burgenland.bswe.algo.search;

import at.hochschule.burgenland.bswe.algo.entities.Flight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class has implemented index search for flights
 */
public class SearchFlight {
  private final HashMap<String, List<Flight>> byOrigin = new HashMap<>();
  private final HashMap<String, List<Flight>> byDestination = new HashMap<>();
  private final HashMap<String, List<Flight>> byAirline = new HashMap<>();
  private final HashMap<String, Flight> byFlightNumber = new HashMap<>();

  /**
   * This method builds all search indexes for the given flights.
   *
   * @param flights list of flights to index
   */
  public SearchFlight(List<Flight> flights) {
    for (Flight flight : flights) {
      indexOrigin(flight);
      indexDestination(flight);
      indexAirline(flight);
      indexFlightNumber(flight);
    }
  }

  /**
   * This method is for index given flight by origin.
   *
   * @param flight flight to index by origin
   */
  private void indexOrigin(Flight flight) {
    String origin = flight.getOrigin().trim().toUpperCase();

    if (!byOrigin.containsKey(origin)) {
      byOrigin.put(origin, new ArrayList<>());
    }

    byOrigin.get(origin).add(flight);
  }

  /**
   * This method is for index given flight by destination.
   *
   * @param flight flight to index by destination
   */
  private void indexDestination(Flight flight) {
    String destination = flight.getDestination().trim().toUpperCase();

    if (!byDestination.containsKey(destination)) {
      byDestination.put(destination, new ArrayList<>());
    }

    byDestination.get(destination).add(flight);

  }

  /**
   * This method indexes flight by airline.
   *
   * @param flight flight to inde by airline
   */
  private void indexAirline(Flight flight) {
    String airline = flight.getAirline().trim();

    if (!byAirline.containsKey(airline)) {
      byAirline.put(airline, new ArrayList<>());
    }

    byAirline.get(airline).add(flight);

  }

  /**
   * This method indexes flight by flightNumber.
   *
   * @param flight flight to inde by flightNumber
   */
  private void indexFlightNumber(Flight flight) {
    String flightNumber = flight.getFlightNumber().trim().toUpperCase();

    if (!flightNumber.isEmpty()) {
      byFlightNumber.put(flightNumber, flight);
    }
  }

  /**
   * This method returns all flights that start at the given origin airport.
   *
   * @param origin origin IATA code
   * @return list of matching flights
   */
  public List<Flight> searchByOrigin(String origin) {
    return byOrigin.getOrDefault(origin.trim().toUpperCase(), List.of());
  }

  /**
   * This method returns all flights that land at the given destination airport.
   *
   * @param destination destination IATA code
   * @return list of matching flights
   */
  public List<Flight> searchByDestination(String destination) {
    return byDestination.getOrDefault(destination.trim().toUpperCase(), List.of());
  }

  /**
   * This method returns all flights of the given airline.
   *
   * @param airline airline name
   * @return list of matching flights
   */
  public List<Flight> searchByAirline(String airline) {
    return byAirline.getOrDefault(airline.trim(), List.of());
  }

  /**
   * This method returns flight with the given flightNumber.
   *
   * @param flightNumber flight number
   * @return matching flight
   */
  public Flight searchByFlightNumber(String flightNumber) {
    return byFlightNumber.get(flightNumber.trim().toUpperCase());
  }

}
