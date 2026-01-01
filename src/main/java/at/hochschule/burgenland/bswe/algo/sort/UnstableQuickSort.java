package at.hochschule.burgenland.bswe.algo.sort;

import at.hochschule.burgenland.bswe.algo.entities.Route;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class UnstableQuickSort {
    public List<Route> sort(List<Route> routes, Comparator<Route> comparator) {
        if (routes == null | routes.size() <= 1) {
            return new ArrayList<>(routes);
        }

        List<Route> copiedRoutes = new ArrayList<>(routes);
        quickSort(copiedRoutes, 0, copiedRoutes.size() - 1, comparator);
        return copiedRoutes;
    }

    private void quickSort(List<Route> routes, int low, int high, Comparator<Route> comparator) {
        if (low < high) {
            int pivotIndex = partition(routes, low, high, comparator);

            quickSort(routes, low, pivotIndex - 1, comparator);
            quickSort(routes, pivotIndex + 1, high, comparator);
        }
    }

    private int partition(List<Route> routes, int low, int high, Comparator<Route> comparator) {
        Route pivot = routes.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (comparator.compare(routes.get(j), pivot) <= 0) {
                i++;
                swap(routes, i, j);
            }
        }
        swap(routes, i + 1, high);
        return i + 1;
    }

    private void swap(List<Route> routes, int i, int j) {
        Route temp = routes.get(i);
        routes.set(i, routes.get(j));
        routes.set(j, temp);
    }
}
