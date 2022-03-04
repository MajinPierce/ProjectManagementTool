# Personal Project Management Tool

![build](https://github.com/MajinPierce/ProjectManagementTool-Heroku/actions/workflows/main_projectmanagementtool.yml/badge.svg?event=push)
[![Quality Gate Status](https://pmt-sonarqube.azurewebsites.net/api/project_badges/measure?project=MajinPierce_ProjectManagementTool-Heroku&metric=alert_status&token=c340e151415bfbc1ee14f94397609b5432e878f5)](https://pmt-sonarqube.azurewebsites.net/dashboard?id=MajinPierce_ProjectManagementTool-Heroku)
[![Reliability Rating](https://pmt-sonarqube.azurewebsites.net/api/project_badges/measure?project=MajinPierce_ProjectManagementTool-Heroku&metric=reliability_rating&token=c340e151415bfbc1ee14f94397609b5432e878f5)](https://pmt-sonarqube.azurewebsites.net/dashboard?id=MajinPierce_ProjectManagementTool-Heroku)
[![Security Rating](https://pmt-sonarqube.azurewebsites.net/api/project_badges/measure?project=MajinPierce_ProjectManagementTool-Heroku&metric=security_rating&token=c340e151415bfbc1ee14f94397609b5432e878f5)](https://pmt-sonarqube.azurewebsites.net/dashboard?id=MajinPierce_ProjectManagementTool-Heroku)
[![Vulnerabilities](https://pmt-sonarqube.azurewebsites.net/api/project_badges/measure?project=MajinPierce_ProjectManagementTool-Heroku&metric=vulnerabilities&token=c340e151415bfbc1ee14f94397609b5432e878f5)](https://pmt-sonarqube.azurewebsites.net/dashboard?id=MajinPierce_ProjectManagementTool-Heroku)
[![Bugs](https://pmt-sonarqube.azurewebsites.net/api/project_badges/measure?project=MajinPierce_ProjectManagementTool-Heroku&metric=bugs&token=c340e151415bfbc1ee14f94397609b5432e878f5)](https://pmt-sonarqube.azurewebsites.net/dashboard?id=MajinPierce_ProjectManagementTool-Heroku)
[![Code Smells](https://pmt-sonarqube.azurewebsites.net/api/project_badges/measure?project=MajinPierce_ProjectManagementTool-Heroku&metric=code_smells&token=c340e151415bfbc1ee14f94397609b5432e878f5)](https://pmt-sonarqube.azurewebsites.net/dashboard?id=MajinPierce_ProjectManagementTool-Heroku)
[![Coverage](https://pmt-sonarqube.azurewebsites.net/api/project_badges/measure?project=MajinPierce_ProjectManagementTool-Heroku&metric=coverage&token=c340e151415bfbc1ee14f94397609b5432e878f5)](https://pmt-sonarqube.azurewebsites.net/dashboard?id=MajinPierce_ProjectManagementTool-Heroku)
[![Lines of Code](https://pmt-sonarqube.azurewebsites.net/api/project_badges/measure?project=MajinPierce_ProjectManagementTool-Heroku&metric=ncloc&token=c340e151415bfbc1ee14f94397609b5432e878f5)](https://pmt-sonarqube.azurewebsites.net/dashboard?id=MajinPierce_ProjectManagementTool-Heroku)

## Description

This project is a personal project management application. It allows a user to create an account, login, create and manage projects and their repective project tasks, along with the information pertaining to these projects and tasks. 

It was created to help learn about full stack web development concepts such as Spring Boot, React, etc, as well as basic DevOps tasks associated with testing and deploying an application to the web.

All java source code can be seen in the Java folder, and dependencies are contained in the maven pom.xml file. The application.properties file is missing the datasource information to connect to the database and the SecurityConstants.java class is missing the secret needed to encrypt passwords, as they are set in the environment variables on Azure for security reasons. The frontend source code and styling is contained in the React folder. Various screenshots of the application can be seen in the img folder. The CI/CD workflow pipeline yaml file that runs through Github Actions is contained in the .github/workflows folder.

The current version can be seen at: [projectmanagementtool.app](https://projectmanagementtool.app/)

## Technologies

* __Backend__
  * MySQL
  * Spring Boot
    * Spring Data JPA
    * Project Lombok
    * EHCache
    * Log4j2
    * JWT
    * Spring Security
* __Frontend__
  * React
    * React developer's tools for Chrome
  * Redux
    * Redux developer's tools for Chrome
  * Bootstrap
  * Axios
* __DevOps__
  * Swagger
  * JaCoCo
  * SonarQube
  * Azure
    * App Services
      * Azure managed SSL certificate
    * Azure Database for MySQL
  * Github Actions build/deploy pipeline

## Screenshots
#### Landing Page
![Alt text](/img/projectmanagementtool.app.png?raw=true "Landing Page")

#### Dashboard
![Alt text](/img/projectmanagementtool.app_dashboard.png?raw=true "Dashboard")

#### Project Tasks
![Alt text](/img/projectmanagementtool.app_projectBoard.png?raw=true "Project Tasks")
