package at.hochschule.burgenland.bswe.algo;

import at.hochschule.burgenland.bswe.algo.util.FlightPlannerManagement;
import lombok.extern.log4j.Log4j2;

/**
 * Main driver class for flight routes planning service
 */
@Log4j2
public class FlightPlanningDriver {
  /**
   * Main method to start the program.
   *
   * @param args command-line arguments
   */
  public static void main(String[] args) {
    log.info("Programm started");
    FlightPlannerManagement plannerManagement = new FlightPlannerManagement();
    plannerManagement.run();
  }
}
