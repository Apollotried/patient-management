# Patient Management Microservice System

![Microservice Architecture](https://img.shields.io/badge/architecture-microservice-blue)
![Spring Boot](https://img.shields.io/badge/framework-springboot-brightgreen)
![Docker](https://img.shields.io/badge/container-docker-blue)
![Kafka](https://img.shields.io/badge/messaging-kafka-purple)
![gRPC](https://img.shields.io/badge/communication-grpc-lightblue)
![AWS](https://img.shields.io/badge/deployment-aws-orange)
![AWS CDK](https://img.shields.io/badge/infrastructure-AWS_CDK-ff69b4)
![LocalStack](https://img.shields.io/badge/testing-LocalStack-yellow)


## System Overview
A complete patient management platform featuring:
- RESTful APIs with Spring Boot
- Microservices communication via gRPC
- Event-driven architecture with Kafka
- JWT-based authentication
- Dockerized deployment
- **AWS Infrastructure as Code (IaC) with CDK**
- **Local development with LocalStack emulation**

## Architecture
<img width="1585" height="850" alt="image" src="https://github.com/user-attachments/assets/911cefaf-ef1b-412f-adb2-8c9b399314ca" />

## AWS Infrastructure

This project focuses on practicing AWS Infrastructure as Code (IaC) using AWS CDK (Cloud Development Kit) to define and deploy cloud resources in a reproducible way.

### Core Infrastructure Components

| Service | Purpose | Configuration |
|---------|---------|---------------|
| **VPC** | Network isolation | Multi-AZ setup with public/private subnets |
| **ECS Fargate** | Container orchestration | Serverless compute with auto-scaling |
| **RDS PostgreSQL** | Relational databases | Multi-service databases with secure credentials |
| **MSK (Kafka)** | Event streaming | Multi-broker cluster for microservices communication |
| **Application Load Balancer** | Traffic distribution | Public-facing API gateway |
| **CloudWatch Logs** | Centralized logging | Structured logs with retention policies |
| **Route53 Health Checks** | Service monitoring | Database connectivity monitoring |

## Local Development with LocalStack

### Prerequisites
- **Docker & Docker Compose** - For containerized development environment
- **AWS CLI configured with dummy credentials** - For interacting with LocalStack
- **LocalStack CLI** - For managing the local AWS services
- **Java 17+ and Maven** - For building and deploying the CDK application

### LocalStack Services Used
- **CloudFormation** - Stack deployment and management
- **EC2/VPC** - Networking and virtual private cloud
- **ECS** - Container orchestration and task management
- **RDS** - Database instances and management
- **MSK** - Kafka clusters and event streaming
- **ELBv2** - Load balancing and traffic distribution
- **Route53** - Health checks and DNS management

### Deployment Script
<img width="931" height="561" alt="image" src="https://github.com/user-attachments/assets/602c21da-6131-47d2-8c03-8ff8f2e822b3" />


## Infrastructure Configuration Details

### VPC Configuration
- **2 Availability Zones** for high availability
- **Public and private subnets** for network segmentation
- **NAT gateways** for outbound connectivity from private subnets

### ECS/Fargate Configuration
- **256 CPU units / 512 MB memory** per service
- **AWS Cloud Map** for service discovery
- **CloudWatch logging** with 1-day retention
- **Fargate** for serverless container execution

### Database Configuration
- **PostgreSQL 17.2** database engine
- **Burstable instance classes** (t2.micro) for cost efficiency
- **Automated backup disabled** (for development purposes)
- **Automatic secret rotation** for secure credential management
- **20GB allocated storage** per database instance

### MSK Configuration
- **Kafka 2.8.0** version
- **2 broker nodes** distributed across Availability Zones
- **kafka.m5.xlarge instances** for optimal performance
- **Default AZ distribution** for broker placement

## Development Notes

This project serves as a practice ground for:

- **AWS CDK infrastructure patterns** and best practices
- **Microservices deployment strategies** in cloud environments
- **Local development with cloud emulation** using LocalStack
- **Infrastructure testing and validation** methodologies
- **Service dependency management** and orchestration
- **Security and networking best practices** in AWS

The infrastructure code demonstrates real-world patterns while maintaining simplicity for educational purposes, providing hands-on experience with production-like AWS environments in a local development setting.
