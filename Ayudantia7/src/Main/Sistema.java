package Main;

public interface Sistema {

    // Devuelve el numero de cuenta creado.
    public String crearCuenta(String titular, String tipo);

    public String crearCuenta(String titular, String tipo, int nivel, double capacidad);

    public void depositar(String numCuenta, double monto) throws Exception;

    public void retirar(String numCuenta, double monto) throws Exception;

    public void eliminarCuenta(String numCuenta) throws Exception;

    public void eliminarCuenta(int id) throws Exception;

    public String revisarEstado(String numCuenta);

    public String revisarEstado(int id);

    public String mostrarGanancias();

    public String mostrarGastos();

}
