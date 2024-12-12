# Employee Management Service

A REST API Service for managing employee, roles and projects data. Including creating, updating, retrieving and deleting records.
This service is dependent on em-persistance-service application, which stores the data in the H2 database.
---

## Table of Contents

- [Installation](#installation)
- [API Documentation](#api-documentation)


---

## Installation

### Prerequisites:
- Java 17 or later
- Maven 
- Docker

### Steps to run the project:

1. **Create a package of the application. In the root directory run the command below**
   ```bash
   mvn clean package

2. **Create the docker file**
   ```bash
   docker build --tag=em-service:latest .

3. **Ensure that the docker file of em-persistence-service is already created. Run docker-compose up to start both applications**
   ```bash
   docker-compose up

## API Documentation

The OpenAPI specification can be found in: src/main/resources/employee-management.yaml

An API Key is needed for authorisation. This can be found in the application.yaml. The API Key has to be provided in the Header as "x-api-key"
A role has to be supplied in the header as well. 

The role can be: ADMIN, USER OR MANAGER. 

