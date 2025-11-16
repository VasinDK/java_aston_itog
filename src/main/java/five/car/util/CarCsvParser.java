package five.car.util;

import five.car.model.Car;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class CarCsvParser {

    private static final Path DEFAULT_CSV_PATH = Paths.get("src", "main", "java", "five", "data", "cars.csv");

    public static List<Car> read(Path path) {
        List<Car> list = new ArrayList<>();

        try {
            Path parent = path.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }

            if (!Files.exists(path)) {
                System.out.println("Файл не найден: " + path.toAbsolutePath());
                return list;
            }

            try (BufferedReader br = Files.newBufferedReader(path)) {
                String line;

                while ((line = br.readLine()) != null) {
                    String[] f = line.split(",");

                    if (f.length != 3) continue;

                    try {
                        int power = Integer.parseInt(f[0].trim());
                        String brand = f[1].trim();
                        int year = Integer.parseInt(f[2].trim());

                        Car car = new Car.Builder()
                                .brand(brand)
                                .power(power)
                                .year(year)
                                .build();

                        list.add(car);
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка формата в строке: " + line);
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Ошибка работы с CSV: " + e.getMessage());
        }

        return list;
    }

    public static List<Car> read() {
        return read(DEFAULT_CSV_PATH);
    }

    public static void write(Path path, List<Car> cars) {
        try {
            Path parent = path.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }

            try (BufferedWriter writer = Files.newBufferedWriter(path,
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {

                for (Car car : cars) {
                    String line = car.getBrand() + "," + car.getPower() + "," + car.getYear() + "\n";
                    writer.write(line);
                }
            }
            System.out.println("Данные сохранены в: " + path.toAbsolutePath());

        } catch (IOException e) {
            System.out.println("Ошибка записи в CSV: " + e.getMessage());
        }
    }

    public static void write(List<Car> cars) {
        write(DEFAULT_CSV_PATH, cars);
    }

    public static void append(Path path, Car car) {
        try {
            Path parent = path.getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }

            try (BufferedWriter writer = Files.newBufferedWriter(path,
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {

                String line = car.getBrand() + "," + car.getPower() + "," + car.getYear() + "\n";
                writer.write(line);
            }

        } catch (IOException e) {
            System.out.println("Ошибка добавления в CSV: " + e.getMessage());
        }
    }

    public static void append(Car car) {
        append(DEFAULT_CSV_PATH, car);
    }
}