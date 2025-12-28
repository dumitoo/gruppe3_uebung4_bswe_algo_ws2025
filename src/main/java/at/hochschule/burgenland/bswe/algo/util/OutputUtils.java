package at.hochschule.burgenland.bswe.algo.util;

import at.hochschule.burgenland.bswe.algo.entities.Airport;
import at.hochschule.burgenland.bswe.algo.entities.Flight;
import at.hochschule.burgenland.bswe.algo.entities.Route;
import at.hochschule.burgenland.bswe.algo.importer.AirportImporter;

import java.util.List;

public class OutputUtils {
    public static void printAirport(Airport airport) {
        System.out.println(airport.getCity() + " (" + airport.getIata() + "), " + airport.getCountry() + " (" + airport.getLatitude() + ", " + airport.getLongitude() + ")");
    }

    public static void printFlight(Flight flight) {
        List<Airport> airports = new AirportImporter().importData();
        Airport origin = airports.stream().filter(airport -> airport.getIata().equals(flight.getOrigin())).findFirst().orElse(null), destination = airports.stream().filter(airport -> airport.getIata().equals(flight.getDestination())).findFirst().orElse(null);
        System.out.print(flight.getDepartureTime() + " - ");
        OutputUtils.printAirport(origin);
        System.out.println("  |");
        System.out.println("  |     " + flight.getFlightNumber() + " (" + flight.getAirline() + ") - " + getHourMinuteString(flight.getDuration()) + ", € " + flight.getPrice());
        System.out.println("  V");
        System.out.print(flight.getDepartureTime().plusMinutes(flight.getDuration()) + " - ");
        OutputUtils.printAirport(destination);
    }

    public static void printRoute(Route route) {
        List<Flight> flights = route.getFlights();
        List<Airport> airports = new AirportImporter().importData();
        Flight first = flights.get(0), last = flights.get(flights.size() - 1);
        int i = 1;

        Airport origin = airports.stream().filter(airport -> airport.getIata().equals(first.getOrigin())).findFirst().orElse(null), destination = airports.stream().filter(airport -> airport.getIata().equals(last.getDestination())).findFirst().orElse(null);
        System.out.println("-------------------------FULL ROUTE-------------------------");
        System.out.print(first.getDepartureTime() + " - ");
        OutputUtils.printAirport(origin);
        System.out.println("  |");
        System.out.println("  |     " + getHourMinuteString(route.getTotalDuration()) + ", € " + route.getTotalPrice() + ", " + route.getStopovers() + " stopovers");
        System.out.println("  V");
        System.out.print(last.getDepartureTime().plusMinutes(last.getDuration()) + " - ");
        OutputUtils.printAirport(destination);
        System.out.println();

        for (Flight flight : flights) {
            System.out.println("-------------------------FLIGHT #" + (i++) + "--------------------------");
            OutputUtils.printFlight(flight);
            System.out.println();
        }
    }

    public static String getHourMinuteString(int minutes) {
        int hours = minutes / 60;
        int remainingMinutes = minutes % 60;

        if (hours == 0) {
            return remainingMinutes + "min";
        }

        if (remainingMinutes == 0) {
            return hours + "h";
        }

        return hours + "h " + remainingMinutes + "min";
    }
}