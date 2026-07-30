<h1 align='center'>Ayudantía 4 - POO Invierno</h1>
<h5 align='center'>Profesor: Cristhian Rabi<br> Ayudante: Paulo Araya</h5>
<h6 align='center'>30 de Julio de 2026</h6>

Se esta haciendo una investigación acerca del deshielo en los polos, tomando varios datos importantes usando microcontroladores.

Te han llamado a ti debido a que últimamente los microcontroladores se están quedando sin memoria debido a la cantidad de datos que se toman, estos están usando una version de Java muy antigua, así que no existen librerías como List.

Simularemos esto con archivos de texto que tienen las muestras de las diferentes mediciones que hacen los sensores. Existen dos archivos:

- **`muestras_diarias.txt`** -> contiene algunas muestras por sensor (simulando lecturas de pocos días).
- **`muestras_mensuales.txt`** -> contiene 30 muestras por sensor (simulando un mes completo de lecturas diarias).

```text title:"muestras_ejemplo.txt"
DiferenciaTemperatura,0.5,0.6,-1.2,0.4
Altura,250,249.1,248.3
Humedad,20,25,40,60,80,20
NivelCO2EnAire,40,20,10,5,2,1
```

Como la LinkedList era muy lenta para recorrer los datos, vamos a implementar una ArrayList, dejando espacios definidos dependiendo de la cantidad de datos y que se pueda acceder por indice.

En Campus Virtual está subida una plantilla que deben seguir para la ArrayList.

Todos sus métodos deben estar implementados con un correcto control de errores.

---
<h2 align='center'>Restricciones </h2>

- Solo se puede usar la ArrayList implementada, arreglos fijos solo para leer los datos del archivo.
- Pueden hacer más clases si lo ven necesario.

