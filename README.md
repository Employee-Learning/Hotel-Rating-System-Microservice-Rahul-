# Hotel Rating System – Microservices Architecture

## Project Overview

The **Hotel Rating System** is a distributed backend application built using **Microservices Architecture with Spring Boot and Spring Cloud**.
The system allows users to register, view hotels, and provide ratings for hotels.

The project demonstrates how independent services communicate through **API Gateway**, **Service Discovery**, and **centralized configuration**, following industry-level backend architecture.

---

## Microservices in the Project

This system consists of multiple independent microservices:

### 1. User Service

Manages all operations related to users.

Responsibilities:

* Create users
* Fetch user details
* Retrieve user ratings

Example Endpoints:

```
GET /users
GET /users/{userId}
POST /users
```

---

### 2. Hotel Service

Handles hotel-related data.

Responsibilities:

* Add hotels
* Retrieve hotel details
* Manage hotel information

Example Endpoints:

```
GET /hotels
GET /hotels/{hotelId}
POST /hotels
```

---

### 3. Rating Service

Stores ratings provided by users for hotels.

Responsibilities:

* Add rating
* Get ratings by user
* Get ratings by hotel

Example Endpoints:

```
POST /ratings
GET /ratings/users/{userId}
GET /ratings/hotels/{hotelId}
```

---

## Infrastructure Services

### API Gateway

Acts as a **single entry point** for all client requests and routes them to the appropriate microservice.

Server Port:

```
8084
```

Responsibilities:

* Request routing
* Security
* Centralized API access

---

### Service Registry (Eureka)

All services register themselves with the **Service Registry** so that they can discover and communicate with each other dynamically.

Benefits:

* Dynamic service discovery
* Load balancing
* No hardcoded service URLs

Example URL:

```
http://localhost:8761
```

---

### Config Server

The **Spring Cloud Config Server** provides centralized configuration management for all microservices.

Benefits:

* Centralized configuration
* Environment management
* Easy updates for all services

Example Config Server:

```
http://localhost:9296
```

---

## Security

Authentication is implemented using **JWT and Okta**.

Security Flow:

1. User authenticates via Okta
2. Okta generates a JWT token
3. Client sends token with requests
4. API Gateway validates the token
5. Request is forwarded to the microservices

---

## Event Communication

The project also demonstrates asynchronous communication using **Apache Kafka**.

Benefits:

* Event-driven architecture
* Loose coupling between services
* High scalability

---

## Technology Stack

Backend:

* Java 17
* Spring Boot
* Spring Cloud

Microservice Tools:

* Eureka Service Registry
* Spring Cloud Config Server
* Spring Cloud Gateway
* Feign Client

Security:

* JWT Authentication
* Okta

Messaging:

* Apache Kafka

Build Tool:

* Maven

---

## Project Structure

```
Hotel-Rating-System-Microservice
│
├── UserService
├── HotelService
├── RatingService
├── ApiGateway
├── ConfigServer
└── ServiceRegistry
```

---

## System Architecture

```
Client
   |
API Gateway (8084)
   |
Service Registry (Eureka)
   |
-----------------------------
|           |               |
User      Hotel           Rating
Service   Service         Service
|           |               |
Database   Database       Database

Config Server
Kafka
```

---

## Future Enhancements

* React Frontend
* Docker containerization
* Deployment on AWS
* API monitoring with Prometheus & Grafana
* Distributed tracing

---

## Author

Rahul Gupta

Microservices Backend Project built for learning and demonstration of distributed system architecture.
