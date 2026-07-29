package poo2;

import java.util.Scanner;
import java.io.File;

// Este codigo no tiene control de errores perfecto
// Si revisan los solves, intenten hacer que el codigo falle de alguna manera
// y implementen el control de errores correspondiente

public class App {

    // pongan el valor que quieran aqui, yo aprete cualquier tecla
    private static String PASSWORD = "ASdwxcnz2Sdbnn3SdhjkaQ";

    private static Empleado[] empleados = new Empleado[100];
    private static Departamento[] departamentos = new Departamento[50];
    private static Proyecto[] proyectos = new Proyecto[50];
    private static int cantEmpleados = 0;
    private static int cantDeptos = 0;
    private static int cantProyectos = 0;

    public static void main(String[] args) {
        cargarDatos();
        mostrarMenu();

    }

    private static void mostrarMenu() {
        Scanner sc = new Scanner(System.in);
        String opcion = "";
        do {
            // realmente no es tan importante hacer un imprimirMenu()
            // pero se veria un poco mas bonito
            System.out.println("Menu:");
            System.out.println("1. Mostrar departamentos.");
            System.out.println("2. Mostrar proyectos.");
            System.out.println("3. Aumentar sueldo a un empleado.");
            System.out.println("4. Buscar empleado por nombre.");
            System.out.println("5. Eliminar Proyecto.");
            System.out.println("6. Eliminar Departamento.");
            System.out.println("7. Despedir empleado.");
            System.out.println("0. Salir.");
            System.out.print("Opcion: ");
            opcion = sc.nextLine();
            switch (opcion) {
                case "1" -> mostrarDepartamentos();
                case "2" -> mostrarProyectos();
                case "3" -> aumentarSueldo(sc);
                case "4" -> buscarEmpleado(sc);
                case "5" -> eliminarProyecto(sc);
                case "6" -> eliminarDepartamento(sc);
                case "7" -> despedirEmpleado(sc);
                case "0" -> System.out.println("Saliendo...");
                default -> System.out.println("Opcion invalida.");
            }
        } while (!opcion.equals("0"));
        sc.close();
    }

    private static void cargarDatos() {
        // hay varias formas de hacer control de errores
        // en la ayudantia hice un try/catch en esta funcion, con throws en las
        // interiores
        // pero para tener modularidad, se deberia tener un try/catch en las interiores
        System.out.println("Cargando empresa...");
        cargarEmpleados("empleados.csv");
        cargarDepartamentos("departamentos.csv");
        cargarProyectos("proyectos.csv");
        System.out.println("Empresa cargada!\n");
    }

    private static void cargarEmpleados(String archivo) {
        try (Scanner sc = new Scanner(new File(archivo))) {
            sc.nextLine();
            while (sc.hasNextLine()) {
                // algo relativamente importante para el q lea los solve
                // parseInt y valueOf NO son lo mismo
                // uno devuelve un int y el otro un objeto Integer
                // no hay demasiada diferencia pero podria causar un problema algun dia
                // usen parseInt
                String[] partes = sc.nextLine().split(",");
                int id = Integer.parseInt(partes[0]);
                String nombre = partes[1];
                String rol = partes[2];
                double sueldo = Double.parseDouble(partes[3]);
                empleados[cantEmpleados++] = new Empleado(id, nombre, rol, sueldo);
            }
        } catch (Exception e) {
            System.out.println("Error al cargar empleados: " + e.getMessage());
        }
    }

    private static void cargarDepartamentos(String archivo) {
        try (Scanner sc = new Scanner(new File(archivo))) {
            sc.nextLine();
            while (sc.hasNextLine()) {
                String[] partes = sc.nextLine().split("\"");
                String[] first = partes[0].split(",");
                int id = Integer.parseInt(first[0].trim());
                // funciona igual q strip, pero me daba un error aqui por la version de Java q
                // ocupo
                // mejor ponerle trim que descargar todo de vuelta
                String nombre = first[1].trim();
                int jefeId = Integer.parseInt(first[2].trim());
                String[] ids = partes[1].split(",");
                Empleado jefe = buscarEmpleadoPorId(jefeId);
                Empleado[] emps = new Empleado[7];
                for (int i = 0; i < 7; i++) {
                    emps[i] = buscarEmpleadoPorId(Integer.parseInt(ids[i].trim()));
                }
                departamentos[cantDeptos++] = new Departamento(id, nombre, jefe, emps);
            }
        } catch (Exception e) {
            System.out.println("Error al cargar departamentos: " + e.getMessage());
        }
    }

