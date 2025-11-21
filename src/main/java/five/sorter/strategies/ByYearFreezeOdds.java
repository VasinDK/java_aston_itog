package five.sorter.strategies;
import five.car.Car;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ByYearFreezeOdds implements SortStrategy {

    @Override
    public List<Car> sort(List<Car> cars) {
        List<Car> evens = new ByYear().sort(
                cars.stream()
                        .filter(it -> it.getYear() % 2 == 0)
                        .collect(Collectors.toList())
        );

        Collections.reverse(evens);

        return cars.stream()
                .map(it -> {
                    if (it.getYear() % 2 == 0) {
                        return evens.remove(evens.size() - 1);
                    } else {
                        return it;
                    }
                })
                .toList();
    }

}