```text title:"Ejemplo de salida:"
============ MENÚ PRINCIPAL ============
1. Cargar muestras diarias
2. Cargar muestras mensuales
3. Salir
========================================
R: X
// opcion 1
=== Datos de Sensores Diarios ===

4. DiferenciaTemperatura [12 muestras]:
   0.5 -> 0.6 -> -1.2 -> 0.4 -> -0.3 -> 0.8 -> 1.1 -> -0.7 -> 0.2 -> 0.0 -> -0.5 -> 0.9
   Min: -1.2 | Max: 1.1 | Promedio: 0.067

5. Altura [12 muestras]:
   250.0 -> 249.1 -> 248.3 -> 247.8 -> 246.5 -> 245.2 -> 244.0 -> 243.1 -> 242.5 -> 241.9 -> 241.0 -> 240.2
   Min: 240.2 | Max: 250.0 | Promedio: 244.97

6. Humedad [17 muestras]:
   20 -> 25 -> 40 -> 60 -> 80 -> 20 -> 35 -> 55 -> 70 -> 90 -> 45 -> 30 -> 50 -> 65 -> 85 -> 15 -> 10
   Min: 10 | Max: 90 | Promedio: 45.29

7. NivelCO2EnAire [12 muestras]:
   40.0 -> 20.0 -> 10.0 -> 5.0 -> 2.0 -> 1.0 -> 0.5 -> 0.3 -> 0.2 -> 0.1 -> 0.05 -> 0.02
   Min: 0.02 | Max: 40.0 | Promedio: 6.60

8. PresionAtmosferica [8 muestras]:
   1013 -> 1012 -> 1011 -> 1010 -> 1008 -> 1005 -> 1003 -> 1000
   Min: 1000 | Max: 1013 | Promedio: 1007.75

9. RadiacionUV [10 muestras]:
   3.2 -> 4.1 -> 5.0 -> 6.3 -> 7.8 -> 8.2 -> 7.5 -> 6.0 -> 4.5 -> 3.0
   Min: 3.0 | Max: 8.2 | Promedio: 5.56

10. VelocidadViento [12 muestras]:
   12 -> 15 -> 20 -> 25 -> 30 -> 28 -> 22 -> 18 -> 14 -> 10 -> 8 -> 5
   Min: 5 | Max: 30 | Promedio: 17.25

=== Fin del reporte ===
// Opcion 2
=== Datos de Sensores Mensuales ===

1. DiferenciaTemperatura [30 muestras]:
   0.5 -> 0.6 -> -1.2 -> 0.4 -> -0.3 -> 0.8 -> 1.1 -> -0.7 -> 0.2 -> 0.0 -> -0.5 -> 0.9 -> 1.3 -> -0.1 -> 0.7 -> -0.8 -> 0.3 -> 1.0 -> -0.4 -> 0.6 -> -1.0 -> 0.5 -> 0.1 -> -0.6 -> 0.8 -> -0.2 -> 0.4 -> -0.9 -> 1.2 -> -0.3
   Min: -1.2 | Max: 1.3 | Promedio: 0.127

2. Altura [30 muestras]:
   250.0 -> 249.1 -> 248.3 -> 247.8 -> 246.5 -> 245.2 -> 244.0 -> 243.1 -> 242.5 -> 241.9 -> 241.0 -> 240.2 -> 239.5 -> 238.8 -> 238.0 -> 237.3 -> 236.7 -> 236.0 -> 235.4 -> 234.8 -> 234.1 -> 233.5 -> 232.9 -> 232.2 -> 231.6 -> 231.0 -> 230.4 -> 229.8 -> 229.2 -> 228.6
   Min: 228.6 | Max: 250.0 | Promedio: 238.3

3. Humedad [30 muestras]:
   20 -> 25 -> 40 -> 60 -> 80 -> 20 -> 35 -> 55 -> 70 -> 90 -> 45 -> 30 -> 50 -> 65 -> 85 -> 15 -> 10 -> 22 -> 38 -> 58 -> 75 -> 88 -> 42 -> 28 -> 48 -> 62 -> 82 -> 18 -> 12 -> 33
   Min: 10 | Max: 90 | Promedio: 44.8

4. NivelCO2EnAire [30 muestras]:
   40.0 -> 20.0 -> 10.0 -> 5.0 -> 2.0 -> 1.0 -> 0.5 -> 0.3 -> 0.2 -> 0.1 -> 0.05 -> 0.02 -> 0.01 -> 0.008 -> 0.005 -> 0.003 -> 0.002 -> 0.001 -> 0.0008 -> 0.0005 -> 0.0003 -> 0.0002 -> 0.0001 -> 0.00008 -> 0.00005 -> 0.00003 -> 0.00002 -> 0.00001 -> 0.000008 -> 0.000005
   Min: 5.0E-6 | Max: 40.0 | Promedio: 2.643

5. PresionAtmosferica [30 muestras]:
   1013 -> 1012 -> 1011 -> 1010 -> 1008 -> 1005 -> 1003 -> 1000 -> 998 -> 995 -> 993 -> 990 -> 992 -> 994 -> 996 -> 998 -> 1000 -> 1002 -> 1004 -> 1006 -> 1008 -> 1010 -> 1011 -> 1012 -> 1013 -> 1012 -> 1011 -> 1010 -> 1009 -> 1008
   Min: 990 | Max: 1013 | Promedio: 1004.5

6. RadiacionUV [30 muestras]:
   3.2 -> 4.1 -> 5.0 -> 6.3 -> 7.8 -> 8.2 -> 7.5 -> 6.0 -> 4.5 -> 3.0 -> 2.1 -> 1.8 -> 2.5 -> 3.8 -> 5.5 -> 7.0 -> 8.5 -> 9.0 -> 8.0 -> 6.5 -> 4.8 -> 3.2 -> 2.0 -> 1.5 -> 2.2 -> 3.5 -> 5.2 -> 6.8 -> 7.2 -> 6.0
   Min: 1.5 | Max: 9.0 | Promedio: 5.12

7. VelocidadViento [30 muestras]:
   12 -> 15 -> 20 -> 25 -> 30 -> 28 -> 22 -> 18 -> 14 -> 10 -> 8 -> 5 -> 7 -> 11 -> 16 -> 22 -> 27 -> 32 -> 30 -> 25 -> 20 -> 15 -> 10 -> 6 -> 9 -> 13 -> 18 -> 24 -> 28 -> 26
   Min: 5 | Max: 32 | Promedio: 18.2

=== Fin del reporte ===
```
