package at.hochschule.burgenland.bswe.algo.util;

import lombok.extern.log4j.Log4j2;

import java.util.Scanner;

/**
 * Utility class for reading and validating user input.
 */
@Log4j2
public class InputUtils {
  private final Scanner scanner = new Scanner(System.in);

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
}
