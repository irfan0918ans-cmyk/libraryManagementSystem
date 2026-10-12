# Library Management System

A Spring Boot REST API for managing authors and books, with a one-to-many relationship between them (one author, many books), soft delete, DTOs and centralized exception handling.

## Tech Stack

- Java 17+
- Spring Boot 3 (Spring Web, Spring Data JPA)
- Hibernate / Jakarta Persistence
- Lombok
- Database: MYSQL

## Features

- CRUD operations for **Authors** and **Books**
- `@ManyToOne` relationship: every book belongs to an author
- **Soft delete** (`isDeleted`, `deletedAt`) for authors and books
- Books of a soft-deleted author are hidden from all book endpoints
- Request/Response **DTOs**, so entities are never exposed directly
- Case-insensitive, partial-match search by name
- Centralized error handling with `@RestControllerAdvice`

## Project Structure

```
com.management.libraryManagementSystem
├── Controller    # REST endpoints
├── Service       # Business logic
├── Repository    # Spring Data JPA repositories
├── Entity        # Author, Book
├── DTO           # Request/Response DTOs (AuthorDTO, BookDTO)
└── Exception     # Custom exceptions and global handler
```

## Getting Started

### Prerequisites

- JDK 17
- Maven or Gradle
- A running database (see configuration below)

### Configuration

Set your datasource in `src/main/resources/application.yaml`:

```yaml
spring:
  datasource:
    url: jdbc:<your-db-url>
    username: <username>
    password: <password>
  jpa:
    hibernate:
      ddl-auto: update
```

### Run

```bash
git clone <your-repo-url>
cd <repo-folder>

# Maven
./mvnw spring-boot:run

# or Gradle
./gradlew bootRun
```

The API starts on `http://localhost:8080` by default.

## API Endpoints

### Author: `/author/api`

| Method | Endpoint     | Params / Body                     | Description                 |
|--------|--------------|-----------------------------------|-----------------------------|
| POST   | `/save`      | Body: `AuthorRequestDTO`          | Create an author            |
| GET    | `/getAuthor` | `?name=`                          | Find an author by name      |
| GET    | `/getAll`    | None                              | List all non-deleted authors|
| PUT    | `/update`    | `?name=`, Body: `AuthorRequestDTO`| Update an author            |
| PATCH  | `/delete`    | `?name=` | Soft delete an author  |                             |

### Book: `/book/api`

| Method | Endpoint           | Params / Body                                | Description |
|--------|--------------------|----------------------------------------------|-------------|
| POST   | `/save`            | `?name=<authorName>`, Body: `BookRequestDTO` | Create a book for the given author |
| GET    | `/getByBooKName`   | `?name=`                                     | Find a book by name |
| GET    | `/getByAuthorName` | `?name=`                                     | List all books of an author |
| GET    | `/getAll`          | None                                         | List all non-deleted books |
| PUT    | `/update`          | `?name=`, Body: `BookRequestDTO`             | Update a book |
| PATCH  | `/delete`          | `?name=`                                     | Soft delete a book |
| DELETE | `/delete`          | `?name=`                                     | Permanently delete a book |

> Note: for `POST /book/api/save`, `name` is the **author's** name, not the book's.

## Example Requests

**Create an author**

```http
POST /author/api/save
Content-Type: application/json

{
  "authorName": "George Orwell",
  "gender": "Male",
  "age": 46
}
```

**Create a book for that author**

```http
POST /book/api/save?name=George Orwell
Content-Type: application/json

{
  "name": "1984",
  "edition": "First",
  "price": 499.0,
  "publishedBy": "Secker & Warburg"
}
```

**Response**

```json
{
  "name": "1984",
  "edition": "First",
  "price": 499.0,
  "publishedBy": "Secker & Warburg",
  "createdAt": "2026-10-12T10:30:00"
}
```

## Error Handling

| Status                       | When |
|------------------------------|------|
| `404 Not Found`              | Author or book does not exist (or is soft-deleted) |
| `400 Bad Request`            | Missing request parameter or malformed JSON |
| `405 Method Not Allowed`     | Wrong HTTP method for an endpoint |
| `500 Internal Server Error`  | Any unexpected error (details are logged server-side, not sent to the client) |

## Design Notes

- **Soft delete:** deleted records stay in the database with `isDeleted = true` and a `deletedAt` timestamp, and are excluded from all queries.
- **Name-based lookup:** searches use case-insensitive partial matching, so book names are expected to be unique.
- **Authors cannot be permanently deleted**, only soft deleted, to avoid foreign key conflicts with their books.

## Possible Improvements

- Bean Validation (`@Valid`, `@NotBlank`, `@Positive`) on request DTOs
- ID-based endpoints instead of name-based
- Pagination for list endpoints
- Unit and integration tests
- Swagger / OpenAPI documentation

## Author

`<Your Name>` | [GitHub](https://github.com/<your-username>)
