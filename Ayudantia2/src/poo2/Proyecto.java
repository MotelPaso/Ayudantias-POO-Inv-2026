package poo2;

public class Proyecto {
    private int id;
    private String nombre;
    private double presupuesto;
    private Empleado lider;
    private Empleado[] equipo;

    public Proyecto(int id, String nombre, double presupuesto, Empleado lider, Empleado[] equipo) {
        this.id = id;
        this.nombre = nombre;
        this.presupuesto = presupuesto;
        this.lider = lider;
        this.equipo = equipo;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPresupuesto() {
        return presupuesto;
    }

    public Empleado getLider() {
        return lider;
    }

    public Empleado[] getEquipo() {
        return equipo;
    }

    public void mostrar() {
        System.out.println("Proyecto: " + nombre);
        System.out.println("  Presupuesto: $" + presupuesto);
        System.out.println("  Lider: " + lider.getNombre() + " - $" + lider.getSueldo());
        System.out.println("  Equipo:");
        for (Empleado e : equipo) {
            if (e != null) {
                System.out.print("    - ");
                e.mostrar();
            }
        }
    }

    public void eliminarEmpleado(Empleado e) {
        for (int i = 0; i < equipo.length; i++) {
            if (equipo[i] == e) {
                equipo[i] = null;
                break;
            }
        }
        if (lider == e)
            lider = null;
    }

    public boolean tieneEmpleado(Empleado e) {
        for (Empleado emp : equipo) {
            if (emp == e)
                return true;
        }
        return false;
    }
}
