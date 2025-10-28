package at.hochschule.burgenland.bswe.algo.importer;

import at.hochschule.burgenland.bswe.algo.entities.Route;
import at.hochschule.burgenland.bswe.algo.util.CSVUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RouteImporter implements Importable<Route> {
    private static final Logger log = LogManager.getLogger(RouteImporter.class);

    @Override
    public List<Route> importData() {
        List<Route> routes = new ArrayList<>();

        CSVUtils.readCSV(Paths.get("src", "main", "resources", "routes.csv")).forEach(line -> {
            try {
                String[] splittedLine = line.replace(" ", "").split(",");
                int splittedLineLength = splittedLine.length;
                if (splittedLineLength != 5) {
                    throw new IllegalArgumentException("Falsche Anzahl an Parametern (" + splittedLineLength + " statt 5)!");
                }
                int id = Integer.parseInt(splittedLine[0]);
                List<Integer> flights = Arrays.stream(splittedLine[1].split("-")).map(Integer::parseInt).toList();
                int totalDuration = Integer.parseInt(splittedLine[2]);
                double totalPrice = Double.parseDouble(splittedLine[3]);
                int stopovers = Integer.parseInt(splittedLine[4]);

                //routes.add(new Route(id, flights, totalDuration, totalPrice, stopovers)); TODO: map id to flight
            } catch (IllegalArgumentException | NullPointerException e) {
                log.error("Fehler beim Importieren der Route: " + e.getLocalizedMessage());
            }
        });

        return routes;
    }
}