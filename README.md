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

#### Database Architecture

Each microservice has its own **MySQL** database, ensuring data is stored separately for each service. This approach follows the **database-per-service** pattern, which allows for better isolation, security, and easier scaling. Key benefits include:
- Independent scaling of services and databases.
- Fault isolation, ensuring that issues in one database do not affect others.
- Flexibility in using different data storage technologies in the future, if needed.

### Technology Stack

1. **Backend:**
   - **Spring Boot:** Provides a powerful, production-ready environment for creating stand-alone, web-based applications. It serves as the foundation for building the microservices architecture.
   - **Spring Security with JWT Authentication:** Handles user authentication and authorization securely across the microservices.
   - **MySQL:** Each microservice has its own MySQL database for independent data management.

2. **Frontend:**
   - **Thymeleaf:** A server-side template engine integrated with Spring Boot, used for rendering dynamic HTML content.
   - **JavaScript (AJAX):** Enables asynchronous communication with the server to dynamically load content without refreshing the page, improving user experience

### AI Innovation

The **Enterprise Hub** platform leverages the **Google Gemini API** to enhance inventory management by analyzing ingredient purchasing and usage data. 

#### Key Features:
- **Data Analysis**: The system sends ingredient purchase and usage information to the Gemini API, which evaluates price trends and consumption rates.
- **Smart Recommendations**: Gemini generates recommendations on when to buy more or less of specific ingredients, helping businesses optimize their procurement strategies and reduce costs.
- **Price Trend Insights**: The AI analyzes historical data to predict future price movements, allowing for strategic purchasing decisions.

By integrating AI-driven insights, **Enterprise Hub** empowers businesses to manage their ingredient inventories efficiently, leading to improved profitability and operational efficiency.



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
### Docker Compose for Deployment

After all the microservices were containerized using Jib, we utilized a **Docker Compose** file to simplify the deployment process. The `docker-compose.yml` file defines the microservices, including **AccountsDue**, **AiTest**, **BusinessOverview**, **CustomerManagement**, **ProductAndServices**, **RawMaterials**, **SalesAndOrders**, along with essential services like the **API Gateway**, **Discovery Server**, and **Security**.

The Docker Compose setup allows us to:
- Launch all microservices simultaneously in a single environment.
- Automatically configure networking between the services.
- Ensure proper service discovery and load balancing through the **Discovery Server** and **API Gateway**.


## Conclusion

The **Enterprise Hub** platform provides a comprehensive, user-friendly solution for businesses seeking to streamline their operations across various functions such as inventory management, sales, accounting, human resources, and customer relationship management. With its microservices architecture, the system ensures scalability and optimal performance while facilitating seamless inter-service communication.

By incorporating advanced AI capabilities through the **Google Gemini API**, the platform not only enhances decision-making with intelligent recommendations but also empowers businesses to manage their resources more effectively. As a result, **Enterprise Hub** stands as an ideal choice for startups and medium-sized enterprises looking to improve operational efficiency and drive growth in a competitive market.

---


## Team Binary Duo

| Name               | Email                     | LinkedIn Profile                                      | University               |
|--------------------|---------------------------|------------------------------------------------------|--------------------------|
| Md. Moonaz Rahman  | mdmoonaz023@gmail.com     | [LinkedIn](https://www.linkedin.com/in/moonaz023/)   | University of Barishal   |
| Neamul Haq         | neamul.cse6.bu@gmail.com  | [LinkedIn](https://www.linkedin.com/in/neamulhaq/)   | University of Barishal   |


### Video Documentation

You can watch the [video documentation here](https://youtu.be/2Bbav0eDb40?si=Jd_wdUCFVCaxxu6S).

### Video Documentation

[![Watch the video](https://images.app.goo.gl/vwkJsxUDWPSVTurB8)]([https://www.youtube.com/watch?v=VIDEO_ID](https://youtu.be/2Bbav0eDb40?si=Jd_wdUCFVCaxxu6S))

