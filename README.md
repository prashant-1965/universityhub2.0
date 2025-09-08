# 🎓 University Management System (Bhardwaj University Software)

A **full-stack university management system** built using **Spring Boot, Thymeleaf, Hibernate, and Spring Security**.  
This project provides a **complete solution** to manage branches, faculties, sections, and students — with **authentication, role-based access control, and a user-friendly UI**.

---

## 💡 Features

🔑 **Authentication & Authorization**
- Custom login & signup using **Spring Security**
- Role-based access control (Admin, Faculty, Student)

🏫 **University Management**
- CRUD operations for Branch, Faculty, Section, and Student
- Exception handling with custom error messages

🎨 **UI Layer**
- Thymeleaf templates for dynamic views
- HTML + CSS + JavaScript for an interactive, responsive UI

🌐 **REST API Support**
- Exposes endpoints for managing university data (Branches, Students, Faculties, etc.)

🛡 **Robust Exception Handling**
- Global exception handler
- Custom exception classes for clean error responses

🧪 **Unit Testing**
- JUnit & Spring Boot Test for controllers and service layer

---

## 📂 Project Structure

```plaintext
bhardwajUniversitySoftware
│── pom.xml                     # Maven build file
│── mvnw / mvnw.cmd             # Maven wrapper
│── HELP.md                     # Spring Boot help file
│
├── src
│   ├── main
│   │   ├── java/com/university
│   │   │   ├── BhardwajUniversitySoftwareApplication.java  # Main entry point
│   │   │   ├── application
│   │   │   │   ├── controller           # REST Controllers
│   │   │   │   ├── entity               # JPA Entities
│   │   │   │   ├── repository           # Spring Data Repositories
│   │   │   │   ├── services             # Service Interfaces & Implementations
│   │   │   │   ├── exceptionclass       # Custom Exceptions
│   │   │   │   └── globalexceptionhandler # Centralized Exception Handler
│   │   │   └── security
│   │   │       ├── config               # Spring Security Configuration
│   │   │       ├── controller           # Auth Controllers
│   │   │       ├── entity               # User Credential Entity
│   │   │       ├── repository           # User Repository
│   │   │       ├── services             # Auth Services
│   │   │       └── uicontroller         # UI Controllers for Thymeleaf
│   │   └── resources
│   │       ├── application.yml          # Main configuration file
│   │       ├── templates                # Thymeleaf HTML templates
│   │       └── static
│   │           ├── style                # CSS files
│   │           └── script               # JavaScript files
│   └── test/java/com/university
│       ├── BhardwajUniversitySoftwareApplicationTests.java
│       └── application/controller       # Controller Test Classes
