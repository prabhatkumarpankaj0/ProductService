# ProductService

Work in progress: a Spring Boot REST API for a product catalog. It uses Spring Data JPA with MySQL and includes an experimental Fake Store API service.

## Requirements

- Java 17
- MySQL with a `productservice` database

## Run locally

Set your database password as an environment variable, then start the app with the Maven wrapper. The default connection is `jdbc:mysql://localhost:3306/productservice` with user `root`; override it with `DB_URL` and `DB_USERNAME` if needed.

```powershell
$env:DB_PASSWORD = 'your-local-password'
.\mvnw.cmd spring-boot:run
```

The API is available at `http://localhost:8080`.

## Current API

The controller is wired to the database-backed service.

| Method | Path | Status |
| --- | --- | --- |
| `POST` | `/product` | Creates a product |
| `GET` | `/product` | Lists products |
| `PUT` | `/product/{id}` | Updates a product |
| `GET` | `/product/{id}` | Not implemented yet |
| `DELETE` | `/product/{id}` | Not implemented yet |

Example request body for creating a product:

```json
{
  "title": "Example product",
  "description": "Example description",
  "price": 19.99,
  "imageUrl": "https://example.com/product.png",
  "categoryName": "Examples"
}
```

The Fake Store API service is experimental and is not currently selected by the controller. More API behavior and tests are still being developed.
