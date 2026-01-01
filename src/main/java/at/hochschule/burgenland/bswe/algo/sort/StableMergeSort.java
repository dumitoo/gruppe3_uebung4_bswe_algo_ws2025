package at.hochschule.burgenland.bswe.algo.sort;

import at.hochschule.burgenland.bswe.algo.entities.Route;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StableMergeSort {

    public List<Route> sort(List<Route> routes, Comparator<Route> comparator) {
        if (routes == null ||routes.size() <= 1) {
            return routes;
        }

        List<Route> copiedRoutes = new ArrayList<>(routes);
        mergeSort(copiedRoutes, 0, copiedRoutes.size() - 1, comparator);
        return copiedRoutes;
    }

    private void mergeSort(List<Route> routes, int left, int right, Comparator<Route> comparator) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            mergeSort(routes, left, mid, comparator);
            mergeSort(routes, mid + 1, right, comparator);
            mergeLists(routes, left, mid, right, comparator);
        }
    }

    private void mergeLists(List<Route> routes, int left, int mid, int right, Comparator<Route> comparator) {
        List<Route> leftList = new ArrayList<>();
        List<Route> rightList = new ArrayList<>();

        for (int i = left; i <= mid; i++) {
            leftList.add(routes.get(i));
        }

        for (int i = mid + 1; i <= right; i++) {
            rightList.add(routes.get(i));
        }

        int i = 0;
        int j = 0;
        int k = left;

        while (i < leftList.size() && j < rightList.size()) {
            if (comparator.compare(leftList.get(i), rightList.get(j)) <= 0) {
                routes.set(k, leftList.get((i)));
                i++;
            } else {
                routes.set(k, rightList.get(j));
                j++;
            }
            k++;
        }

        while (i < leftList.size()) {
            routes.set(k, leftList.get(i));
            i++;
            k++;
        }


        while (j < rightList.size()) {
            routes.set(k, rightList.get(j));
            j++;
            k++;
        }
    }
}
