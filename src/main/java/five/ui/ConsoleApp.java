package five.ui;

import five.validation.InputValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleApp {

    private final Scanner scanner = new Scanner(System.in);
    private int arraySize = 0; //запоминаем длину массива
    // TODO временно: хранение автомобилей как строк. После появления класса Car заменить на List<Car>.
    private List<String> cars = new ArrayList<>();


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
                case "2" -> System.out.println("Тут будет сортировка");
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
            case "3" -> System.out.println("Ручное заполнение (будет реализовано позже)");
            default -> System.out.println("Неверный выбор. Возврат в главное меню.");
        }
    }

    /**
     * TODO временно: заполнение массива случайными автомобилями как строками.
     * После появления класса Car нужно заменить на создание объектов Car через Builder.
     */
    private void  fillRandom(){
        cars.clear(); // Пока не знаю как будет храниться наш список. Поэтому я его пока что очищаю каждый раз.

        String[] brands = {"BMW", "Audi", "Toyota", "Ford", "Mazda", "Mercedes"};
        int[] powers = {90, 110, 150, 200, 250, 300};
        int[] years = {2005, 2010, 2015, 2018, 2020, 2023};

        for (int i = 0; i < arraySize; i++) {
            String car = brands[(int) (Math.random() * brands.length)] + " | " +
                         powers[(int) (Math.random() * powers.length)] + " л.с | " +
                         years[(int) (Math.random() * years.length)] + " г.";
            cars.add(car);
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


}