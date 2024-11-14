# OrangeX API

An API for a social media platform similar to X, developed with Java, Spring Framework, MongoDB, and JWT authentication. This API provides endpoints for user authentication, posting, and interacting with posts.

## Project Overview

- **Technologies**: Java, Spring Framework, MongoDB, JWT Authentication
- **Purpose**: To provide core functionalities of a social media platform, such as posting and interacting with content.

## Prerequisites

- **JDK 21 or higher**: Version 
- **Apache Maven Version 3.8.1 or higher**
- **MongoDB**: Ensure a running MongoDB instance.

## Getting Started

1. **Clone the repository**:
```bash
   git clone https://github.com/henrythuler/orangex-api
   cd orangex-api
```

2. **Configure application.properties**:

   - Update the `src/main/resources/application.properties` file with your MongoDB credentials and a JWT Key.

```
spring.data.mongodb.uri=your_mongodb_uri
spring.data.mongodb.database=your_mongodb_database

#JWT Secret Key
jwt.secret.key=${JWT.SECRET:your_jwt_key}
```

3. **Run the Application**:

- Inside the project folder, open your terminal and run:

```bash
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`.

## API Documentation

Access the Swagger UI for API documentation at:
`http://localhost:8080/swagger-ui`

This provides an interactive interface for exploring available endpoints, including expected inputs and responses.

## Key Features

- **JWT Authentication**: Secures user access to endpoints.
- **User Management**: Register, authenticate, and manage user profiles.
- **Post Creation**: Users can create, view, edit, and delete posts.
- **Like and Comment**: Users can like and comment on posts.

## Dependencies

The following dependencies are used in this project:
- **Spring Boot** for application and dependency management.
- **MongoDB** driver for database integration.
- **JWT** for secure, token-based user authentication.

All dependencies are managed via Maven: running `mvn install` to download everything required.
