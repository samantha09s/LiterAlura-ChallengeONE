<h1 align="center"> LiterAlura - Catálogo Digital de Libros en Java </h1>

![LiterAlura - Challenge ONE - Banner](https://github.com/samantha09s/LiterAlura-ChallengeONE/assets/140031528/ab128db4-25f6-4f44-9ee3-bcb03456f37d)

![Java](https://img.shields.io/badge/Java-17-007396?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.0-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![GitHub](https://img.shields.io/badge/GitHub-Repository-181717?style=for-the-badge&logo=github&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-Project%20Management-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)

## Índice

- [Descripción del Proyecto](#descripción-del-proyecto)
- [Objetivo](#objetivo)
- [Estado del Proyecto](#estado-del-proyecto)
- [Características](#características)
- [Instalación y Configuración](#instalación-y-configuración)
- [Tecnologías Utilizadas](#tecnologías-utilizadas)
- [Personas Contribuyentes](#personas-contribuyentes)
- [Licencia](#licencia)
- [Agradecimientos](#agradecimientos)

## Descripción del Proyecto

El **Desafío LiterAlura** es una aplicación de consola desarrollada en Java que permite a los usuarios interactuar con un catálogo de libros. Los libros se obtienen a través de la API de **Gutendex**, se almacenan en una base de datos y se pueden consultar mediante varias opciones de búsqueda. Este proyecto fue realizado como parte del programa **Oracle Next Education (ONE)** de **Oracle**.

## Objetivo

Desarrollar un Catálogo de Libros que ofrezca interacción textual (vía consola) con los usuarios, proporcionando opciones de interacción. Los libros se buscarán a través de una API específica. Los pasos para completar este desafío incluyen:

1. Configuración del Ambiente Java
2. Creación del Proyecto
3. Consumo de la API
4. Análisis de la Respuesta JSON
5. Inserción y consulta en la base de datos
6. Exibición de resultados a los usuarios

## Estado del Proyecto

![Estado del Proyecto](https://img.shields.io/badge/Estado-Finalizado-brightgreen)

El proyecto está finalizado y funcional.

## Características

- **Buscar libro por autor:** Realiza una petición a la API de libros (Gutendex) y guarda la respuesta en la base de datos.
- **Mostrar libros registrados:** Consulta y muestra todos los libros y autores almacenados en la base de datos.
- **Mostrar autores registrados:** Consulta y muestra todos los autores almacenados.
- **Buscar autores en un determinado año:** Permite ingresar un año y listar los autores desde ese año en adelante.
- **Mostrar libros por idioma**: Muestra libros según el idioma seleccionado por el usuario.
- **Mostrar top 10 libros descargados:** Consulta los 10 libros más descargados almacenados en la base de datos.
- **Mostrar estadísticas generales** Visualiza estadísticas generales de los libros almacenados, incluyendo promedio, máximo y mínimo de descargas.
- **Buscar autor por nombre:** Permite buscar autores por su nombre.

## Instalación y Configuración

1. **Clonar el repositorio:**
    ```sh
    git clone https://github.com/samantha09s/LiterAlura-ChallengeONE.git
    cd LiterAlura-ChallengeONE
    ```

2. **Configurar la base de datos:**
    - Ejecutar PostgreSQL y crear la base de datos `literalura`.
    - Configurar las credenciales de la base de datos en `src/main/resources/application.properties`:
      ```properties
      # Nombre de la aplicación
      spring.application.name=literalura

      # Configuración de la conexión a la base de datos PostgreSQL
      spring.datasource.url=jdbc:postgresql://${DB_HOST}/desafio_literalura
      spring.datasource.username=${DB_USER}
      spring.datasource.password=${DB_PASSWORD}
      spring.datasource.driver-class-name=org.postgresql.Driver

      # Configuración específica de Hibernate para PostgreSQL
      hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

      # Propiedades opcionales de JPA para mostrar y formatear las consultas SQL
      # spring.jpa.show-sql=true
      # spring.jpa.format-sql=true
      ```

3. **Compilar y ejecutar la aplicación:**
    - Compilar el proyecto usando Maven:
      ```sh
      mvn clean install
      ```
    - Ejecutar la aplicación:
      ```sh
      mvn spring-boot:run
      ```

4. **Interactuar con la aplicación:**
    - Una vez que la aplicación esté en ejecución, puedes interactuar con ella a través de la consola. Se te presentará un menú con varias opciones:

    ```plaintext
    BIBLIOTECA DIGITAL
    ----------------- MENÚ PRINCIPAL -----------------
    1. Buscar libro por título
    2. Listar libros registrados
    3. Listar autores registrados
    4. Listar autores vivos en un determinado año
    5. Listar libros por idioma
    6. Visualizar datos estadísticos
    7. Mostrar top 10 libros descargados
    8. Buscar autor por nombre
    9. Listar autores que nacieron y murieron en un determinado rango de años
    0. Salir de la aplicación

    Selecciona una opción (1-9):
    ```

5. **Ejecutar desde un IDE:**
    - Abre el proyecto en tu IDE preferido (por ejemplo, IntelliJ IDEA).
    - Navega a la clase `LiteraluraApplication` en el paquete `com.literalura.literalura`.
    - Ejecuta la clase `LiteraluraApplication`.

## Tecnologías Utilizadas

- Java SE 17
- Spring Boot 3.3.0
- Maven
- PostgreSQL
- Gutendex API

## Personas Contribuyentes

- Samantha Munguía

[![LinkedIn](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/samanthamunguia/)

## Licencia

Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo [LICENSE](https://docs.github.com/es/repositories/managing-your-repositorys-settings-and-features/customizing-your-repository/licensing-a-repository) para más detalles.

## Agradecimientos

Agradecimiento especial al equipo de Alura por el material y el contenido del curso. También agradecemos al programa Oracle Next Education por la oportunidad de desarrollo.

![badge_literalura_insignia](https://github.com/samantha09s/LiterAlura-ChallengeONE/assets/140031528/003be1af-fff6-4e6d-b9e8-aa081b1242b2)
