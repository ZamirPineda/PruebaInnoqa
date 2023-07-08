# PruebaInnoqa
Prueba técnica para Innoqa, usando SpringBoot 3.1.1 y Java 17. 

## Flujo de desarrollo

 - Lo primero que se hizo fue configurar la BD h2 en memorias mediante el archivo application.properties 

![h2config Image](./images/h2config.png)

 - Luego empezamos a crear las entidades para darle forma a las tablas.

![h2config Image](./images/pricesentity.png)
![h2config Image](./images/brandentity.png)

 - Luego se creo los @Repository utilizando la herencia a JPA para la conexión a la BD.

![h2config Image](./images/brandrepo.png)
![h2config Image](./images/pricesrepo.png)

 - Luego se crearon los @RestController para construir los endpoint y generar las consultas.

![h2config Image](./images/brandcontroller.png)
![h2config Image](./images/pricescontroller.png)

 - Se crea un @RestControllerAdvice para controlar los errores en el código y asi validar el correcto funcionamiento del mismo

![h2config Image](./images/exceptioncontroller.png)

 - Por último, se crea un Util para realizar el mapeo de la salida y la lógica para validar si esta dentro de las fechas en la BD

![h2config Image](./images/util.png)

## Flujo de test unitarios

 - Lo primero fue crear las etiquetas @SpringBootTest y @AutoConfigureMockMvc, se traen las clases principales a usar en los test con @Autowired y lo más importante para iniciar es el @BeforeEach en el cual se guardaran las 4 listas a la BD H2 antes de iniciar la ejecución de los test

![test Image](./images/before.png)

 - Despues de crea un @AfterEach, para que cuando terminen la ejecución de los test elimine la data insertada en la BD y creamos el primer test más sencillo para probar la funcionalidad de la tabla Brand

![test Image](./images/after.png)

 - Una vez tenemos data lista, creamos los 6 test requeridos por la prueba técnica, en los cuales, cada uno trae la siguiente linea de código para validar la funcionalidad de los mismos:  
      result.andExpect(status().isOk()).andExpect(jsonPath("$[0].price").value(35.50)); //Valor esperado
![test Image](./images/untest.png)
![test Image](./images/focustest.png)

 - Por último, adjunto la evidencia de los 6 test ejecutados correctamente:

![test Image](./images/6testincode.png)

## Flujo de pruebas con postman

 - Primero creamos data en la tabla Brand, ya que, necesitamos tener al menos una cadena por la llave foranea.

![prueba Image](./images/Brand.png)

 - Luego de tener data en la tabla Brand, podemos empezar a insertar data en la tabla PRICES, igualmente como se realizo en los test unitarios.

![prueba Image](./images/createPrice0.png) ![prueba Image](./images/createPrice1.png) ![prueba Image](./images/createPrice2.png) ![prueba Image](./images/createPrice3.png)

 - Ya con data, podemos empezar a realizar las consultas, en este caso, realice 5 consultas en postman tal cual se requeria en los test, y una adicional para traer todas las listas de la tabla.

![prueba Image](./images/test1.png) ![prueba Image](./images/test2.png) ![prueba Image](./images/test3.png) ![prueba Image](./images/test4.png) ![prueba Image](./images/test5.png) ![prueba Image](./images/getAll.png)
