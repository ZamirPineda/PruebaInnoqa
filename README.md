# PruebaInnoqa
Prueba técnica para Innoqa, usando SpringBoot 3.1.1 y Java 17. 

## Flujo de desarrollo

Lo primero que se hizo fue configurar la BD h2 en memorias mediante el archivo application.properties 

![h2config Image](./images/h2config.png)

Luego empezamos a crear las entidades para darle forma a las tablas.

![h2config Image](./images/pricesentity.png)
![h2config Image](./images/brandentity.png)

Luego se creo los @Repository utilizando la herencia a JPA para la conexión a la BD.

![h2config Image](./images/brandrepo.png)
![h2config Image](./images/pricesrepo.png)

Luego se crearon los @RestController para construir los endpoint y generar las consultas.

![h2config Image](./images/brandcontroller.png)
![h2config Image](./images/pricescontroller.png)

Se crea un @RestControllerAdvice para controlar los errores en el código y asi validar el correcto funcionamiento del mismo

![h2config Image](./images/exceptioncontroller.png)

![Test Image](./images/6testincode.png)
