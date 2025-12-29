package at.hochschule.burgenland.bswe.algo.graph;

import at.hochschule.burgenland.bswe.algo.entities.Flight;
import at.hochschule.burgenland.bswe.algo.search.SearchFlight;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SearchFlightTest {

  @Test
  void searchByOriginTest(){
    List<Flight> flights = new ArrayList<>();
    Flight flight1 = new Flight(1,"VIE","FRA","Austrian","OS101",80,120.00, LocalTime.of(6,30));
    Flight flight2 = new Flight(2,"VIE","MUC","Austrian","OS115",60,110.00,LocalTime.of(7,0));
    Flight flight3 = new Flight(4,"FRA","LHR","Lufthansa","LH900",90,130.00,LocalTime.of(8,30));

    flights.add(flight1);
    flights.add(flight2);
    flights.add(flight3);

    SearchFlight searchFlight = new SearchFlight(flights);
    List<Flight> results = searchFlight.searchByOrigin("VIE");

    assertEquals(2, results.size());
    assertTrue(results.stream().anyMatch(f -> f.getFlightNumber().equals("OS115")));
    assertTrue(results.stream().anyMatch(f -> f.getFlightNumber().equals("OS101")));
  }

  @Test
  void searchByDestinationTest() {
    List<Flight> flights = new ArrayList<>();
    Flight flight1 = new Flight(1,"VIE","FRA","Austrian","OS101",80,120.00, LocalTime.of(6,30));
    Flight flight2 = new Flight(2,"VIE","MUC","Austrian","OS115",60,110.00,LocalTime.of(7,0));
    Flight flight3 = new Flight(7,"MUC","FRA","Lufthansa","LH201",70,100.00,LocalTime.of(8,30));

    flights.add(flight1);
    flights.add(flight2);
    flights.add(flight3);

    SearchFlight searchFlight = new SearchFlight(flights);
    List<Flight> results = searchFlight.searchByDestination("FRA");

    assertEquals(2, results.size());
    assertTrue(results.stream().anyMatch(f -> f.getFlightNumber().equals("OS101")));
    assertTrue(results.stream().anyMatch(f -> f.getFlightNumber().equals("LH201")));
  }

  @Test
  void searchByAirline() {
    List<Flight> flights = new ArrayList<>();
    Flight flight1 = new Flight(1,"VIE","FRA","Austrian","OS101",80,120.00, LocalTime.of(6,30));
    Flight flight2 = new Flight(2,"VIE","MUC","Austrian","OS115",60,110.00,LocalTime.of(7,0));
    Flight flight3 = new Flight(7,"MUC","FRA","Lufthansa","LH201",70,100.00,LocalTime.of(8,30));

    flights.add(flight1);
    flights.add(flight2);
    flights.add(flight3);

    SearchFlight searchFlight = new SearchFlight(flights);
    List<Flight> results = searchFlight.searchByAirline("Lufthansa");

    assertEquals(1, results.size());
  }

  @Test
  void searchByFlightNumber() {
    List<Flight> flights = new ArrayList<>();
    Flight flight1 = new Flight(1,"VIE","FRA","Austrian","OS101",80,120.00, LocalTime.of(6,30));
    Flight flight2 = new Flight(2,"VIE","MUC","Austrian","OS115",60,110.00,LocalTime.of(7,0));
    Flight flight3 = new Flight(4,"FRA","LHR","Lufthansa","LH900",90,130.00,LocalTime.of(8,30));

    flights.add(flight1);
    flights.add(flight2);
    flights.add(flight3);

    SearchFlight searchFlight = new SearchFlight(flights);
    Flight result = searchFlight.searchByFlightNumber("OS101");

    assertNotNull(result);
    assertEquals(1, result.getId());
    assertEquals("VIE", result.getOrigin());
  }

  @Test
  void notExistedData() {
    List<Flight> flights = new ArrayList<>();
    Flight flight1 = new Flight(1,"VIE","FRA","Austrian","OS101",80,120.00, LocalTime.of(6,30));
    Flight flight2 = new Flight(2,"VIE","MUC","Austrian","OS115",60,110.00,LocalTime.of(7,0));
    Flight flight3 = new Flight(4,"FRA","LHR","Lufthansa","LH900",90,130.00,LocalTime.of(8,30));

    flights.add(flight1);
    flights.add(flight2);
    flights.add(flight3);

    SearchFlight searchFlight = new SearchFlight(flights);

    assertTrue(searchFlight.searchByOrigin("NOT").isEmpty());
    assertTrue(searchFlight.searchByDestination("NOT").isEmpty());
    assertTrue(searchFlight.searchByAirline("NotExist").isEmpty());
    assertNull(searchFlight.searchByFlightNumber("NO000"));
  }
}
