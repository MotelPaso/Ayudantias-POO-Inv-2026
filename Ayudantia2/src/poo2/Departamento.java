package poo2;

public class Departamento {
    private int id;
    private String nombre;
    private Empleado jefe;
    private Empleado[] empleados;

    public Departamento(int id, String nombre, Empleado jefe, Empleado[] empleados) {
        this.id = id;
        this.nombre = nombre;
        this.jefe = jefe;
        this.empleados = empleados;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Empleado getJefe() {
        return jefe;
    }

    public Empleado[] getEmpleados() {
        return empleados;
    }

    public void mostrar() {
        System.out.println("Departamento: " + nombre);
        System.out.println("  Jefe: " + jefe.getNombre() + " - $" + jefe.getSueldo());
        System.out.println("  Empleados:");
        for (Empleado e : empleados) {
            if (e != null) {
                System.out.print("    - ");
                e.mostrar();
            }
        }
    }

    // algo que probablemente comente despues
    // cada objeto es encargado de sus datos (encapsulacion)
    // por esto se hace el metodo eliminarEmpleado dentro de Departamento y no del
    // main.

    public void eliminarEmpleado(Empleado e) {
        for (int i = 0; i < empleados.length; i++) {
            if (empleados[i] == e) {
                empleados[i] = null;
                break;
            }
        }
        if (jefe == e)
            jefe = null;
    }

    public boolean tieneEmpleado(Empleado e) {
        for (Empleado emp : empleados) {
            if (emp == e)
                return true;
        }
        return false;
    }
}
