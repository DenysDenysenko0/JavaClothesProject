package sumdu.edu.ua;

import sumdu.edu.ua.enums.Size;

/**
 * Клас сорочок який походить від Clothes
 */
public class Shirts extends Clothes {

    private int sleeveLength;

    public Shirts(String name, Size size, double price, String brand, int quantity, int sleeveLength) {
        super(name, size, price, brand, quantity);
        setSleeveLength(sleeveLength);
    }

    public int getSleeveLength() {
        return sleeveLength;
    }

    public void setSleeveLength(int sleeveLength) {
        if (sleeveLength <= 0) {
            throw new IllegalArgumentException("Sleeve length must be bigger than 0");
        }
        this.sleeveLength = sleeveLength;
    }

    @Override
    public String toString() {
        return "Shirt {" + super.toString() + ", sleeveLength=" + sleeveLength + '}';
    }
}
