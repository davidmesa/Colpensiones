# CASO I. 
## Manejo de la concurrencia 
Como se mencionó en el enunciado, la aplicación de Historia Laboral recibe múltiples consultas y debe tener 
un buen tiempo de respuesta puesto que estos se refleja en una eficiente atención al público. 
Las consultas consisten en un número de cédula, y la aplicación debe retornar la historia laboral (secuencia 
de los empleos que ha tenido la persona, y cada empleo se describe con un rangos de años, NIT del 
empleador y monto pagado). Sin embargo, en este caso, no tendremos en cuenta el contenido de esos 
mensajes, puesto que deseamos concentrarnos en el esquema de concurrencia. Para probar el programa, 
las consultas pueden consistir en números generados en secuencia, o al azar, y la respuesta en este número 
incrementado en uno. 

## Objetivo 
Diseñar un mecanismo de comunicación para manejar las consultas de múltiples clientes sobre la aplicación 
de historia laboral. Para este caso, los clientes y el servidor serán threads en la misma máquina (en casos 
posteriores se implementará como un esquema distribuido; este es solo un prototipo). 
El proyecto debe ser realizado en java, usando threads. Para la sincronización solo pueden usar las 
funcionalidades básicas de Java; synchronized, wait, notify y notifyAll. 

## Funcionamiento 
Cada thread cliente hace un cierto número de consultas y termina. El número de clientes y el número de 
mensajes que envía cada uno deben ser un número arbitrario (para cada cliente se debe generar un número 
particular de mensajes enviados). Para cada mensaje, el thread cliente debe generar un objeto de tipo 
Mensaje e inicializarlo, después debe decirle que se envíe (el mensaje se encarga de comunicarse con el 
buffer para enviarse). Cuando termine, el cliente le debe avisar al buffer que se retira. 
El servidor, por su lado, estará compuesto por varios threads para poder atender múltiples consultas 
simultáneamente. Estos threads deben terminar cuando no haya más clientes. Los threads servidores 
estarán continuamente solicitando mensajes al buffer y respondiendo las respectivas consultas (responder 
consiste en incrementar el valor del mensaje y avisarle al cliente que puede continuar). El número de 
servidores también debe ser un número arbitrario. 

## Diseño
En el sistema tendremos: clientes, servidores, buffer y mensajes. Los clientes y servidores son los antes 
descritos. 

En cuanto al buffer, es un sitio donde los clientes almacenan los mensajes para que sean recogidos por los 
servidores; este buffer debe tener una cierta capacidad limitada, y funcionar en esquema productor-consumidor. El buffer será no bloqueante: cuando recibe una solicitud para almacenar un mensaje, mira si hay espacio; si hay, lo almacena y retorna true; si no hay, retorna false. Cuando recibe una solicitud para retirar un mensaje, mira si no está vacío; si hay un mensaje lo retorna, si no, retorna null.

Por su parte, los mensajes son objetos con la consulta que hace el cliente, y donde el servidor deja la 
respuesta. 

El funcionamiento es el siguiente: un cliente genera un mensaje, y este intenta depositarse en el buffer; si 
no es posible vuelve a intentar. Cuando logra depositar el mensaje, el cliente debe quedarse dormido en el 
mensaje a la espera de la respuesta del servidor. 

Cada servidor, por su parte, está continuamente intentando retirar mensajes del buffer; si está vacío, vuelve 
a intentar. Una vez retirado el mensaje, genera una respuesta, y procede a despertar al cliente que se 
encuentra a la espera en el mensaje. El servidor debe revisar si todavía hay clientes o no, en caso de que no 
haya, debe terminar. 



Note que el cliente no se comunica directamente con el buffer, sino con el mensaje, y es este último quien 
se comunica con el buffer. 


