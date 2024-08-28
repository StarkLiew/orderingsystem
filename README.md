
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
3. Run the application:

```bash
mvn spring-boot:run
```
## Endpoints
### Anatomy of an endpoint
The anatomy of an endpoint should look like this:

| Method | Endpoint                                         |
|--------|--------------------------------------------------|
| `GET`  | /products/{product_code}                         |
| `POST` | /orders/create?[userId]&[productCode]&[quantity] |


## Issue
1. Have timeout issue with RocketMQ broker network when run on docker environment.


## License

This project is licensed under the MIT License.
