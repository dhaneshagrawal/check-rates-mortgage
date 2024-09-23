# Mortgage Check Application


## Overview
This project is a Spring Boot application designed for mortgage check operations. It includes validation services for mortgage applications, utilizes Docker for containerization, and Kubernetes for deployment. Additionally, it integrates with Prometheus for monitoring.

## Architecture

In this project, we have used clean code architecture implementing 3 modules where the "core" module has the business logic, the "integration" module has the controllers exposed to the outside world and the "delivery" module hooking up all the modules.

## Design Pattern

Implemented Validation Pattern for Validation which is a combination of Chain Of Responsibility along with Strategy Pattern. ALso refactored code to strive for Singe Point of Responsbility Pattern.

## Clone the Repository
```
git clone https://github.com/your-username/mortgage-check-application.git
cd mortgage-check-application
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
mvn spring-boot:run
Or you can run the JAR file if it has been built:

java -jar target/check-rates-mortgage.jar
The application will be accessible at http://localhost:8080.
```

Push to the branch (git push origin feature/YourFeature).
Create a new Pull Request.
License
This project is licensed under the MIT License. See the LICENSE file for details.

Replace placeholder texts (e.g., <your-dockerhub-username>, https://github.com/your-username/mortgage-check-application) with actual values relevant to your project.
