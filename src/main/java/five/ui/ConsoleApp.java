package five.ui;

import java.util.Scanner;

public class ConsoleApp {

    private final Scanner scanner = new Scanner(System.in);

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
                    System.out.println("Тут будет заполнение массива");
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
}
