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
    - /find/email
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
        [x] cache main courses
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
    - /delete
    - /create
    - /update
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