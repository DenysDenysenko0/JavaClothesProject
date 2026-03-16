package sumdu.edu.ua;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Клас гардеробу користувача.
 */
public class Wardrobe {

    private String ownerName;
    private List<Clothes> items;

    /**
     * Створює гардероб для вказаного власника.
     *
     * @param ownerName ім'я власника
     * @throws IllegalArgumentException якщо ім'я порожнє
     */
    public Wardrobe(String ownerName) {
        setOwnerName(ownerName);
        this.items = new ArrayList<Clothes>();
    }

    /**
     * Повертає ім'я власника.
     *
     * @return ім'я власника
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * Встановлює ім'я власника.
     *
     * @param ownerName ім'я власника
     * @throws IllegalArgumentException якщо ім'я порожнє
     */
    public void setOwnerName(String ownerName) {
        if (ownerName == null || ownerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Owner name cannot be empty");
        }
        this.ownerName = ownerName.trim();
    }

    /**
     * Додає елемент одягу до гардеробу.
     *
     * @param clothes об'єкт одягу
     * @throws IllegalArgumentException якщо clothes == null
     */
    public void addItem(Clothes clothes) {
        if (clothes == null) {
            throw new IllegalArgumentException("Clothes cannot be null");
        }
        items.add(clothes);
    }

    /**
     * Повертає список елементів одягу.
     *
     * @return список одягу
     */
    public List<Clothes> getItems() {
        return Collections.unmodifiableList(items);
    }

    /**
     * Виведення об'єктів.
     */
    public void printAll() {
        if (items.isEmpty()) {
            System.out.println("Список одягу порожній");
            return;
        }

        int i;
        for (i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i));
        }
    }
}
