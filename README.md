# Voting System

## Table of Contents

- [Introduction](#Introduction)
- [Prerequisites](#Prerequisites)
- [Technologies Used](#Technologies)
- [Architecture](#Architecture)
- [Setup Instructions](#Setup)
- [Usage](#Usage)

## Introduction

The Voting System is a web application designed to facilitate voting processes for various agendas.

It provides functionalities for creating agendas, opening voting sessions, casting votes, and calculating voting results. This README provides information on prerequisites, technologies used, project architecture, setup instructions, usage, and contributing guidelines.

## Prerequisites

Before running the project, ensure you have the following installed:

- Java Development Kit (JDK) 17
- Apache Maven
- Docker 26.0.0 (for running the PostgreSQL database container)

## Technologies

The project is built using the following technologies:

- Java
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Swagger (OpenAPI)
- Maven

## Architecture

This project follows the principles of Clean Architecture, which emphasizes separation of concerns and independence of frameworks. It consists of several layers:

- **Controller**: Handles incoming HTTP requests, delegates to the appropriate use case, and returns HTTP responses.
- **Core**:
  - **DTOs**: Contains Data Transfer Objects used to transfer data between layers.
  - **Entities**: Contains the core business entities of the application.
  - **Enums**: Contains enums used in the application.
  - **Exceptions**: Contains custom exceptions thrown by the application.
  - **Mappers**: Provides mapping between DTOs and domain entities.
  - **Repository**: Provides an interface for data access, allowing the application to interact with the database.
  - **Usecases**: Contains the application's business logic and use cases.
- **Infra**:
  - **PostgreSQL**:
    - **Mappers**: Provides mapping between PostgreSQL entities and domain entities.
    - **Models**: Contains PostgreSQL entity models.
    - **Repositories**: Provides data access methods specific to PostgreSQL.
  - **Spring**:
    - **Repositories**: Contains Spring Data repositories.
    - **Routines**: Contains routines specific to Spring framework.
- **Service**: Contains the business logic and orchestrates the interaction between repositories and controllers.

## Setup

1. Clone the repository:

    ```shell
    git clone https://github.com/StPfeffer/desafio-votacao.git
    ```

2. Configure the database:

    The PostgreSQL database is configured to run in a container. To start the container you can run the following command:

    ```shell
    cd desafio-votacao
    docker compose up
    ```

    By default, the container will listen to the port 6532.


3. Build the project:

    ```shell
    mvn clean install
    ```

4. Run the application:

    ```shell
    mvn spring-boot:run
    ```

5. Access the application:

    The application will be accessible at http://localhost:8080.

## Usage

- Use the provided API documentation (Swagger UI) to explore and interact with the available endpoints. You can find it [here](http://localhost:8080/swagger-ui/index.html).
- Create agendas, open voting sessions, cast votes, and view voting results using the provided API endpoints.

You can find the Postman collection in the `resources` folder, or clicking [here](https://github.com/StPfeffer/desafio-votacao/blob/main/src/main/resources/Votacao.postman_collection.json).
