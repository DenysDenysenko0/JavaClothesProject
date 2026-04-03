package sumdu.edu.ua;

/**
 * Допоміжний клас для зчитування і запису об'єктів у JSON.
 */
public class ClothesJsonData {

    private String type;
    private String name;
    private String size;
    private double price;
    private String brand;
    private int quantity;
    private Integer waistSize;
    private Integer sleeveLength;
    private Boolean hasHood;
    private String soleMaterial;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Integer getWaistSize() {
        return waistSize;
    }

    public void setWaistSize(Integer waistSize) {
        this.waistSize = waistSize;
    }

    public Integer getSleeveLength() {
        return sleeveLength;
    }

    public void setSleeveLength(Integer sleeveLength) {
        this.sleeveLength = sleeveLength;
    }

    public Boolean getHasHood() {
        return hasHood;
    }

    public void setHasHood(Boolean hasHood) {
        this.hasHood = hasHood;
    }

    public String getSoleMaterial() {
        return soleMaterial;
    }

    public void setSoleMaterial(String soleMaterial) {
        this.soleMaterial = soleMaterial;
    }
}