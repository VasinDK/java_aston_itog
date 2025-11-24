package five.car;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

public class CarImplTest {
    @ParameterizedTest
    @CsvSource({
            "bmw, 321, 1964",
            "audi, 112, 1999",
            "mb, 0, 1000",
            "Not, -10, -1000",
            "super, 1110, 10000",
    })
    void testPositive(String a, int b, int c) {
        Car car = new CarImpl(a, b, c);
        assertNotNull(car);
        assertAll(
                () -> assertEquals(car.getBrand(), a),
                () -> assertEquals(car.getPower(), b),
                () -> assertEquals(car.getYear(), c)
        );
    }
}
