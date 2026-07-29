public class ListaImpl {

    private static class Nodo {
        public Nodo next;
        public double data;

        Nodo(double data) {
            this.data = data;
            this.next = null;
        }
    }

    private Nodo head;
    private Nodo tail;
    private int size;

    ListaImpl() {
        head = null;
        tail = null;
        size = 0;
    }

    // Agrega un nodo con el valor al final de la lista.
    void append(double value) {
        Nodo nodo = new Nodo(value);
        if (tail != null) {
            tail.next = nodo;
        } else {
            head = nodo;
        }
        tail = nodo;
        size++;
    }

    // Inserta value en la posición index, desplazando los elementos siguientes.
    void addAtIndex(int index, double value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == size) {
            append(value);
            return;
        }
        Nodo nodo = new Nodo(value);
        if (index == 0) {
            nodo.next = head;
            head = nodo;
            if (size == 0) {
                tail = nodo;
            }
        } else {
            Nodo curr = head;
            int i = 0;
            while (i < index - 1) {
                curr = curr.next;
                i++;
            }
            nodo.next = curr.next;
            curr.next = nodo;
        }
        size++;
    }

    void removeAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            head = head.next;
            if (head == null) {
                tail = null;
            }
        } else {
            Nodo curr = head;
            int i = 0;
            while (i < index - 1) {
                curr = curr.next;
                i++;
            }
            curr.next = curr.next.next;
            if (curr.next == null) {
                tail = curr;
            }
        }
        size--;
    }

    boolean pop(double value) {
        Nodo curr = head;
        Nodo prev = null;
        while (curr != null) {
            if (curr.data == value) {
                if (prev == null) {
                    head = curr.next;
                    if (head == null) {
                        tail = null;
                    }
                } else {
                    prev.next = curr.next;
                    if (prev.next == null) {
                        tail = prev;
                    }
                }
                size--;
                return true;
            }
            prev = curr;
            curr = curr.next;
        }
        return false;
    }

    // Reemplaza el elemento en la posición index con value.
    // Retorna el valor anterior.
    double set(int index, double value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Nodo curr = head;
        int i = 0;
        while (i < index) {
            curr = curr.next;
            i++;
        }
        double old = curr.data;
        curr.data = value;
        return old;
    }

    // Retorna el elemento en la posición index.
    double get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Nodo curr = head;
        int i = 0;
        while (i < index) {
            curr = curr.next;
            i++;
        }
        return curr.data;
    }

    // Retorna true si value aparece en algún lugar de la lista.
    boolean contains(double value) {
        Nodo curr = head;
        while (curr != null) {
            if (curr.data == value) {
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    // Retorna el índice de la primera ocurrencia de value, o -1 si no está.
    int indexOf(double value) {
        Nodo curr = head;
        int i = 0;
        while (curr != null) {
            if (curr.data == value) {
                return i;
            }
            curr = curr.next;
            i++;
        }
        return -1;
    }

    // Retorna la cantidad de elementos almacenados actualmente.
    int size() {
        return size;
    }

    // Retorna true si la lista esta vacia.
    boolean isEmpty() {
        return size == 0;
    }

    // Elimina todos los elementos.
    void clear() {
        head = null;
        tail = null;
        size = 0;
    }
}