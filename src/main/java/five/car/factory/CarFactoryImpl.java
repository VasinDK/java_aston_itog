package five.car.factory;

import five.car.model.Car;
import five.car.util.CarCsvParser;
import java.nio.file.Path;
import java.util.*;

public class CarFactoryImpl implements CarFactory {

    @Override
    public List<Car> createManual(Scanner scanner) {
        List<Car> list = new ArrayList<>();

        System.out.print("Введите бренд: ");
        String brand = scanner.nextLine();

        System.out.print("Введите мощность: ");
        int power = Integer.parseInt(scanner.nextLine());

        System.out.print("Введите год: ");
        int year = Integer.parseInt(scanner.nextLine());

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
        Random r = new Random();

        String[] brands = {"Toyota", "BMW", "Audi", "Ford", "Chevrolet", "Honda", "Kia", "Hyundai", "Лада"};

        for (int i = 0; i < count; i++) {
            Car car = new Car.Builder()
                    .power(50 + r.nextInt(200))
                    .brand(brands[r.nextInt(brands.length)])
                    .year(1990 + r.nextInt(35))
                    .build();

            list.add(car);
        }

        return list;
    }

    @Override
    public List<Car> createFromCsv(Path file) {
        return CarCsvParser.read(file);
    }
}
