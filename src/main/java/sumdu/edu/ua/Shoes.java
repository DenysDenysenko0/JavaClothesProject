package sumdu.edu.ua;

import sumdu.edu.ua.enums.Size;

/**
 * Клас взуття який походить від Clothes
 */
public class Shoes extends Clothes {

    private String soleMaterial;

    public Shoes(String name, Size size, double price, String brand, int quantity, String soleMaterial) {
        super(name, size, price, brand, quantity);
        setSoleMaterial(soleMaterial);
    }

    public String getSoleMaterial() {
        return soleMaterial;
    }

    public void setSoleMaterial(String soleMaterial) {
        if (soleMaterial == null || soleMaterial.trim().isEmpty()) {
            throw new IllegalArgumentException("Sole material cannot bee empty");
        }
        this.soleMaterial = soleMaterial.trim();
    }

    @Override
    public String toString() {
        return "Shoes {" + super.toString() + ", soleMaterial='" + soleMaterial + '\'' + '}';
    }
}