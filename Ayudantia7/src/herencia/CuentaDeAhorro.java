package herencia;

public class CuentaDeAhorro extends Cuenta {

    private double interes;

    public CuentaDeAhorro(int id, String numCuenta, String titular) {
        super(id, numCuenta, titular);
        this.CAP_MAXIMA = 50_000_000;
        calcularInteres();
    }

    public double getInteres() {
        calcularInteres();
        return interes;
    }

    private void calcularInteres() {
        if (this.saldo < 1_000_000) {
            this.interes = 0.5;
        } else if (this.saldo <= 9_999_999) {
            this.interes = 1.0;
        } else {
            this.interes = 1.5;
        }
    }

    @Override
    public String getEstado() {
        String datos = "Cuenta De Ahorro: \nTitular: " + this.titular + "\nSaldo: " + this.saldo + "\nInteres Mensual:"
                + this.interes;
        return datos;
    }

    @Override
    public double gananciaMensual() {
        return this.saldo * getInteres() / 100;
    }

    @Override
    public double costoMensual() {
        return 0;
    }

}
