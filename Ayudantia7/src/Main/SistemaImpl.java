package Main;

import java.util.ArrayList;

import herencia.Cuenta;
import herencia.CuentaCorriente;
import herencia.CuentaDeAhorro;
import herencia.CuentaVista;

public class SistemaImpl implements Sistema {

    private static SistemaImpl instancia;

    private ArrayList<Cuenta> cuentas = new ArrayList<>();
    private int idActual = 1;
    private int numCuentaActual = 1;

    public static SistemaImpl getInstancia() {
        return instancia == null ? instancia = new SistemaImpl() : instancia;
    }

    @Override
    public String crearCuenta(String titular, String tipo) {
        if (tipo.equalsIgnoreCase("Corriente")) {
            throw new IllegalArgumentException(
                    "Cuenta Corriente requiere nivel socioeconomico: use crearCuenta(titular, tipo, nivel).");
        }
        return crearCuenta(titular, tipo, 0, 0);
    }

    @Override
    public String crearCuenta(String titular, String tipo, int nivel, double capacidad) {
        int id = idActual++;
        String numCuenta = siguienteNumCuenta();

        Cuenta cuenta;
        switch (tipo.toLowerCase()) {
            case "vista" -> cuenta = new CuentaVista(id, numCuenta, titular);
            case "ahorro" -> cuenta = new CuentaDeAhorro(id, numCuenta, titular);
            case "corriente" -> cuenta = new CuentaCorriente(id, numCuenta, titular, nivel, capacidad);
            default -> throw new IllegalArgumentException("Tipo de cuenta desconocido: " + tipo);
        }

        cuentas.add(cuenta);
        return numCuenta;
    }

    @Override
    public void depositar(String numCuenta, double monto) throws Exception {
        Cuenta cuenta = buscarCuenta(numCuenta);
        cuenta.depositar(monto);
    }

    @Override
    public void retirar(String numCuenta, double monto) throws Exception {
        Cuenta cuenta = buscarCuenta(numCuenta);
        cuenta.retirar(monto);
    }

    @Override
    public void eliminarCuenta(String numCuenta) throws Exception {
        Cuenta cuenta = buscarCuenta(numCuenta);
        cuentas.remove(cuenta);
    }

    @Override
    public void eliminarCuenta(int id) throws Exception {
        Cuenta cuenta = buscarCuenta(id);
        cuentas.remove(cuenta);
    }

    @Override
    public String revisarEstado(String numCuenta) {
        Cuenta cuenta = buscarCuenta(numCuenta);
        return cuenta.getEstado();
    }

    @Override
    public String revisarEstado(int id) {
        Cuenta cuenta = buscarCuenta(id);
        return cuenta.getEstado();
    }

    @Override
    public String mostrarGanancias() {
        String resultado = "=== Ganancias Mensuales ===\n";
        double total = 0;
        for (Cuenta cuenta : cuentas) {
            double ganancia = cuenta.gananciaMensual();
            total += ganancia;
            resultado += cuenta.getEstado().split("\n")[0] + " -> " + cuenta.getNumCuenta() + " (Titular: "
                    + cuenta.getTitular() + "): $" + ganancia + "\n";
        }
        resultado += "Total: $" + total;
        return resultado;
    }

    @Override
    public String mostrarGastos() {
        String resultado = "=== Gastos Mensuales ===\n";
        double total = 0;
        for (Cuenta cuenta : cuentas) {
            double costo = cuenta.costoMensual();
            total += costo;
            resultado += cuenta.getEstado().split("\n")[0] + " -> " + cuenta.getNumCuenta() + " (Titular: "
                    + cuenta.getTitular() + "): $" + costo + "\n";
        }
        resultado += "Total: $" + total;
        return resultado;
    }

    private Cuenta buscarCuenta(String numCuenta) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumCuenta().equals(numCuenta)) {
                return cuenta;
            }
        }
        throw new IllegalArgumentException("No existe cuenta con numero " + numCuenta);
    }

    private Cuenta buscarCuenta(int id) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getId() == id) {
                return cuenta;
            }
        }
        throw new IllegalArgumentException("No existe cuenta con id " + id);
    }

    private String siguienteNumCuenta() {
        return String.format("%07d", numCuentaActual++);
    }

}
