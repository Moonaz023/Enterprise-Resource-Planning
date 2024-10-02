# EnterpriseHub - Comprehensive ERP Platform

## Overview

**EnterpriseHub** is a versatile, web-based ERP (Enterprise Resource Planning) platform designed to streamline business operations for organizations of all sizes. Whether it's managing Production, inventory, sales, accounting, human resources, or customer relationships, EnterpriseHub brings it all together in one place, making it easier for businesses to stay organized and efficient.

The platform includes an intelligent recommendation system that analyzes data to provide insights on the best times to purchase raw materials and optimize production schedules based on customer demand. This smart feature allows businesses to make data-driven decisions, ultimately improving efficiency and profitability.

EnterpriseHub operates on a flexible subscription model, making it accessible to both small startups and large enterprises. The platform is designed with scalability in mind, ensuring that it grows alongside your business.

## Objectives

The key objectives of EnterpriseHub are:

- **Centralized Management**: Provide a unified platform for managing all business operations.
- **User-Friendly Interface**: Ensure an intuitive user experience, making it easy for businesses to navigate and use the platform.
- **Scalability**: Support businesses as they grow, adapting to their needs without disruption.
- **Data-Driven Decisions**: Offer powerful analytics and reporting tools that empower businesses to make informed decisions.

## Motivation

In today’s competitive business environment, managing various operations such as production, inventory, sales, accounting, and HR can be challenging, especially for startups and medium-sized businesses. These businesses often rely on multiple software tools because no single platform provides all the necessary tools in one place. Each department or function whether it's accounting, inventory management, or HR typically requires specialized software, which results in the use of separate systems. This fragmentation leads to inefficiencies, increases the likelihood of errors, and makes it difficult for businesses to maintain a clear overview of their operations.

**EnterpriseHub** addresses this issue by consolidating all essential business functions into a single platform. This eliminates the need for multiple software systems, saving time, reducing errors, and making overall management smoother and more efficient. 

The motivation behind EnterpriseHub is to provide businesses with a simple yet powerful tool that enables them to operate efficiently while making data-driven decisions. Additionally, EnterpriseHub is designed to grow with your business, ensuring that as your company expands, the platform scales to meet your increasing needs.


## Key Features

EnterpriseHub provides a wide range of features to meet the diverse needs of modern businesses:

- **Platform Security**: Implements robust security measures, including data encryption, audit trails, and secure authentication using JWT (JSON Web Token).
- **Inventory Management**: Real-time tracking of stock levels, raw materials, and products, along with supplier and purchase order management.
- **Production Management**: Optimize production schedules and track raw material usage to meet demand efficiently.
- **Sales Management**: Streamline order processing, invoicing, customer database management, and sales reporting.
- **Accounting and Finance**: Manage accounts payable/receivable, track expenses, and automatically generate financial statements.
- **Customer Relationship Management (CRM)**: Support lead management, customer support, and track marketing campaigns.
- **Reporting and Analytics**: Generate custom reports and visualizations, with intelligent recommendations to optimize business operations.

## Why Choose EnterpriseHub?

- **All-in-One Solution**: Manage your entire business from one platform.
- **Data-Driven Insights**: Make smarter decisions with real-time data and intelligent recommendations.
- **Scalable for Growth**: Whether you're a startup or a growing business, EnterpriseHub adapts to your evolving needs.
- **User-Friendly Design**: Intuitive interface for effortless navigation and use.
- **Enhanced Security**: Advanced security features to protect your business data.


## System Design Overview

The **EnterpriseHub** platform follows a microservices architecture to ensure scalability, flexibility, and performance. Each service operates independently, allowing for better modularity and the ability to scale specific components of the system as the business grows.

#### Core Microservices

The project is composed of several key microservices, each responsible for a distinct business function:

- **AccountsDue**: Manages accounts receivable and payable.
- **AiTest**: Provides AI-based features for business insights and smart recomendation.
- **BusinessOverview**: Offers an aggregated view of business performance through dashboards and reports.
- **CustomerManagement**: Handles customer data, including lead management, support, and engagement.
- **ProductAndServices**: Manages products, services,Production and associated inventory.
- **RawMaterials**: Tracks and manages raw materials, ensuring production efficiency.
- **SalesAndOrders**: Oversees sales processing, order management, and invoicing.

Each of these microservices is hosted on separate servers to ensure optimal performance and scalability. This separation enables the system to handle large volumes of requests by distributing the load across multiple servers, ensuring no single service becomes a bottleneck.

#### Key Modules

To facilitate communication between the microservices and manage system-wide concerns like security and load balancing, several additional modules are employed:

- **API Gateway**: Acts as the single entry point for all client requests, routing them to the appropriate microservices. This also allows for easier monitoring, logging, and rate-limiting of incoming requests.
  
- **Discovery Server (Eureka)**: This server is responsible for service discovery, enabling microservices to dynamically register themselves and discover other services. It allows for easy scaling and resilience, as new instances of services can be added or removed without manual intervention.

- **Security Service**: This module ensures that all requests are authenticated and authorized. It leverages JWT (JSON Web Token) for secure authentication, ensuring that only authorized users have access to sensitive endpoints.

#### Load Balancing with OpenFeign

**OpenFeign** is used for inter-service communication within the platform. It simplifies REST client calls and allows for transparent load balancing between microservices. By leveraging OpenFeign alongside **Eureka Discovery**, the system dynamically balances the load between multiple instances of the same microservice, improving reliability and performance. This ensures that no single instance is overwhelmed with requests, leading to higher availability.

## Build & Deployment

### Using Maven Jib for Building Docker Images

To simplify the containerization process, we used **Maven Jib** for building Docker images for each microservice. Jib allows us to automatically package our Spring Boot applications into Docker images without needing a `Dockerfile`.

Here are the steps we followed to create and push the Docker images:

- **Configure Jib in Maven**:
   Maven Jib was configured in the parent pom.xml to automate the process of building Docker images for all microservices. This setup allowed us to package and push each service's Docker image to Docker Hub without needing individual Dockerfile configurations for each service.
   Example:
   ```xml
   <plugin>
       <groupId>com.google.cloud.tools</groupId>
       <artifactId>jib-maven-plugin</artifactId>
       <version>3.1.4</version>
       <configuration>
           <to>
               <image>dockerhubusername/microservice-name:latest</image>
           </to>
       </configuration>
   </plugin>
   
- **Jib** in the parent `pom.xml` handles all microservices, automatically generating Docker images and pushing them to Docker Hub.
- By running a single Maven command at the root of the project, all the microservices are built, containerized, and deployed to the Docker registry.

## Requirements

- **Java 17**
- **Spring Boot**
- **MySQL**
- **Docker**
- **JWT Authentication**
- **Microservices Architecture**

---

