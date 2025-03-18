Here's a simpler and more standard README:

---

# 🍕 SliceMate-Backend
Microservices-based backend for a Pizza Delivery Application built with Spring Boot.

## 🛠️ Tech Stack
- **Java 23**, **Spring Boot**
- **Spring Cloud** (Eureka, Feign, Gateway)
- **RabbitMQ**, **PostgreSQL**
- **Docker** (Optional)
- **OAuth Authentication (Google)**
- **Spring Security**

## 🚀 Getting Started
1. **Start Eureka Server:**  
   ```bash
   cd eureka-server && mvn clean install && mvn spring-boot:run
   ```
2. **Run Services (Example: user-service):**  
   ```bash
   cd user-service && mvn clean install && mvn spring-boot:run
   ```
3. **Run API Gateway:**  
   ```bash
   cd API-Gateway && mvn clean install && mvn spring-boot:run
   ```

## 📌 Environment Variables
Create `.env` files where necessary or configure via GitHub Secrets.

---
