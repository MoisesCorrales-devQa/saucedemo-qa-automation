package model;

public class ProductItem {
    protected  String name;
    protected  String description;
    protected  String price;

    public ProductItem(String name, String description, String price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    // Getters
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getPrice() { return price; }
}
