<h1 align='center'>Ayudantía 5- POO Invierno</h1>
<h5 align='center'>Profesor: Cristhian Rabi<br> Ayudante: Paulo Araya</h5>
<h6 align='center'>31 de Julio de 2026</h6>

## Contexto

Un restaurante quiere digitalizar su sistema de pedidos y entregas. no hay forma de registrar qué repartidor entregó qué pedido ni si el cliente dejó alguna instrucción especial (por ejemplo, "dejar en la puerta" o "tocar el timbre dos veces").

Haremos una simulación usando el `Test.java` subido a Campus Virtual, deben lograr que el código se ejecute sin errores y pase todas las pruebas necesarias.
Toda la lógica importante estará encapsulada en las clases, no puedes editar `Test.java`.

Deben hacer 4 clases:
- Item
- Pedido
- Repartidor
- Restaurante

Sus atributos estarán definidos en el archivo de prueba.

### 1. `Item`

Representa un producto dentro de un pedido.

| Atributo   | Tipo     | Validación                 |
| ---------- | -------- | -------------------------- |
| `nombre`   | `String` | no puede ser nulo ni vacío |
| `precio`   | `double` | debe ser mayor a 0         |
| `cantidad` | `int`    | debe ser mayor a 0         |

- Método `calcularSubtotal()`: retorna `precio * cantidad`.

### 2. `Pedido`

Agrupa una lista de ítems para un cliente.

|Atributo|Tipo|
|---|---|
|`cliente`|`String`|
|`direccion`|`String`|
|`items`|`List<Item>`|

- `agregarItem(Item i)`: agrega un ítem a la lista.
- `calcularTotal()`: recorre `items` y suma todos los subtotales.
    
    > Si `items` está vacío al llamar `calcularTotal()`, se debe lanzar una `IllegalStateException` — no tiene sentido calcular el total de un pedido sin productos.
    

### 3. `Repartidor`

Representa a la persona que entrega el pedido.

|Atributo|Tipo|
|---|---|
|`nombre`|`String`|
|`vehiculo`|`String`|

- **Sobrecarga obligatoria** del método `entregar`:
    - `entregar(Pedido p)` → entrega estándar, imprime un mensaje genérico de confirmación.
    - `entregar(Pedido p, String notaCliente)` → misma entrega, pero incorporando la nota del cliente en el mensaje (por ejemplo, instrucciones de dónde dejar el pedido).

### 4. `Restaurante`

Coordina pedidos y repartidores.

|Atributo|Tipo|
|---|---|
|`pedidos`|`List<Pedido>`|
|`repartidores`|`List<Repartidor>`|

- `registrarPedido(Pedido p)`: agrega un pedido a la lista.
- `agregarRepartidor(Repartidor r)`: agrega un repartidor a la lista.
- `asignarRepartidor(Pedido p, Repartidor r)`: el `Restaurante` **no calcula ni entrega nada directamente** — simplemente le pide al `Repartidor` que entregue el `Pedido` (usando la sobrecarga que corresponda).

---

## Tareas

1. Implementa la clase `Item` con sus atributos privados, constructor, getters/setters con validación, y `calcularSubtotal()`.
2. Implementa la clase `Pedido`, incluyendo el manejo de la lista de ítems y `calcularTotal()` con su respectiva excepción.
3. Implementa la clase `Repartidor`, incluyendo **ambas** versiones sobrecargadas de `entregar(...)`.
4. Implementa la clase `Restaurante`, incluyendo el registro de pedidos/repartidores y `asignarRepartidor(...)`.

---

## Restricciones

- Puedes usar la librería List.
- Debe existir un control de errores, como ejemplo: puedes usar`IllegalArgumentException` para datos invalidos de Item, como precio negativo.

---

## Ejemplo de salida

```
=== Simulación de Reparto de Comida ===

Pedido creado para: Ana Torres
  - Agregado: Hamburguesa x2
  - Agregado: Papas fritas x1

Total del pedido: $15400.0

>> Intentando crear un ítem inválido...
Error al crear ítem: El precio debe ser mayor a 0

Repartidor Juan (Moto) entregando pedido de Ana Torres...
Entrega estándar confirmada.

Repartidor Juan (Moto) entregando pedido de Ana Torres...
Nota del cliente: "Dejar en la puerta, no tocar el timbre"
Entrega con instrucciones especiales confirmada.

>> Intentando calcular total de un pedido vacío...
Error: No se puede calcular el total de un pedido sin ítems.

=== Fin de la simulación ===
```
