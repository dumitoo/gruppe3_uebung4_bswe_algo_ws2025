package at.hochschule.burgenland.bswe.algo.menu;

import at.hochschule.burgenland.bswe.algo.entities.Flight;
import at.hochschule.burgenland.bswe.algo.entities.Route;
import at.hochschule.burgenland.bswe.algo.graph.FlightRouter;
import at.hochschule.burgenland.bswe.algo.graph.WeightFunction;
import at.hochschule.burgenland.bswe.algo.importer.RouteImporter;
import at.hochschule.burgenland.bswe.algo.util.CSVUtils;
import at.hochschule.burgenland.bswe.algo.util.InputUtils;
import at.hochschule.burgenland.bswe.algo.util.OutputUtils;

import java.nio.file.Paths;
import java.util.List;

public class RouteMenu {
    public static void runMenu() {
        System.out.println("-------------------------ROUTE MENU-------------------------");
        String origin = InputUtils.getValidIATACode("Origin"), destination = InputUtils.getValidIATACode("Destination");
        int option = InputUtils.getValidMenuOption(List.of("Cheapest Route", "Slowest Route", "Least Stopovers"));

        WeightFunction weightFunction = switch (option) {
            case 1 -> Flight::getPrice;
            case 2 -> Flight::getDuration;
            case 3 -> flight -> 1.0;
            default -> throw new IllegalStateException("Unexpected value: " + option);
        };

        List<Route> routes = FlightRouter.findAllRoutes(origin, destination, weightFunction);

        if(routes.isEmpty()) {
            System.out.println("No possible route found!");
            return;
        }

        Route route = switch (option) {
            case 1, 3 -> routes.get(0);
            case 2 -> routes.get(routes.size() - 1);
            default -> throw new IllegalStateException("Unexpected value: " + option);
        };

        OutputUtils.printRoute(route);

        List<Route> existingRoutes = new RouteImporter().importData();
        if (existingRoutes.stream().noneMatch(existingRoute -> existingRoute.equals(route))) {
            route.setId(existingRoutes.get(existingRoutes.size() - 1).getId() + 1);

            List<Flight> flights = route.getFlights();
            StringBuilder line = new StringBuilder(route.getId() + "," + flights.get(0).getId());
            for (int i = 1; i < flights.size(); i++) {
                line.append("-").append(flights.get(i).getId());
            }
            line.append(",").append(route.getTotalDuration()).append(",").append(route.getTotalPrice()).append(",").append(route.getStopovers());
            CSVUtils.writeCSV(Paths.get("src", "main", "resources", "routes.csv"), line.toString());
        }
    }
}