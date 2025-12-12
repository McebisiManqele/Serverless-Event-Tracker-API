# Project Requirements

## 🔍 1. Project Description

Build a serverless cloud API that allows users to create and view "events" (e.g., meeting reminders, tasks, logs, or any simple data).
The API will have endpoints like:

- POST /events → create an event
- GET /events → list events
- GET /events/{id} → get single event

The project uses:

- AWS Lambda (serverless compute)
- API Gateway (exposes REST API)
- DynamoDB (NoSQL data storage)
- CloudWatch (logging & metrics)
- IAM (permissions)
- S3 (optional: static frontend or logs)
- GitHub Actions (CI/CD deploy pipeline via IaC)

It's small, portable, cheap, and fully cloud-native.

## ❗ 2. The Problem It Solves

Companies need lightweight, scalable APIs without managing servers.
Traditional APIs require:

- servers
- patching
- scaling
- networking configuration

This project solves that by using serverless architecture — meaning:

- no servers
- pay-per-use
- auto-scaling
- event-driven

It demonstrates you understand modern cloud practices that companies actually use.

## 🏆 3. What Success Looks Like

A recruiter should see:

✅ A fully deployed, working cloud API
— With a real endpoint they can hit (or at least a demo video / screenshots).

✅ A clean architecture diagram
— Showing events → API → Lambda → DynamoDB → CloudWatch.

✅ A GitHub repo with:
- /infrastructure folder (Terraform or AWS CDK or CloudFormation)
- API code (Python, Node, or Java)
- CI/CD pipeline workflow
- README with instructions + diagram + costs + lessons learned

✅ Logging + monitoring visible
Screenshots from CloudWatch metrics and DynamoDB table.

## ❇️ Optional Nice Touch

A tiny frontend hosted on S3 that hits the API (simple HTML form).
This is optional, but it impresses recruiters a lot.

## ⚙️ 4. Tools You'll Use

| Layer | Tech | Why |
|-------|------|-----|
| Compute | AWS Lambda | Easiest, fastest backend on AWS |
| API | API Gateway | Public endpoints without servers |
| Database | DynamoDB | Serverless NoSQL — cheap & scalable |
| Monitoring | CloudWatch Logs + Metrics | Industry standard |
| Security | IAM Roles & Policies | Show understanding of least-privilege |
| Storage (optional) | S3 | Host static website / store logs |
| Infra-as-Code | Terraform / AWS CDK | Looks very professional |
| CI/CD | GitHub Actions | Automate deployment |
| Language | Python or Node.js | Simple for Lambda |

## 🧱 5. Architecture Breakdown

```
Client (Postman / S3 Website)
        ↓
API Gateway (REST API)
        ↓
Lambda Function(s)
        ↓
DynamoDB Table (Event storage)
        ↓
CloudWatch (Logs, metrics)
```

Bonus:
GitHub → GitHub Actions → AWS (auto deploy)

## 🛠️ 6. Features to Implement (Simple but Professional)

### MVP (must-have)

- Create event
- Retrieve all events
- Retrieve event by ID
- Store event data in DynamoDB
- Log every request in CloudWatch
- Secure API (API keys or IAM auth)

### Additional Nice Extras (choose 1–2)

- TTL (auto-expiring events in DynamoDB)
- Retry / error handling with CloudWatch alarms
- S3-hosted frontend
- Swagger / OpenAPI documentation
- Basic dashboard using CloudWatch Metrics

## 📝 7. README Structure (For Resume + Recruiters)

Include:

- Project Summary
- Architecture Diagram
- Why Serverless
- Tools Used
- How to Deploy
- API Endpoints
- Lessons Learned
- Future Improvements

This makes it look like a real production service, not a toy.