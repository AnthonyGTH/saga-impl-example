# saga-impl-example

This is a basic Spring Boot example demonstrating the usage of **AGT Orchestrated Saga**, a lightweight annotation-based library for handling orchestrated sagas in reactive microservices using Project Reactor.

---

## 📌 What it demonstrates

This project simulates a user onboarding process composed of **5 transactional steps**:

1. Create user account  
2. Save user profile  
3. Send welcome email  
4. Assign permissions  
5. Send welcome gift _(this step is forced to fail to demonstrate rollback)_

Each step has an associated compensation method to undo the operation in case the saga fails.

---

## 📦 Requirements

- Java 17+ (Java 21 recommended)
- Maven 3.8+
- Spring Boot 3.1+
- Project Reactor

---

## ▶️ How to Run

Clone the project and run it using Maven or your preferred IDE:

```bash
mvn spring-boot:run
