# Application Tracker API

## Description

The Application Tracker API is a REST API for managing job applications.

It allows users to:

- create applications
- retrieve all applications
- retrieve a single application by ID
- filter applications by status
- filter applications by company name
- update the status of an application
- delete an application

This project was built as a backend learning project to practice core Spring Boot concepts such as layered architecture, DTOs, validation, exception handling, PostgreSQL integration, and integration testing.

---

## Tech Stack

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- JUnit 5
- MockMvc

---

## Project Structure

The project follows a layered backend architecture:

- `controller` – handles HTTP requests and responses
- `service` – contains business logic
- `repository` – handles database access
- `model` – contains entities and enums
- `dto` – contains request and response DTOs
- `exception` – contains custom exceptions and global exception handling

---

## Features

- Create a new application
- Get all applications
- Get a single application by ID
- Filter applications by status
- Filter applications by company name
- Update the status of an application
- Delete an application
- Request DTOs and Response DTOs
- Validation for incoming requests
- Global exception handling
- Integration tests with MockMvc
- PostgreSQL database integration

---

## Data Model

Each application contains the following fields:

- `id`
- `companyName`
- `position`
- `status`
- `applicationDate`
- `notes`

The status is represented as an enum.

Example statuses:

- `APPLIED`
- `INTERVIEW`
- `REJECTED`

---

## API Endpoints

### Create Application

**POST** `/applications`

#### Request Body

```json
{
  "companyName": "SAP",
  "position": "Werkstudent Java",
  "status": "APPLIED",
  "notes": "First application"
}
