package five.reader;

import five.car.Car;
import five.exception.CarIllegalArgumentException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;


public class CustomReaderTest {
    @Test
    void testPositiveReadFromFile() {
        String filePath = "src/test/resources/cars.txt";
        CustomReader reader = new CustomReader();

        try {
            List<Car> cars = reader.readFromFile(filePath);
            assertEquals(200, cars.get(1).getPower());
            assertEquals(1999, cars.get(1).getYear());
            assertEquals("bmw", cars.get(1).getBrand());
        } catch (CarIllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testNegativeReadFromFile() {
        String filePath = "src/test/resources/carsZero.txt";
        CustomReader reader = new CustomReader();

        assertThrows(CarIllegalArgumentException.class, () -> {
            reader.readFromFile(filePath);
        });
    }
}
