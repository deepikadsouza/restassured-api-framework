# REST Assured API Automation Framework

## Overview

This project is an API Automation Testing Framework developed using REST Assured, Java, TestNG, and Maven.

The framework is designed to automate REST API validation with reusable utilities, POJO classes, request specifications, and modular test execution.

---

# Tech Stack

- Java
- REST Assured
- TestNG
- Maven
- JSON
- POJO Classes
- Eclipse IDE

---

# Framework Features

- CRUD API validation
- Reusable request specifications
- POJO-based request body handling
- Modular framework design
- TestNG execution support
- Configurable environment setup
- Response validation
- Status code verification
- JSON request and response handling

---

# Project Structure

```text
restassured-api-framework
│
├── configenv
│   └── configdata.properties
│
├── src
│   ├── main/java
│   │   ├── GenericUtility
│   │   ├── PojoUtility
│   │   ├── constantEndPoints
│
│   ├── test/java/com/shopperStack
│   │   ├── CartModuleTest.java
│   │   ├── DeleteShopperTest.java
│   │   ├── RegisterShopperTest.java
│   │   ├── ShopperDetailsTest.java
│
├── pom.xml
├── testng.xml
