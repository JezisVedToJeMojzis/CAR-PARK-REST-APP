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

| Method | Url            | Parameters                                            | Code for successful response | Query object  | Object response        |
|--------|----------------|-------------------------------------------------------|------------------------------|---------------|------------------------|
| GET    | /carparks      | **name**: String                                      | 200                          |               | Array\<Car park\>      |
| GET    | /carparks/{id} |                                                       | 200                          |               | Car park               |
| POST   | /carparks      |                                                       | 201                          | Car park      | Car park               |
| PUT    | /carparks/{id} |                                                       | 200                          | Car park      | Car park               |
| DELETE | /carparks/{id} |                                                       | 204                          |               |                        |

### Car park floor

| Method | Url                                | Code for successful response | Object query  | Object response    | Note                                                      |
|--------|------------------------------------|------------------------------|---------------|--------------------|-----------------------------------------------------------|
| GET    | /carparks/{id}/floors              | 200                          |               | Array\<Floor\>     |                                                           |
| GET    | /carparks/{id}/floors/{identifier} | 200                          |               | Floor              | Floor has composite primary key                           |
| GET    | /carparkfloors/{id}                | 200                          |               | Floor              | Floor has  auto-generated primary key                     |
| POST   | /carparks/{id}/floors              | 201                          | Floor         | Floor              |                                                           |
| PUT    | /carparks/{id}/floors/{identifier} | 200                          | Floor         | Floor              |                                                           |
| DELETE | /carparks/{id}/floors/{identifier} | 204                          |               |                    |                                                           |

### Car park spot (place)

| Method | Url                                      | Parameters                                                                      | Code for successful response | Object query     | Object response             |
|--------|------------------------------------------|---------------------------------------------------------------------------------|------------------------------|-------------------|-------------------------------|
| GET    | /carparks/{id}/spots                     | **free**: Boolean (true for free spots, false for occupied spots)               | 200                          |                   | Array\<Parking spot     \> |
| GET    | /carparks/{id}/floors/{identifier}/spots |                                                                                 | 200                          |                   | Array\<Parking spot\>      |
| GET    | /parkingspots/{id}                       |                                                                                 | 200                          |                   | Parking spot               |
| POST   | /carparks/{id}/floors/{identifier}/spots |                                                                                 | 201                          | Parking spot      | Parking spot               |
| PUT    | /parkingspots/{id}                       |                                                                                 | 200                          | Parking spot      | Parking spot               |
| DELETE | /parkingspots/{id}                       |                                                                                 | 204                          |                   |                               |

### Car

| Method | Url        | Parameters                                                                               | Code for successful response | Object query | Object response |
|--------|------------|------------------------------------------------------------------------------------------|-------------------------|---------------|-----------------|
| GET    | /cars      | **user**: Long (Id user) <br/> **vrp**: String (License car) | 200                       |               | Array\<Car\>   |
| GET    | /cars/{id} |                                                                                          | 200                     |               | Car             |
| POST   | /cars      |                                                                                          | 201                     | Car          | Car            |
| PUT    | /cars/{id} |                                                                                          | 200                     | Car          | Car            |
| DELETE | /cars/{id} |                                                                                          | 204                     |               |                |

### Customer (user)

| Method | Url         | Parameters                                        | Code for successful response | Object query  | Object response   |
|--------|-------------|--------------------------------------------------|-------------------------------|---------------|-------------------|
| GET    | /users      | **email**: String (email)                        | 200                           |               | Array\<User\>     |
| GET    | /users/{id} |                                                  | 200                           |               | User              |
| POST   | /users      |                                                  | 201                           | User          | User              |
| PUT    | /users/{id} |                                                  | 200                           | User          | User              |
| DELETE | /users/{id} |                                                  | 204                           |               |                   |

### Reservation

| Method | Url                    | Parameters                                                                                                                                                                                                     | Code for successful response | Object query    | Object response        |
|--------|------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------|---------------|---------------------|
| GET    | /reservations          | **user**: Long (id user) <br/> **spot**: Long (id parking spot) <br/> **date**: Date (date; format yyyy-MM-dd    | 200                    |               | Array\<Reservation\> |
| GET    | /reservations/{id}     |                                                                                                                                                                                                                | 200                          |               | Reservation         |
| POST   | /reservations/{id}/end |                                                                                                                                                                                                                | 200                          | Empty         | Reservation         |
| POST   | /reservations          |                                                                                                                                                                                                                | 201                          | Reservation   | Reservation         |
| PUT    | /reservations/{id}     |                                                                                                                                                                                                                | 200                          | Reservation   | Reservation         |

### Type of car

| Method | Url            | Parameters                                    | Code for successful response | Query object  | Response object   |
|--------|----------------|-----------------------------------------------|------------------------------|---------------|-------------------|
| GET    | /cartypes      | **name**: String                              | 200                          |               | Array\<Car type\> |
| GET    | /cartypes/{id} |                                               | 200                          |               | Car type          |
| POST   | /cartypes      |                                               | 201                          | Typ auta      | Car type          |
| DELETE | /cartypes/{id} |                                               | 204                          |               |                   |



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

