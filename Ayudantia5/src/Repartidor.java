public class Repartidor {
    private String nombre;
    private String vehiculo;

    public Repartidor(String nombre, String vehiculo) {
        this.nombre = nombre;
        this.vehiculo = vehiculo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(String vehiculo) {
        this.vehiculo = vehiculo;
    }

    public void entregar(Pedido pedido) {
        System.out.println("Repartidor " + nombre + " (" + vehiculo + ") entregando pedido de " + pedido.getCliente() + "...");
        System.out.println("Entrega estándar confirmada.");
    }

    public void entregar(Pedido pedido, String notaCliente) {
        System.out.println("Repartidor " + nombre + " (" + vehiculo + ") entregando pedido de " + pedido.getCliente() + "...");
        System.out.println("Nota del cliente: \"" + notaCliente + "\"");
        System.out.println("Entrega con instrucciones especiales confirmada.");
    }
}
