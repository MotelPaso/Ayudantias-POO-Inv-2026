## Solicitud de Desarrollo

Requerimos el desarrollo de un sistema que permita administrar los distintos productos de cuenta que ofrece el banco. Actualmente cada tipo de cuenta se gestiona de forma independiente, lo que ha generado errores operativos y duplicación de trabajo entre equipos. Se busca una solución centralizada que permita gestionar la totalidad de las cuentas del banco, independientemente de su tipo.

Actualmente el banco ofrece cuatro productos: Cuenta de Ahorro, Cuenta Corriente, Cuenta Vista y Depósito a Plazo. A continuación se detallan los requerimientos de cada uno.

Toda cuenta debe contar con un número, un titular y un saldo. El número de cuenta se asigna al momento de la apertura y no debe poder modificarse posteriormente. El sistema debe permitir depositar y retirar dinero de cada cuenta, así como consultar en cualquier momento el estado de una cuenta determinada.

### Cuenta de Ahorro

Genera intereses mensuales calculados dependiendo del saldo disponible. No debe permitirse que el saldo quede en negativo bajo ninguna circunstancia. Tiene una capacidad maxima de 50M$, si se quiere ingresar más, debería ser un deposito a plazo.

Los intereses quedan definidos en la siguiente tabla:

|Nivel|Saldo Disponible|Tasa Mensual|
|---|---|---|
|A|≥ 10.000.000$|1,5%|
|B|1.000.000$ - 9.999.999$|1,0%|
|C|< 1.000.000$|0,5%|

### Cuenta Corriente

Los clientes de cuenta corriente cuentan con acceso a un sobregiro equivalente al 20% de la capacidad máxima de la cuenta. Por este beneficio, se cobra una comisión fija de 0.2 UF.

La capacidad máxima de la cuenta se determina según el nivel socioeconómico del cliente, visto en la siguiente tabla:

|Nivel|Capacidad Máxima|
|---|---|
|100 - 80|100M$|
|79 - 60|40M$|
|50 - 40|20M$|
|< 40|4M$|

### Cuenta Vista

Corresponde al producto más simple del banco, orientado al uso diario. No presenta restricciones especiales para depósitos ni retiros, no genera intereses ni cobra comisiones. Cuenta con una capacidad máxima de 2.000.000$ CLP.

### Depósito a Plazo

Al abrir un depósito a plazo, el cliente se compromete a mantener sus fondos inmovilizados durante un período determinado, durante el cual no se permite el retiro de dinero. Como compensación por este compromiso, la tasa de interés mensual ofrecida es superior a la de la Cuenta de Ahorro.

Los intereses quedan definidos en la siguiente tabla:


|Plazo|Tasa Mensual|Monto Mínimo|
|---|---|---|
|30 días|1,8%|500.000$|
|90 días|2,2%|1.000.000$|
|180 días|2,8%|2.000.000$|


### Gestión del Conjunto de Cuentas

Como cada cuenta tiene diferentes requisitos, se espera poder revisar cada cuenta a detalle, mostrando los datos del titular y los costos/ganancias futuras de su cuenta.

El sistema creado debe permitir la creación y eliminación de cuentas, simular depósitos y retiros, consultar el estado de las cuentas registradas por su numero y mostrar ganancias y gastos mensuales de cada cuenta.