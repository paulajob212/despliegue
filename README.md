# Makaia_Bank
Proyecto usando Java, Springboot, MySQL y JWT

## Sistema de Transacciones Bancarias (Back end)

Los sistemas de transacciones bancarias desempeñan un papel fundamental al facilitar y agilizar la gestión de los movimientos de dinero entre cuentas y entidades financieras. 
Estos sistemas representan la columna vertebral de las operaciones bancarias, permitiendo a individuos, empresas e instituciones realizar una variedad de transacciones de manera segura, eficiente y conveniente.

### Descripción

Este codigo proporciona la logica y el codigo fuente del proyecto de Sistema de transacciones bancarias, teniendo como valor agregado la capacidad de adaptarse a cualquier tipo de proyecto Bancario.

### Objetivo

El objetivo de este ejercicio es simular un sistema de transacciones bancarias básico. Los usuarios podrán abrir cuentas, realizar depósitos en sus cuentas, y transferir dinero entre cuentas, ademas, realizar diferentes operaciones a sus bolsillos asociados.


### Documentación con Swagger

Puedes visualizar la estructura de los controladores en la interfaz gráfica de [Swagger](http://localhost:8080/swagger-ui/index.html), para consultar los endpoints del CRUD.


### Características Principales

- Creacion de cuentas bancarias solo para el rol administrador.
- Creacion de bolsillos asociados a una cuenta bancaria.
- Transacciones entre diferentes cuentas y de una cuenta a su bolsillo asociado.
- Realizar depositos en sus cuentas.
- Consultar su cuenta y bolsillo asociado.

### Tecnologías Utilizadas

- Java: Lenguaje de programacion de tipado fuerte.
- Spring Boot: Framework de Java para la creacion de esta API Rest Back End.
- Spring JPA: Facilita el acceso y la gestión de la capa de datos.
- SQL: Sistema de gestión de Bases de Datos Relacionales utilizando el motor de bases de datos MySQL.
- Lombok: Librería externa para un codigo mas limpio.
- JUnit y Mockito: Utilizado para la realizacion de pruebas unitarias.
- CI: Integracion continua

### Documentación

Diagrama UML proyecto completo [Modelado] 
![Diagrama UML](https://github.com/jvianad/Makaia_Bank/blob/dbcc31f9389abe48edf706666dde444fd0158934/Diagrama%20UML.jpg)


[Jean Carlo Viaña De Mares / Makaia Bootcamp]
