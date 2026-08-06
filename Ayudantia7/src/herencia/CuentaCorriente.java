package herencia;

public class CuentaCorriente extends Cuenta {

    private double sobregiroUsado;
    private double sobregiroMax;

    private double comision = calcComision();

    private double calcComision() {
        int precioUF = 63_000;
        return precioUF * 0.12;
    };

    public CuentaCorriente(int id, String numCuenta, String titular, int nivel, double capacidad) {
        super(id, numCuenta, titular);
        this.CAP_MAXIMA = capacidad;
        calcularDatosCuenta(nivel);
    }

    private void calcularDatosCuenta(int nivel) {
        reiniciarSobregiro();

    }

    private void reiniciarSobregiro() {
        this.sobregiroMax = CAP_MAXIMA * 0.20;
        this.sobregiroUsado = 0;
    }

    @Override
    public void depositar(double monto) throws Exception {
        if (monto < 0) {
            throw new IllegalArgumentException("Monto a agregar no puede ser negativo.");
        }
        if (sobregiroUsado > 0) {
            double pagoSobregiro = (monto <= sobregiroUsado) ? monto : sobregiroUsado;
            this.sobregiroUsado -= pagoSobregiro;
            monto -= pagoSobregiro;
        }
        if (this.saldo + monto > CAP_MAXIMA) {
            throw new IllegalStateException("Saldo no puede ser mayor a la capacidad maxima");
        }
        this.saldo += monto;
    }

    @Override
    public void retirar(double monto) throws Exception {
        if (monto < 0) {
            throw new IllegalArgumentException("Monto a retirar no puede ser negativo.");
        }
        double deficit = monto - this.saldo;
        if (deficit > 0) {
            if (deficit + sobregiroUsado > sobregiroMax) {
                throw new IllegalStateException("Transaccion supera al sobregiro.");
            }
            this.sobregiroUsado += deficit;
            this.saldo += deficit;
        }
        this.saldo -= monto;
    }

    @Override
    public String getEstado() {

        String datos = "Cuenta Corriente: \nTitular: " + this.titular + "\nSaldo: " + this.saldo;

        if (sobregiroUsado > 0) {
            datos += "\nSobregiro Usado:" + this.sobregiroUsado;
            datos += "\nSobregiro Maximo: " + this.sobregiroMax;
        }
        return datos;
    }

    @Override
    public double gananciaMensual() {
        return 0;
    }

    @Override
    public double costoMensual() {
        return comision;
    }

}
