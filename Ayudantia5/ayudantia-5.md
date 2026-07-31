<h1 align='center'>Ayudantía 5 - POO Invierno</h1>
<h5 align='center'>Profesor: Cristhian Rabi<br> Ayudante: Paulo Araya</h5>
<h6 align='center'>31 de Julio de 2026</h6>

## Contexto

Un restaurante quiere digitalizar su sistema de pedidos y entregas. no hay forma de registrar qué repartidor entregó qué pedido ni si el cliente dejó alguna instrucción especial.

Haremos una simulación usando el `Test.java` subido a Campus Virtual, deben lograr que el código se ejecute sin errores y pase todas las pruebas necesarias.

Toda la lógica importante debe estar encapsulada en las clases, no puedes editar los métodos ni crear nuevos en `Test.java`.

Deben hacer 4 clases:
- Item
	- Tiene nombre, precio y cantidad.
	- `calcularSubtotal()` retorna `precio * cantidad`.
- Pedido
	- Tiene cliente, dirección y los Items a enviar, con un máximo de 5.
	- Lleva un `estado`: `PENDIENTE` → `EN_PREPARACION` → `EN_REPARTO` → `ENTREGADO`, con métodos `preparar()`, `despachar()` y `marcarEntregado()`. Las transiciones inválidas deben lanzar `IllegalStateException`.
- Repartidor
	- Tiene nombre y vehículo.
	- `entregar(Pedido p)` y `entregar(Pedido p, String nota)` imprimen la confirmación correspondiente y marcan el pedido como `ENTREGADO`.
- Restaurante
	- Tiene pedidos y repartidores.
	- No registra pedidos ni repartidores duplicados, y solo asigna pedidos y repartidores que le pertenecen.
	- `asignarRepartidor(Pedido, Repartidor)` coordina la entrega delegándola al repartidor.

Pueden revisar los ejemplos de salida para más información acerca de los métodos que deberían tener las clases.

Al tener todos los "tests" pasados, deberán crear un diagrama de clases.

---
<h2 align='center'>Restricciones </h2>

- Puedes usar ArrayList.
- Debe existir un control de errores, puedes usar `IllegalArgumentException` para datos inválidos de Item o `IllegalStateException` para estados inválidos del pedido o límites excedidos.


## Ejemplos de salida


