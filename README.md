# Documentation

# Table of Contents
1. Introduction
2. System Overview
3. Architecture
4. Technologies Used
5. Setup Instructions
6. API Documentation and API testing
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
- Configure the MySQL database connection properties in the application.properties file. And create a database using MySQL Command Line.
- Build and run the application using Maven or Gradle.
- Access the API endpoints using a tool like Postman.

# 6. API Documentation and API testing
The following API endpoints are available:
- POST /user/add: Create a new user.
- POST /auth/login: Authenticates the user.
- GET /api/wishlists: Retrieve a user's wishlist.
- POST /api/wishlists: Create a new wishlist item.
- DELETE /api/wishlists/{id}: Remove a wishlist item by ID.

You can test APIs using Postman.
- For POST /user/add:
  - Create a new request in Postman.
  - Set the request URL to http://localhost:8088/user/add.
  - Choose the POST HTTP method.
  - Go to body->raw then select "JSON" from the type dropdown. After that provide user details in JSON form like username and password. 
  - Send the request.

- For POST /auth/login:
  - Create a new request in Postman.
  - Set the request URL to http://localhost:8088/auth/login.
  - Choose the POST HTTP method.
  - Go to body->raw then select "JSON" from the type dropdown. After that provide user details in JSON form like username and password. 
  - Send the request.
    
- For GET /api/wishlists:
  - Create a new request in Postman.
  - Set the request URL to http://localhost:8088/api/wishlist.
  - Choose the GET HTTP method.
  - In Postman, go to the "Authorization" tab of your request.
  - Select "Basic Auth" from the Type dropdown.
  - Enter the username and password for your user.
  - Send the request.
    
- For POST /api/wishlists:
  - Create a new request in Postman.
  - Set the request URL to http://localhost:8088/api/wishlist.
  - Choose the POST HTTP method.
  - Go to body->raw then select "JSON" from the type dropdown. After that provide item details in JSON form. 
  - In Postman, go to the "Authorization" tab of your request.
  - Select "Basic Auth" from the Type dropdown.
  - Enter the username and password for your user.
  - Send the request.
    
- For DELETE /api/wishlists/{id}:
  - Create a new request in Postman.
  - Set the request URL to http://localhost:8088/api/wishlist.
  - Add the Id of the item you wish to delete like http://localhost:8088/api/wishlist/1.
  - Choose the DELETE HTTP method.
  - In Postman, go to the "Authorization" tab of your request.
  - Select "Basic Auth" from the Type dropdown.
  - Enter the username and password for your user.
  - Send the request.
 
Screenshots of all APIs are attached below.

# 7. Testing
Unit tests have been implemented using JUnit to validate the functionality of wishlist-related components, including controllers, services, and repositories. These tests ensure the reliability of the application and help maintain code quality.

# 8. Conclusion
The Wishlist Management System provides a robust platform for users to manage their wishlists effectively. It offers a seamless experience for users to track their desired items.

# Screenshots of APIs
1. For POST /user/add
<img src="https://github.com/VinaySoni04/Xindus_Trade_Assignment/assets/98009479/7ef3517a-6983-4df4-bed5-2ba3dfc03728" width="600" height="400">

2. For POST /auth/login
<img src="https://github.com/VinaySoni04/Xindus_Trade_Assignment/assets/98009479/5dfea155-54dd-4600-a3c8-d4e43fbd3874" width="600" height="400"> 

3. For GET /api/wishlists
<img src="https://github.com/VinaySoni04/Xindus_Trade_Assignment/assets/98009479/b7b32083-7b98-475c-986b-5538bc62e6ad" width="600" height="400">

4. For POST /api/wishlists
<img src="https://github.com/VinaySoni04/Xindus_Trade_Assignment/assets/98009479/fc36dd69-6a91-42c3-9b5b-53e110cfa2be" width="600" height="400">

5. For DELETE /api/wishlists/{id}
<img src="https://github.com/VinaySoni04/Xindus_Trade_Assignment/assets/98009479/ad34f521-8c85-4c03-9269-5abd5e980536" width="600" height="400">

