package poo2;

public class Empleado {
    private int id;
    private String nombre;
    private String rol;
    private double sueldo;

    public Empleado(int id, String nombre, String rol, double sueldo) {
        this.id = id;
        this.nombre = nombre;
        this.rol = rol;
        this.sueldo = sueldo;
    }

    // Personalmente, no me gusta llenar de getters un codigo
    // Con una clase con 4 atributos esta """bien""", pero en el mundo real
    // las clases tienen mayor complejidad
    // ademas de que no todos los datos de una clase "deberian" realmente ser
    // obtenidos por tu equipo

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRol() {
        return rol;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    // este getSueldo y setSueldo "funcionan",
    // pero volviendo a la app en la linea 213 dice:
    // emp.setSueldo(emp.getSueldo() + monto);
    // que funciona bien, pero realmente deberia ser un metodo asi:
    // emp.aumentarSueldo(monto);
    // y que la clase Empleado se encargue del resto

    public void mostrar() {
        System.out.println(nombre + " - " + rol + " - $" + sueldo);
    }
}
