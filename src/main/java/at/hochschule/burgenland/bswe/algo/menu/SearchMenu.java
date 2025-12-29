package at.hochschule.burgenland.bswe.algo.menu;

import at.hochschule.burgenland.bswe.algo.entities.Airport;
import at.hochschule.burgenland.bswe.algo.entities.Flight;
import at.hochschule.burgenland.bswe.algo.importer.AirportImporter;
import at.hochschule.burgenland.bswe.algo.importer.FlightsImporter;
import at.hochschule.burgenland.bswe.algo.search.SearchFlight;
import at.hochschule.burgenland.bswe.algo.util.InputUtils;
import at.hochschule.burgenland.bswe.algo.util.OutputUtils;

import java.util.List;

/**
 * Class to run menu for searching flights.
 */
public class SearchMenu {
  /**
   * This method to start menu.
   */
  public static void runMenu() {
    List<Airport> airports = new AirportImporter().importData();
    List<Flight> flights = new FlightsImporter().importData();

    SearchFlight searchFlight = new SearchFlight(flights);

    boolean isRunningSearch = true;
    while (isRunningSearch) {
      System.out.println();
      System.out.println("------------------------SEARCH MENU-------------------------");
      int option = InputUtils.getValidMenuOption(List.of(
          "Search by origin (IATA)",
          "Search by destination (IATA)",
          "Search by airline",
          "Search by flightNumber",
          "Back"
      ));

      switch (option) {
        case 1 -> searchOrigin(airports, searchFlight);
        case 2 -> searchDestination(airports, searchFlight);
        case 3 -> searchAirline(searchFlight);
        case 4 -> searchFlightNumber(searchFlight);
        case 5 -> isRunningSearch = false;
        default -> throw new IllegalStateException("Unexpected value: " + option);
      }

      System.out.println();
    }

  }

  /**
   * This method prints a list of given flight results.
   *
   * @param results list of flights returned by a search
   */
  private static void printSearchResults(List<Flight> results) {
    if (results.isEmpty()) {
      System.out.println("No flights found.");
      return;
    }

    System.out.println("--------------------------FLIGHTS---------------------------");
    for (Flight flight : results) {
      System.out.println("------------------------------------------------------------");
      OutputUtils.printFlight(flight);
      System.out.println();
    }
  }

  /**
   * This method to perform searching by origin.
   *
   * @param airports list of loaded airports
   * @param searchFlight index search structure for flights
   */
  private static void searchOrigin(List<Airport> airports, SearchFlight searchFlight) {
    String origin = InputUtils.getValidIATACode("Origin");

    Airport airport = airports.stream()
        .filter(airportEl -> airportEl.getIata().equals(origin))
        .findFirst()
        .orElse(null);

    if (airport != null) {
      System.out.println("--------------------------AIRPORT---------------------------");
      OutputUtils.printAirport(airport);
    }

    List<Flight> results = searchFlight.searchByOrigin(origin);
    printSearchResults(results);
  }

  /**
   * This method to perform searching by destination.
   *
   * @param airports list of loaded airports
   * @param searchFlight index search structure for flights
   */
  private static void searchDestination(List<Airport> airports, SearchFlight searchFlight){
    String destination = InputUtils.getValidIATACode("Destination");

    Airport airport = airports.stream()
        .filter(airportEl -> airportEl.getIata().equals(destination))
        .findFirst()
        .orElse(null);

    if (airport != null) {
      System.out.println("--------------------------AIRPORT---------------------------");
      OutputUtils.printAirport(airport);
    }

    List<Flight> results = searchFlight.searchByDestination(destination);
    printSearchResults(results);
  }

  /**
   * This method to perform searching by airport.
   *
   * @param searchFlight index search structure for flights
   */
  public static void searchAirline(SearchFlight searchFlight) {
    String airline = InputUtils.readValidString("Airline:");

    List<Flight> results = searchFlight.searchByAirline(airline);
    printSearchResults(results);
  }

  /**
   * This method to perform searching by flightNumber
   *
   * @param searchFlight index search structure for flights
   */
  public static void searchFlightNumber(SearchFlight searchFlight) {
    String flightNumber = InputUtils.readValidString("Flight Number: ");

    Flight flight = searchFlight.searchByFlightNumber(flightNumber);

    if (flight == null) {
      System.out.println("No flights found with flightNumber: " + flightNumber);
      return;
    }

    System.out.println("----------------------FLIGHT DETAILS-----------------------");
    OutputUtils.printFlight(flight);
    System.out.println();
  }

}