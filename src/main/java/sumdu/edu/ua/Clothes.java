package sumdu.edu.ua;

import java.util.Objects;

/**
 * Модель елемента одягу.
 */
public class Clothes {

    private String name;
    private String size;
    private double price;
    private String brand;
    private int quantity;

    /**
     * Створює об'єкт одягу з валідацією.
     *
     * @param name назва
     * @param size розмір
     * @param price ціна
     * @param brand бренд
     * @param quantity кількість
     * @throws IllegalArgumentException якщо дані некоректні
     */
    public Clothes(String name, String size, double price, String brand, int quantity) {
        setName(name);
        setSize(size);
        setPrice(price);
        setBrand(brand);
        setQuantity(quantity);
    }

    public String getName() {
        return name;
    }

    /**
     * Встановлює назву.
     *
     * @param name назва
     * @throws IllegalArgumentException якщо назва порожня
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name.trim();
    }

    public String getSize() {
        return size;
    }

    /**
     * Встановлює розмір.
     *
     * @param size розмір
     * @throws IllegalArgumentException якщо розмір порожній
     */
    public void setSize(String size) {
        if (size == null || size.trim().isEmpty()) {
            throw new IllegalArgumentException("Size cannot be empty");
        }
        this.size = size.trim();
    }

    public double getPrice() {
        return price;
    }

    /**
     * Встановлює ціну.
     *
     * @param price ціна
     * @throws IllegalArgumentException якщо ціна <= 0
     */
    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be more than 0");
        }
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    /**
     * Встановлює бренд.
     *
     * @param brand бренд
     * @throws IllegalArgumentException якщо бренд порожній
     */
    public void setBrand(String brand) {
        if (brand == null || brand.trim().isEmpty()) {
            throw new IllegalArgumentException("Brand cannot be empty");
        }
        this.brand = brand.trim();
    }

    public int getQuantity() {
        return quantity;
    }

    /**
     * Встановлює кількість.
     *
     * @param quantity кількість
     * @throws IllegalArgumentException якщо кількість < 0
     */
    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be minus number");
        }
        this.quantity = quantity;
    }


    @Override
    public String toString() {
        return "Clothes{" + "name='" + name + '\'' + ", size='" + size + '\'' + ", price=" + price + ", brand='" + brand + '\'' + ", quantity=" + quantity + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Clothes clothes)) return false;
        return Double.compare(clothes.price, price) == 0
                && quantity == clothes.quantity
                && Objects.equals(name, clothes.name)
                && Objects.equals(size, clothes.size)
                && Objects.equals(brand, clothes.brand);
    }
}
