package five.sorter;

import five.car.Car;
import five.exception.CarIllegalArgumentException;
import five.reader.CustomReader;
import five.sorter.strategies.ByBrand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class SortExecutorTest {
    @Test
    void testByBrand() {
        String filePath = "src/test/resources/cars.txt";
        CustomReader reader = new CustomReader();
        List<Car> cars = new ArrayList<>();

        try {
            cars = reader.readFromFile(filePath);
        } catch(CarIllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        SortExecutor executor = new SortExecutor(cars);
        executor.setStrategy(new ByBrand());
        cars = executor.exec();

        assertEquals("bmw", cars.get(0).getBrand());
        assertEquals(200, cars.get(0).getPower());
        assertEquals(1999, cars.get(0).getYear());
    }
}
