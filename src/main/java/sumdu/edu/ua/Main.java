package sumdu.edu.ua;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import sumdu.edu.ua.enums.Size;
import sumdu.edu.ua.enums.Category;

/**
 * Драйвер програми з консольним меню.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Wardrobe wardrobe = new Wardrobe("Denys");
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt(scanner, "Оберіть пункт: ");

            switch (choice) {
                case 1 -> createClothes(scanner, wardrobe);
                case 2 -> wardrobe.printAll();
                case 3 -> copyClothesByIndex(scanner, wardrobe);
                case 4 -> System.out.println("Створено об'єктів Clothes: " + Clothes.getCreatedCount());
                case 5 -> {
                    running = false;
                    System.out.println("Вихід");
                }
                default -> System.out.println("Невірний пункт меню. Число повинно бути 1-5");
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
        System.out.println("3. Створити копію об'єкта за номером");
        System.out.println("4. Показати кількість створених об'єктів");
        System.out.println("5. Вихід");
    }

    private static Size readSize(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String raw = scanner.nextLine().trim().toUpperCase();
            try {
                return Size.valueOf(raw);
            } catch (IllegalArgumentException e) {
                System.out.println("Помилка: допустимі XS,S,M,L,XL");
            }
        }
    }

    private static Category readCategory(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String raw = scanner.nextLine().trim().toUpperCase();
            try {
                return Category.valueOf(raw);
            } catch (IllegalArgumentException e) {
                System.out.println("Помилка: допустимі SHIRT,PANTS,JACKET,DRESS,SHOES");
            }
        }
    }

    /**
     * Створення об'єктів.
     */
    private static void createClothes(Scanner scanner, Wardrobe wardrobe) {
        try {
            String name = readNonEmptyString(scanner, "Назва: ");
            Category category = readCategory(scanner, "Категорія (SHIRT, PANTS, HOODIE, JACKET, SHOES, HAT, SHORTS): ");
            Size size = readSize(scanner, "Розмір (XS, S, M, L, XL): ");
            double price = readDouble(scanner, "Ціна: ");
            String brand = readNonEmptyString(scanner, "Бренд: ");
            int quantity = readInt(scanner, "Кількість: ");

            Clothes clothes = new Clothes(name, size, price, brand, quantity, category);
            wardrobe.addItem(clothes);

            System.out.println("Об'єкт створено");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка валідації: " + e.getMessage());
        }
    }

    /**
     * Копіювання об'єкта.
     */
    private static void copyClothesByIndex(Scanner scanner, Wardrobe wardrobe) {
        wardrobe.printAll();
        int number = readInt(scanner, "Введіть номер елемента для копіювання: ");

        if (number < 1 || number > wardrobe.getItems().size()) {
            System.out.println("Помилка: невірний номер");
            return;
        }

        Clothes original = wardrobe.getItems().get(number - 1);
        Clothes copy = new Clothes(original);
        wardrobe.addItem(copy);

        System.out.println("Копію створено і додано до вашого гардеробу");
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
