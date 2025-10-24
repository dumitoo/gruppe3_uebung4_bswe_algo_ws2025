package at.hochschule.burgenland.bswe.algo.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class CSVUtils {
    private static final Logger log = LogManager.getLogger(CSVUtils.class);

    public static List<String> readCSV(Path path) {
        String line;
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

        } catch (IOException e) {
            log.error("Fehler beim Lesen der Datei: " + e.getLocalizedMessage());
        }

        return lines;
    }
}