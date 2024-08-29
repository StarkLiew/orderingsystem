
# Ordering System

This a simple Ordering System is built using Spring Boot.

## **Prerequisites**

Before installing the Ordering System, ensure the following:

- Java 17 is installed on your machine.

## Installation

1. Clone the repository from GitHub.
2. Setup docker environment
```bash
docker-compose up -d
```
3. Import database/orderingsystem.sql to MySQL
4. Run the application:

```bash
mvn spring-boot:run
```
## Endpoints

The available endpoints:

| Method | Endpoint                                         |
|--------|--------------------------------------------------|
| `GET`  | /products/{product_code}                         |
| `POST` | /orders/create?[userId]&[productCode]&[quantity] |


## License

This project is licensed under the MIT License.
