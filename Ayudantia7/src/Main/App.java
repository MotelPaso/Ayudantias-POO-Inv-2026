package Main;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    private static Sistema sistema = SistemaImpl.getInstancia();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        int opcion = 0;
        do {
            mostrarMenu();
            opcion = leerInt("Seleccione una opcion: ");
            System.out.println();

            try {
                switch (opcion) {
                    case 1 -> crearCuenta();
                    case 2 -> depositar();
                    case 3 -> retirar();
                    case 4 -> eliminarCuenta();
                    case 5 -> revisarEstado();
                    case 6 -> System.out.println(sistema.mostrarGanancias());
                    case 7 -> System.out.println(sistema.mostrarGastos());
                    case 8 -> System.out.println("Hasta luego!");
                    default -> System.out.println("Opcion invalida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println();
        } while (opcion != 8);
    }

    private static void mostrarMenu() {
        System.out.println("=== Sistema Bancario ===");
        System.out.println("1. Crear cuenta");
        System.out.println("2. Depositar");
        System.out.println("3. Retirar");
        System.out.println("4. Eliminar cuenta");
        System.out.println("5. Revisar estado");
        System.out.println("6. Mostrar ganancias");
        System.out.println("7. Mostrar gastos");
        System.out.println("8. Salir");
    }

    private static void crearCuenta() {
        String titular = leerTexto("Titular: ");
        String tipo = leerTexto("Tipo (Vista / Ahorro / Corriente): ");

        String numCuenta;
        if (tipo.equalsIgnoreCase("Corriente")) {
            int nivel = leerInt("Nivel socioeconomico (0 - 100): ");
            double capacidad = leerDouble("Capacidad: ");
            numCuenta = sistema.crearCuenta(titular, tipo, nivel, capacidad);
        } else {
            numCuenta = sistema.crearCuenta(titular, tipo);
        }
        System.out.println("Cuenta creada con numero " + numCuenta);
    }

    private static void depositar() throws Exception {
        String numCuenta = leerTexto("Numero de cuenta: ");
        double monto = leerDouble("Monto a depositar: ");
        sistema.depositar(numCuenta, monto);
        System.out.println("Deposito realizado.");
    }

    private static void retirar() throws Exception {
        String numCuenta = leerTexto("Numero de cuenta: ");
        double monto = leerDouble("Monto a retirar: ");
        sistema.retirar(numCuenta, monto);
        System.out.println("Retiro realizado.");
    }

    private static void eliminarCuenta() throws Exception {
        String numCuenta = leerTexto("Numero de cuenta: ");
        sistema.eliminarCuenta(numCuenta);
        System.out.println("Cuenta eliminada.");
    }

    private static void revisarEstado() {
        String numCuenta = leerTexto("Numero de cuenta: ");
        System.out.println(sistema.revisarEstado(numCuenta));
    }

    private static int leerInt(String mensaje) throws InputMismatchException {
        System.out.print(mensaje);
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }

    private static double leerDouble(String mensaje) throws InputMismatchException {
        System.out.print(mensaje);
        double valor = scanner.nextDouble();
        scanner.nextLine();
        return valor;
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

}
