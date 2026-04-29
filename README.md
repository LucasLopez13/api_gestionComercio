# API REST - Gestión de Comercio

Una API robusta y escalable desarrollada con Spring Boot 3 y Java 17/21, diseñada para gestionar el ciclo de vida completo de las ventas, inventario y clientes de un comercio.

## Características Principales

* **Seguridad Transaccional:** El registro de ventas implementa `@Transactional`. El backend es la única fuente de verdad para el cálculo de precios y descuentos de stock, previniendo manipulaciones desde el cliente.
* **Aislamiento de Capas:** Integración de `MapStruct` para la conversión automática entre Entidades JPA y DTOs, evitando la exposición del modelo de base de datos.
* **Borrado Lógico:** Las entidades principales no se eliminan físicamente de la base de datos (`@SQLDelete`).
* **Escalabilidad:** Todos los endpoints de listado (GET) implementan la interfaz `Pageable` de Spring Data JPA para devolver datos paginados, protegiendo al servidor de desbordamientos de memoria (Out Of Memory) ante bases de datos de gran volumen.

## Tecnologías Utilizadas

* **Backend:** Java, Spring Boot 3, Spring Web, Spring Data JPA, Spring Validation.
* **Base de Datos:** MySQL.
* **Herramientas:** MapStruct (Mapeo de objetos), Lombok, Maven.

## Estructura de Endpoints (Ejemplo)

| Método | Endpoint | Descripción |
|---|---|---|
| `POST` | `/api/ventas` | Registra una nueva transacción y descuenta stock automáticamente. |
| `GET` | `/api/productos?page=0&size=10` | Devuelve el catálogo de productos de forma paginada. |
| `DELETE` | `/api/clientes/{id}` | Realiza un borrado lógico del cliente (si no tiene ventas asociadas). |


Lucas Lopez, Desarrollador Trainee.