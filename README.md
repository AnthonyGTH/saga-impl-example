# saga-impl-example

This is a basic Spring Boot project that demonstrates how to implement a reactive **orchestrated saga** using the [agt-orchestrated-saga](https://github.com/your-org/agt-orchestrated-saga) library. It shows how to coordinate multiple transactional steps with automatic rollback handling in case of failures.

---

## 📦 Requirements

- Java 21+
- Spring Boot 3.4.5
- Project Reactor
- [agt-orchestrated-saga](https://github.com/your-org/agt-orchestrated-saga) library

---

## ⚙️ Setup

### Install the `agt-orchestrated-saga` library

In your `pom.xml`, include:

```xml
<dependency>
  <groupId>com.agt</groupId>
  <artifactId>agt-orchestrated-saga</artifactId>
  <version>1.0.0</version>
</dependency>
(see full pom.xml below)

▶️ Run the Example
[STEP] Creating user account...
[STEP] Saving user profile...
[STEP] Sending welcome email...
[STEP] Assigning permissions...
[STEP] Sending welcome gift...
[ROLLBACK] Removing permissions...
[ROLLBACK] Cancelling welcome email...
[ROLLBACK] Reverting profile save...
[ROLLBACK] Deleting user account...
This simulates a user onboarding flow where the last step fails and triggers a reverse rollback of all previous steps.

🧠 How It Works
The @SagaTransaction annotation initializes a new SagaContext for each request.

Methods annotated with @SagaCompensation are registered automatically along the chain.

If any step fails, registered compensations are executed in reverse order.

📂 Project Structure
saga-impl-example/
├── SagaExampleApp.java                   // Main Spring Boot entry point
├── ProcessContext.java                   // Shared state across steps
├── UserOnboardingSagaTransactionV1.java  // Contains @SagaTransaction flow
└── UserOnboardingSagaStepV1.java         // Contains @SagaCompensation steps
⚠️ Important: AOP Class Separation
For the annotations to be intercepted correctly by Spring AOP, the following separation is mandatory:

@SagaTransaction must reside in a class like *SagaTransactionV1.java

@SagaCompensation must reside in a different class like *SagaStepV1.java

This is required because Spring proxies can only intercept external method calls between beans, not self-invocations.

🔧 Required Configuration
Make sure you include the SagaConfig class (provided by the library or implemented yourself), annotated with:

java
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(basePackages = "com.agt.saga") // or your package
public class SagaConfig {
}
Without this, AOP interception for @SagaTransaction and @SagaCompensation will not work.

📄 License
MIT © Anthony Gabriel Torres
