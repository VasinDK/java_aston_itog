package five.car;

import java.nio.file.Path;
import java.util.List;

public interface CarFactory {
    List<Car> createManual(String brand, int power, int year);
    List<Car> createRandom(int count);
    List<Car> createFromCsv(Path file);
}
