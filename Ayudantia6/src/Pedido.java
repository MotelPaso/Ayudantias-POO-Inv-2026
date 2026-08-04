import java.util.Arrays;

public class Pedido {
    private double price;
    private String[] foodItems;

    public Pedido(double price, String[] foodItems) {
        this.price = price;
        this.foodItems = foodItems;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String[] getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(String[] foodItems) {
        this.foodItems = foodItems;
    }

    @Override
    public String toString() {
        return "===\nPedido\nPrecio: $" + String.format("%.2f", price) +
                "\nPlatos: " + Arrays.toString(foodItems) +
                "\n===";
    }
}
