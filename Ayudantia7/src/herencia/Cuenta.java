package herencia;

public abstract class Cuenta {

    protected int id;
    protected String numCuenta;
    protected String titular;
    protected double saldo;
    protected double CAP_MAXIMA;

    public Cuenta(int id, String numCuenta, String titular) {
        this.id = id;
        this.numCuenta = numCuenta;
        this.titular = titular;
        this.saldo = 0;
    }

    public void depositar(double monto) throws Exception {
        if (monto < 0) {
            throw new IllegalArgumentException("Monto a agregar no puede ser negativo.");
        }
        if (this.saldo + monto > CAP_MAXIMA) {
            throw new IllegalStateException("Saldo no puede ser mayor a la capacidad maxima");
        }
        this.saldo += monto;
    }

    public void retirar(double monto) throws Exception {
        if (monto < 0) {
            throw new IllegalArgumentException("Monto a retirar no puede ser negativo.");
        }
        if (this.saldo - monto < 0) {
            throw new IllegalStateException("Saldo a retirar no puede ser menor al saldo.");
        }
        this.saldo -= monto;
    }

    public abstract String getEstado();

    public abstract double gananciaMensual();

    public abstract double costoMensual();

    public int getId() {
        return id;
    }

    public String getNumCuenta() {
        return numCuenta;
    }

    public String getTitular() {
        return titular;
    }
}
