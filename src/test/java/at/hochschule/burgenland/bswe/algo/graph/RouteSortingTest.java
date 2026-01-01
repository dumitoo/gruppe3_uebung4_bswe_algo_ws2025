package at.hochschule.burgenland.bswe.algo.graph;

import at.hochschule.burgenland.bswe.algo.comparators.CombinedComparator;
import at.hochschule.burgenland.bswe.algo.comparators.DurationAscendingComparator;
import at.hochschule.burgenland.bswe.algo.comparators.PriceAscendingComparator;
import at.hochschule.burgenland.bswe.algo.comparators.StopoversAscendingComparator;
import at.hochschule.burgenland.bswe.algo.entities.Flight;
import at.hochschule.burgenland.bswe.algo.entities.Route;
import at.hochschule.burgenland.bswe.algo.sort.StableMergeSort;
import at.hochschule.burgenland.bswe.algo.sort.UnstableQuickSort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RouteSortingTest {
    private StableMergeSort mergeSort;
    private UnstableQuickSort quickSort;

    private List<Route> testRoutes;

    @BeforeEach
    void setup() {
        mergeSort = new StableMergeSort();
        quickSort = new UnstableQuickSort();
        testRoutes = createTestRoutes();
    }

    private List<Route> createTestRoutes() {
        List<Route> routes = new ArrayList<>();

        routes.add(createRoute(1, "Austrian", "OS123", 150.0, 480, 3));
        routes.add(createRoute(2, "Wizz Air", "WZ456", 350.0, 120, 0));
        routes.add(createRoute(3, "Lufthansa", "LH789", 250.0, 240, 1));
        routes.add(createRoute(4, "Emirates", "EK101", 250.0, 300, 2));
        routes.add(createRoute(5, "Ryanair", "FR202", 99.0, 600, 2));

        return routes;
    }

    private Route createRoute(int id, String airline, String flightNumber, double price, int duration, int stopovers) {
        Flight flight = new Flight(id, "VIE", "JFK", airline, flightNumber, duration, price, LocalTime.of(10,0));

        List<Flight> flights = new ArrayList<>();
        flights.add(flight);

        return new Route(id, flights, duration, price, stopovers);
    }

    @Test
    void mergeSortByPrice() {
        Comparator<Route> comparator = new PriceAscendingComparator();

        List<Route> sortedList = mergeSort.sort(testRoutes, comparator);

        assertEquals(5, sortedList.get(0).getId());
        assertEquals(1, sortedList.get(1).getId());
        assertEquals(250.0, sortedList.get(2).getTotalPrice());
        assertEquals(250.0, sortedList.get(3).getTotalPrice());
        assertEquals(2, sortedList.get(4).getId());
    }

    @Test
    void quickSortByPrice() {
        Comparator<Route> comparator = new PriceAscendingComparator();

        List<Route> sortedList = quickSort.sort(testRoutes, comparator);

        assertEquals(5, sortedList.get(0).getId());
        assertEquals(1, sortedList.get(1).getId());
        assertEquals(250.0, sortedList.get(2).getTotalPrice());
        assertEquals(250.0, sortedList.get(3).getTotalPrice());
        assertEquals(2, sortedList.get(4).getId());
    }

    @Test
    void mergeSortByDuration() {
        Comparator<Route> comparator = new DurationAscendingComparator();

        List<Route> sortedList = mergeSort.sort(testRoutes, comparator);

        assertEquals(2, sortedList.get(0).getId());
        assertEquals(3, sortedList.get(1).getId());
        assertEquals(4, sortedList.get(2).getId());
        assertEquals(1, sortedList.get(3).getId());
        assertEquals(5, sortedList.get(4).getId());
    }

    @Test
    void quickSortByDuration() {
        Comparator<Route> comparator = new DurationAscendingComparator();

        List<Route> sortedList = quickSort.sort(testRoutes, comparator);

        assertEquals(2, sortedList.get(0).getId());
        assertEquals(3, sortedList.get(1).getId());
        assertEquals(4, sortedList.get(2).getId());
        assertEquals(1, sortedList.get(3).getId());
        assertEquals(5, sortedList.get(4).getId());
    }

    @Test
    void mergeSortByStopovers() {
        Comparator<Route> comparator = new StopoversAscendingComparator();

        List<Route> sortedList = mergeSort.sort(testRoutes, comparator);

        assertEquals(0, sortedList.get(0).getStopovers());
        assertEquals(1, sortedList.get(1).getStopovers());
        assertEquals(2, sortedList.get(2).getStopovers());
        assertEquals(2, sortedList.get(3).getStopovers());
        assertEquals(3, sortedList.get(4).getStopovers());
    }

    @Test
    void combinedComparatorSortByPriceThenDuration() {
        Comparator<Route> comparator = new CombinedComparator();
        List<Route> testRoutes = new ArrayList<>();
        testRoutes.add(createRoute(1, "A", "A1", 200.0, 300, 1));
        testRoutes.add(createRoute(2, "B", "B1", 200.0, 150, 1));
        testRoutes.add(createRoute(3, "C", "C1", 100.0, 400, 1));

        List<Route> sortedList = mergeSort.sort(testRoutes, comparator);

        assertEquals(3, sortedList.get(0).getId());
        assertEquals(2, sortedList.get(1).getId());
        assertEquals(1, sortedList.get(2).getId());
    }

    @Test
    void combinedComparatorFallsThroughToStopover() {
           Comparator<Route> comparator = new CombinedComparator();
           List<Route> testRoutes = new ArrayList<>();
           testRoutes.add(createRoute(1, "A", "A1", 200.0, 120, 3));
           testRoutes.add(createRoute(2, "B", "B1", 200.0, 120, 1));
           testRoutes.add(createRoute(3, "C", "C1", 200.0, 120, 2));

           List<Route> sortedList = mergeSort.sort(testRoutes, comparator);

           assertEquals(2, sortedList.get(0).getId());
           assertEquals(3, sortedList.get(1).getId());
           assertEquals(1, sortedList.get(2).getId());
    }

    @Test
    void mergeSortIsStable() {
        Comparator<Route> comparator = new PriceAscendingComparator();
        List<Route> testRoutes = new ArrayList<>();
        testRoutes.add(createRoute(1, "A", "A1", 100.0, 100, 0));
        testRoutes.add(createRoute(2, "B", "B1", 200.0, 100, 0));
        testRoutes.add(createRoute(3, "C", "C1", 100.0, 150, 0));
        testRoutes.add(createRoute(4, "D", "D1", 100.0, 200, 0));

        List<Route> sortedList = mergeSort.sort(testRoutes, comparator);

        assertEquals(1, sortedList.get(0).getId());
        assertEquals(3, sortedList.get(1).getId());
        assertEquals(4, sortedList.get(2).getId());
        assertEquals(2, sortedList.get(3).getId());

    }
}
