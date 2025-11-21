package five.sorter.strategies;
import five.car.Car;
import java.util.List;

public interface SortStrategy {
    List<Car> sort(List<Car> cars);
}
