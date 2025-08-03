This is a Full-Stack University Management System built using Spring Boot, Thymeleaf, Hibernate, and Spring Security.
This project provides a complete solution to manage branches, faculties, sections, and students, with authentication and role-based access control.

📌 Features
--> Authentication & Authorization

--> Custom login & signup system using Spring Security

--> Role-based access control

--> University Management

--> CRUD operations for Branch, Faculty, Section, and Student

--> Exception handling with custom error messages

UI Layer

--> Thymeleaf templates for user interaction

--> CSS & JavaScript for improved user experience

REST API Support

--> Exposes endpoints for managing university data

Robust Exception Handling

--> Global exception handler with custom exception classes

Unit Testing

--> JUnit & Spring Boot Test for controllers and application layer

📂 Project Structure
bash
Copy
Edit
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
│   │   │   │   ├── controller       # REST Controllers
│   │   │   │   ├── entity           # JPA Entities
│   │   │   │   ├── repository       # Spring Data Repositories
│   │   │   │   ├── services         # Service Interfaces & Implementations
│   │   │   │   ├── exceptionclass   # Custom Exceptions
│   │   │   │   └── globalexceptionhandler # Centralized Exception Handler
│   │   │   └── security
│   │   │       ├── config           # Security Configuration
│   │   │       ├── controller       # Auth Controllers
│   │   │       ├── entity           # User Credential Entity
│   │   │       ├── repository       # User Repository
│   │   │       ├── services         # Auth Services
│   │   │       └── uicontroller     # UI Controllers for Thymeleaf
│   │   └── resources
│   │       ├── application.yml      # Main configuration
│   │       ├── templates            # Thymeleaf HTML templates
│   │       └── static
│   │           ├── style            # CSS files
│   │           └── script           # JavaScript files
│   └── test/java/com/university
│       ├── BhardwajUniversitySoftwareApplicationTests.java
│       └── application/controller   # Controller Test Classes
⚙️ Tech Stack
Backend: Spring Boot, Spring Security, Hibernate, JPA

Frontend: Thymeleaf, HTML, CSS, JavaScript

Database: Configurable via application.yml (e.g., MySQL, H2)

Build Tool: Maven

Testing: JUnit, Spring Boot Test

Version Control: GitHub

🚀 Getting Started
Prerequisites
Java 11+

Maven 3.6+

PostgresSQL or H2 Database

-->Steps to execute
# Clone repository
git clone https://github.com/your-username/universityhub2.0.git
cd universityhub2.0

# Build project
./mvnw clean install

# Run application
./mvnw spring-boot:run
Access the application at:
👉 http://localhost:8080

📌 API Endpoints (Sample)
Method	Endpoint	    Description
GET	   /api/branches	List all branches
POST	 /api/branches	Create a new branch
GET	   /api/students	List all students
POST	 /api/students	Create a new student
POST	 /signup	      Register a new user
POST	 /login	        Authenticate user

🧪 Testing
To run tests:
./mvnw test

Includes unit tests for controllers and application layer.

📸 UI Preview
Login Page

Sign Up Page

Dean Dashboard

Branch, Faculty, Section, and Student Directories

📖 Author
👨‍💻 Developed with dedication and hard work by Prashant Bhardwaj
✨ This project reflects a full-stack implementation of a university management portal.
