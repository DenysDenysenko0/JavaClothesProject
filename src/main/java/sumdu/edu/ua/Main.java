package sumdu.edu.ua;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть кількість елементів: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        Clothes[] clothesArray = new Clothes[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Введення даних для одягу №" + (i + 1));

            System.out.print("Назва: ");
            String name = scanner.nextLine();

            System.out.print("Розмір: ");
            String size = scanner.nextLine();

            System.out.print("Ціна: ");
            double price = scanner.nextDouble();

            scanner.nextLine();
            clothesArray[i] = new Clothes(name, size, price);
        }

        System.out.println("\nСписок одягу: ");
        for (Clothes clothes : clothesArray) {
            System.out.println(clothes);
        }

        scanner.close();
    }
}
