package model;

public class CartItem extends ProductItem {

    private int quantity;

    public CartItem(String name, String description, String price, int quantity) {
        super(name, description, price);
        this.quantity = quantity;
    }

    // Getter y setter para cantidad
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // toString personalizado
    @Override
    public String toString() {
        return quantity + "x " + name + " - " + price;
    }
}
