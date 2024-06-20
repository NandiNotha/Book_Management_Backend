# Book Management Backend

This is the backend repository for the Book Management application built with Spring Boot and MongoDB.

## Features

- CRUD operations for books
- GraphQL API
- MongoDB integration

## Prerequisites

Before you begin, ensure you have met the following requirements:

- You have installed the latest version of Java (Java 17 or later)
- You have installed Maven
- You have a running instance of MongoDB

## Installation

To install this project, follow these steps:

1. Clone the repository:
    ```bash
    git clone https://github.com/NandiNotha/Book_Management_Backend
    ```

2. Navigate to the project directory:
    ```bash
    cd book-management-backend
    ```

3. Install the dependencies and build the project:
    ```bash
    mvn clean install
    ```

## Usage

To run the application, use the following command:

```bash
mvn spring-boot:run

Project Structure
The project structure is as follows:
book-management-backend/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/
│   │   │   │   ├── example/
│   │   │   │   │   ├── demo/
│   │   │   │   │   │   ├── entity/
│   │   │   │   │   │   ├── repo/
│   │   │   │   │   │   ├── service/
│   │   │   │   │   │   ├── graphql/
│   │   │   │   │   │   ├── BookManagementApplication.java
│   ├── resources/
│   │   ├── application.properties
├── .gitignore
├── pom.xml
├── README.md

