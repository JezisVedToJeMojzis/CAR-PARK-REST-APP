# Car park REST application

![Java 1.8](https://img.shields.io/badge/Java-1.8-blue)
![EclipseLink 2.7.10](https://img.shields.io/badge/EclipseLink-2.7.10-green)
[![Public domain](https://img.shields.io/badge/License-Unlicense-lightgray)](https://unlicense.org)

## Functionality

The application must provide CRUD operations via published REST services. It must also provide a booking service
parking spot to the user for his own car. A reservation can only be made for a specific time. After calling
services for the termination of the reservation, the termination time is entered and the total price of the reservation is calculated.

The application must allow the user to display a list of reservations for a specific day and parking spot, a list of his
of active reservations and must also include the possibility of checking the occupancy of the car park.

When deleting a car park, all its floors and parking spots must also be deleted. Same as deleted
the customer's cars must also be deleted. If an entity is deleted, all its associations must be reset.

### Car park (parking garage)

```
| Method | Url | Parameters | Code for successful response | Query object | Response object |
|--------|----------------|----------------------- --------------------------------|----------------- --------|--------------|-----------------------|
| GET | /carparks | **name**: String (parking house name; optional) | 200 | | Array\<Parking house\> |
| GET | /carparks/{id} | | 200 | | Parking garage |
| POST | /carparks | | 201 | Parking garage | Parking garage |
| PUT | /carparks/{id} | | 200 | Parking garage | Parking garage |
| DELETE | /carparks/{id} | | 204 | | |
```

### Car park floor

```
| Method | Url | Code for successful response | Query object | Response object | Note |
|--------|---------------------------------------|--- ----------------------|--------------|----------- ---------|------------------------------------------------ -------------------|
| GET | /carparks/{id}/floors | 200 | | Array\<Floor\> | |
| GET | /carparks/{id}/floors/{identifier} | 200 | | Floor | Implement if the Floor has a composite primary key |
| GET | /carparkfloors/{id} | 200 | | Floor | Implement if the Floor has an auto-generated primary key |
| POST | /carparks/{id}/floors | 201 | Floor | Floor | |
| PUT | /carparks/{id}/floors/{identifier} | 200 | Floor | Floor | |
| DELETE | /carparks/{id}/floors/{identifier} | 204 | | | |
```

### Parking spot (place)

```
| Method | Url | Parameters | Code for successful response | Query object | Response object |
|--------|------------------------------------------------ --|------------------------------------------------ -----------------------------------|-------------- ----------|-------------------|------------ ---------|
| GET | /carparks/{id}/spots | **free**: Boolean (true for free seats, false for occupied seats; optional) | 200 | | Array\<Parking space\> |
| GET | /carparks/{id}/floors/{identifier}/spots | | 200 | | Array\<Parking space\> |
| GET | /parkingspots/{id} | | 200 | | Parking place |
| POST | /carparks/{id}/floors/{identifier}/spots | | 201 | Parking place | Parking place |
| PUT | /parkingspots/{id} | | 200 | Parking place
```
### Car

```
| Method | Url | Parameters | Code for successful response | Query object | Response object |
|--------|------------|------------------------ -------------------------------------------------- -------------|--------------------------|--------- -----|-----------------|
| GET | /cars | **user**: Long (Id of car owner; optional) <br/> **vrp**: String (ECV of car; optional) | 200 | | Array\<Auto\> |
| GET | /cars/{id} | | 200 | | Auto |
| POST | /cars | | 201 | Auto | Auto |
| PUT | /cars/{id} | | 200 | Auto | Auto |
| DELETE | /cars/{id} | | 204 | | |
```

### Customer (user)

```
| Method | Url | Parameters | Code for successful response | Query object | Response object |
|--------|------------|------------------------- -----------------------|------------------------ |---------------|-------------------|
| GET | /users | **email**: String (user's email; optional) | 200 | | Array\<Customer\> |
| GET | /users/{id} | | 200 | | Customer |
| POST | /users | | 201 | Customer | Customer |
| PUT | /users/{id} | | 200 | Customer | Customer |
| DELETE | /users/{id} | | 204 | | |
```

### Reservation

```
| Method | Url | Parameters | Code for successful response | Query object | Response object |
|--------|------------------------|------------- -------------------------------------------------- -------------------------------------------------- -------------------------------------------------- -----------------------------------------|------ -------------------|---------------|------------- -------|
| GET | /reservations | **user**: Long (user id; optional) <br/> **spot**: Long (parking spot id; mandatory only in combination with 'date') <br/> **date**: Date ( date; format yyyy-MM-dd; mandatory only in combination with 'spot') | 200 | | Array\<Reservation\> |
| GET | /reservations/{id} | | 200 | | Reservation |
| POST | /reservations/{id}/end | | 200 | Empty | Reservation |
| POST | /reservations | | 201 | Reservation | Reservation |
| PUT | /reservations/{id} | | 200 | Reservation | Reservation |
```

### Car type

```
| Method | Url | Parameters | Code for successful response | Query object | Response object |
|--------|----------------|----------------------- -----------------------|------------------------ |---------------|-------------------|
| GET | /cartypes | **name**: String (name of car type; optional) | 200 | | Array\<Car Type\> |
| GET | /cartypes/{id} | | 200 | | Car type |
| POST | /cartypes | | 201 | Car type | Car type |
| DELETE | /cartypes/{id} | | 204 | | |
```

## Objects

This section lists the minimum object structures used in the services defined above. The **'int64'** data type represents the type
**Java Long**. The 'number' data type can be any number type. An attribute assigned using the **'?:' symbol is optional**.
An attribute with the value **'$ref: …' indicates** that the value is **of the type of another object** pointed to by the reference. All **dates** are in
format **yyyy-MM-dd**, i.e. according to the ISO-8601 standard.

### Car park

```
{
    "id" ?: int64,
    "name": "string",
    "address": "string",
    "prices": number
    "floors" ?: [
        $ref: "Poschodie"
    ]
}
```

### Car park floor

```
{
    "id" ?: int64,
    "identifier": "string",
    "carPark" ?: int64,
    "spots" ?: [
        $ref: "Parkovacie miesto"
    ]
}
```

### Parking spot

```
{
    "id" ?: int64,
    "identifier": "string",
    "carParkFloor": "string",
    "carPark" ?: int64,
    "type": $ref: "Typ auta",
    "free" ?: boolean,
    "reservations" ?: [
        $ref: "Rezervácia"
    ]
}
```

### Car

```
{
    "id" ?: int64,
    "brand": "string",
    "model": "string",
    "vrp": "string",
    "colour": "string",
    "type": $ref: "Typ auta",
    "owner": $ref: "Zákazník",
    "reservations" ?: [
        $ref: "Rezervácia"
    ]
}
```

### Customer

```
{
    "id" ?: int64,
    "firstName": "string",
    "lastName": "string",
    "email": "string",
    "cars" ?: [
        $ref: "Auto"
    ],
    "coupons" ?: [
        $ref: "Zľavový kupón"
    ]
}
```

### Reservation

```
{
    "id" ?: int64,
    "start": Date(yyyy-MM-dd),
    "end" ?: Date(yyyy-MM-dd),
    "prices" ?: number,
    "car": $ref: "Auto",
    "spot": $ref: "Parkovacie miesto",
    "coupon" ?: $ref: "Zľavový kupón"
}
```


### Type of car

```
{
    "id" ?: int64,
    "name": "string"
}
```

