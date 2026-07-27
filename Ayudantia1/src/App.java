import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    private static int[][] mapa;
    private static File archivo;
    private static int filaElegida = -1;
    private static int colElegida = -1;

    public static void main(String[] args) {
        // Los nombres de las funciones podrian no ser iguales
        // pq se me olvidaron y no los anote
        System.out.println("=== Sistema de Reserva de Asientos ===");
        try {
            leerArchivos();
            mostrarMenu();
        } catch (InputMismatchException e) {
            System.out.println("No hay salas disponibles... Revisa bien los archivos de la carpeta");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void leerArchivos() throws Exception {
        String salas = buscarSalasenCarpeta();
        if (salas.equals("")) {
            throw new InputMismatchException();
        }
        mostrarSalasDisponibles(salas);
        try {
            elegirSala();
            leerDatosSala();
            guardarSalaEnMatriz();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void guardarSalaEnMatriz() throws FileNotFoundException {
        Scanner s = new Scanner(archivo);
        int fila = 0;
        while (s.hasNextLine()) {
            String linea = s.nextLine();
            String[] partes = linea.split(" ");

            for (int col = 0; col < partes.length; col++) {
                mapa[fila][col] = Integer.parseInt(partes[col]);
            }
            fila++;
        }
        System.out.println(archivo.getName() + " cargada!");

    }

    private static void leerDatosSala() throws FileNotFoundException {
        Scanner s = new Scanner(archivo);
        int filas = 1;
        int cols = 0;
        String linea = s.nextLine();
        String[] partes = linea.split(" ");
        cols = partes.length;
        while (s.hasNextLine()) {
            linea = s.nextLine();
            filas++;
        }
        mapa = new int[filas][cols];
    }

    private static void elegirSala() throws FileNotFoundException {
        System.out.println("Ingrese el numero de sala a cargar: ");
        String numSala = leerString();
        File sala = new File("sala" + numSala + ".txt");

        if (!sala.exists()) {
            throw new FileNotFoundException("La sala no existe...");
        }
        System.out.println("Cargando sala" + numSala + ".txt...");
        archivo = sala;
    }

    private static String buscarSalasenCarpeta() {
        int numSala = 1;
        String salas = "";
        boolean quedanSalas = true;
        while (quedanSalas) {
            String salaActual = "sala" + numSala + ".txt";
            File sala = new File(salaActual);
            if (!sala.exists()) {
                quedanSalas = false;
            } else {
                if (!salas.equals("")) {
                    salas += ", ";
                }
                salas += salaActual;
                numSala++;
            }
        }
        return salas;

    }

    private static void mostrarSalasDisponibles(String salas) {
        System.out.println("Salas disponibles: " + salas);
    }

    private static void mostrarMenu() {

        String opcion = "";
        do {
            imprimirMenu();
            opcion = leerString();

            // los switch se pueden escribir asi:
            switch (opcion) {
                case "1" -> mostrarResumen();
                case "2" -> buscarAsientos();
                case "3" -> reservarAsiento();
                case "0" -> System.out.println("Adios!");
                default -> System.out.println("Opcion invalida.");
            }
            // pero debe ser solo una "linea", lo siguiente no funcionaria.
            // case "2" -> print("hola"); num ++;
        } while (!opcion.equals("0"));

    }

    private static void imprimirMenu() {
        System.out.print("Menu:\n" + //
                "1. Mostrar resumen de ocupación.\n" + //
                "2. Buscar asientos para grupo.\n" + //
                "3. Reservar un asiento.\n" + //
                "0. Salir" + //
                "Opcion: ");
    }

    private static String leerString() {
        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }

    private static void mostrarResumen() {
        System.out.println("--- Resumen de Ocupación ---");
        mostrarMapa();
        System.out.println(calcularAsientosOcupados());
        System.out.println(filaConMasAsientosDispo());
    }

    private static void mostrarMapa() {
        System.out.print("Mapa de la sala:\nx ");
        for (int i = 1; i < mapa[0].length + 1; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < mapa.length; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < mapa[0].length; j++) {
                if (i == filaElegida && j == colElegida) {
                    System.out.print("X ");
                } else if (mapa[i][j] == 1) {
                    System.out.print("# ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    private static String filaConMasAsientosDispo() {
        int cantAsientosMayor = 0;
        int fila = 0;
        for (int i = 0; i < mapa.length; i++) {
            int asientosFila = 0;
            for (int j = 0; j < mapa[0].length; j++) {
                if (mapa[i][j] == 0) {
                    asientosFila++;
                }
            }
            if (cantAsientosMayor < asientosFila) {
                fila = i + 1;
                cantAsientosMayor = asientosFila;
            }
        }
        return "Fila con más asientos disponibles: Fila " + fila + " (" + cantAsientosMayor + " disponibles)";
    }

    private static String calcularAsientosOcupados() {
        int asientosOcupados = 0;
        int asientosTotales = 0;
        for (int i = 0; i < mapa.length; i++) {

            for (int j = 0; j < mapa[0].length; j++) {
                asientosTotales++;
                if (mapa[i][j] == 1) {
                    asientosOcupados++;
                }
            }
        }
        double porcentaje = ((double) asientosOcupados / asientosTotales) * 100;
        return "Asientos ocupados: " + asientosOcupados + " de " + asientosTotales + " ("
                + String.format("%.1f", porcentaje) + "%)";

    }

    private static void buscarAsientos() {
        System.out.print("Buscando bloque de N asientos consecutivos vacios...\n");
        System.out.print("Cuantos asientos busca? ");
        String asientos = leerString();
        try {
            String resultado = revisarFilas(asientos);
            if (resultado.equals("")) {
                System.out.println("No se encontro bloque de " + asientos + " asientos consecutivos vacios.");
            } else {
                System.out.println(resultado);
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada invalida. Ingrese un numero.");
        }
    }

    private static String revisarFilas(String asientos) {
        int cantBuscada = Integer.parseInt(asientos);
        String filas = "";
        for (int i = 0; i < mapa.length; i++) {
            int consecutivos = 0;
            for (int j = 0; j < mapa[0].length; j++) {
                if (mapa[i][j] == 0) {
                    consecutivos++;
                    if (consecutivos == cantBuscada) {
                        int inicio = j - cantBuscada + 2;
                        filas += "Bloque encontrado en Fila " + (i + 1) + ", comenzando en el asiento " + inicio
                                + ".\n";
                        break;
                    }
                } else {
                    consecutivos = 0;
                }
            }
        }
        return filas;
    }

    private static void reservarAsiento() {
        mostrarMapa();
        System.out.print("¿Desea reservar un asiento? (s/n): ");
        String resp = leerString();
        if (!resp.equalsIgnoreCase("s")) {
            System.out.println("Volviendo al menu...");
            return;
        }
        int[] pos = getDatosReserva();
        if (pos == null)
            return;
        if (!confirmarReserva(pos[0], pos[1]))
            return;
        mapa[pos[0]][pos[1]] = 1;
        System.out.println("Asiento reservado exitosamente.");
        guardarArchivo();
    }

    private static int[] getDatosReserva() {
        try {
            System.out.print("Ingrese fila (1-" + mapa.length + "): ");
            int fila = Integer.parseInt(leerString()) - 1;
            System.out.print("Ingrese asiento (1-" + mapa[0].length + "): ");
            int col = Integer.parseInt(leerString()) - 1;
            if (!esValido(fila, col)) {
                System.out.println("Posicion invalida.");
                return null;
            }
            if (mapa[fila][col] == 1) {
                System.out.println("Ese asiento ya esta ocupado.");
                return null;
            }
            return new int[] { fila, col };
        } catch (NumberFormatException e) {
            System.out.println("Entrada invalida. Ingrese un numero.");
            return null;
        }
    }

    private static boolean confirmarReserva(int fila, int col) {
        filaElegida = fila;
        colElegida = col;
        System.out.println("\nMapa actualizado:");
        mostrarMapa();
        System.out.print("Confirma su eleccion? (s/n): ");
        String confirma = leerString();
        boolean ok = confirma.equalsIgnoreCase("s");
        filaElegida = -1;
        colElegida = -1;
        return ok;
    }

    private static boolean esValido(int fila, int col) {
        return fila >= 0 && fila < mapa.length && col >= 0 && col < mapa[0].length;
    }

    private static void guardarArchivo() {
        try {
            java.io.PrintWriter pw = new java.io.PrintWriter(archivo);
            for (int i = 0; i < mapa.length; i++) {
                for (int j = 0; j < mapa[0].length; j++) {
                    pw.print(mapa[i][j]);
                    if (j < mapa[0].length - 1) {
                        pw.print(" ");
                    }
                }
                pw.println();
            }
            pw.close();
            System.out.println("Guardando cambios en " + archivo.getName() + "...");
            System.out.println("Cambios guardados correctamente.");
        } catch (java.io.FileNotFoundException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

}
