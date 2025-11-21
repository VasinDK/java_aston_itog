package five.sorter.strategies;

import java.util.List;
import java.util.stream.Collectors;

public class ByYearFreezeOdds implements SortStrategy {

    @Override
    public List<Car> sort(List<Car> cars) {
        List<Car> evens = new ByPower().sort(cars.stream()
                .filter(it -> it.getYear() % 2 == 0)
                .collect(Collectors.toList())
                .reversed());

        return cars.stream().map(it -> {
            if (it.getYear() % 2 == 0) {
                return evens.removeLast();
            } else {
                return it;
            }
        }).toList();
    }
}
