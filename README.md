# Employee Management System (Spring Boot REST API)

This project is a layered Spring Boot REST API for managing employee records with full CRUD operations, validation, exception handling, and database persistence using Spring Data JPA.

## Tech Stack

- Java 17
- Spring Boot 3
- Spring Data JPA
- Bean Validation (`@Valid`, `@NotBlank`, `@Email`, `@Positive`)
- H2 (file-based, default) / MySQL (optional)
- Maven

## Project Structure

```text
src/main/java/com/system/employee_management
├── controller
│   └── EmployeeController.java
├── entity
│   └── Employee.java
├── exception
│   ├── EmployeeNotFoundException.java
│   └── GlobalExceptionHandler.java
├── repository
│   └── EmployeeRepository.java
├── service
│   └── EmployeeService.java
└── EmployeeManagementApplication.java
```

## Employee Fields

- `id` (auto-generated)
- `name` (not blank)
- `email` (valid email)
- `department` (not blank)
- `salary` (> 0)
- `dateOfJoining` (ISO date format: `yyyy-MM-dd`)

## API Endpoints

- `POST /employees` - Add employee
- `GET /employees` - Get all employees
- `GET /employees/{id}` - Get employee by id
- `PUT /employees/{id}` - Update employee
- `DELETE /employees/{id}` - Delete employee

Base URL: `http://localhost:8081`

---

## Manual Configuration (Important)

### 1) Prerequisites

- JDK 17 installed
- Maven installed (or use Maven Wrapper included in the project)
- Postman installed

### 2) Database Setup Option A (Default, easiest): H2 file DB

No manual DB installation required. It is already configured in `application.properties`.

Current default config:

```properties
spring.datasource.url=jdbc:h2:file:./data/employee_db
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
```

Optional H2 console:

- URL: `http://localhost:8081/h2-console`
- JDBC URL: `jdbc:h2:file:./data/employee_db`
- User: `sa`
- Password: *(empty)*

### 3) Database Setup Option B: MySQL

Create DB in MySQL:

```sql
CREATE DATABASE employee_management;
```

Then update `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/employee_management
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

---

## How To Run

From project root:

### Windows (PowerShell)

```powershell
.\mvnw.cmd spring-boot:run
```

### Or (all platforms)

```bash
./mvnw spring-boot:run
```

App starts at: `http://localhost:8081`

---

## Postman Testing Instructions

Set `Content-Type: application/json`.

### 1) Add Employee

- Method: `POST`
- URL: `http://localhost:8081/employees`
- Body (raw JSON):

```json
{
  "name": "Rahul Sharma",
  "email": "rahul.sharma@example.com",
  "department": "IT",
  "salary": 65000,
  "dateOfJoining": "2024-06-10"
}
```

Expected: `201 Created` with saved employee (includes generated `id`).

### 2) Get All Employees

- Method: `GET`
- URL: `http://localhost:8081/employees`

Expected: `200 OK` with list.

### 3) Get Employee by ID

- Method: `GET`
- URL: `http://localhost:8081/employees/1`

Expected: `200 OK` if found, `404 Not Found` if missing.

### 4) Update Employee

- Method: `PUT`
- URL: `http://localhost:8081/employees/1`
- Body:

```json
{
  "name": "Rahul Sharma",
  "email": "rahul.updated@example.com",
  "department": "Engineering",
  "salary": 72000,
  "dateOfJoining": "2024-06-10"
}
```

Expected: `200 OK` with updated employee.

### 5) Delete Employee

- Method: `DELETE`
- URL: `http://localhost:8081/employees/1`

Expected: `200 OK` with success message.

---

## Validation and Error Testing in Postman

Try this invalid payload:

```json
{
  "name": "",
  "email": "invalid-email",
  "department": "",
  "salary": -1000,
  "dateOfJoining": "2024-06-10"
}
```

Expected: `400 Bad Request` with field-level messages.

Try `GET /employees/9999` (non-existing id):

Expected: `404 Not Found` with clear message.

---

## Build and Test Commands

```bash
./mvnw test
./mvnw clean package
```

For Windows PowerShell:

```powershell
.\mvnw.cmd test
.\mvnw.cmd clean package
```

---

## Resume-friendly Highlights

- Layered architecture (`Controller -> Service -> Repository`)
- Production-style validation and global exception handling
- Real DB persistence using Spring Data JPA
- Fully testable REST API with Postman
