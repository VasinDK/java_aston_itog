package five.sorter.strategies;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ByBrand implements SortStrategy {
    Comparator<String> comparator = String::compareToIgnoreCase;

    private List<Car> merge(List<Car> left, List<Car> right) {
        List<Car> mergeResult = new ArrayList<>(left.size() + right.size());
        int i = 0;
        int j = 0;

        while (i < left.size() && j < right.size()) {
            Car first = left.get(i);
            Car second = right.get(j);

            if (comparator.compare(first.getBrand(), second.getBrand()) <= 0) {
                mergeResult.add(first);
                i++;
            } else {
                mergeResult.add(second);
                j++;
            }
        }

        while (i < left.size()) {
            mergeResult.add(left.get(i++));
        }

        while (j < right.size()) {
            mergeResult.add(right.get(j++));
        }

        return mergeResult;
    }

    @Override
    public List<Car> sort(List<Car> cars) {
        if (cars.size() < 2) {
            return new ArrayList<>(cars);
        }
        int mid = cars.size() / 2;
        List<Car> left = new ArrayList<>(cars.subList(0, mid));
        List<Car> right = new ArrayList<>(cars.subList(mid, cars.size()));

        List<Car> sortedLeft = sort(left);
        List<Car> sortedRight = sort(right);

        return merge(sortedLeft, sortedRight);
    }
}