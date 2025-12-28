package at.hochschule.burgenland.bswe.algo.graph;

import at.hochschule.burgenland.bswe.algo.entities.Flight;
import at.hochschule.burgenland.bswe.algo.entities.Route;
import at.hochschule.burgenland.bswe.algo.importer.FlightsImporter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightRouterTest {
    static List<Flight> flights;

    @BeforeAll
    static void beforeAll() {
        flights = new FlightsImporter().importData();
    }

    @Test
    void testCheapestRoute_VIEtoJFK() {
        List<Route> routes = FlightRouter.findAllRoutes("VIE", "JFK", Flight::getPrice);

        Route cheapestRoute = new Route(List.of(
                flights.stream().filter(flight -> flight.getId() == 1).findFirst().orElseThrow(),
                flights.stream().filter(flight -> flight.getId() == 6).findFirst().orElseThrow()
        ), 780, 770, 1);

        assertEquals(cheapestRoute, routes.get(0));
    }

    @Test
    void testSlowestRoute_VIEtoJFK() {
        List<Route> routes = FlightRouter.findAllRoutes("VIE", "JFK", new DurationWeightFunction());

        Route slowestRoute = new Route(List.of(
                flights.stream().filter(flight -> flight.getId() == 1).findFirst().orElseThrow(),
                flights.stream().filter(flight -> flight.getId() == 5).findFirst().orElseThrow(),
                flights.stream().filter(flight -> flight.getId() == 11).findFirst().orElseThrow(),
                flights.stream().filter(flight -> flight.getId() == 13).findFirst().orElseThrow()
        ), 900, 1035, 3);

        assertEquals(slowestRoute, routes.get(routes.size() - 1));
    }

    @Test
    void testLeastStopovers_VIEtoJFK() {
        List<Route> routes = FlightRouter.findAllRoutes("VIE", "JFK", flight -> 1);

        Route leastStopovers = new Route(List.of(
                flights.stream().filter(flight -> flight.getId() == 1).findFirst().orElseThrow(),
                flights.stream().filter(flight -> flight.getId() == 6).findFirst().orElseThrow()
        ), 780, 770, 1);

        assertEquals(leastStopovers, routes.get(0));
    }

    @Test
    void testCheapestRoute_VIEtoORD() {
        List<Route> routes = FlightRouter.findAllRoutes("VIE", "ORD", Flight::getPrice);

        Route cheapestRoute = new Route(List.of(
                flights.stream().filter(flight -> flight.getId() == 1).findFirst().orElseThrow(),
                flights.stream().filter(flight -> flight.getId() == 6).findFirst().orElseThrow(),
                flights.stream().filter(flight -> flight.getId() == 15).findFirst().orElseThrow()
        ), 960, 950, 2);

        assertEquals(cheapestRoute, routes.get(0));
    }

    @Test
    void testSlowestRoute_VIEtoORD() {
        List<Route> routes = FlightRouter.findAllRoutes("VIE", "ORD", new DurationWeightFunction());

        Route slowestRoute = new Route(List.of(
                flights.stream().filter(flight -> flight.getId() == 1).findFirst().orElseThrow(),
                flights.stream().filter(flight -> flight.getId() == 6).findFirst().orElseThrow(),
                flights.stream().filter(flight -> flight.getId() == 15).findFirst().orElseThrow()
        ), 960, 950, 2);

        assertEquals(slowestRoute, routes.get(routes.size() - 1));
    }

    @Test
    void testLeastStopovers_VIEtoORD() {
        List<Route> routes = FlightRouter.findAllRoutes("VIE", "ORD", flight -> 1);

        routes.forEach(System.out::println);

        Route leastStopovers = new Route(List.of(
                flights.stream().filter(flight -> flight.getId() == 1).findFirst().orElseThrow(),
                flights.stream().filter(flight -> flight.getId() == 4).findFirst().orElseThrow(),
                flights.stream().filter(flight -> flight.getId() == 14).findFirst().orElseThrow()
        ), 950, 970, 2);

        assertEquals(leastStopovers, routes.get(0));
    }
}