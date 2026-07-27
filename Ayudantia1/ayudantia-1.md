<h1 align='center'>Ayudantía 1  - POO Invierno</h1>
<h5 align='center'>Profesor: Cristhian Rabi<br>  Ayudante: Paulo Araya</h5>
<h6 align='center'>27 de Julio de 2026</h6>

Se tienen los archivos `salaX.txt`, que contiene un mapa de los asientos de una sala de cine, donde **0** es un asiento vacío y **1** es un asiento ocupado. Los valores están separados por un espacio.

```text title:"Archivo de Ejemplo"
0 0 0 1 0 0 1
1 0 0 1 0 0 1
1 1 1 0 0 0 0
0 1 0 1 0 0 0
```
Escribe un programa en Java que permita elegir uno de los mapas y reporte los siguientes datos:

1. Cantidad y porcentaje de asientos ocupados.
2. La fila con mayor cantidad de asientos desocupados.
3. Buscar cualquier fila con N asientos desocupados consecutivos.
> Por ejemplo, si pido una fila con 4 asientos desocupados, debería mostrar la fila 3 del ejemplo, la fila 4 no son consecutivos así que no se muestra.
4. Mostrar el mapa completo de forma estética, que no se pueda ver ningún numero.
> Puedes mostrarlo como tú decidas, pero debe existir una diferencia notable entre vacío, ocupado y elegido.

Finalmente, se debe preguntar si se quiere reservar un asiento. Esto debe verse reflejado en el .txt elegido inicialmente y mostrado para confirmar antes de ser escrito.
<h3 align='center'>Restricciones </h3>

- Deben usar un mínimo de 5 funciones. Ninguna función debe sobrepasar las 20 lineas.
- Las salas tienen diferentes tamaños.
- Deben aplicar control de errores, si existe un error **no** se debe detener el programa.
- No se puede usar la librería List. Unicamente arreglos fijos.
- No se pueden crear nuevas clases.

```text title:"Ejemplo de salida"
=== Sistema de Reserva de Asientos ===
Salas disponibles: sala1.txt, sala2.txt, sala3.txt 
Ingrese el numero de sala a cargar: 1

Cargando sala1.txt...
Sala 1 Cargada!
Menu:
1. Mostrar resumen de ocupación.
2. Buscar asientos para grupo.
3. Reservar un asiento.
Opcion: X

// Opcion 1
--- Resumen de Ocupación ---
Mapa de la sala:
x 1 2 3 4 5 6 7
1 . . . # . . #
2 # . . # . . #
3 # # # . . . .
4 . # . # . . .

Asientos ocupados: 10 de 28 (35.7%)
Fila con más asientos disponibles: Fila 1 (5 disponibles)
// Opcion 2

Buscando bloque de N asientos consecutivos vacios...
Bloque encontrado en Fila 3, comenzando en el asiento 4.

// Opcion 3
Mapa de la sala:
x 1 2 3 4 5 6 7
1 . . . # . . #
2 # . . # . . #
3 # # # . . . .
4 . # . # . . .

¿Desea reservar un asiento? (s/n): s
Ingrese fila (1-4): 3
Ingrese asiento (1-7): 5

Mapa actualizado:
x 1 2 3 4 5 6 7
1 . . . # . . #
2 # . . # . . #
3 # # # . X . .
4 . # . # . . .
Confirma su eleccion? (s/n):
Asiento reservado exitosamente.
Guardando cambios en sala1.txt...
Cambios guardados correctamente.
// volver al menu
```