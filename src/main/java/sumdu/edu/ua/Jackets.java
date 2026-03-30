package sumdu.edu.ua;

import sumdu.edu.ua.enums.Size;

/**
 * Клас курток який походить від Clothes
 */
public class Jackets extends Clothes {

    private boolean hasHood;

    public Jackets(String name, Size size, double price, String brand, int quantity, boolean hasHood) {
        super(name, size, price, brand, quantity);
        this.hasHood = hasHood;
    }

    public boolean isHasHood() {
        return hasHood;
    }

    public void setHasHood(boolean hasHood) {
        this.hasHood = hasHood;
    }

    @Override
    public String toString() {
        return "Jackets {" + super.toString() + ", hasHood=" + hasHood + '}';
    }
}