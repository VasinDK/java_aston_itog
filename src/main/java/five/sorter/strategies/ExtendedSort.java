package five.sorter.strategies;

import five.sorter.Car;
import five.sorter.SortStrategy;

import java.util.ArrayList;
import java.util.List;

public class ExtendedSort  implements SortStrategy {
    @Override
    public List<Car> sort(List<Car> cars) {
        if (cars == null || cars.size() < 2) {
            return new ArrayList<>(cars);
        }
        List<Car> evenCars = new ArrayList<>();
        List<Integer> evenIndices = new ArrayList<>();

        for (int i = 0; i < cars.size(); i++) {
            Car car = cars.get(i);
            if (car.getPower() % 2 == 0) {
                evenCars.add(car);
                evenIndices.add(i);
            }
        }

        evenCars.sort((a, b) -> Integer.compare(a.getPower(), b.getPower()));

        List<Car> result = new ArrayList<>(cars);

        for (int i = 0; i < evenIndices.size(); i++) {
            int pos = evenIndices.get(i);
            result.set(pos, evenCars.get(i));
        }

        return result;
    }
}
