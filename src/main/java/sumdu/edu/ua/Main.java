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
        List<Clothes> clothesList = ClothesJsonStorage.loadFromJson("src/main/java/sumdu/edu/ua/input.json");
        boolean running = true;
        System.out.println("Завантажено " + clothesList.size() + " об'єктів з JSON файлу");

        while (running) {
            printMenu();
            int choice = readInt(scanner, "Оберіть пункт: ");

            switch (choice) {
                case 1 -> searchMenu(scanner, clothesList);
                case 2 -> createObjectMenu(scanner, clothesList);
                case 3 -> printAll(clothesList);
                case 4 -> {
                    ClothesJsonStorage.saveToJson(clothesList, "src/main/java/sumdu/edu/ua/input.json");
                    running = false;
                    System.out.println("Вихід");
                }
                default -> System.out.println("Невірний пункт меню. Число повинно бути 1-4");
            }
        }
        scanner.close();
    }

    private static void searchMenu(Scanner scanner, List<Clothes> list) {
        boolean searching = true;

        while (searching) {
            System.out.println("\nПошук об'єкта:");
            System.out.println("1. Пошук за брендом");
            System.out.println("2. Пошук за розміром");
            System.out.println("3. Пошук за діапазоном ціни");
            System.out.println("4. Пошук за типом об'єкта");
            System.out.println("5. Повернутися в головне меню");

            int choice = readInt(scanner, "Оберіть пункт: ");

            switch (choice) {
                case 1 -> printSearchResults(searchByBrand(scanner, list));
                case 2 -> printSearchResults(searchBySize(scanner, list));
                case 3 -> printSearchResults(searchByPriceRange(scanner, list));
                case 4 -> printSearchResults(searchByType(scanner, list));
                case 5 -> searching = false;
                default -> System.out.println("Невірний пункт меню. Число повинно бути 1-5");
            }
        }
    }

    private static List<Clothes> searchByBrand(Scanner scanner, List<Clothes> list) {
        String brand = readNonEmptyString(scanner, "Введіть бренд для пошуку: ");
        List<Clothes> result = new ArrayList<Clothes>();

        int i;
        for (i = 0; i < list.size(); i++) {
            Clothes item = list.get(i);
            if (item.getBrand().equalsIgnoreCase(brand)) {
                result.add(item);
            }
        }

        return result;
    }

    private static List<Clothes> searchBySize(Scanner scanner, List<Clothes> list) {
        Size size = readSize(scanner, "Введіть розмір (XS, S, M, L, XL): ");
        List<Clothes> result = new ArrayList<Clothes>();

        int i;
        for (i = 0; i < list.size(); i++) {
            Clothes item = list.get(i);
            if (item.getSize() == size) {
                result.add(item);
            }
        }

        return result;
    }

    private static List<Clothes> searchByPriceRange(Scanner scanner, List<Clothes> list) {
        double minPrice = 0;
        double maxPrice = 1;
        List<Clothes> result = new ArrayList<Clothes>();

        minPrice = readDouble(scanner, "Введіть мінімальну ціну: ");
        maxPrice = readDouble(scanner, "Введіть максимальну ціну: ");

        while (minPrice > maxPrice) {
            System.out.println("Мінімальна ціна не може бути більшою за максимум");
            minPrice = readDouble(scanner, "Введіть мінімальну ціну: ");
            maxPrice = readDouble(scanner, "Введіть максимальну ціну: ");
        }

        int i;
        for (i = 0; i < list.size(); i++) {
            Clothes item = list.get(i);
            if (item.getPrice() >= minPrice && item.getPrice() <= maxPrice) {
                result.add(item);
            }
        }

        return result;
    }

    private static List<Clothes> searchByType(Scanner scanner, List<Clothes> list) {
        System.out.println("Оберіть тип:");
        System.out.println("1. Clothes");
        System.out.println("2. Pants");
        System.out.println("3. Shirts");
        System.out.println("4. Jackets");
        System.out.println("5. Shoes");

        int choice = 1;
        choice = readInt(scanner, "Оберіть тип: ");
        List<Clothes> result = new ArrayList<Clothes>();

        while (choice > 5 || choice < 1) {
            System.out.println("Невірний пункт меню. Число повинно бути 1-5");
            choice = readInt(scanner, "Оберіть тип: ");
        }

        int i;
        for (i = 0; i < list.size(); i++) {
            Clothes item = list.get(i);

            if (choice == 1 && item.getClass() == Clothes.class) {
                result.add(item);
            } else if (choice == 2 && item instanceof Pants) {
                result.add(item);
            } else if (choice == 3 && item instanceof Shirts) {
                result.add(item);
            } else if (choice == 4 && item instanceof Jackets) {
                result.add(item);
            } else if (choice == 5 && item instanceof Shoes) {
                result.add(item);
            }
        }

        return result;
    }

    private static void printSearchResults(List<Clothes> result) {
        if (result.isEmpty()) {
            System.out.println("Нічого не знайдено");
            return;
        }

        System.out.println("Знайдені об'єкти:");

        int i;
        for (i = 0; i < result.size(); i++) {
            System.out.println((i + 1) + ". " + result.get(i));
        }
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
        System.out.println("\n1. Пошук об'єкта");
        System.out.println("2. Створити новий об'єкт");
        System.out.println("3. Показати всі об'єкти");
        System.out.println("4. Вихід");
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