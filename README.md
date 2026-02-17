Project Overview

The system includes multiple backend services that work together to provide currency exchange and conversion functionality.

The architecture follows modern microservice principles:

Independent services

Service discovery

API gateway routing

Distributed tracing

Containerized deployment

All services communicate using service names through Eureka rather than hardcoded URLs.

Services Included

Naming Server
Acts as the service registry using Eureka. All services register themselves here.

API Gateway
Serves as the single entry point to the system. It handles routing requests to the correct microservice.

Currency Exchange Service
Provides exchange rate information between currencies.

Currency Conversion Service
Uses the exchange service to calculate final conversion amounts.

Zipkin Server
Collects and visualizes distributed tracing data between services.

Technologies Used

Java 17

Spring Boot

Spring Cloud (Eureka and Gateway)

Maven

Docker

Docker Compose

Zipkin

Key Concepts Demonstrated

This project demonstrates:

Microservices architecture design

Inter-service communication

Service discovery using Eureka

Centralized routing through API Gateway

Distributed tracing using Zipkin

Containerized deployment using Docker Compose

It reflects how modern backend systems are structured in production environments.

Running the Project

All services can be started together using Docker Compose from the project root.
Once started, services automatically register with Eureka and become discoverable.

The Eureka dashboard allows you to verify service registration.
Zipkin dashboard allows you to monitor service-to-service communication.

Purpose of This Project

This project was created to practice real-world microservice architecture and containerization. It is suitable for:

Backend development learning

Portfolio demonstration

Interview preparation

Cloud deployment practice
