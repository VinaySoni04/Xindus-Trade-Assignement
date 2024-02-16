# Documentation

# Table of Contents
1. Introduction
2. System Overview
3. Architecture
4. Technologies Used
5. Setup Instructions
6. API Documentation
7. Testing
8. Conclusion

# 1. Introduction
The Wishlist Management System is a web application designed to allow users to create, manage, and track their wishlists. It provides RESTful API endpoints for wishlist management, user authentication, and item manipulation.

# 2. System Overview
The system comprises the following main components:-
- **User Controller:-** Handles HTTP requests related to user management.
- **User Service:-** Implements the business logic for managing users.
- **User Repository:-** Provides CRUD operations for interacting with the database.
- **User Authentication:-** Handles user authentication and authorization.
- **Wishlist Controller:-** Handles HTTP requests related to wishlist management.
- **Wishlist Service:-** Implements the business logic for managing wishlists.
- **Wishlist Repository:-** Provides CRUD operations for interacting with the database.
- **Database Integration:-** Integrates the application with a relational database using Spring Data JPA.

# 3. Architecture
The system follows a layered architecture pattern:
- **Presentation Layer:-** RESTful API endpoints exposed by controllers.
- **Service Layer:-** Implements business logic and interacts with repositories.
- **Data Access Layer:-** Repositories perform CRUD operations on the database.

# 4. Technologies Used
- **Spring Boot:-** Provides the framework for building the application. You can refer https://docs.spring.io/spring-boot/docs/current/reference/html/
- **Spring Security:-** Handles user authentication and authorization. You can refer https://docs.spring.io/spring-security/reference/getting-spring-security.html
- **Spring Data JPA:-** Simplifies database interactions. You can refer https://docs.spring.io/spring-data/jpa/reference/jpa/getting-started.html
- **MySQL:-** Relational database used for storing user information and wishlist items.
- **JUnit and Mockito:-** Used for unit testing. You can refer https://junit.org/junit5/

# 5. Setup Instructions
To set up the Wishlist Management System locally:-
- Clone the repository from GitHub.
- Configure the mysql database connection properties in the application.properties file.
- Build and run the application using Maven or Gradle.
- Access the API endpoints using a tool like Postman.

# 6. API Documentation
The following API endpoints are available:
- GET /api/wishlists: Retrieve a user's wishlist.
- POST /api/wishlists: Create a new wishlist item.
- DELETE /api/wishlists/{id}: Remove a wishlist item by ID.

# 7. Testing
Unit tests have been implemented using JUnit to validate the functionality of wishlist-related components, including controllers, services, and repositories. These tests ensure the reliability of the application and help maintain code quality.

# 8. Conclusion
The Wishlist Management System provides a robust platform for users to manage their wishlists effectively. With its user-friendly interface and comprehensive feature set, it offers a seamless experience for users to track their desired items.
