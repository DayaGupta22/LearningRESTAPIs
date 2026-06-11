# Employee Management REST API

A Spring Boot REST API project developed to learn and implement core backend development concepts such as MVC Architecture, RESTful APIs, Hibernate/JPA, DTO Mapping, Service Layer Design, and Response Handling.

## 🚀 Technologies Used

* Java 21
* Spring Boot
* Spring Data JPA
* Hibernate
* H2 Database
* Maven
* ModelMapper
* Lombok
* REST APIs

---

## 📂 Project Structure

```text
src
├── controller
│   └── EmployeeController
├── service
│   └── EmployeeService
├── repository
│   └── EmployeeRepository
├── entity
│   └── EmployeeEntity
├── dto
│   └── EmployeeDTO
├── config
│   └── ModelMapperConfig
└── application.properties
```

---

## 🎯 Features Implemented

### Employee CRUD Operations

* Create Employee
* Get Employee By Id
* Get All Employees
* Update Employee
* Patch Employee (Partial Update)
* Delete Employee

---

## 🏗 MVC Architecture

This project follows the MVC (Model View Controller) pattern:

### Controller Layer

Handles incoming HTTP requests and returns appropriate responses.

Example:

```java
@GetMapping("/{id}")
public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
    return ResponseEntity.ok(employeeService.getEmployeeById(id));
}
```

### Service Layer

Contains business logic and communicates with the repository layer.

### Repository Layer

Handles database operations using Spring Data JPA.

```java
public interface EmployeeRepository
        extends JpaRepository<EmployeeEntity, Long> {
}
```

---

## 🗄 Database Integration

Used H2 Database with Hibernate ORM.

### Hibernate Features Used

* Entity Mapping
* Primary Key Generation
* CRUD Operations
* Repository Pattern

Example:

```java
@Entity
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
```

---

## 🔄 DTO Pattern

Used DTO (Data Transfer Object) to avoid exposing entity objects directly.

### EmployeeDTO

```java
public class EmployeeDTO {

    private Long id;
    private String name;
    private String email;
    private Integer age;
}
```

### Benefits

* Better Security
* Loose Coupling
* Cleaner API Responses

---

## ⚙ ModelMapper

Used ModelMapper for Entity ↔ DTO conversion.

Example:

```java
EmployeeDTO dto =
        modelMapper.map(employeeEntity, EmployeeDTO.class);
```

---

## 📡 REST API Endpoints

| Method | Endpoint        | Description             |
| ------ | --------------- | ----------------------- |
| POST   | /employees      | Create Employee         |
| GET    | /employees      | Get All Employees       |
| GET    | /employees/{id} | Get Employee By Id      |
| PUT    | /employees/{id} | Update Employee         |
| PATCH  | /employees/{id} | Partial Update Employee |
| DELETE | /employees/{id} | Delete Employee         |

---

## 📬 ResponseEntity Usage

Implemented ResponseEntity to send proper HTTP status codes.

Examples:

```java
return ResponseEntity.ok(employeeDTO);
```

```java
return ResponseEntity.status(HttpStatus.CREATED)
                     .body(employeeDTO);
```

```java
return ResponseEntity.notFound().build();
```

### Status Codes Used

* 200 OK
* 201 CREATED
* 204 NO CONTENT
* 400 BAD REQUEST
* 404 NOT FOUND

---

## 📚 Concepts Learned

* Spring Boot Fundamentals
* MVC Architecture
* RESTful API Development
* Dependency Injection
* Spring Data JPA
* Hibernate ORM
* DTO Pattern
* ModelMapper
* ResponseEntity
* CRUD Operations
* Exception Handling
* H2 Database Integration

---

## 🔮 Future Improvements

* Global Exception Handling
* Validation using @Valid
* MySQL Integration
* Spring Security
* JWT Authentication
* Swagger/OpenAPI Documentation
* Pagination & Sorting

---

## 👨‍💻 Author

Dayanand Kumar Gupta

Aspiring Backend & Full Stack Developer passionate about building scalable REST APIs using Java and Spring Boot.
