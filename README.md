🔐 Spring Boot JWT Authentication (Spring Security + MySQL)

This project is a simple authentication system built with Spring Boot 4, Spring Security, JWT, and MySQL.

It demonstrates how to implement secure user registration and login using token-based authentication instead of session-based security.

The goal of this project was to build a clean, production-style authentication flow using modern Spring Security configuration.

🚀 Features

User registration with encrypted passwords (BCrypt)

Login endpoint that generates a JWT

Stateless authentication using JWT tokens

Role-based authorization (USER / ADMIN)

Custom UserDetailsService backed by MySQL

Secure Spring Security filter chain configuration

🛠 Tech Stack

Java 25

Spring Boot 4.0.1

Spring Security

Spring Data JPA

MySQL

JWT (jjwt library)

Maven

🔄 How Authentication Works

A user registers using /api/auth/register.

The password is encrypted using BCrypt and stored in MySQL.

The user logs in via /api/auth/login.

If credentials are valid, the system generates a JWT.

The client includes the token in future requests:

Authorization: Bearer <JWT_TOKEN>


A custom JWT filter validates the token and sets authentication in the Spring Security context.

This makes the application completely stateless — no HTTP sessions are used.

📌 API Endpoints
Register

POST /api/auth/register

Login

POST /api/auth/login

After login, the response contains a JWT token that must be used for protected endpoints.

⚙️ Configuration

Make sure MySQL is running and create a database:

CREATE DATABASE user_db;


Update application.properties with your DB credentials and JWT secret.

⚠️ The JWT secret must be Base64 encoded.

▶️ Running the Application
mvn clean install
mvn spring-boot:run


Application runs on:

http://localhost:8080
