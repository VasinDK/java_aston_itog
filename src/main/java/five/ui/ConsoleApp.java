package five.ui;

import five.validation.InputValidator;

import java.util.Scanner;

public class ConsoleApp {

    private final Scanner scanner = new Scanner(System.in);
    private int arraySize = 0; //запоминаем длину массива

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
                case "1" -> {
                    fillArray();
                }
                case "2" -> {
                    System.out.println("Тут будет сортировка");
                }
                case "3" -> {
                    System.out.println("Тут будет вывод текущего массива");
                }
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
        }catch (IllegalArgumentException e){
            System.out.println("Ошибка: " +e.getMessage());
        }
    }
}
