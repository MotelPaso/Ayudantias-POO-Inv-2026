import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {
    public static void cargarPedido(String archivo, Queue<Pedido> cola) {
        try (Scanner fileScanner = new Scanner(new File(archivo))) {
            while (fileScanner.hasNextLine()) {
                String linea = fileScanner.nextLine();
                String[] partes = linea.split(";");
                double precio = Double.parseDouble(partes[0]);
                String[] items = partes[1].split(",");
                cola.enqueue(new Pedido(precio, items));
            }
            System.out.println("Se cargaron " + cola.size() + " Pedidos desde " + archivo + "\n");
        } catch (FileNotFoundException e) {
            System.out.println("Error al leer " + archivo + ": " + e.getMessage());
        }
    }

    public static void mostrarMenu(Queue<Pedido> cola, Stack<Pedido> historial) {
        System.out.println("=== SISTEMA DE PEDIDOS ===");
        System.out.println("Pedidos pendientes: " + cola.size());
        System.out.println("Pedidos procesados: " + historial.size());
        System.out.println("1. Procesar siguiente pedido");
        System.out.println("2. Deshacer ultimo pedido");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opcion: ");
    }

    public static void procesarPedido(Queue<Pedido> cola, Stack<Pedido> historial) {
        if (cola.isEmpty()) {
            System.out.println("No hay Pedidos pendientes.\n");
            return;
        }
        Pedido pedido = cola.dequeue();
        historial.push(pedido);
        System.out.println("Pedido procesado:\n" + pedido + "\n");
    }

    public static void deshacerPedido(Stack<Pedido> historial) {
        if (historial.isEmpty()) {
            System.out.println("No hay Pedidos para deshacer.\n");
            return;
        }
        Pedido deshecho = historial.pop();
        System.out.println("Pedido deshecho: " + deshecho + "\n");
    }

    public static void main(String[] args) {
        Queue<Pedido> colaPedido = new Queue<>();
        Stack<Pedido> historialPedido = new Stack<>();
        Scanner scanner = new Scanner(System.in);

        cargarPedido("pedidos.txt", colaPedido);

        while (true) {
            mostrarMenu(colaPedido, historialPedido);

            int opcion;
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\nOpcion invalida, intente de nuevo.\n");
                continue;
            }

            System.out.println();
            switch (opcion) {
                case 1:
                    procesarPedido(colaPedido, historialPedido);
                    break;
                case 2:
                    deshacerPedido(historialPedido);
                    break;
                case 3:
                    System.out.println("Saliendo del sistema...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opcion invalida, intente de nuevo.\n");
            }
        }
    }
}
