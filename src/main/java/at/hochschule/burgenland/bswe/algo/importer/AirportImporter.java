package at.hochschule.burgenland.bswe.algo.importer;

import at.hochschule.burgenland.bswe.algo.entities.Airport;
import at.hochschule.burgenland.bswe.algo.util.CSVUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AirportImporter implements Importable {
  private static final Logger log = LogManager.getLogger(AirportImporter.class);

  @Override
  public List<Object> importData() {
    List<Object> airports = new ArrayList<>();

    CSVUtils.readCSV(Paths.get("src", "main", "resources", "routes.csv")).forEach(line -> {
      try {
        String[] split = line.split(",");
        int count = split.length;
        if (count != 6) {
          throw new IllegalArgumentException("Falsche Anzahl an Parametern (" + count + " statt 6)!");
        }

        int id = Integer.parseInt(split[0].trim());
        String iata = split[1].trim();
        String city = split[2].trim();
        String country = split[3].trim();
        double latitude = Double.parseDouble(split[4].trim());
        double longitude = Double.parseDouble(split[5].trim());

        airports.add(new Airport(id, iata, city, country, latitude, longitude));
      } catch (IllegalArgumentException | NullPointerException e) {
        log.error("Fehler beim Importieren der Flughafens: " + e.getLocalizedMessage());
      }
    });
    return airports;
  }
}
