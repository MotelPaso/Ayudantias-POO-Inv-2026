import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Test {

    private static int pasados = 0;
    private static int fallidos = 0;

    public static void main(String[] args) {
        System.out.println("=== Tests — Simulación de Reparto de Comida ===\n");

        testItem();
        testPedido();
        testRepartidor();
        testRestaurante();

        System.out.println("\n=== Resumen ===");
        System.out.println("Pasados: " + pasados);
        System.out.println("Fallidos: " + fallidos);

        if (fallidos > 0) {
            System.exit(1);
        }
    }

    // ===== Helpers del harness =====

    private static void assertTrue(boolean condicion, String mensaje) {
        if (condicion) {
            pasados++;
            System.out.println("  [OK]    " + mensaje);
        } else {
            fallidos++;
            System.out.println("  [FALLO] " + mensaje);
        }
    }

    private static void assertEquals(Object esperado, Object obtenido, String mensaje) {
        boolean iguales = esperado == null ? obtenido == null : esperado.equals(obtenido);
        if (iguales) {
            pasados++;
            System.out.println("  [OK]    " + mensaje + " -> " + esperado);
        } else {
            fallidos++;
            System.out.println("  [FALLO] " + mensaje + " -> esperado: " + esperado + ", obtenido: " + obtenido);
        }
    }

    private static void assertThrows(Class<? extends Exception> excepcion, Runnable bloque, String mensaje) {
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

    private static String capturarSalida(Runnable bloque) {
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

    // ===== Tests =====

    private static void testItem() {
        System.out.println(">> Test de Item");

        Item hamburguesa = new Item("Hamburguesa", 7700, 2);
        assertEquals(15400.0, hamburguesa.calcularSubtotal(), "Subtotal de Hamburguesa x2");
        assertEquals("Hamburguesa", hamburguesa.getNombre(), "getNombre()");
        assertEquals(2, hamburguesa.getCantidad(), "getCantidad()");

        Item papas = new Item("Papas fritas", 2500, 1);
        assertEquals(2500.0, papas.calcularSubtotal(), "Subtotal de Papas fritas x1");

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

    private static void testPedido() {
        System.out.println(">> Test de Pedido");

        Pedido pedido = new Pedido("Ana Torres", "Av. Siempre Viva 123");
        assertEquals("Ana Torres", pedido.getCliente(), "getCliente()");
        assertEquals("Av. Siempre Viva 123", pedido.getDireccion(), "getDireccion()");

        assertThrows(IllegalStateException.class, pedido::calcularTotal, "calcularTotal() con pedido vacío");

        pedido.agregarItem(new Item("Hamburguesa", 7700, 2));
        pedido.agregarItem(new Item("Papas fritas", 2500, 1));
        assertEquals(17900.0, pedido.calcularTotal(), "calcularTotal() con 2 ítems");
        assertEquals(2, pedido.getItems().size(), "getItems() con 2 ítems");

        System.out.println();
    }

    private static void testRepartidor() {
        System.out.println(">> Test de Repartidor");

        Repartidor juan = new Repartidor("Juan", "Moto");
        assertEquals("Juan", juan.getNombre(), "getNombre()");
        assertEquals("Moto", juan.getVehiculo(), "getVehiculo()");

        Pedido pedido = new Pedido("Ana Torres", "Av. Siempre Viva 123");
        pedido.agregarItem(new Item("Hamburguesa", 7700, 2));

        String salidaEstandar = capturarSalida(() -> juan.entregar(pedido));
        assertTrue(salidaEstandar.contains("Ana Torres"), "entregar(pedido) menciona al cliente");
        assertTrue(salidaEstandar.contains("Entrega estándar confirmada."), "entregar(pedido) confirma entrega estándar");

        String nota = "Dejar en la puerta, no tocar el timbre";
        String salidaConNota = capturarSalida(() -> juan.entregar(pedido, nota));
        assertTrue(salidaConNota.contains("Ana Torres"), "entregar(pedido, nota) menciona al cliente");
        assertTrue(salidaConNota.contains("Nota del cliente"), "entregar(pedido, nota) imprime la nota");
        assertTrue(salidaConNota.contains(nota), "entregar(pedido, nota) incluye el texto de la nota");
        assertTrue(salidaConNota.contains("Entrega con instrucciones especiales confirmada."),
                "entregar(pedido, nota) confirma entrega con instrucciones");

        System.out.println();
    }

    private static void testRestaurante() {
        System.out.println(">> Test de Restaurante");

        Restaurante restaurante = new Restaurante();
        assertEquals(0, restaurante.getPedidos().size(), "getPedidos() inicia vacío");
        assertEquals(0, restaurante.getRepartidores().size(), "getRepartidores() inicia vacío");

        Pedido pedido = new Pedido("Ana Torres", "Av. Siempre Viva 123");
        pedido.agregarItem(new Item("Hamburguesa", 7700, 2));
        restaurante.registrarPedido(pedido);
        assertEquals(1, restaurante.getPedidos().size(), "getPedidos() tras registrar 1 pedido");

        Repartidor juan = new Repartidor("Juan", "Moto");
        restaurante.agregarRepartidor(juan);
        assertEquals(1, restaurante.getRepartidores().size(), "getRepartidores() tras agregar 1 repartidor");

        String salida = capturarSalida(() -> restaurante.asignarRepartidor(pedido, juan));
        assertTrue(salida.contains("Entrega estándar confirmada."), "asignarRepartidor delega a entregar(pedido)");

        System.out.println();
    }
}
