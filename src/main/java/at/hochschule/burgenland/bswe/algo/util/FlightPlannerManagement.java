package at.hochschule.burgenland.bswe.algo.util;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class FlightPlannerManagement {
  private final InputUtils utils = new InputUtils();

  /**
   * Method to run the programm.
   */
  public void run() {
    log.info("Running..");
    System.out.println("-- Welcome to the Flight Route Planning service! --");
    String menu = """
        
        Please choose:
        1 - Not yet implemented
        2 - Not yet implemented
        3 - Not yet implemented
        
        0 - Exit
        """;

    boolean isRunning = true;
    while (isRunning) {
      String input = utils.readValidString(menu);
      switch (input) {
        case "1" -> System.out.println("Not yet implemented1");
        case "2" -> System.out.println("Not yet implemented2");
        case "3" -> System.out.println("Not yet implemented3");
        case "0" -> {
          log.info("Exiting...");
          System.out.println("Exiting...");
          isRunning = false;
        }
        default -> {
          log.warn("Invalid input: " + input);
          System.err.println("Invalid input. Please try again.");
        }
      }
    }
  }
}
