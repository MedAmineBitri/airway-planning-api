# Airway Planning Demo Project

This project is a demonstration of an airway planning system, showcasing how to create and manage flight data using Spring Boot and various third-party libraries.

## Table of Contents

- [Introduction](#introduction)
- [Project Structure](#project-structure)
- [Third-Party Libraries](#third-party-libraries)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Introduction

The Airway Planning Demo Project is designed to illustrate the development of a web-based airway planning system. It enables users to manage flight data, perform CRUD operations on flights, and handle various business logic scenarios related to flight planning.

## Project Structure

The project follows a standard Maven project structure:

- `src/main/java`: Contains the Java source code of the project.
- `src/test/java`: Contains unit and integration tests.
- `src/main/resources`: Includes application properties and configuration files.
- `pom.xml`: Maven configuration file for dependencies and build settings.

## Third-Party Libraries

The project utilizes several third-party libraries to simplify development and enhance functionality:

1. **Spring Boot Starter Web**: Enables building web applications with Spring MVC.
2. **Spring Boot Starter Data JPA**: Simplifies database access using Spring Data JPA.
3. **H2 Database**: An in-memory and persistent database engine for development and testing.
4. **Spring Boot Starter Test**: Includes testing utilities and configurations for Spring Boot.
5. **Dozer Mapping**: Simplifies Java Bean mapping for DTOs and entities.
6. **Lombok**: Reduces boilerplate code by generating common methods.
7. **JUnit**: A popular testing framework for unit tests.
8. **Mockito**: A mocking framework for creating mock objects in tests.

## Getting Started

To get started with the project:

1. Clone the repository: `https://github.com/MedAmineBitri/AirwayPlanningApi.git`
2. Navigate to the project directory: `cd airway-planning-demo`
3. Build the project: `mvn clean install`

## Usage

After building the project, you can run it using Spring Boot:

```bash
mvn spring-boot:run
