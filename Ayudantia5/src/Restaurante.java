import java.util.ArrayList;
import java.util.List;

public class Restaurante {
    private List<Pedido> pedidos;
    private List<Repartidor> repartidores;

    public Restaurante() {
        this.pedidos = new ArrayList<>();
        this.repartidores = new ArrayList<>();
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public List<Repartidor> getRepartidores() {
        return repartidores;
    }

    public void registrarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public void agregarRepartidor(Repartidor repartidor) {
        repartidores.add(repartidor);
    }

    public void asignarRepartidor(Pedido pedido, Repartidor repartidor) {
        repartidor.entregar(pedido);
    }
}
