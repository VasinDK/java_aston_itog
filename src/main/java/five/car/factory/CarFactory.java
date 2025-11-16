package five.car.factory;

import five.car.model.Car;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public interface CarFactory {
    List<Car> createManual(Scanner scanner);
    List<Car> createRandom(int count);
    List<Car> createFromCsv(Path file);
}
