import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Test {

    // Este archivo se utiliza igual que una clase Main/App
    // Importenlo a su proyecto y ejecutenlo desde aqui

    private static int pasados = 0;
    private static int fallidos = 0;

    public static void main(String[] args) {
        System.out.println("=== Tests — Simulación de Reparto de Comida ===\n");

        testItem();
        testPedido();
        testEstadoPedido();
        testRepartidor();
        testRestaurante();

        System.out.println("\n=== Resumen ===");
        System.out.println("Pasados: " + pasados);
        System.out.println("Fallidos: " + fallidos);

        if (fallidos > 0) {
            System.exit(1);
        }
    }

    // ===== Tests =====

    /*
     * Test de Item: verifica que los getters devuelvan los valores entregados,
     * que calcularSubtotal() retorne precio * cantidad, y que el constructor
     * y los setters lancen IllegalArgumentException con datos inválidos:
     * nombre nulo o vacío, precio 0 o negativo, cantidad 0 o negativa.
     */
    private static void testItem() {
        System.out.println(">> Test de Item");

        Item hamburguesa = new Item("Hamburguesa", 7700, 2);
        assertEquals(15400.0, hamburguesa.calcularSubtotal(), "Subtotal de Hamburguesa x2");
        assertEquals("Hamburguesa", hamburguesa.getNombre(), "getNombre()");
        assertEquals(2, hamburguesa.getCantidad(), "getCantidad()");

        Item papas = new Item("Papas fritas", 2500, 1);
        assertEquals(2500.0, papas.calcularSubtotal(), "Subtotal de Papas fritas x1");

        // Revision de Excepciones

        assertThrows(IllegalArgumentException.class, () -> new Item(null, 1000, 1), "Item con nombre null");
        assertThrows(IllegalArgumentException.class, () -> new Item("", 1000, 1), "Item con nombre vacío");
        assertThrows(IllegalArgumentException.class, () -> new Item("Bebida", 0, 1), "Item con precio 0");
        assertThrows(IllegalArgumentException.class, () -> new Item("Bebida", -500, 1), "Item con precio negativo");
        assertThrows(IllegalArgumentException.class, () -> new Item("Bebida", 1000, 0), "Item con cantidad 0");
        assertThrows(IllegalArgumentException.class, () -> new Item("Bebida", 1000, -1), "Item con cantidad negativa");

        assertThrows(IllegalArgumentException.class, () -> hamburguesa.setNombre(""), "setNombre() con valor vacío");
        assertThrows(IllegalArgumentException.class, () -> hamburguesa.setPrecio(-1), "setPrecio() con valor negativo");
        assertThrows(IllegalArgumentException.class, () -> hamburguesa.setCantidad(0), "setCantidad() con valor 0");

        System.out.println();
    }

    /*
     * Test de Pedido: verifica cliente, dirección y estado inicial, que
     * calcularTotal() lance IllegalStateException con un pedido vacío y sume
     * los subtotales de los ítems agregados, que agregarItem() respete el
     * límite de Pedido.MAX_ITEMS, y que la propina afecte a
     * calcularTotalConPropina() y rechace valores negativos.
     */
    private static void testPedido() {
        System.out.println(">> Test de Pedido");

        Pedido pedido = new Pedido("Ana Torres", "Av. Siempre Viva 123");
        assertEquals("Ana Torres", pedido.getCliente(), "getCliente()");
        assertEquals("Av. Siempre Viva 123", pedido.getDireccion(), "getDireccion()");
        assertEquals("PENDIENTE", pedido.getEstado(), "getEstado() inicia en PENDIENTE");

        assertThrows(IllegalStateException.class, pedido::calcularTotal, "calcularTotal() con pedido vacío");

        pedido.agregarItem(new Item("Hamburguesa", 7700, 2));
        pedido.agregarItem(new Item("Papas fritas", 2500, 1));
        assertEquals(17900.0, pedido.calcularTotal(), "calcularTotal() con 2 ítems");
        assertEquals(2, pedido.getItems().size(), "getItems() con 2 ítems");

        // Límite de ítems por pedido
        Pedido pedidoLleno = new Pedido("Cliente X", "Dirección X");
        for (int i = 0; i < Pedido.MAX_ITEMS; i++) {
            pedidoLleno.agregarItem(new Item("Ítem " + i, 100, 1));
        }
        assertEquals(Pedido.MAX_ITEMS, pedidoLleno.getItems().size(), "getItems() al límite de " + Pedido.MAX_ITEMS);
        assertThrows(IllegalStateException.class,
                () -> pedidoLleno.agregarItem(new Item("Ítem extra", 100, 1)),
                "agregarItem() superando el límite de " + Pedido.MAX_ITEMS);

        // Propina
        assertEquals(0.0, pedido.getPropina(), "getPropina() inicia en 0");
        pedido.setPropina(10);
        assertEquals(10.0, pedido.getPropina(), "getPropina() tras setPropina(10)");
        assertEquals(19690.0, pedido.calcularTotalConPropina(), "calcularTotalConPropina() con 10% de propina");
        assertThrows(IllegalArgumentException.class, () -> pedido.setPropina(-5), "setPropina() con valor negativo");

        System.out.println();
    }

    /*
     * Test de Estado del Pedido: verifica que se cumpla
     * PENDIENTE -> EN_PREPARACION -> EN_REPARTO -> ENTREGADO y verifica que
     * preparar(), despachar() y marcarEntregado() lancen IllegalStateException
     * si se intentan usar desde un estado inválido.
     */
    private static void testEstadoPedido() {
        System.out.println(">> Test de Estado del Pedido");

        Pedido pedido = new Pedido("Ana Torres", "Av. Siempre Viva 123");
        assertEquals("PENDIENTE", pedido.getEstado(), "Estado inicial de un pedido");

        pedido.preparar();
        assertEquals("EN_PREPARACION", pedido.getEstado(), "Estado tras preparar()");

        pedido.despachar();
        assertEquals("EN_REPARTO", pedido.getEstado(), "Estado tras despachar()");

        pedido.marcarEntregado();
        assertEquals("ENTREGADO", pedido.getEstado(), "Estado tras marcarEntregado()");

        // Transiciones inválidas
        Pedido recienCreado = new Pedido("Cliente A", "Dirección A");
        assertThrows(IllegalStateException.class, recienCreado::despachar, "despachar() desde PENDIENTE");
        assertThrows(IllegalStateException.class, recienCreado::marcarEntregado, "marcarEntregado() desde PENDIENTE");

        Pedido enPreparacion = new Pedido("Cliente B", "Dirección B");
        enPreparacion.preparar();
        assertThrows(IllegalStateException.class, enPreparacion::preparar, "preparar() dos veces");
        assertThrows(IllegalStateException.class, enPreparacion::marcarEntregado,
                "marcarEntregado() desde EN_PREPARACION");

        Pedido entregado = new Pedido("Cliente C", "Dirección C");
        entregado.preparar();
        entregado.despachar();
        entregado.marcarEntregado();
        assertThrows(IllegalStateException.class, entregado::preparar, "preparar() desde ENTREGADO");
        assertThrows(IllegalStateException.class, entregado::despachar, "despachar() desde ENTREGADO");
        assertThrows(IllegalStateException.class, entregado::marcarEntregado, "marcarEntregado() dos veces");

        System.out.println();
    }

    /*
     * Test de Repartidor: verifica nombre y vehículo, que la sobrecarga
     * entregar(Pedido) imprima la confirmación estándar y que
     * entregar(Pedido, String) imprima la nota del cliente y la confirmación
     * con instrucciones. Además, ambas versiones deben marcar el pedido como
     * ENTREGADO y lanzar IllegalStateException si el pedido ya fue entregado.
     */
    private static void testRepartidor() {
        System.out.println(">> Test de Repartidor");

        Repartidor juan = new Repartidor("Juan", "Moto");
        assertEquals("Juan", juan.getNombre(), "getNombre()");
        assertEquals("Moto", juan.getVehiculo(), "getVehiculo()");

        Pedido pedido = new Pedido("Ana Torres", "Av. Siempre Viva 123");
        pedido.agregarItem(new Item("Hamburguesa", 7700, 2));
        pedido.preparar();
        pedido.despachar();

        String salidaEstandar = capturarSalida(() -> juan.entregar(pedido));
        assertTrue(salidaEstandar.contains("Ana Torres"), "entregar(pedido) menciona al cliente");
        assertTrue(salidaEstandar.contains("Entrega estándar confirmada."),
                "entregar(pedido) confirma entrega estándar");
        assertEquals("ENTREGADO", pedido.getEstado(), "entregar(pedido) marca el pedido como ENTREGADO");
        assertThrows(IllegalStateException.class, () -> juan.entregar(pedido), "entregar() un pedido ya entregado");

        Pedido pedidoConNota = new Pedido("Ana Torres", "Av. Siempre Viva 123");
        pedidoConNota.agregarItem(new Item("Hamburguesa", 7700, 2));
        pedidoConNota.preparar();
        pedidoConNota.despachar();

        String nota = "Dejar en la puerta, no tocar el timbre";
        String salidaConNota = capturarSalida(() -> juan.entregar(pedidoConNota, nota));
        assertTrue(salidaConNota.contains("Ana Torres"), "entregar(pedido, nota) menciona al cliente");
        assertTrue(salidaConNota.contains("Nota del cliente"), "entregar(pedido, nota) imprime la nota");
        assertTrue(salidaConNota.contains(nota), "entregar(pedido, nota) incluye el texto de la nota");
        assertTrue(salidaConNota.contains("Entrega con instrucciones especiales confirmada."),
                "entregar(pedido, nota) confirma entrega con instrucciones");
        assertEquals("ENTREGADO", pedidoConNota.getEstado(), "entregar(pedido, nota) marca el pedido como ENTREGADO");

        System.out.println();
    }

    /*
     * Test de Restaurante: verifica que las listas inicien vacías, que
     * registrarPedido() y agregarRepartidor() no acepten duplicados, que
     * asignarRepartidor() rechace pedidos no registrados o repartidores
     * desconocidos, y que delegue la entrega al repartidor marcando el pedido
     * como ENTREGADO. Un segundo envío del mismo pedido debe lanzar
     * IllegalStateException.
     */
    private static void testRestaurante() {
        System.out.println(">> Test de Restaurante");

        Restaurante restaurante = new Restaurante();
        assertEquals(0, restaurante.getPedidos().size(), "getPedidos() inicia vacío");
        assertEquals(0, restaurante.getRepartidores().size(), "getRepartidores() inicia vacío");

        Pedido pedido = new Pedido("Ana Torres", "Av. Siempre Viva 123");
        pedido.agregarItem(new Item("Hamburguesa", 7700, 2));
        restaurante.registrarPedido(pedido);
        assertEquals(1, restaurante.getPedidos().size(), "getPedidos() tras registrar 1 pedido");
        assertEquals("EN_PREPARACION", pedido.getEstado(), "registrarPedido() prepara el pedido");

        assertThrows(IllegalArgumentException.class, () -> restaurante.registrarPedido(pedido),
                "registrarPedido() con pedido duplicado");

        Repartidor juan = new Repartidor("Juan", "Moto");
        restaurante.agregarRepartidor(juan);
        assertEquals(1, restaurante.getRepartidores().size(), "getRepartidores() tras agregar 1 repartidor");
        assertThrows(IllegalArgumentException.class, () -> restaurante.agregarRepartidor(juan),
                "agregarRepartidor() con repartidor duplicado");

        Repartidor desconocido = new Repartidor("Pedro", "Auto");
        assertThrows(IllegalArgumentException.class, () -> restaurante.asignarRepartidor(pedido, desconocido),
                "asignarRepartidor() con repartidor desconocido");

        Pedido noRegistrado = new Pedido("Cliente X", "Dirección X");
        noRegistrado.agregarItem(new Item("Bebida", 1000, 1));
        assertThrows(IllegalArgumentException.class, () -> restaurante.asignarRepartidor(noRegistrado, juan),
                "asignarRepartidor() con pedido no registrado");

        String salida = capturarSalida(() -> restaurante.asignarRepartidor(pedido, juan));
        assertTrue(salida.contains("Entrega estándar confirmada."), "asignarRepartidor delega a entregar(pedido)");
        assertEquals("ENTREGADO", pedido.getEstado(), "asignarRepartidor() entrega el pedido");
        assertThrows(IllegalStateException.class, () -> restaurante.asignarRepartidor(pedido, juan),
                "asignarRepartidor() con pedido ya entregado");

        System.out.println();
    }

    // Funciones para el Test
    // Si quieren las pueden leer, pero no influyen mucho en sus clases
    // Deje algunos comentarios si las quieren entender

    // Revisar si una condicion es verdadera
    private static void assertTrue(boolean condicion, String mensaje) {
        if (condicion) {
            pasados++;
            System.out.println("  [OK]    " + mensaje);
        } else {
            fallidos++;
            System.out.println("  [FALLO] " + mensaje);
        }
    }

    // Revisar si dos objetos son iguales
    private static void assertEquals(Object esperado, Object obtenido, String mensaje) {
        /*
         * esto es algo que se llama shorthand if-else
         * funciona asi:
         * boolean hola = (condicion) ? true : false;
         * transformado a if/else:
         *
         * if (condicion) {
         * hola = true;
         * } else {
         * hola = false;
         * }
         * hola puede ser cualquier tipo de dato:
         * String hola = edad > 18 ? "mayor de edad" : "menor de edad";
         */

        boolean iguales = esperado == null ? obtenido == null : esperado.equals(obtenido);
        if (iguales) {
            pasados++;
            System.out.println("  [OK]    " + mensaje + " -> " + esperado);
        } else {
            fallidos++;
            System.out.println("  [FALLO] " + mensaje + " -> esperado: " + esperado + ", obtenido: " + obtenido);
        }
    }

    // Revisar si el codigo entregado tira la excepcion que deberia.
    private static void assertThrows(Class<? extends Exception> excepcion, Runnable bloque, String mensaje) {
        /*
         * El codigo se envia mediante una funcion anonima, que lo han visto en GUI
         * Si tira cualquier excepcion, se atrapa en el catch
         */

        try {
            bloque.run();
            fallidos++;
            System.out.println("  [FALLO] " + mensaje + " -> no se lanzó " + excepcion.getSimpleName());
        } catch (Exception e) {
            if (excepcion.isInstance(e)) {
                pasados++;
                System.out.println("  [OK]    " + mensaje + " -> se lanzó " + e.getClass().getSimpleName());
            } else {
                fallidos++;
                System.out.println("  [FALLO] " + mensaje + " -> se lanzó " + e.getClass().getSimpleName()
                        + " en vez de " + excepcion.getSimpleName());
            }
        }
    }

    // Guarda los print de un codigo, y no los muestra en la consola.
    private static String capturarSalida(Runnable bloque) {
        /*
         * Esto es un poco complejo, asi que lo explicare de la forma mas simple posible
         *
         * La clase Scanner lee desde un "stream" de datos, como un File o System.in
         * System.in es cuando uno le pide al usuario que INgrese algo a la consola
         * System.out es la salida a la consola, desde ahi podemos "agarrar" los datos
         * antes de que salgan.
         * Lo que hace este codigo es tomar el "stream" directamente y guardarlo en una
         * clase, con el toString() podemos devolver lo que estaba dentro del
         * System.out.println();
         */

        PrintStream original = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));
        try {
            bloque.run();
        } finally {
            System.setOut(original);
        }
        return buffer.toString();
    }

}
