package herencia;

public class CuentaVista extends Cuenta {

    public CuentaVista(int id, String numCuenta, String titular) {
        super(id, numCuenta, titular);
        this.CAP_MAXIMA = 2_000_000;
    }

    @Override
    public String getEstado() {
        String datos = "Cuenta Vista: \nTitular: " + this.titular + "\nSaldo: " + this.saldo;
        return datos;
    }

    @Override
    public double gananciaMensual() {
        return 0;
    }

    @Override
    public double costoMensual() {
        return 0;
    }

}
