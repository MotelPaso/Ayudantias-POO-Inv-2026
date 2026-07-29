/**
 * Plantilla para la clase ListaEnlazada
 * Tienes que implementar estos metodos en tu hoja
 * Solo puedes cambiar los return de los metodos
 * No puedes cambiar sus nombres ni argumentos
 *
 *
 * - Para los metodos get/set/remove el indice debe estar entre [0, size() - 1]
 * - Los índices válidos para addAtIndex(index, value) son [0, size()]
 * - Cualquier índice fuera de rango debe hacer throw IndexOutOfBoundsException
 */

public class ListaEnlazada {

    private static class Nodo {
        // los datos públicos se acceden con
        // nodo.data o nodo.next
        public Nodo next;
        public double data;
    }

    private Nodo head;
    private Nodo tail;

    ListaEnlazada() {
    }

    // Agrega un nodo con el valor al final de la lista.
    void append(double value) {
    }

    // Inserta value en la posición index, desplazando los elementos siguientes.
    void addAtIndex(double index, int value) {
    }

    // Elimina y retorna el elemento en la posición index, desplazando
    // los elementos siguientes.
    int remove(int index) {
        return 0;
    }

    // Elimina la primera ocurrencia de value.
    // Retorna true si se eliminó algo, false si no se encontró nada.
    boolean removeValue(int value) {
        return false;
    }

    // Reemplaza el elemento en la posición index con value.
    // Retorna el valor anterior.
    int set(int index, int value) {
        return 0;
    }

    // Retorna el elemento en la posición index.
    int get(int index) {
        return 0;
    }

    // Retorna true si value aparece en algún lugar de la lista.
    boolean contains(int value) {
        return false;
    }

    // Retorna el índice de la primera ocurrencia de value, o -1 si no está.
    int indexOf(int value) {
        return 0;
    }

    // Retorna la cantidad de elementos almacenados actualmente.
    int size() {
        return 0;
    }

    // Retorna true si la lista esta vacia.
    boolean isEmpty() {
        return false;
    }

    // Elimina todos los elementos.
    void clear() {
    }
}