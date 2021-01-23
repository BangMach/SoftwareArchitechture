# Software Architecture
This project is to develop a Restaurant website applying modern, scalable, high available architecture by implementing OAuth2, micro-service, Redis, Kafka message queue, Docker, Kubernetes and AWS cloud.
This repo is the backend for all microservices. 

## Project micro-services  
- [x] Discovery Service
- [x] API Gateway Service
- [x] Kafka Service
- [x] Account Service
- [x] Login service     
- [x] Menu Service
- [x] Table Service
- [x] Reservation Service
- [x] User Service
 
## URLs
### Service Discovery 
- `http://54.203.146.96:8761`
### API Gateway  
- `http://54.214.208.194:8989`
### Account Service 
- `http://34.222.65.129:8083`
#### `/accounts`
- GET  
    - Params: startAt, maxResults
- POST, PUT 
#### `/accounts/email/{email}`
- GET
    - Called by Login Service
#### `/accounts/attributes`
- POST 
    - Params: startAt, maxResults
    - Find accounts by different attributes
#### `/accounts/{id}`
- DELETE
#### `/accounts/password`
- POST
### Login Service 
- `http://34.217.83.118:8081`
#### `/login/email`
- GET
    - OAuth2 Login with google
### Menu Service 
- `http://54.184.58.188:8084`
#### `/menu`
- GET  
    - Params: startAt, maxResults
- POST, PUT 
#### `/menu/{category}`
- GET
    - Params: startAt, maxResults
#### `/menu/attributes`
- POST 
    - Params: startAt, maxResults
    - Find dishes by different attributes
#### `/menu/{id}`
- DELETE
### Table Service 
- `http://54.190.164.68:8082`
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
### Kafka Service 
- `http://34.218.66.165:9000`
#### `/kafka/reservation/save`
- POST
#### `/kafka/reservation/put`
- POST
### Reservation Service 
- `http://34.218.66.165:8086`
#### `/reservations`
- POST, PUT 
    - Called by User Service
#### `/reservations/{id}`
- GET
    - Called by User Service
#### `/reservations/{id}`
- DELETE
### User Service 
- `http://54.202.141.13:8085`
#### `/users/tables/{startTime}`
- GET
    - Find available tables from a given time
#### `/users/reservations`
- GET
    - Params: startAt, maxResults
- POST, PUT
#### `/users/reservations/attributes`
- POST
    - Find reservations by different attributes

## Redis usage
- Save/load main courses of the menu

## Kafka usage
- Handle request for create and update reservations

## Work flow 
1. Launch Discovery service first. 
2. Launch Kafka service before Reservation service.
3. Launch Table and Reservation services before User service.
4. Launch Redis server before Menu service
5. Check services' name here `http://54.203.146.96:8761`
6. All APIs should be called from API Gateway with corresponding service name
* IP for each service and for discovery service specified in application.yml of each service has to be checked and modified manually to make sure it is up-to-date.

### How to launch a service
1. Run mvn clean install to create target folder and .jar file
2. Run docker-compose up --build command 

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
