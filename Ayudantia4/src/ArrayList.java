/* Plantilla para ArrayList
 * Tienes que rellenar todos los metodos
 * No puedes cambiar sus parametros.
 *
 * Recordar hacer control de errores para indices fuera del rango
 */

public class ArrayList {

    private double[] elementos;
    private int size;
    private static final int CAPACIDAD_INICIAL = 10;

    public ArrayList() {
        this.elementos = new double[CAPACIDAD_INICIAL];
        this.size = 0;
    }

    // Agrega un valor al final de la lista. Si está llena, redimensiona.
    public void add(double valor) {
        ensureCapacity();
        this.elementos[size] = valor;
        size++;
    }

    // Retorna el valor en la posición dada.
    public double get(int indice) {
        if (indice < 0 || indice >= size) {
            throw new IndexOutOfBoundsException("Indice " + indice + " fuera de rango. Tamano: " + size);
        }
        return this.elementos[indice];
    }

    // Reemplaza el valor en la posición dada.
    public void set(int indice, double valor) {
        if (indice < 0 || indice >= size) {
            throw new IndexOutOfBoundsException("Indice " + indice + " fuera de rango. Tamano: " + size);
        }
        this.elementos[indice] = valor;
    }

    // Retorna la cantidad de elementos en la lista.
    public int size() {
        return this.size;
    }

    // Retorna true si la lista está vacía, false en caso contrario.
    public boolean isEmpty() {
        return this.size == 0;
    }

    // Elimina el elemento en la posición dada, desplaza los siguientes y lo
    // retorna.
    public double remove(int indice) {
        if (indice < 0 || indice >= size) {
            throw new IndexOutOfBoundsException("Indice " + indice + " fuera de rango. Tamano: " + size);
        }
        double valorEliminado = this.elementos[indice];
        for (int i = indice; i < size - 1; i++) {
            this.elementos[i] = this.elementos[i + 1];
        }
        size--;
        return valorEliminado;
    }

    // Elimina todos los elementos de la lista.
    public void clear() {
        this.elementos = new double[CAPACIDAD_INICIAL];
        this.size = 0;
    }

    // Retorna true si el valor existe en la lista, false en caso contrario.
    public boolean contains(double valor) {
        return indexOf(valor) >= 0;
    }

    // Retorna el índice del valor, o -1 si no existe.
    public int indexOf(double valor) {
        for (int i = 0; i < size; i++) {
            if (Double.compare(this.elementos[i], valor) == 0) {
                return i;
            }
        }
        return -1;
    }

    // Redimensiona el arreglo duplicando su capacidad cuando se llene.
    private void ensureCapacity() {
        if (this.size == this.elementos.length) {
            double[] nuevo = new double[this.elementos.length * 2];
            for (int i = 0; i < this.elementos.length; i++) {
                nuevo[i] = this.elementos[i];
            }
            this.elementos = nuevo;
        }
    }
}
