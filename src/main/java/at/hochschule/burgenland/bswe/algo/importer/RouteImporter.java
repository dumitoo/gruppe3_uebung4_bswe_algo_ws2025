package at.hochschule.burgenland.bswe.algo.importer;

import at.hochschule.burgenland.bswe.algo.entities.Flight;
import at.hochschule.burgenland.bswe.algo.entities.Route;
import at.hochschule.burgenland.bswe.algo.util.CSVUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Paths;
import java.util.*;

public class RouteImporter implements Importable<Route> {
    private static final Logger log = LogManager.getLogger(RouteImporter.class);

    @Override
    public List<Route> importData() {
        Map<Integer, Flight> flightMap = new HashMap<>();
        new FlightsImporter().importData().forEach(flight -> flightMap.put(flight.getId(), flight));

        List<Route> routes = new ArrayList<>();

        CSVUtils.readCSV(Paths.get("src", "main", "resources", "routes.csv")).forEach(line -> {
            try {
                String[] splittedLine = line.replace(" ", "").split(",");
                int splittedLineLength = splittedLine.length;
                if (splittedLineLength != 5) {
                    throw new IllegalArgumentException("Falsche Anzahl an Parametern (" + splittedLineLength + " statt 5)!");
                }
                int id = Integer.parseInt(splittedLine[0]);
                List<Flight> flights = Arrays.stream(splittedLine[1].split("-")).map(Integer::parseInt).map(flightMap::get).toList();
                int totalDuration = Integer.parseInt(splittedLine[2]);
                double totalPrice = Double.parseDouble(splittedLine[3]);
                int stopovers = Integer.parseInt(splittedLine[4]);

                routes.add(new Route(id, flights, totalDuration, totalPrice, stopovers));
            } catch (IllegalArgumentException | NullPointerException e) {
                log.error("Fehler beim Importieren der Route: " + e.getLocalizedMessage());
            }
        });

        return routes;
    }
}