```text title:"Tests Pasados"

=== Tests — Simulación de Reparto de Comida ===

>> Test de Item
  [OK]    Subtotal de Hamburguesa x2 -> 15400.0
  [OK]    getNombre() -> Hamburguesa
  [OK]    getCantidad() -> 2
  [OK]    Subtotal de Papas fritas x1 -> 2500.0
  [OK]    Item con nombre null -> se lanzó IllegalArgumentException
  [OK]    Item con nombre vacío -> se lanzó IllegalArgumentException
  [OK]    Item con precio 0 -> se lanzó IllegalArgumentException
  [OK]    Item con precio negativo -> se lanzó IllegalArgumentException
  [OK]    Item con cantidad 0 -> se lanzó IllegalArgumentException
  [OK]    Item con cantidad negativa -> se lanzó IllegalArgumentException
  [OK]    setNombre() con valor vacío -> se lanzó IllegalArgumentException
  [OK]    setPrecio() con valor negativo -> se lanzó IllegalArgumentException
  [OK]    setCantidad() con valor 0 -> se lanzó IllegalArgumentException

>> Test de Pedido
  [OK]    getCliente() -> Ana Torres
  [OK]    getDireccion() -> Av. Siempre Viva 123
  [OK]    getEstado() inicia en PENDIENTE -> PENDIENTE
  [OK]    calcularTotal() con pedido vacío -> se lanzó IllegalStateException
  [OK]    calcularTotal() con 2 ítems -> 17900.0
  [OK]    getItems() con 2 ítems -> 2
  [OK]    getItems() al límite de 5 -> 5
  [OK]    agregarItem() superando el límite de 5 -> se lanzó IllegalStateException
  [OK]    getPropina() inicia en 0 -> 0.0
  [OK]    getPropina() tras setPropina(10) -> 10.0
  [OK]    calcularTotalConPropina() con 10% de propina -> 19690.0
  [OK]    setPropina() con valor negativo -> se lanzó IllegalArgumentException

>> Test de Estado del Pedido
  [OK]    Estado inicial de un pedido -> PENDIENTE
  [OK]    Estado tras preparar() -> EN_PREPARACION
  [OK]    Estado tras despachar() -> EN_REPARTO
  [OK]    Estado tras marcarEntregado() -> ENTREGADO
  [OK]    despachar() desde PENDIENTE -> se lanzó IllegalStateException
  [OK]    marcarEntregado() desde PENDIENTE -> se lanzó IllegalStateException
  [OK]    preparar() dos veces -> se lanzó IllegalStateException
  [OK]    marcarEntregado() desde EN_PREPARACION -> se lanzó IllegalStateException
  [OK]    preparar() desde ENTREGADO -> se lanzó IllegalStateException
  [OK]    despachar() desde ENTREGADO -> se lanzó IllegalStateException
  [OK]    marcarEntregado() dos veces -> se lanzó IllegalStateException

>> Test de Repartidor
  [OK]    getNombre() -> Juan
  [OK]    getVehiculo() -> Moto
  [OK]    entregar(pedido) menciona al cliente
  [OK]    entregar(pedido) confirma entrega estándar
  [OK]    entregar(pedido) marca el pedido como ENTREGADO -> ENTREGADO
  [OK]    entregar() un pedido ya entregado -> se lanzó IllegalStateException
  [OK]    entregar(pedido, nota) menciona al cliente
  [OK]    entregar(pedido, nota) imprime la nota
  [OK]    entregar(pedido, nota) incluye el texto de la nota
  [OK]    entregar(pedido, nota) confirma entrega con instrucciones
  [OK]    entregar(pedido, nota) marca el pedido como ENTREGADO -> ENTREGADO

>> Test de Restaurante
  [OK]    getPedidos() inicia vacío -> 0
  [OK]    getRepartidores() inicia vacío -> 0
  [OK]    getPedidos() tras registrar 1 pedido -> 1
  [OK]    registrarPedido() prepara el pedido -> EN_PREPARACION
  [OK]    registrarPedido() con pedido duplicado -> se lanzó IllegalArgumentException
  [OK]    getRepartidores() tras agregar 1 repartidor -> 1
  [OK]    agregarRepartidor() con repartidor duplicado -> se lanzó IllegalArgumentException
  [OK]    asignarRepartidor() con repartidor desconocido -> se lanzó IllegalArgumentException
  [OK]    asignarRepartidor() con pedido no registrado -> se lanzó IllegalArgumentException
  [OK]    asignarRepartidor delega a entregar(pedido)
  [OK]    asignarRepartidor() entrega el pedido -> ENTREGADO
  [OK]    asignarRepartidor() con pedido ya entregado -> se lanzó IllegalStateException


=== Resumen ===
Pasados: 59
Fallidos: 0
```

  

