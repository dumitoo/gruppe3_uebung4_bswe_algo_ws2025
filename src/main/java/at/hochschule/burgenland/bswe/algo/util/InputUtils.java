package at.hochschule.burgenland.bswe.algo.util;

import at.hochschule.burgenland.bswe.algo.entities.Airport;
import at.hochschule.burgenland.bswe.algo.importer.AirportImporter;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Scanner;

/**
 * Utility class for reading and validating user input.
 */
@Log4j2
public class InputUtils {
    private final static Scanner scanner = new Scanner(System.in);

    /**
     * Reads a non-empty string from standard input.
     *
     * @param message message shown before reading input
     * @return valid user input string
     */
    public String readValidString(String message) {
        while (true) {
            System.out.println(message);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            } else {
                log.warn("Input cannot be empty, please try again");
                System.err.println("Input cannot be empty, please try again");
            }
        }
    }

    public static String getValidIATACode(String name) {
        List<Airport> airports = new AirportImporter().importData();
        String input = "";
        boolean inputIsValid = false;

        do {
            try {
                System.out.print(name + " (IATA): ");
                input = scanner.nextLine().trim().toUpperCase();

                if (input.length() != 3) {
                    throw new IllegalArgumentException("IATA Code must be 3 characters long!");
                }

                String finalIataCode = input;
                if (airports.stream().noneMatch(airport -> airport.getIata().equals(finalIataCode))) {
                    throw new IllegalArgumentException("Invalid IATA Code!");
                }

                inputIsValid = true;
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        } while (!inputIsValid);

        return input;
    }

    public static int getValidMenuOption(List<String> options) {
        int input = 0;
        boolean inputIsValid = false;

        do {
            try {
                int i = 1;

                System.out.println("Choose an option:");
                for (String option : options) {
                    System.out.println(i++ + ") " + option);
                }
                System.out.print("Option: ");

                input = Integer.parseInt(scanner.nextLine().trim());

                if (input > options.size() || input < 1) {
                    throw new IllegalArgumentException("Invalid input!");
                }

                inputIsValid = true;
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        } while (!inputIsValid);

        return input;
    }
}