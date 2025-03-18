# 🧠 Quiz Application - Microservices Architecture

🚀 **This project started as a Monolithic Application and later migrated to a Microservices Architecture.**

---

## ⚙️ Tech Stack & Dependencies
- **Spring Boot** for building the services.
- **MySQL** for data storage.
- **Eureka Discovery Client** for service registration and discovery.
- **OpenFeign** for seamless communication between microservices.
- **Spring Cloud Gateway** for API gateway management.

---

## 🏗️ Microservices Structure
The application consists of two core services:

1. **Quiz Service**  
   📌 Handles quiz-related operations.  

2. **Question Service**  
   📌 Handles question generation, retrieval, and scoring.  

🔹 The **Quiz Service** runs on **one instance**.  
🔹 The **Question Service** runs on **two instances** for load balancing.  

---

## 🔌 API Endpoints
### **Quiz Service Endpoints**
> Uses **OpenFeign** for internal service communication.  
```http
POST /question/generate
Description: Generates a quiz with random questions.
GET /question/getQuestions
Description: Fetches available questions for a quiz.
POST /question/getScore
Description: Calculates and returns the user's score.


🌐 Service Registration
✅ Each service automatically registers itself with Eureka Server.
✅ Services are load-balanced using OpenFeign.

📍 API Gateway Configuration
🔸 The API Gateway handles routing and directs requests to appropriate services.
🔸 Ensures secure and efficient communication between services.

🏁 Getting Started
Step 1: Clone the Repository
git clone <repository-url>
cd QuizApplication
Step 2: Database Configuration (MySQL)
Create a database named quiz_db.
Update the application.properties files for each service with the appropriate database credentials:
spring.datasource.url=jdbc:mysql://localhost:3306/quiz_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
Step 3: Eureka Server Setup
Run your Eureka server before starting the services.

Step 4: Start the Microservices
Start the services in the following order:
# Start Eureka Server
./mvnw spring-boot:run -Dspring-boot.run.profiles=eureka

# Start Quiz Service
./mvnw spring-boot:run -Dspring-boot.run.profiles=quiz

# Start Question Service (2 instances for load balancing)
./mvnw spring-boot:run -Dspring-boot.run.profiles=question
./mvnw spring-boot:run -Dspring-boot.run.profiles=question2

# Start API Gateway
./mvnw spring-boot:run -Dspring-boot.run.profiles=gateway
Step 5: Testing
Test endpoints via Postman or Swagger.

/quiz-service
  └── src
      └── main
          └── java
              └── com.quizservice
/question-service
  └── src
      └── main
          └── java
              └── com.questionservice
/api-gateway
  └── src
      └── main
          └── java
              └── com.apigateway
/eureka-server
  └── src
      └── main
          └── java
              └── com.eurekaserver
📄 Future Enhancements
✅ Implement authentication & authorization.
✅ Add caching mechanisms for improved performance.
✅ Introduce CI/CD pipelines for automated deployment.

🧑‍💻 Author
Charan Mahendrakar
📧 charanmahendrakar9@gmail.com

---

### 🔹 **Next Steps**
- If you'd like specific sections enhanced (e.g., badges, installation GIFs, etc.), let me know! 🚀
