package sumdu.edu.ua;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Wardrobe {

    private String ownerName;
    private List<Clothes> items;

    public Wardrobe(String ownerName) {
        setOwnerName(ownerName);
        this.items = new ArrayList<Clothes>();
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        if (ownerName == null || ownerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Owner name cannot be empty");
        }
        this.ownerName = ownerName.trim();
    }

    public void addItem(Clothes clothes) {
        if (clothes == null) {
            throw new IllegalArgumentException("Clothes cannot be null");
        }
        items.add(clothes);
    }

    public List<Clothes> getItems() {
        return Collections.unmodifiableList(items);
    }

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
