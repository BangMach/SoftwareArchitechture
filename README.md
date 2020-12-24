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
        - Called by Login Service
    - /find/email 
        - Called by Login Service
    - /all
        - Paginated with startAt and maxResults params
    - /filter
        - Paginated with startAt and maxResults params
    - /update
    - /delete
- Login Service (http://localhost:8081/login)
    - /password
    - /google
- Menu Service (http://localhost:8084/menu)
    - /add
    - /all
        - Paginated with startAt and maxResults params
    - /find/category 
        - Redis cache for main courses
        - Paginated with startAt and maxResults params
    - /update
    - /delete
    - /filter
        - Paginated with startAt and maxResults params
- Table Service (http://localhost:8082/tables)
    - /create
    - /filter
        - Paginated with startAt and maxResults params
    - /find
    - /all
        - Paginated with startAt and maxResults params
    - /update
- Reservation Service (http://localhost:8086/reservations)
    - /find
        - Called by User Service
    - /delete
    - /create
        - Called by User Service
    - /update
        - Called by User Service
- User Service (http://localhost:8085/users)
    - /available/tables
        - Paginated with startAt and maxResults params
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
- Seats cannot be equal 0

### Menu
- Valid categories { appetizer, main, dessert }
- Category cannot be updated

### Reservation
- Valid statuses { booked, cancelled, done }
- Name, phone, start time, table id are required for creating new reservation
- One reservation can last up to 2 hours

## Use cases
### User
- Search, view menu
- Search, view available tables
- Create reservation

### Admin
- Login by username password, google
- Search, view, available, unavailable tables
- Search, view, create, update, delete accounts
- Search, view, create, update, delete menu
- Search, view, create, update, delete reservation