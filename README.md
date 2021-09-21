# Sistema De Reserva De Habitacion
Es un sistema en donde se realizan registro de reservas, empleados, huéspedes y habitaciones, diseñado para que lo gestionen de manera intuitiva.

### Proyecto Java Web con Jsp (Java Persistence API), Jpa (Java Server Pages ), capa Lógica, Persistencia y Servlet.


## Escenario

Un prestigioso hotel desea llevar a cabo un sistema para la informatización de las reservas de sus diferentes habitaciones que tiene disponibles.
Cada habitación cuenta con un número identificatorio, el piso donde se encuentra, un nombre según su temática, el tipo (single, doble, triple o múltiple) y el precio por noche.

Para poder realizar una reserva de una habitación es necesario contar con la fecha de check-in (ingreso al hotel) y fecha de check-out (egreso del hotel). Antes de confirmar una reserva, el sistema deberá verificar que la habitación solicitada no esté reservada en el período de tiempo que se haya elegido. Por ejemplo, si la habitación está reservada del 14/07 al 21/07 y la nueva reserva es del 12/07 al 15/07 no deberá permitir realizar la misma, dado que la fecha de check-out interfiere con fechas en la que se encuentra ocupada la habitación.

A partir de la cantidad de días que se hospede la persona y del tipo de habitación que haya elegido, el sistema deberá devolver el monto total de la estadía; al mismo tiempo, se deberá verificar que la cantidad de personas que se hospedarán no supere la cantidad establecida por el tipo de habitación.

Además de verificar las fechas de reservación, la cantidad de personas a hospedarse, etc, es necesario registrar los datos del huésped que se hará cargo de la reserva. Entre estos datos es necesario solicitar: dni, nombre, apellido, fecha de nacimiento, dirección y profesión. Por cuestiones de privacidad del hotel, no se registrarán los datos de los acompañantes.

El sistema está pensado para ser utilizado únicamente por los EMPLEADOS del hotel, por lo que la interfaz gráfica y la utilización debe ser pensada de esta manera (no como un sistema de reservas online).

Para la administración de los empleados, cada uno de ellos contará con un usuario y contraseña que le permitirá ingresar al sistema; para ello, cada uno de ellos deberá de estar registrado en el sistema con sus correspondientes datos personales (dni, nombre, apellido, fecha de nacimiento, dirección y cargo). Cabe destacar que los empleados reciben bonificaciones monetarias por cantidad de reservas realizadas por día, por lo que cada reserva debe tener asignada el usuario que la dio de alta.

### Por otra parte, el sistema deberá poder permitir visualizar las siguientes consultas/informes:

  a. Todas las reservas realizadas en un determinado día.
  b. Todos los huéspedes registrados en el sistema.
  c. Lista de las reservas realizadas por un determinado empleado.
  d. Listas de todas las reservas realizadas por un determinado huésped en un período desde/hasta.

## Consideraciones

Se deberá presentar:
  - Diagrama de clases del modelo de datos de la aplicación a desarrollar (en formato digital).
  - Archivos de código fuente de la aplicación web.
  - Especificación de la configuración de la base de datos (usuarios, contraseñas, etc para poder replicar).
  - Documento de supuestos: Un documento con todas las suposiciones que tuvo el analista/programador. Por ejemplo, se supone que el límite máximo de personas por habitación múltiple es de 8.
         
## Método de Evaluación

  La docente realizará las siguientes acciones:
  - Verificación del diagrama de clases (modelo de datos) utilizado.
  - Prueba del funcionamiento de la aplicación. Ejecución, realización de ABML (altas, bajas, modificaciones y lectura/consulta de datos).

  Para la aprobación se tendrá en cuenta:
  - El sistema deberá contar con un 70% de funcionalidad.
  - Se tendrá consideración sobre errores pequeños.
  - La aplicación DEBERÁ de forma OBLIGATORIA implementar el modelo de capas correctamente.
  - No se tolerarán bucles infinitos, errores por desbordamiento, o errores graves.
  - Se espera que la aplicación logre, como mínimo, un ABML COMPLETO desde la aplicación web.

## Se valorará (puntos extra):

  - Interfaz Gráfica atractiva e interactiva
  - Correcta visualización de los datos que se muestren por pantalla
  - Eficiencia en el armado del UML
  - Aportes o ideas extras por parte del alumno

## Características
Actualmente incluye:

 - Inicio de sesión de administrador con múltiples usuarios y permisos editables.
 - Seccion de inicio con tarjetas informativas.
 - Gestion de Administradores.
 - Gestion Habitaciones.
 - Gestion de Reserva.
 - Gestion de Huespedes.

 Construida con:
 - Plantilla Admin Dashboard, para el frond-end, se personalizo y optimizo acorde para el proyecto.
 - Se uso lenguaje Java y POO.
 - Se uso de JPA.
 - Se uso CSS y HTML (permitiendo el uso de plantillas).
 - Se uso Javascript basico.
 - Se uso JSP y comunicación entre capas.
 - Mysql 5.6 para la gestion de la base de datos.

 
 Dependencias utilizadas:
  - jQuery 
  - Bootstrap 5
  - Bootstrap-icons
  - Sweetalert2
  - Font Awesome
  
## Vistas del panel:

- Login 
<img src=" "/>

-Seccion de inicio
<img  src=" "/>

-Gestion de reservas
<img src=" "/>
 
 
  