```text title:"Ejemplo de errores"

=== Tests — Simulación de Reparto de Comida ===

>> Test de Item

[FALLO] Subtotal de Hamburguesa x2 -> esperado: 15400.0, obtenido: 1925.0
[FALLO] getNombre() -> esperado: Hamburguesa, obtenido: Hamburguesa!
[FALLO] getCantidad() -> esperado: 2, obtenido: 4
[FALLO] Subtotal de Papas fritas x1 -> esperado: 2500.0, obtenido: 1250.0
[OK] Item con nombre null -> se lanzó IllegalArgumentException
[OK] Item con nombre vacío -> se lanzó IllegalArgumentException
[FALLO] Item con precio 0 -> no se lanzó IllegalArgumentException
[OK] Item con precio negativo -> se lanzó IllegalArgumentException
[FALLO] Item con cantidad 0 -> no se lanzó IllegalArgumentException
[OK] Item con cantidad negativa -> se lanzó IllegalArgumentException
[OK] setNombre() con valor vacío -> se lanzó IllegalArgumentException
[OK] setPrecio() con valor negativo -> se lanzó IllegalArgumentException
[FALLO] setCantidad() con valor 0 -> no se lanzó IllegalArgumentException
  
>> Test de Pedido
[OK] getCliente() -> Ana Torres
[OK] getDireccion() -> Av. Siempre Viva 123
[OK] getEstado() inicia en PENDIENTE -> PENDIENTE
[FALLO] calcularTotal() con pedido vacío -> no se lanzó IllegalStateException
[FALLO] calcularTotal() con 2 ítems -> esperado: 17900.0, obtenido: 3175.0
[OK] getItems() con 2 ítems -> 2
[OK] getItems() al límite de 5 -> 5
[OK] agregarItem() superando el límite de 5 -> se lanzó IllegalStateException
[OK] getPropina() inicia en 0 -> 0.0
[OK] getPropina() tras setPropina(10) -> 10.0
[FALLO] calcularTotalConPropina() con 10% de propina -> esperado: 19690.0, obtenido: 3492.5000000000005
[OK] setPropina() con valor negativo -> se lanzó IllegalArgumentException

>> Test de Estado del Pedido
[OK] Estado inicial de un pedido -> PENDIENTE
[OK] Estado tras preparar() -> EN_PREPARACION
[OK] Estado tras despachar() -> EN_REPARTO
[OK] Estado tras marcarEntregado() -> ENTREGADO
[FALLO] despachar() desde PENDIENTE -> no se lanzó IllegalStateException
[FALLO] marcarEntregado() desde PENDIENTE -> no se lanzó IllegalStateException
[FALLO] preparar() dos veces -> no se lanzó IllegalStateException
[FALLO] marcarEntregado() desde EN_PREPARACION -> no se lanzó IllegalStateException
[FALLO] preparar() desde ENTREGADO -> no se lanzó IllegalStateException
[FALLO] despachar() desde ENTREGADO -> no se lanzó IllegalStateException
[FALLO] marcarEntregado() dos veces -> no se lanzó IllegalStateException

>> Test de Repartidor
[FALLO] getNombre() -> esperado: Juan, obtenido: Juan!
[FALLO] getVehiculo() -> esperado: Moto, obtenido: Moto!
[OK] entregar(pedido) menciona al cliente
[FALLO] entregar(pedido) confirma entrega estándar
[FALLO] entregar(pedido) marca el pedido como ENTREGADO -> esperado: ENTREGADO, obtenido: EN_REPARTO
Repartidor Juan! (Moto!) entregando pedido de Ana Torres...
Entrega estándar confirmada
[FALLO] entregar() un pedido ya entregado -> no se lanzó IllegalStateException
[OK] entregar(pedido, nota) menciona al cliente
[FALLO] entregar(pedido, nota) imprime la nota
[FALLO] entregar(pedido, nota) incluye el texto de la nota
[FALLO] entregar(pedido, nota) confirma entrega con instrucciones
[FALLO] entregar(pedido, nota) marca el pedido como ENTREGADO -> esperado: ENTREGADO, obtenido: EN_REPARTO

>> Test de Restaurante
[OK] getPedidos() inicia vacío -> 0
[OK] getRepartidores() inicia vacío -> 0
[OK] getPedidos() tras registrar 1 pedido -> 1
[OK] registrarPedido() prepara el pedido -> EN_PREPARACION
[OK] registrarPedido() con pedido duplicado -> se lanzó IllegalArgumentException
[OK] getRepartidores() tras agregar 1 repartidor -> 1
[OK] agregarRepartidor() con repartidor duplicado -> se lanzó IllegalArgumentException
[OK] asignarRepartidor() con repartidor desconocido -> se lanzó IllegalArgumentException
[OK] asignarRepartidor() con pedido no registrado -> se lanzó IllegalArgumentException
[FALLO] asignarRepartidor delega a entregar(pedido)
[FALLO] asignarRepartidor() entrega el pedido -> esperado: ENTREGADO, obtenido: EN_REPARTO
[FALLO] asignarRepartidor() con pedido ya entregado -> no se lanzó IllegalStateException

=== Resumen ===
Pasados: 30
Fallidos: 29

```