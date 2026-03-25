package sumdu.edu.ua;

import sumdu.edu.ua.enums.Size;

/**
 * Клас штанів який походить від Clothes
 */
public class Pants extends Clothes {

    private int waistSize;

    public Pants(String name, Size size, double price, String brand, int quantity, int waistSize) {
        super(name, size, price, brand, quantity);
        setWaistSize(waistSize);
    }

    public int getWaistSize() {
        return waistSize;
    }

    public void setWaistSize(int waistSize) {
        if (waistSize <= 0) {
            throw new IllegalArgumentException("Waist size must be bigger than 0");
        }
        this.waistSize = waistSize;
    }

    @Override
    public String toString() {
        return "Pants {" + super.toString() + ", waistSize=" + waistSize + '}';
    }
}
