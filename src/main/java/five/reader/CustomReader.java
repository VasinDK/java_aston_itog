package five.reader;

import five.car.CarBuilderImpl;
import five.exception.CarIllegalArgumentException;
import five.car.Car;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CustomReader {

    /**
     * Класс, который реализует чтение/запись даннных как с/из консоли, так и с/из файла.
     *
     * Использование:
     * В main создать экземпляр класса CustomReader и работать в последующем с его экземпляром
     *
     * Пример:
     * CustomReader reader = new CustomReader();
     * try {
     *             // 1. Чтение автомобилей из файла (используется путь по умолчанию)
     *             System.out.println("Чтение данных из файла...");
     *             List<Car> cars = reader.readFromFile("");
     *
     *             reader.writeToConsoleLn("Загружено автомобилей: " + cars.size());
     *             cars.forEach(System.out::println);
     *
     *             // 2. Многопоточные подсчёты
     *
     *             // По мощности
     *             reader.countCarsAsync(cars, car -> car.getPower() == 150);
     *
     *             // По году выпуска
     *             reader.countCarsAsync(cars, car -> car.getYear() == 2006);
     *
     *             // По марке
     *             reader.countCarsAsync(cars, car -> "Mazda".equals(car.getModel()));
     *
     *             // Можно добавить паузу, чтобы увидеть результаты из фоновых потоков
     *             // (иначе main может завершиться раньше вывода)
     *             try {
     *                 Thread.sleep(500);
     *             } catch (InterruptedException e) {
     *                 Thread.currentThread().interrupt();
     *             }
     *
     *             // 3. (Опционально) запись отфильтрованных машин в файл
     *             List<Car> mazdaCars = cars.stream()
     *                     .filter(car -> "Mazda".equals(car.getModel()))
     *                     .toList();
     *
     *             reader.writeCarsToFile("C:\\LearnJava\\mazda_cars.txt", mazdaCars);
     *             reader.writeToConsoleLn("Автомобили Mazda записаны в файл.");
     *
     *         } catch (CarIllegalArgumentException e) {
     *             reader.writeToConsoleLn("Ошибка: " + e.getMessage());
     *             e.printStackTrace();
     *         } finally {
     *             reader.close(); // закрываем Scanner
     * */
    private static final String DEFAULT_TEST_PATH = "C:\\LearnJava\\cars.txt";

    private final Scanner consoleScanner = new Scanner(System.in);

    /**
     * Метод для чтения автомобилей из файла.
     */
    public List<Car> readFromFile(String filePath) throws CarIllegalArgumentException {
        String actualPath = filePath.isEmpty() ? DEFAULT_TEST_PATH : filePath;
        try {
            if (!Files.exists(Paths.get(actualPath))) {
                throw new CarIllegalArgumentException("Файл не найден: " + actualPath);
            }

            return Files.readAllLines(Paths.get(actualPath), StandardCharsets.UTF_8)
                    .stream()
                    .filter(line -> !line.trim().isEmpty())
                    .map(this::parseCarLine)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new CarIllegalArgumentException("Ошибка при чтении файла: " + actualPath, e);
        }
    }

    /**
     * Вспомогательный метод парсинга строки в Car.
     */
    private Car parseCarLine(String line) {
        try {
            // Убираем слово "Автомобиль:" если оно есть
            String cleaned = line.replace("Автомобиль:", "").trim();

            // Разбиваем по запятым
            String[] parts = cleaned.split(",");

            if (parts.length != 3) return null;

            // Извлекаем бренд
            String brandPart = parts[0].trim(); // "бренд = Ford"
            String powerPart = parts[1].trim(); // "мощность = 150 лс"
            String yearPart  = parts[2].trim(); // "год = 2018"

            String brand = brandPart.replace("бренд =", "").trim();
            String powerString = powerPart
                    .replace("мощность =", "")
                    .replace("лс", "")
                    .trim();
            String yearString = yearPart.replace("год =", "").trim();

            int power = Integer.parseInt(powerString);
            int year = Integer.parseInt(yearString);

            return new CarBuilderImpl()
                    .brand(brand)
                    .power(power)
                    .year(year)
                    .build();

        } catch (Exception e) {
            writeToConsoleLn("Ошибка парсинга строки: \"" + line + "\" — пропущено");
            return null;
        }
    }


    /**
     * Запись списка машин в файл.
     */
    public void writeCarsToFile(String outputPath, List<Car> cars) throws CarIllegalArgumentException {
        if (outputPath == null || outputPath.isBlank()) {
            outputPath = DEFAULT_TEST_PATH;
        }
        try {
            List<String> lines = cars.stream()
                    .map(Car::toString)
                    .collect(Collectors.toList());
            Files.write(Paths.get(outputPath), lines,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new CarIllegalArgumentException("Не удалось записать данные в файл: " + outputPath, e);
        }
    }

    /**
     * Многопоточно подсчитывает количество автомобилей, удовлетворяющих условию.
     * Результат выводится в консоль.
     *
     * @param cars коллекция автомобилей (не должна изменяться во время выполнения)
     * @param condition условие для фильтрации (например: car -> car.getPower() == 150)
     */
    public void countCarsAsync(List<Car> cars, Predicate<Car> condition) {
        List<Car> safeCopy = List.copyOf(cars);

        CompletableFuture
                .supplyAsync(() -> safeCopy.stream().filter(condition).count())
                .thenAccept(count -> writeToConsoleLn("Найдено совпадений: " + count))
                .exceptionally(ex -> {
                    writeToConsoleLn("Ошибка при подсчёте: " + ex.getMessage());
                    return null;
                });
    }


    /** ===== Консольные методы ===== */

    public String readFromConsole(String prompt, Predicate<String> validator) {
        while (true) {
            System.out.print(prompt);
            String input = consoleScanner.nextLine();
            if (validator == null || validator.test(input)) {
                return input;
            }
            System.out.println("Некорректный ввод. Попробуйте снова.");
        }
    }

    public String readFromConsole(String prompt) {
        return readFromConsole(prompt, null);
    }

    public void writeToConsole(String data) {
        System.out.print(data);
    }
    public void writeToConsoleLn(String data) {
        System.out.println(data);
    }

    public void close() {
        consoleScanner.close();
    }

}