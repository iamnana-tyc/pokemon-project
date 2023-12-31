# Pokemon Spring Boot Project

## Overview

This project is a Spring Boot application that implements basic CRUD operations for Pokemon and Review entities.
The project utilizes a PostgreSQL database for data storage and Maven as the build tool.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java Development Kit (JDK) installed.
- Maven for building the project.
- Database : PostgreSQL for storing the entities data.
- [Spring Boot](https://spring.io/projects/spring-boot) knowledge.

## Features

- **Pokemon Entity:** Represents a Pokemon with details such as name, type.

- **Review Entity:** Represents a review for a Pokemon, allowing multiple reviews for each Pokemon.

- **One-to-Many Relationship:** Defined between Pokemon and Review entities. A Pokemon can have many reviews.

- **Many-to-One Relationship:** Defined between Review and Pokemon entities. Many reviews can belong to a single Pokemon.

## Technologies Used

- **Spring Boot:** A framework for building Java-based enterprise applications.

- **PostgreSQL:** A powerful, open-source relational database system.

- **Maven:** A build automation tool used for managing dependencies and building the project.


### Configuration

1. Open the `application.properties` file and configure the database settings:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/databaseName
   spring.datasource.username=root
   spring.datasource.password=password
   ```

## Setup

1. **Database Configuration:**
    - Ensure you have a PostgreSQL database running.
    - Update the `application.properties` file with your database configuration.

2. **Build the Project:**
   ```
   mvn clean install
   ```

3. **Run the Application:**
   ```
   mvn spring-boot:run
   ```


This is a basic template, and you can customize it further based on your specific project details and structure. 
Add information about the project's purpose, how to run tests, and any other relevant details that might be helpful for users and contributors.