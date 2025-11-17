package five.car;

import five.car.util.CarCsvParser;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CarFactoryImpl implements CarFactory {

    private final Random rnd = new Random();

    private void validate(String brand, int power, int year) {
        if (brand == null || brand.isBlank()) {
            throw new IllegalArgumentException("Бренд не может быть пустым");
        }
        if (power <= 0) {
            throw new IllegalArgumentException("Мощность должна быть положительной");
        }
        if (year < 1900 || year > 2025) {
            throw new IllegalArgumentException("Неверный год");
        }
    }

    @Override
    public List<Car> createManual(String brand, int power, int year) {
        validate(brand, power, year);

        List<Car> list = new ArrayList<>();
        Car car = new Car.Builder()
                .brand(brand)
                .power(power)
                .year(year)
                .build();
        list.add(car);

        return list;
    }

    @Override
    public List<Car> createRandom(int count) {
        List<Car> list = new ArrayList<>();
        String[] brands = {"Toyota", "BMW", "Audi", "Ford", "Chevrolet", "Honda", "Kia", "Hyundai", "Лада"};

        for (int i = 0; i < count; i++) {
            String brand = brands[rnd.nextInt(brands.length)];
            int power = 50 + rnd.nextInt(200);
            int year = 1990 + rnd.nextInt(35);

            list.add(
                    new Car.Builder()
                            .brand(brand)
                            .power(power)
                            .year(year)
                            .build()
            );
        }

        return list;
    }

    @Override
    public List<Car> createFromCsv(Path file) {
        return CarCsvParser.read(file);
    }
}
