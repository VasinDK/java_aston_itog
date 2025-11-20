package five.sorter.strategies;

import java.util.Collections;
import java.util.List;

public class ReverseStrategy implements SortStrategy {
    private final SortStrategy strategy;

    public ReverseStrategy(SortStrategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public List<Car> sort(List<Car> cars) {
        List<Car> sorted = strategy.sort(cars);
        Collections.reverse(sorted);
        return sorted;
    }
}
