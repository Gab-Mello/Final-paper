![BovInA](https://i.imgur.com/1UzEy1n.png) 
## Project description
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white) ![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white) ![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white) ![AWS](https://img.shields.io/badge/AWS-%23FF9900.svg?style=for-the-badge&logo=amazon-aws&logoColor=white)

BovInA is a digital platform developed as part of my course completion project (TCC) in collaboration with a colleague, and it was presented at MOSTRATEC, the largest science and technology fair in Latin America, where it won 3rd place in the Computer Science category. This repository contains the back-end of the system—the portion I was responsible for developing—which focuses on digitizing and optimizing the management of In Vitro Embryo Production (PIVE) in cattle. By shifting traditional manual processes to a centralized digital system, BovInA enhances the organization, monitoring, and efficiency of crucial steps in the PIVE process, such as oocyte collection, in vitro fertilization, and embryo cultivation. The ultimate goal is to provide a more effective tool for managing the in vitro production of bovine embryos, contributing to genetic improvement and advancing reproductive efficiency in the livestock sector.
## Main features
- **Animal Management**: Registration and tracking of donors, receivers, and bulls.
- **Oocyte Collection**: Recording collections and monitoring the number of total and viable oocytes.
- **Embryo Production**: Monitoring success rates in the production of viable embryos.
- **Pregnancy**: Tracking the success of pregnancies from productions.
- **Advanced Filters**: In-depth data analysis with filters such as:
  - Efficiency in the production of viable embryos by animal.
  - Average number of viable oocytes collected per donor.
  - Best combination of bull and donor.

## Technologies used in the system's back-end
- **Java**: Primary programming language for the system.
- **Spring Boot**:  Framework used for building web applications, including the RESTful API layer.
- **MySQL**: Database for storing and managing data.
- **Swagger**: Tool for API documentation to simplify integration between the backend and frontend, and to assist in system maintenance.
- **AWS**: Deployment of the backend using EC2 service, with the MySQL database hosted on RDS.

## System Architecture
![System Architecture](https://i.imgur.com/FYP72wJ.png)

The system architecture is organized in layers for improved scalability and maintenance:

1. **Front-end** (HTML / CSS / JavaScript / React / React Native)
   - A user-friendly interface designed for data input and visualization.
   - Communicates with the backend through a REST API.

2. **Back-end** (Java / Spring Boot)
   - **Controllers**: Handles request validation and management.
   - **Services**: Business logic and data processing.
   - **Repositories**: Database interaction using JPA/Hibernate.
   - **MySQL Database**: Structured storage for managing system data.


