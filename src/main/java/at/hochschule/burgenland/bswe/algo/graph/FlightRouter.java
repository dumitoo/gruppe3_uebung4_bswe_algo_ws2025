package at.hochschule.burgenland.bswe.algo.graph;

import at.hochschule.burgenland.bswe.algo.entities.Flight;
import at.hochschule.burgenland.bswe.algo.entities.Route;
import at.hochschule.burgenland.bswe.algo.importer.FlightsImporter;

import java.time.LocalTime;
import java.util.*;

public class FlightRouter {
    public static List<Route> findAllRoutes(String origin, String destination, WeightFunction weightFunction) {
        Map<String, List<Flight>> graph = new HashMap<>();
        new FlightsImporter().importData().forEach(flight -> graph.computeIfAbsent(flight.getOrigin(), k -> new ArrayList<>()).add(flight));

        PriorityQueue<State> queue = new PriorityQueue<>(Comparator.comparingDouble(State::getCost));
        queue.add(new State(origin, new ArrayList<>(), 0.0, -1));

        List<Route> routes = new ArrayList<>();

        while (!queue.isEmpty()) {
            State currentState = queue.poll();
            String currentAirport = currentState.getAirport();
            List<Flight> currentPath = currentState.getPath();
            int currentStopovers = currentState.getStopovers();

            if (currentAirport.equals(destination) && currentStopovers >= 0) {
                Flight firstFlight = currentPath.get(0);
                Flight lastFlight = currentPath.get(currentPath.size() - 1);

                int departureFirst = minutesOfDay(firstFlight.getDepartureTime());
                int arrivalLast = minutesOfDay(lastFlight.getDepartureTime().plusMinutes(lastFlight.getDuration()));

                int totalDuration = arrivalLast - departureFirst;

                double totalPrice = currentPath.stream()
                        .mapToDouble(Flight::getPrice)
                        .sum();

                routes.add(new Route(new ArrayList<>(currentPath), totalDuration, totalPrice, currentStopovers));

                continue;
            }


            List<Flight> outgoingFlights = graph.getOrDefault(currentAirport, List.of());

            for (Flight flight : outgoingFlights) {
                LocalTime departureTime = flight.getDepartureTime();
                LocalTime arrivalTime = departureTime.plusMinutes(flight.getDuration());
                if (arrivalTime.isBefore(departureTime)) {
                    continue;
                }

                if (!currentPath.isEmpty()) {
                    Flight prevFlight = currentPath.get(currentPath.size() - 1);
                    LocalTime arrivalTimePrev = prevFlight.getDepartureTime().plusMinutes(prevFlight.getDuration());

                    if (arrivalTimePrev.plusMinutes(20).isAfter(departureTime)) {
                        continue;
                    }
                }

                int newStopovers = currentStopovers + 1;

                if (newStopovers > 3) {
                    continue;
                }

                double newCost = currentState.getCost() + weightFunction.getWeight(flight);

                List<Flight> newPath = new ArrayList<>(currentPath);
                newPath.add(flight);

                queue.add(new State(flight.getDestination(), newPath, newCost, newStopovers));
            }
        }

        if (weightFunction instanceof DurationWeightFunction) {
            routes.sort(Comparator.comparingInt(Route::getTotalDuration));
        }

        return routes;
    }

    private static int minutesOfDay(LocalTime time) {
        return time.getHour() * 60 + time.getMinute();
    }
}