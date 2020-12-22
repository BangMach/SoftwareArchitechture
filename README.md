# Software Architecture
This project is to develop a Restaurant website applying modern, scalable, high available architecture by implementing OAuth2, micro-service, Redis, Kafka message queue, Docker, Kubernetes and AWS cloud. 

## Project micro-services  
- [x] Account Service
- [x] Login service     
- [x] Menu Service
- [x] Table Service
- [x] Reservation Service
- [x] User Service
 
## Endpoints
- Service Discovery (http://localhost:8761)
- Account Service (http://localhost:8083/accounts)
    - /create 
    - /find/username 
        - [x] Called by Login Service
    - /find/email 
        - [x] Called by Login Service
    - /all
    - /filter
    - /update
    - /delete
- Login Service (http://localhost:8081/login)
    - /password
    - /google
- Menu Service (http://localhost:8084/menu)
    - /add
    - /all
    - /find/category 
        - [x] Redis cache for main courses
    - /update
    - /delete
- Table Service (http://localhost:8082/tables)
    - /create
    - /filter
    - /find
    - /all
    - /update
- Reservation Service (http://localhost:8086/reservations)
    - /all
    - /find
        - [x] Called by User Service
    - /delete
    - /create
        - [x] Called by User Service
    - /update
        - [x] Called by User Service
- User Service (http://localhost:8085/users)
    - /available/tables
    - /reservation/details
    - /create/reservation
    - /update/reservation

## Redis usage
- Save/load main courses of the menu

## Work flow
1. Start Discovery service first.
2. Run Table and Reservation services before User service.
3. Run Redis server before Menu service: Redis -> 64 BIT -> redis-server

## Constraints
### Account
- User and email must be unique and not empty
- User, password and email are required for creating new account

### Table
- Valid statuses { available, unavailable }

### Menu
- Valid categories { appetizer, main, dessert }

### Reservation
- Valid statuses { booked, cancelled, done }
- Name, phone, start time, table id are required for creating new reservation
- One reservation can last up to 2 hours