# Event Scheduler API — Serverless Java + AWS

A lightweight, cloud-native event scheduling API built using Java, AWS Lambda, API Gateway, and DynamoDB.
This project demonstrates how to design, deploy, and operate a real serverless backend using modern AWS tooling.

## 🚀 Overview

This API allows users to create and retrieve scheduled events.
It is fully serverless — no servers to manage, no manual scaling, and minimal cost.

Core features include:

- REST endpoints (Create, List, Fetch by ID)
- Java 17 Lambda functions
- DynamoDB as the persistent data layer
- Infrastructure deployed through AWS SAM
- JSON request/response models
- Production-ready folder structure and clean code design
- CloudWatch logging and monitoring
- IAM security with least-privilege access

This project was built to strengthen cloud skills across Lambda, API Gateway, DynamoDB, and Java backend development.

## 🧩 Architecture

```
Client → API Gateway → Lambda (Java) → DynamoDB
                            ↓
                    CloudWatch Logs
```

### Services Used

- **AWS Lambda** — serverless compute
- **API Gateway** — routing + REST API
- **DynamoDB** — NoSQL event storage
- **AWS SAM** — deployment + IaC
- **CloudWatch Logs** — monitoring and logging
- **IAM** — security and permissions

## 🛠️ Tech Stack

| Layer | Technology |
|-------|------------|
| Language | Java 17 |
| Runtime | AWS Lambda |
| API | AWS API Gateway (REST) |
| Database | DynamoDB |
| Infra | AWS SAM (YAML) |
| Build | Maven |
| Monitoring | CloudWatch |
| Security | IAM Roles & Policies |

## 📡 API Endpoints

### ➤ POST /events

Create a new event.

**Body example:**
```json
{
  "title": "Game Night",
  "date": "2025-02-15",
  "location": "Online"
}
```

### ➤ GET /events

Retrieve all events.

**Sample response:**
```json
[
  {
    "id": "abc123",
    "title": "Game Night",
    "date": "2025-02-15",
    "location": "Online"
  }
]
```

### ➤ GET /events/{id}

Retrieve a single event by ID.

## 📁 Project Structure

```
src/
 └── main/java/com/cloud/events
        ├── CreateEventHandler.java
        ├── ListEventsHandler.java
        ├── GetEventByIdHandler.java
        ├── model/Event.java
        └── util/DynamoDBClientFactory.java

template.yaml
pom.xml
README.md
```

## ⚙️ Local Development

### Build
```bash
mvn clean package
```

### Run locally with SAM
```bash
sam local start-api
```

## 🚀 Deploy to AWS

```bash
sam deploy --guided
```

After deployment, SAM outputs your API Gateway URL, e.g.:
```
https://xxxxxxxxxx.execute-api.region.amazonaws.com/Prod/events
```

## 🧪 Testing

Use Postman or curl:

```bash
curl -X POST https://your-url/events \
  -d '{"title":"Study Session","date":"2025-02-20"}'
```

## 💰 Cost Efficiency

This serverless architecture is extremely cost-effective:
- **Lambda**: Pay only for execution time (milliseconds)
- **API Gateway**: Pay per API call
- **DynamoDB**: Pay for storage and read/write capacity
- **CloudWatch**: Basic logging included in free tier

Typical monthly cost for moderate usage: **< $5**

## 🔒 Security Features

- IAM roles with least-privilege access
- API Gateway request validation
- DynamoDB encryption at rest
- CloudWatch audit logging
- No hardcoded credentials

## 📊 Monitoring & Observability

- CloudWatch Logs for all Lambda executions
- API Gateway access logs
- DynamoDB metrics
- Custom CloudWatch dashboards (optional)
- Error tracking and alerting

## 🎯 What This Project Demonstrates

- Real cloud architecture using AWS serverless services
- Java-based Lambda functions and JSON serialization
- DynamoDB schema design using AWS SDK v2
- API design and cloud deployment
- Production-ready folder structuring
- Logging, error handling, and clean coding principles
- Infrastructure as Code with AWS SAM
- Cost-efficient serverless design patterns

## 📈 Lessons Learned

- Serverless architecture reduces operational overhead significantly
- DynamoDB's single-table design requires careful planning
- Cold starts in Java Lambda can be mitigated with proper optimization
- AWS SAM simplifies serverless deployment compared to raw CloudFormation
- Proper IAM policies are crucial for security and functionality

## 📌 Future Improvements

- Add update/delete endpoints (PUT /events/{id}, DELETE /events/{id})
- Add authentication with AWS Cognito
- Add CloudWatch alarms for error monitoring
- Build a small React or CLI client
- Add pagination and sorting for large event lists
- Implement event TTL (auto-expiring events)
- Add Swagger/OpenAPI documentation
- Set up CI/CD pipeline with GitHub Actions

## 👤 Author

**Mcebisi Thapelo Manqele**  
Software Engineering Student @ WeThinkCode_  
Passionate about cloud computing, Java backend systems, and distributed architectures.

---

*This project showcases production-ready serverless development practices and modern cloud architecture patterns.*