    private static void cargarProyectos(String archivo) {
        try (Scanner sc = new Scanner(new File(archivo))) {
            sc.nextLine();
            while (sc.hasNextLine()) {
                String[] partes = sc.nextLine().split("\"");
                // el \ le dice al compilador que tome el siguiente caracter como literal, no
                // como un nuevo string
                String[] first = partes[0].split(",");
                int id = Integer.parseInt(first[0].trim());
                String nombre = first[1].trim();
                double presupuesto = Double.parseDouble(first[2].trim());
                int liderId = Integer.parseInt(first[3].trim());
                String[] ids = partes[1].split(",");
                Empleado lider = buscarEmpleadoPorId(liderId);
                Empleado[] equipo = new Empleado[10];
                for (int i = 0; i < 10; i++) {
                    equipo[i] = buscarEmpleadoPorId(Integer.parseInt(ids[i].trim()));
                }
                proyectos[cantProyectos++] = new Proyecto(id, nombre, presupuesto, lider, equipo);
            }
        } catch (Exception e) {
            System.out.println("Error al cargar proyectos: " + e.getMessage());
        }
    }

    // funciones utiles
    // estas funciones cuentan como abstracciones
    // poo se invento para trabajar en empresas o equipos grandes
    // si trabajasemos tu y yo en un proyecto, no deberias recordar
    // que hacen mis funciones, la idea es que solo las llames
    // y sepas que hacen mediante su nombre/documentacion

    private static Empleado buscarEmpleadoPorId(int id) {
        for (int i = 0; i < cantEmpleados; i++) {
            if (empleados[i].getId() == id)
                return empleados[i];
        }
        return null;
    }

    private static Empleado buscarEmpleadoPorNombre(String nombre) {
        for (int i = 0; i < cantEmpleados; i++) {
            if (empleados[i].getNombre().equalsIgnoreCase(nombre))
                return empleados[i];
        }
        return null;
    }

    private static Departamento buscarDepartamentoPorId(int id) {
        for (int i = 0; i < cantDeptos; i++) {
            if (departamentos[i].getId() == id)
                return departamentos[i];
        }
        return null;
    }

    private static Proyecto buscarProyectoPorId(int id) {
        for (int i = 0; i < cantProyectos; i++) {
            if (proyectos[i].getId() == id)
                return proyectos[i];
        }
        return null;
    }

    private static void mostrarDepartamentos() {
        System.out.println("--- Departamentos ---");
        for (int i = 0; i < cantDeptos; i++) {
            departamentos[i].mostrar(); // abstraccion
            System.out.println();
        }
    }

    private static void mostrarProyectos() {
        System.out.println("--- Proyectos ---");
        for (int i = 0; i < cantProyectos; i++) {
            proyectos[i].mostrar();
            System.out.println();
        }
    }

    private static void aumentarSueldo(Scanner sc) {
        System.out.print("Ingrese el nombre del empleado: ");
        String nombre = sc.nextLine();
        Empleado emp = buscarEmpleadoPorNombre(nombre);
        if (emp == null) {
            System.out.println("Empleado no encontrado.");
            return;
        }
        System.out.print("Ingrese el monto del aumento: ");
        double monto = sc.nextDouble();
        sc.nextLine();
        if (monto <= 0) {
            System.out.println("No se puede hacer un aumento negativo.");
            return;
        }
        emp.setSueldo(emp.getSueldo() + monto);
        System.out.println("Sueldo actualizado. " + emp.getNombre() + " ahora gana $" + emp.getSueldo());
    }

