package five.car.model;

import five.car.factory.CarFactory;
import five.car.factory.CarFactoryImpl;
import five.car.util.CarCsvParser;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarTest {
    public static void main(String[] args) {
        CarFactory factory = new CarFactoryImpl();
        Scanner scanner = new Scanner(System.in);
        Path csvPath = null;
        boolean fileLoaded = false;

        while (true) {
            if (!fileLoaded) {
                System.out.println("\n--- Меню ---");
                System.out.println("1 - Создать базу данных");
                System.out.println("2 - Открыть базу данных");
                System.out.println("0 - Выход");
                System.out.print("Выберите действие: ");
                String choice = scanner.nextLine().trim();

                switch (choice) {
                    case "1" -> {
                        System.out.println("\nСоздание базы данных:");
                        System.out.println("1 - Вручную");
                        System.out.println("2 - Случайные данные");
                        System.out.print("Выберите способ: ");
                        String createMode = scanner.nextLine().trim();

                        System.out.print("Введите имя файла: ");
                        String fileName = scanner.nextLine().trim();
                        if (fileName.isEmpty()) fileName = "cars";
                        csvPath = Path.of("src", "main", "java", "five", "data", fileName + ".csv");

                        List<Car> cars;
                        if ("1".equals(createMode)) {
                            System.out.print("Сколько машин создать? ");
                            int count = Integer.parseInt(scanner.nextLine());
                            cars = new ArrayList<>();
                            for (int i = 0; i < count; i++) {
                                System.out.println("\nМашина #" + (i + 1));
                                cars.add(factory.createManual(scanner).get(0));
                            }
                        } else if ("2".equals(createMode)) {
                            System.out.print("Сколько случайных машин сгенерировать? ");
                            int count = Integer.parseInt(scanner.nextLine());
                            cars = factory.createRandom(count);
                        } else {
                            System.out.println("Неверный выбор.");
                            continue;
                        }

                        CarCsvParser.write(csvPath, cars);
                        System.out.println("\nБаза данных сохранена: " + csvPath);
                        cars.forEach(System.out::println);
                        fileLoaded = true;
                    }
                    case "2" -> {
                        System.out.println("\nДоступные файлы в data/:");
                        try {
                            Files.list(Path.of("src", "main", "java", "five", "data"))
                                  .filter(p -> p.toString().endsWith(".csv"))
                                  .map(p -> p.getFileName().toString())
                                  .sorted()
                                  .forEach(System.out::println);
                        } catch (IOException e) {
                            System.out.println("Не удалось прочитать папку data");
                        }
                        
                        String fileName;
                        while (true) {
                            System.out.print("\nВведите имя файла для открытия (без .csv): ");
                            fileName = scanner.nextLine().trim();
                            if (fileName.isEmpty()) fileName = "cars";
                            csvPath = Path.of("src", "main", "java", "five", "data", fileName + ".csv");

                            if (Files.exists(csvPath)) {
                                fileLoaded = true;
                                break;
                            } else {
                                System.out.println("Файл не обнаружен: " + csvPath.getFileName());
                                System.out.println("1 - Ввести другое название");
                                System.out.println("2 - Вернуться в меню");
                                System.out.print("Выберите действие: ");
                                String fileAction = scanner.nextLine().trim();
                                if ("2".equals(fileAction)) {
                                    break;
                                }

                            }
                        }
                        if (!fileLoaded) continue;
                    }
                    case "0" -> {
                        System.out.println("Выход.");
                        return;
                    }
                    default -> System.out.println("Неверный выбор.");
                }
            } else {
                List<Car> cars = factory.createFromCsv(csvPath);
                System.out.println("\n--- Содержимое файла: " + csvPath.getFileName() + " ---");
                if (cars.isEmpty()) {
                    System.out.println("Файл пуст.");
                } else {
                    cars.forEach(System.out::println);
                }

                System.out.println("\n--- Действия с базой данных ---");
                System.out.println("1 - Добавить машины вручную");
                System.out.println("2 - В главное меню");
                System.out.println("0 - Выход");
                System.out.print("Выберите действие: ");
                String action = scanner.nextLine().trim();

                switch (action) {
                    case "1" -> {
                        System.out.print("Сколько машин добавить? ");
                        int count = Integer.parseInt(scanner.nextLine());
                        List<Car> newCars = new ArrayList<>();
                        for (int i = 0; i < count; i++) {
                            System.out.println("\nМашина #" + (i + 1));
                            newCars.add(factory.createManual(scanner).get(0));
                        }
                        for (Car car : newCars) {
                            CarCsvParser.append(csvPath, car);
                        }
                        System.out.println("\nМашины добавлены.");
                    }
                    case "2" -> {
                        fileLoaded = false;
                    }
                    case "0" -> {
                        System.out.println("Выход.");
                        return;
                    }
                    default -> System.out.println("Неверный выбор.");
                }
            }
        }
    }
}