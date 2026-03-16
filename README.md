# 🛒 E-Commerce Microservices Backend

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/SpringBoot-Microservices-green)
![Kafka](https://img.shields.io/badge/Event%20Streaming-Kafka-black)
![Docker](https://img.shields.io/badge/Container-Docker-blue)
![AWS](https://img.shields.io/badge/Cloud-AWS-orange)

A distributed e-commerce backend built using **Spring Boot microservices**, **Spring Cloud**, **Kafka**, **Docker**, and deployed on **AWS EC2**.

The project demonstrates **service discovery, API gateway routing, event-driven architecture, circuit breakers, and containerized cloud deployment**.

---

## 🔁 Order Processing Flow

Client places order

↓  

API Gateway routes request

↓

Order Service processes order

↓

Order Service publishes event to Kafka

↓

Inventory Service consumes event

↓

Inventory stock updated

## 📁 Project Structure
ecommerce-microservices
│
├── api-gateway
├── authentication-service
├── cart-service
├── product-service
├── order-service
├── inventory-service
├── discovery-server
│
├── docker-compose.yml
└── README.md


## 🚀 System Architecture

```text
                         Client
                           │
                           ▼
                     API Gateway
                           │
                           ▼
                    Eureka Discovery
                           │
     ┌─────────────────────┼─────────────────────┐
     ▼                     ▼                     ▼
Authentication        Product Service        Cart Service
     │                     │                     │
     └───────────────┬─────┴─────┬───────────────┘
                     ▼           ▼
                  Order Service
                       │
                       ▼
                   Kafka Broker
                       │
                       ▼
                Inventory Service
                       │
                       ▼
                     MySQL
```

This architecture enables **independent deployment, scalability, and loose coupling between services.**

---

## ⚡ Event Driven Communication

The system uses **Apache Kafka** for asynchronous communication between services.

### Event Flow

```text
Order Service
      │
      ▼
Publishes OrderPlacedEvent
      │
      ▼
Kafka Topic
      │
      ▼
Inventory Service
      │
      ▼
Updates Product Stock
```

Benefits:

- Loose coupling
- Scalable system
- Resilient architecture

---

## 🛡 Fault Tolerance

The system implements the **Circuit Breaker pattern using Resilience4j**.

Example:

```text
Order Service → Inventory Service
```

If Inventory Service becomes unavailable:

1. Circuit breaker detects repeated failures  
2. Circuit opens  
3. Requests are temporarily blocked  
4. Prevents cascading failures

Example configuration:

```properties
resilience4j.circuitbreaker.instances.inventoryBreaker.slidingWindowSize=5
resilience4j.circuitbreaker.instances.inventoryBreaker.failureRateThreshold=50
resilience4j.circuitbreaker.instances.inventoryBreaker.waitDurationInOpenState=10s
```

---

## 🔄 Service Communication

### Synchronous

Used for real-time interactions.

```
Feign Client / REST APIs
```

Example:

```
Cart Service → Product Service
Order Service → Inventory Service
```

---

### Asynchronous

Used for event processing.

```
Apache Kafka
```

Example:

```
Order Service → Kafka → Inventory Service
```

---

## 🛠 Tech Stack

### Backend

```
Java
Spring Boot
Spring Cloud
Spring Data JPA
Spring Security
Resilience4j
```

### Infrastructure

```
Docker
Docker Compose
Kafka
Zookeeper
MySQL
```

### Cloud

```
AWS EC2
```

### Service Discovery

```
Netflix Eureka
```

---

## 📦 Containerized Deployment

Each service runs in its own **Docker container**.

Infrastructure containers:

```
MySQL
Kafka
Zookeeper
Eureka Server
```

Application containers:

```
API Gateway
Authentication Service
Product Service
Cart Service
Order Service
Inventory Service
```

All services are orchestrated using **Docker Compose**.

---

## ☁️ Deployment

The application is deployed on **AWS EC2**.

Steps:

```
1. Launch EC2 instance
2. Install Docker and Docker Compose
3. Clone repository
4. Build services using Maven
5. Run docker-compose
```

Run:

```bash
docker-compose up -d
```

---

## 🌐 API Endpoints

Example endpoints through API Gateway:

```
GET    /api/products
POST   /api/products
POST   /api/cart/add
POST   /api/order/place
```

Example request:

```json
POST /api/products

{
  "name": "iPhone 17",
  "price": 89999,
  "quantity": 10
}
```

---

## 🔎 Service Discovery

Eureka dashboard:

```
http://<EC2_PUBLIC_IP>:8761
```

API Gateway:

```
http://<EC2_PUBLIC_IP>:8087
```

---

## 📊 Key Features

✔ Microservices architecture  
✔ Service discovery with Eureka  
✔ API Gateway routing  
✔ Event driven communication using Kafka  
✔ Circuit breaker using Resilience4j  
✔ Docker containerization  
✔ AWS cloud deployment  
✔ Scalable distributed backend

---
## 🚧 Future Improvements

- Kubernetes deployment
- Distributed tracing with Zipkin
- CI/CD pipeline with GitHub Actions
- API documentation using Swagger

---

##  Author

Sakshi Kulkarni
