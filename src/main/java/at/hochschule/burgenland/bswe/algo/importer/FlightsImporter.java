package at.hochschule.burgenland.bswe.algo.importer;

import at.hochschule.burgenland.bswe.algo.entities.Flight;
import at.hochschule.burgenland.bswe.algo.util.CSVUtils;

import java.nio.file.Path;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.PatternSyntaxException;

public class FlightsImporter implements Importable{
    List<Object> flights = new ArrayList<>();


    @Override
    public List<Object> importData() {
        List<String> csvImport = CSVUtils.readCSV(Path.of("src", "main", "resources", "flights.csv"));

        for (String line : csvImport) {
            Flight flight = returnFlightIfValid(line);

            if (flight != null) {
                flights.add(flight);
            } else {
                System.out.println("Skipping invalid line.");
            }
        }
        return flights;
    }



    private Flight returnFlightIfValid(String line) {
        try {
            String[] att = line.split(",");

            if (att.length != 8) {
                throw new IllegalArgumentException("flights.csv file must contain exactly 8 columns.");
            }

            int id = Integer.parseInt(att[0]);
            String origin = att[1];
            String destination = att[2];
            String airline = att[3];
            String flightNumber = att[4];
            int duration = Integer.parseInt(att[5]);
            double price = Double.parseDouble(att[6]);
            LocalTime departureTime = LocalTime.parse(att[7], DateTimeFormatter.ofPattern("HH:mm"));

            return new Flight(id, origin, destination, airline, flightNumber, duration, price, departureTime);

        } catch (PatternSyntaxException e) {
            System.out.println("Delimiter in csv must be \",\"");
            System.out.println(Arrays.toString(e.getStackTrace()));
        } catch (NumberFormatException e) {
            System.out.println("While creating Flight object expected int, got invalid data type.");
            System.out.println(Arrays.toString(e.getStackTrace()));
        } catch (DateTimeParseException e) {
            System.out.println("While creating Flight object expected timestamp, got invalid data type.");
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }

        return null;
    }
}
