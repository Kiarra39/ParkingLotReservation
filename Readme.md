# Parking Lot Reservation System

A comprehensive Spring Boot REST API for managing parking lot operations including floor management, parking slot allocation, and reservation system.

##  Live Deployment

**Live API Base URL:** https://parkinglotreservation.onrender.com

### Available Endpoints:
- **Health Check:** https://parkinglotreservation.onrender.com/actuator/health
- **Floors API:** https://parkinglotreservation.onrender.com/floors
- **Slots API:** https://parkinglotreservation.onrender.com/slots
- **Reservations API:** https://parkinglotreservation.onrender.com/reservations
- **H2 Console:** https://parkinglotreservation.onrender.com/h2-console

##  Table of Contents

- [Features](#features)
- [Technology Stack](#technology-stack)
- [Quick Start](#quick-start)
- [API Documentation](#api-documentation)
- [Database Schema](#database-schema)
- [Testing](#testing)
- [Deployment](#deployment)
- [Project Structure](#project-structure)
- [Troubleshooting](#troubleshooting)

##  Features

- **Floor Management**: Create, read, and delete parking floors
- **Slot Management**: Manage parking slots on each floor with vehicle type support
- **Reservation System**: Reserve and cancel parking slots with time-based availability
- **Real-time Availability**: Check slot availability in real-time
- **RESTful API**: Clean, standardized API endpoints
- **Error Handling**: Comprehensive exception handling with meaningful error messages
- **Data Validation**: Input validation and business logic enforcement

##  Technology Stack

- **Backend Framework**: Spring Boot 3.2.0
- **Java Version**: 17
- **Build Tool**: Maven 3.8.8
- **Database**: H2 Database (Embedded, In-memory)
- **ORM**: Spring Data JPA with Hibernate
- **API Documentation**: Spring REST Docs
- **Testing**: JUnit 5, Mockito, Spring Boot Test
- **Containerization**: Docker



### Prerequisites

- Java 17 or higher
- Maven 3.8.8 or higher
- Git

