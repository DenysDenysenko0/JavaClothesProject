package sumdu.edu.ua;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

/**
 * Драйвер програми з консольним меню.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Clothes> clothesList = new ArrayList<>();
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt(scanner, "Оберіть пункт: ");

            switch (choice) {
                case 1 -> createClothes(scanner, clothesList);
                case 2 -> printAll(clothesList);
                case 3 -> {
                    running = false;
                    System.out.println("Вихід");
                }
                default -> System.out.println("Невірно обраний пункт меню. Число повинно бути 1, 2 або 3");
            }
        }

        scanner.close();
    }

    /**
     * Меню.
     */
    private static void printMenu() {
        System.out.println("\n1. Створити новий об'єкт");
        System.out.println("2. Показати всі об'єкти");
        System.out.println("3. Вихід");
    }

    /**
     * Створення об'єктів.
     */
    private static void createClothes(Scanner scanner, List<Clothes> clothesList) {
        try {
            String name = readNonEmptyString(scanner, "Назва: ");
            String size = readNonEmptyString(scanner, "Розмір: ");
            double price = readDouble(scanner, "Ціна: ");
            String brand = readNonEmptyString(scanner, "Бренд: ");
            int quantity = readInt(scanner, "Кількість: ");

            Clothes clothes = new Clothes(name, size, price, brand, quantity);
            clothesList.add(clothes);
            System.out.println("Об'єкт створено");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка валідації: " + e.getMessage());
        }
    }

    /**
     * Виведення об'єктів.
     */
    private static void printAll(List<Clothes> clothesList) {
        if (clothesList.isEmpty()) {
            System.out.println("Список порожній.");
            return;
        }

        System.out.println("\nСписок об'єктів:");
        for (int i = 0; i < clothesList.size(); i++) {
            System.out.println((i + 1) + ". " + clothesList.get(i));
        }
    }

    /**
     * Безпечне зчитування int з обробкою нечислового вводу.
     */
    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = scanner.nextInt();
                scanner.nextLine();
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Помилка, введіть ціле число");
                scanner.nextLine();
            }
        }
    }

    /**
     * Безпечне зчитування double з обробкою нечислового вводу.
     */
    private static double readDouble(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = scanner.nextDouble();
                scanner.nextLine();
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Помилка, введіть число");
                scanner.nextLine();
            }
        }
    }

    /**
     * Зчитує непорожній рядок.
     */
    private static String readNonEmptyString(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("Помилка, рядок не може бути порожнім");
        }
    }
}
