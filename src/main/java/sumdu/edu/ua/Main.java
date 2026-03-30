package sumdu.edu.ua;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import sumdu.edu.ua.enums.Size;

/**
 * Драйвер програми з консольним меню.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Clothes> clothesList = new ArrayList<Clothes>();
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt(scanner, "Оберіть пункт: ");

            switch (choice) {
                case 1 -> createObjectMenu(scanner, clothesList);
                case 2 -> printAll(clothesList);
                case 3 -> {
                    running = false;
                    System.out.println("Вихід");
                }
                default -> System.out.println("Невірний пункт меню. Число повинно бути 1-3");
            }
        }
        scanner.close();
    }

    private static void createObjectMenu(Scanner scanner, List<Clothes> list) {

        System.out.println("\nОберіть тип об'єкта:");
        System.out.println("1. Clothes");
        System.out.println("2. Pants");
        System.out.println("3. Shirts");
        System.out.println("4. Jackets");
        System.out.println("5. Shoes");

        int choice = readInt(scanner, "Оберіть пункт: ");

        switch (choice) {
            case 1 -> createBaseClothes(scanner, list);
            case 2 -> createPants(scanner, list);
            case 3 -> createShirts(scanner, list);
            case 4 -> createJackets(scanner, list);
            case 5 -> createShoes(scanner, list);
            default -> System.out.println("Невірний вибір типу.");
        }
    }

    private static void printMenu() {
        System.out.println("\n1. Створити новий об'єкт");
        System.out.println("2. Показати всі об'єкти");
        System.out.println("3. Вихід");
    }

    /**
     * Створення звичайного одягу.
     */
    private static void createBaseClothes(Scanner scanner, List<Clothes> list) {
        try {
            String name = readNonEmptyString(scanner, "Назва: ");
            Size size = readSize(scanner, "Розмір (XS, S, M, L, XL): ");
            double price = readDouble(scanner, "Ціна: ");
            String brand = readNonEmptyString(scanner, "Бренд: ");
            int quantity = readInt(scanner, "Кількість: ");

            list.add(new Clothes(name, size, price, brand, quantity));
            System.out.println("Clothes створено");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка валідації: " + e.getMessage());
        }
    }

    /**
     * Створення штанів.
     */
    private static void createPants(Scanner scanner, List<Clothes> list) {
        try {
            String name = readNonEmptyString(scanner, "Назва: ");
            Size size = readSize(scanner, "Розмір (XS, S, M, L, XL): ");
            double price = readDouble(scanner, "Ціна: ");
            String brand = readNonEmptyString(scanner, "Бренд: ");
            int quantity = readInt(scanner, "Кількість: ");
            int waistSize = readInt(scanner, "Обхват талії: ");

            list.add(new Pants(name, size, price, brand, quantity, waistSize));
            System.out.println("Pants створено");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка валідації: " + e.getMessage());
        }
    }

    /**
     * Створення курток.
     */
    private static void createJackets(Scanner scanner, List<Clothes> list) {
        try {
            String name = readNonEmptyString(scanner, "Назва: ");
            Size size = readSize(scanner, "Розмір (XS, S, M, L, XL): ");
            double price = readDouble(scanner, "Ціна: ");
            String brand = readNonEmptyString(scanner, "Бренд: ");
            int quantity = readInt(scanner, "Кількість: ");
            boolean hasHood = readBoolean(scanner, "Чи є капюшон? (true/false): ");

            list.add(new Jackets(name, size, price, brand, quantity, hasHood));
            System.out.println("Jackets створено");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка валідації: " + e.getMessage());
        }
    }

    /**
     * Створення черевиків.
     */
    private static void createShoes(Scanner scanner, List<Clothes> list) {
        try {
            String name = readNonEmptyString(scanner, "Назва: ");
            Size size = readSize(scanner, "Розмір (XS, S, M, L, XL): ");
            double price = readDouble(scanner, "Ціна: ");
            String brand = readNonEmptyString(scanner, "Бренд: ");
            int quantity = readInt(scanner, "Кількість: ");
            String soleMaterial = readNonEmptyString(scanner, "Матеріал підошви: ");

            list.add(new Shoes(name, size, price, brand, quantity, soleMaterial));
            System.out.println("Shoes створено");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка валідації: " + e.getMessage());
        }
    }

    /**
     * Створення сорочок.
     */
    private static void createShirts(Scanner scanner, List<Clothes> list) {
        try {
            String name = readNonEmptyString(scanner, "Назва: ");
            Size size = readSize(scanner, "Розмір (XS, S, M, L, XL): ");
            double price = readDouble(scanner, "Ціна: ");
            String brand = readNonEmptyString(scanner, "Бренд: ");
            int quantity = readInt(scanner, "Кількість: ");
            int sleeveLength = readInt(scanner, "Довжина рукава: ");

            list.add(new Shirts(name, size, price, brand, quantity, sleeveLength));
            System.out.println("Shirts створено");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка валідації: " + e.getMessage());
        }
    }

    /**
     * Виведення об'єктів.
     */
    private static void printAll(List<Clothes> list) {
        if (list.isEmpty()) {
            System.out.println("Список порожній.");
            return;
        }

        int i;
        for (i = 0; i < list.size(); i++) {
            Clothes item = list.get(i);
            System.out.println((i + 1) + ". " + item);
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

    /**
     * Зчитує розміру XS, S, M, L або XL.
     */
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

    /**
     * Зчитує boolean true або false.
     */
    private static boolean readBoolean(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim().toLowerCase();

            if (value.equals("true")) {
                return true;
            }
            if (value.equals("false")) {
                return false;
            }

            System.out.println("Помилка, введіть true або false");
        }
    }
}