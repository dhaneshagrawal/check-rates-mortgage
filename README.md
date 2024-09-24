# Mortgage Check Application


## Overview
This project is a Spring Boot application designed for mortgage check operations. It includes validation services for mortgage applications, utilizes Docker for containerization, and Kubernetes for deployment. Additionally, it integrates with Prometheus for monitoring.

## Architecture

In this project, we have used clean code architecture implementing 3 modules where the "core" module has the business logic, the "integration" module has the controllers exposed to the outside world and the "delivery" module hooking up all the modules.

## Design Pattern

Implemented Validation Pattern for Validation which is a combination of Chain Of Responsibility along with Strategy Pattern. This makes it easy to add new validations to the service.

Also refactored code to strive for Singe Point of Responsbility Pattern.

## Clone the Repository
```
git clone https://github.com/dhaneshagrawal/check-rates-mortgage.git
cd check-rates-mortgage
```

Build the Project
Ensure you have Maven installed. Build the project using:

```
mvn clean install
```

Running the Application Locally
Start Spring Boot Application
You can run the Spring Boot application directly using Maven:

```
mvn spring-boot:run -pl delivery

Or you can run the JAR file if it has been built:

java -jar target/check-rates-mortgage.jar

The application will be accessible at http://localhost:8080.
```

or from an IDE you can manually run he main class, which is "CheckRatesMortgageApplication.java"


### TODO

1. Implement Docker, Kubernetes and Prometheus monitoring
2. Add Security
3. Add Rate Limiting using Resilience4J
