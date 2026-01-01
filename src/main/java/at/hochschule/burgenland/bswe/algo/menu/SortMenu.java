package at.hochschule.burgenland.bswe.algo.menu;

import at.hochschule.burgenland.bswe.algo.comparators.CombinedComparator;
import at.hochschule.burgenland.bswe.algo.comparators.DurationAscendingComparator;
import at.hochschule.burgenland.bswe.algo.comparators.PriceAscendingComparator;
import at.hochschule.burgenland.bswe.algo.comparators.StopoversAscendingComparator;
import at.hochschule.burgenland.bswe.algo.entities.Route;
import at.hochschule.burgenland.bswe.algo.importer.RouteImporter;
import at.hochschule.burgenland.bswe.algo.sort.StableMergeSort;
import at.hochschule.burgenland.bswe.algo.sort.UnstableQuickSort;
import at.hochschule.burgenland.bswe.algo.util.InputUtils;
import at.hochschule.burgenland.bswe.algo.util.OutputUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class SortMenu {

    private static final Scanner scanner = new Scanner(System.in);

    public static void runMenu() {
        System.out.println("-------------------------SORT MENU--------------------------");

        List<Route> selectedRoutes = getRoutesFromUserInput();
        if (selectedRoutes.isEmpty()) {
            System.out.println("Returning to main menu...");
            return;
        }
        
        int algoChoice = InputUtils.getValidMenuOption(List.of(
                "Merge Sort (Stable)",
                "Quick Sort (Unstable)"
        ));

        Comparator<Route> comparator = getComparatorFromUserInput();

        List<Route> sortedRoute = sortRoutes(selectedRoutes, algoChoice, comparator);

        int counter = 1;
        System.out.println("-------------------------SORTED ROUTES--------------------------");
        for (Route route : sortedRoute) {
            System.out.println("\n" + counter + ")\n");
            OutputUtils.printRoute(route);
            System.out.println();
            counter++;
        }
    }

    private static List<Route> sortRoutes(List<Route> routes, int algoChoice, Comparator<Route> comparator) {
        if (algoChoice == 1) {
            System.out.println("\nUsing stable merge sort...");
            StableMergeSort mergeSort = new StableMergeSort();
            return mergeSort.sort(routes, comparator);
        } else {
            System.out.println("\nUsing unstable quick sort...");
            UnstableQuickSort quickSort = new UnstableQuickSort();
            return quickSort.sort(routes, comparator);
        }
    }

    private static Comparator<Route> getComparatorFromUserInput() {
        System.out.println("Select sorting parameters: ");
        boolean isRunning = true;
        Comparator<Route> comparator = null;

        while (isRunning) {
            int choice = InputUtils.getValidMenuOption(List.of(
                    "(1) Price (ascending, cheapest first)",
                    "(2) Duration (ascending, fastest first)",
                    "(3) Stopovers (ascending, fewest first)",
                    "(4) Combined (In order: Price, Duration, Stopovers)"
            ));

            comparator = switch (choice) {
                case 1 -> new PriceAscendingComparator();
                case 2 -> new DurationAscendingComparator();
                case 3 -> new StopoversAscendingComparator();
                case 4 -> new CombinedComparator();
                default -> null;
            };


            if (comparator == null) {
                System.out.println("Choice " + choice + " doesnt exist. Please try again \n");
                isRunning = false;
            } else {
                return comparator;
            }
        }
        return comparator;
    }

    private static List<Route> getRoutesFromUserInput() {
        List<Route> allRoutes = new RouteImporter().importData();
        List<Route> selectedRoutes = new ArrayList<>();

        System.out.print("Enter route IDs (comma-seperated): ");
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            System.out.println("No routes were entered");
            return selectedRoutes;
        }

        String[] routeIds = input.split(",");

        for (String idString : routeIds) {
            try {
                int id = Integer.parseInt(idString.trim());
                Route route = findRouteById(allRoutes, id);

                if (route != null) {
                    selectedRoutes.add(route);
                } else  {
                    System.out.println("Route with ID " + id + " not found");
                }
            } catch (NumberFormatException e) {
                System.out.println(idString + " is not a valid number");
            }
        }

        return selectedRoutes;
    }

    private static Route findRouteById(List<Route> routes, int id) {
        for (Route route : routes) {
            if (route.getId() == id) {
                return route;
            }
        }
        return null;
    }
}