    private static void buscarEmpleado(Scanner sc) {
        System.out.print("Ingrese el nombre a buscar: ");
        String nombre = sc.nextLine();
        Empleado emp = buscarEmpleadoPorNombre(nombre);
        if (emp == null) {
            System.out.println("Empleado no encontrado.");
            return;
        }
        System.out.println("\n" + emp.getNombre() + " encontrada:");
        for (int i = 0; i < cantDeptos; i++) {
            Departamento d = departamentos[i];
            if (d.tieneEmpleado(emp)) {
                String rol = (d.getJefe() == emp) ? "Jefe" : "Miembro";
                System.out.println("  - Departamento: " + d.getNombre() + " (" + rol + ") - $" + emp.getSueldo());
            }
        }
        for (int i = 0; i < cantProyectos; i++) {
            Proyecto p = proyectos[i];
            if (p.tieneEmpleado(emp)) {
                String rol = (p.getLider() == emp) ? "Lider" : "Miembro";
                System.out.println("  - Proyecto: " + p.getNombre() + " (" + rol + ") - $" + emp.getSueldo());
            }
        }
    }

    private static boolean verificarPassword(Scanner sc) {
        System.out.println("\nAccion destructiva, se necesitan permisos de administrador.");
        System.out.print("Ingrese su contrasena: "); // no tengo ene
        String pass = sc.nextLine();
        if (!pass.equals(PASSWORD)) {
            System.out.println("contrasena incorrecta.");
            return false;
        }
        return true;
    }

    private static boolean confirmarEliminacion(Scanner sc, String nombre) {
        System.out.print("Esta seguro que quiere eliminar este " + nombre + "? (s/n): ");
        String conf = sc.nextLine();
        if (!conf.equalsIgnoreCase("s")) {
            System.out.println("Operacion cancelada.");
            return false;
        }
        return true;
    }

    private static void eliminarProyecto(Scanner sc) {
        if (!verificarPassword(sc))
            return;
        System.out.print("Ingrese el ID a eliminar: ");
        int id = sc.nextInt();
        sc.nextLine();
        Proyecto p = buscarProyectoPorId(id);
        if (p == null) {
            System.out.println("Proyecto no encontrado.");
            return;
        }
        System.out.println("\nProyecto: " + p.getNombre());
        if (!confirmarEliminacion(sc, "proyecto"))
            return;
        int indiceBorrado = 0;
        while (indiceBorrado < cantProyectos && proyectos[indiceBorrado] != p)
            indiceBorrado++;
        for (int i = indiceBorrado; i < cantProyectos - 1; i++) {
            proyectos[i] = proyectos[i + 1];
        }
        proyectos[--cantProyectos] = null;
        System.out.println("Proyecto eliminado...");
    }

    private static void eliminarDepartamento(Scanner sc) {
        if (!verificarPassword(sc))
            return;
        System.out.print("Ingrese el ID a eliminar: ");
        int id = sc.nextInt();
        sc.nextLine();
        Departamento d = buscarDepartamentoPorId(id);
        if (d == null) {
            System.out.println("Departamento no encontrado.");
            return;
        }
        System.out.println("\nDepartamento: " + d.getNombre());
        if (!confirmarEliminacion(sc, "departamento"))
            return;
        int indiceBorrado = 0;
        while (indiceBorrado < cantDeptos && departamentos[indiceBorrado] != d)
            indiceBorrado++;
        for (int i = indiceBorrado; i < cantDeptos - 1; i++) {
            departamentos[i] = departamentos[i + 1];
        }
        departamentos[--cantDeptos] = null;
        System.out.println("Departamento eliminado...");
    }

    private static void despedirEmpleado(Scanner sc) {
        if (!verificarPassword(sc))
            return;
        System.out.print("Ingrese el ID del empleado a despedir: ");
        int id = sc.nextInt();
        sc.nextLine();
        Empleado emp = buscarEmpleadoPorId(id);
        if (emp == null) {
            System.out.println("Empleado no encontrado.");
            return;
        }
        System.out.println("\nEmpleado: " + emp.getNombre());
        if (!confirmarEliminacion(sc, "empleado"))
            return;
        for (int i = 0; i < cantDeptos; i++) {
            departamentos[i].eliminarEmpleado(emp);
        }
        for (int i = 0; i < cantProyectos; i++) {
            proyectos[i].eliminarEmpleado(emp);
        }
        int indiceBorrado = 0;
        while (indiceBorrado < cantEmpleados && empleados[indiceBorrado] != emp)
            indiceBorrado++;
        for (int i = indiceBorrado; i < cantEmpleados - 1; i++) {
            empleados[i] = empleados[i + 1];
        }
        empleados[--cantEmpleados] = null;
        System.out.println("Empleado despedido...");
    }
}
