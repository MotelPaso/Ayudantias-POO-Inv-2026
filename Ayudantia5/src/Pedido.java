import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private String cliente;
    private String direccion;
    private List<Item> items;

    public Pedido(String cliente, String direccion) {
        this.cliente = cliente;
        this.direccion = direccion;
        this.items = new ArrayList<>();
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Item> getItems() {
        return items;
    }

    public void agregarItem(Item item) {
        items.add(item);
    }

    public double calcularTotal() {
        if (items.isEmpty()) {
            throw new IllegalStateException("No se puede calcular el total de un pedido sin ítems.");
        }
        double total = 0;
        for (Item item : items) {
            total += item.calcularSubtotal();
        }
        return total;
    }
}
