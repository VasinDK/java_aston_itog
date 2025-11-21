package five.ui;

import five.car.Car;
import five.car.CarBuilderImpl;
import five.exception.CarIllegalArgumentException;
import five.sorter.SortExecutor;
import five.sorter.strategies.ByBrand;
import five.sorter.strategies.ByPower;
import five.sorter.strategies.ByYear;
import five.sorter.strategies.ReverseStrategy;
import five.validation.InputValidator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ConsoleApp {

    private final Scanner scanner = new Scanner(System.in);
    private int arraySize = 0; //запоминаем длину массива

    private List<Car> cars = new ArrayList<>();


    private void printMenu() {
        System.out.println("\n=== МЕНЮ ===");
        System.out.println("1. Заполнить массив автомобилей");
        System.out.println("2. Отсортировать массив");
        System.out.println("3. Показать текущий массив");
        System.out.println("0. Выход");
        System.out.print("Ваш выбор: ");
    }

    public void run(){
        boolean running = true;

        while (running){
            printMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> fillArray();
                case "2" -> sortArray();
                case "3" -> showArray();
                case "0" -> {
                    System.out.println("Выход из программы");
                    running = false;
                }
                default -> System.out.println("Неизвестная команда, попробуйте ещё раз.");
            }
        }
    }

    /**
     * Запрашивает у пользователя длину массива и проверяет корректность ввода.
     * При корректном значении сохраняет его, при ошибке выводит сообщение.
     */
    private void fillArray(){
        System.out.print("Введите длину массива: ");
        String input = scanner.nextLine();

        try {
            arraySize = InputValidator.parsePositiveInt(input, "Длина массива");
            System.out.println("Длина массива установлена: " + arraySize);

            chooseFillingMethod();
        }catch (IllegalArgumentException e){
            System.out.println("Ошибка: " +e.getMessage());
        }
    }

    /**
     * Показывает меню выбора способа заполнения массива и обрабатывает выбор пользователя.
     */
    private void chooseFillingMethod() {
        System.out.println("\nВыберите способ заполнения массива:");
        System.out.println("1. Из файла");
        System.out.println("2. Случайными данными");
        System.out.println("3. Вручную");
        System.out.print("Ваш выбор: ");

        String input = scanner.nextLine();

        switch (input) {
            case "1" -> System.out.println("Заполнение из файла (будет реализовано позже)");
            case "2" -> fillRandom();
            case "3" -> fillManual();
            default -> System.out.println("Неверный выбор. Возврат в главное меню.");
        }
    }

    /**
     * После появления класса Car нужно заменить на создание объектов Car через Builder.
     */
    private void fillRandom() {
        cars.clear();

        String[] brands = {"BMW", "Audi", "Toyota", "Ford", "Mazda", "Mercedes"};
        int[] powers = {90, 110, 150, 200, 250, 300};
        int[] years = {2005, 2010, 2015, 2018, 2020, 2023};

        for (int i = 0; i < arraySize; i++) {
            try {
                Car car = new CarBuilderImpl()
                        .brand(brands[(int) (Math.random() * brands.length)])
                        .power(powers[(int) (Math.random() * powers.length)])
                        .year(years[(int) (Math.random() * years.length)])
                        .build();

                cars.add(car);
            } catch (CarIllegalArgumentException e) {
                System.out.println("Ошибка при создании автомобиля: " + e.getMessage());
                i--;
            }
        }

        System.out.println("Массив случайных автомобилей создан.");
    }


    /**
     * Выводит текущий массив автомобилей.
     */
    private void showArray(){
        if(cars.isEmpty()){
            System.out.println("Массив пуст. Сначала выполните заполнение.");
            return;
        }
        System.out.println("\n=== Текущий массив автомобилей ===");
        for (int i = 0; i < cars.size(); i++) {
            System.out.println((i + 1) + ") " + cars.get(i));
        }
    }

    /**
     * Ручной ввод автомобилей с валидацией.
     * TODO временно сохраняются строки. После появления класса Car заменить на создание объектов Car.
     */
    private void fillManual() {
        cars.clear();  // Пока не знаю как будет храниться наш список. Поэтому я его пока что очищаю каждый раз.

        for (int i = 0; i < arraySize; i++) {
            System.out.println("\nВведите данные для автомобиля №" + (i + 1));
            System.out.print("Модель: ");
            String model = scanner.nextLine().trim();

            System.out.print("Мощность (л.с): ");
            String powerInput = scanner.nextLine().trim();

            System.out.print("Год выпуска: ");
            String yearInput = scanner.nextLine().trim();

            try {
                int power = InputValidator.parsePositiveInt(powerInput, "Мощность");
                int year = InputValidator.parsePositiveInt(yearInput, "Год выпуска");

                Car car = new CarBuilderImpl()
                        .brand(model)
                        .power(power)
                        .year(year)
                        .build();

                cars.add(car);
            } catch (CarIllegalArgumentException | IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
                i--;
            }
        }
        System.out.println("\nМассив автомобилей заполнен вручную.");
    }

    /**
     * Показывает меню выбора стратегии сортировки и выполняет сортировку массива.
     * TODO временно сортируются строки. После появления класса Car — сортировать объекты Car.
     */
    private void sortArray() {
        if (cars.isEmpty()) {
            System.out.println("Массив пуст. Сначала выполните заполнение.");
            return;
        }

        System.out.println("\nВыберите способ сортировки:");
        System.out.println("1. По бренду (модели)");
        System.out.println("2. По мощности");
        System.out.println("3. По году выпуска");
        System.out.println("4. В обратном порядке");
        System.out.print("Ваш выбор: ");

        String input = scanner.nextLine();

        SortExecutor executor = new SortExecutor(cars);

        switch (input) {
            case "1" -> executor.setStrategy(new ByBrand());
            case "2" -> executor.setStrategy(new ByPower());
            case "3" -> executor.setStrategy(new ByYear());
            case "4" -> executor.setStrategy(new ReverseStrategy(new ByBrand()));
            default -> {
                System.out.println("Некорректный выбор.");
                return;
            }
        }

        cars = executor.exec();
        System.out.println("Сортировка выполнена.");
    }


}