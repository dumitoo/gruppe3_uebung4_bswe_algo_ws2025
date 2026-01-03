package at.hochschule.burgenland.bswe.algo.search;

import at.hochschule.burgenland.bswe.algo.entities.Flight;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * This class has implemented index and binary search for flights
 */
public class SearchFlight {
  private final HashMap<String, List<Flight>> byOrigin = new HashMap<>();
  private final HashMap<String, List<Flight>> byDestination = new HashMap<>();
  private final HashMap<String, List<Flight>> byAirline = new HashMap<>();
  private final List<Flight> byFlightNumber;

  /**
   * Builds search indexes and prepares a sorted list for binary search by flight number.
   *
   * @param flights list of flights to index
   */
  public SearchFlight(List<Flight> flights) {
    for (Flight flight : flights) {
      indexOrigin(flight);
      indexDestination(flight);
      indexAirline(flight);
    }
    this.byFlightNumber = new ArrayList<>(flights);
    this.byFlightNumber.sort(Comparator.comparing(flight -> flight.getFlightNumber().trim().toUpperCase()));
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
   * @param flight flight to index by airline
   */
  private void indexAirline(Flight flight) {
    String airline = flight.getAirline().trim().toLowerCase();

    if (!byAirline.containsKey(airline)) {
      byAirline.put(airline, new ArrayList<>());
    }

    byAirline.get(airline).add(flight);

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
    return byAirline.getOrDefault(airline.trim().toLowerCase(), List.of());
  }

  /**
   * This method returns flight with the given flightNumber using binary search.
   *
   * @param flightNumber flight number
   * @return matching flight
   */
  public Flight searchByFlightNumber(String flightNumber) {
    String searchedFlight = flightNumber.trim().toUpperCase();

    if (searchedFlight.isEmpty()) {
      return null;
    }

    int left = 0;
    int right = byFlightNumber.size() - 1;

    while (left <= right) {
      int middle = left + (right - left) / 2;

      Flight middleFlight = byFlightNumber.get(middle);
      String middleFlightNumber = middleFlight.getFlightNumber().trim().toUpperCase();

      int compared = middleFlightNumber.compareTo(searchedFlight);
      if (compared == 0) return middleFlight;
      if (compared < 0) left = middle + 1;
      else right = middle - 1;
    }

    return null;
  }
}
