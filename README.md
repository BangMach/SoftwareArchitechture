# Software Architecture
This project is to develop a Restaurant website applying modern, scalable, high available architecture by implementing OAuth2, micro-service, Redis, Kafka message queue, Docker, Kubernetes and AWS cloud. 

## Project micro-services  
- [x] Account Service
- [x] Login service     
- [x] Menu Service
- [x] Table Service
- [x] Reservation Service
- [x] User Service
 
## URLs
### Service Discovery 
- `http://localhost:8761`
### API Gateway  
- `http://localhost:8989`
### Account Service 
- `http://localhost:8083`
#### `/accounts`
- GET  
    - Params: startAt, maxResults
- POST, PUT 
#### `/accounts/email/{email}`, `/accounts/username/{username}`
- GET
    - Called by Login Service
#### `/accounts/attributes`
- POST 
    - Params: startAt, maxResults
    - Find accounts by different attributes
#### `/accounts/{id}`
- DELETE
### Login Service 
- `http://localhost:8081`
#### `/login/username`
- POST
    - Login with username and password
#### `/login/email`
- GET
    - OAuth2 Login with google
### Menu Service 
- `http://localhost:8084`
#### `/menu`
- GET  
    - Params: startAt, maxResults
- POST, PUT 
#### `/menu/category/{category}`
- GET
    - Params: startAt, maxResults
#### `/menu/attributes`
- POST 
    - Params: startAt, maxResults
    - Find dishes by different attributes
#### `/menu/{id}`
- DELETE
### Table Service 
- `http://localhost:8082`
#### `/tables`
- GET  
    - Params: startAt, maxResults
- POST, PUT 
#### `/tables/attributes`
- POST 
    - Params: startAt, maxResults
    - Find tables by different attributes
#### `/tables/{id}`
- GET
    - Called by User Service
### Reservation Service 
- `http://localhost:8086`
#### `/reservations`
- POST, PUT 
    - Called by User Service
#### `/reservations/{id}`
- GET
    - Called by User Service
#### `/reservations/{id}`
### User Service 
- `http://localhost:8085`
#### `/users/tables/{startTime}`
- GET
    - Find available tables from a given time
#### `/reservations`
- GET
    - Params: startAt, maxResults
- POST, PUT
#### `/reservations/attributes`
- POST
    - Find reservations by different attributes

## Redis usage
- Save/load main courses of the menu

## Work flow
1. Start Discovery service first.
2. Run Table and Reservation services before User service.
3. Run Redis server before Menu service: Redis -> 64 BIT -> redis-server
4. All APIs should be called from API Gateway with port `8989`

## Constraints
### Account
- User and email must be unique and not empty
- User, password and email are required for creating new account

### Table
- Valid statuses { available, unavailable }
- Seats must be greater than 0 and are required for creating new table
- Cannot add new, remove table or update seats (fixed physical map)

### Menu
- Valid categories { appetizer, main, dessert }
- Dish name must not be empty and is required for creating new dish
- Category cannot be updated

### Reservation
- Valid statuses { booked, cancelled, done }
- Name, phone, start time, table id are required and must not be empty for creating new reservation
- One reservation can last up to 2 hours
- Booked and unavailable tables cannot be reserved

## Use cases
### User
- Search, view menu
- Search, view available tables
- Create reservation

### Admin
- Login by username password, google
- Search, view, update tables
- Search, view, create, update, delete accounts
- Search, view, create, update, delete menu
- Search, view, create, update, delete reservation