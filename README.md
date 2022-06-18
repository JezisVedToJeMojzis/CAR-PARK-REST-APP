# B-VSA LS 21/22 - Semestrálny projekt 2

![Java 1.8](https://img.shields.io/badge/Java-1.8-blue)
![EclipseLink 2.7.10](https://img.shields.io/badge/EclipseLink-2.7.10-green)
[![Public domain](https://img.shields.io/badge/License-Unlicense-lightgray)](https://unlicense.org)

Cieľom 2. semestrálneho projektu je naprogramovať webovú aplikáciu publikujúc RESTful API podľa definovanej
špecifikácie. Projekt má nadväzovať na semestrálny projekt 1, v ktorom ste mali implementovať spojenie s SQL databázou s
použitím technológie JPA.

V tomto projekte vychádzajte z riešenia pre 1. semestrálny projekt. Zachovajte asociácie a objekty definované v 1.
projekte. Nakoľko predmetom tohto zadania je implementácia REST webových služieb, môžete upraviť riešenie z
predchádzajúceho zadania podľa svojho najlepšieho vedomia a svedomia, tak aby splnilo toto zadanie.

## Funkcionalita

Aplikácia musí zabezpečiť CRUD operácie cez publikované REST služby. Taktiež musí poskytovať službu pre rezervovanie
parkovacieho miesta používateľom pre vlastné auto. Rezervácia môže byť vytvorené iba na špecifický čas. Po zavolaním
služby pre ukončenie rezervácie sa zapíše čas ukončenia a vypočíta celková cena rezervácie.

Aplikácia musí umožniť používateľovi zobrazenie zoznamu rezervácií za špecifický deň a parkovacie miesto, zoznam svojich
aktívnych rezervácií a taktiež musí obsahovať možnosť kontroly obsadenosti parkovacieho domu.

Pri vymazaní parkovacieho domu musia byť vymazané aj všetky jeho poschodia aj parkovacie miesta. Rovnako ak je vymazaný
zákazník musia byť vymazané aj jeho autá. Ak je vymazaná entita všetky jeho asociácie musia byť vynulované.

## Špecifikácia

Pri implementácií použite protokol HTTP/1.1, ako formát prenášaných objektov použite **application/json** s UTF-8
kódovaním. Pri chybových stavoch môžete vrátiť prázdnu odpoveď. Kód odpovede nastavte tak, aby najlepšie vystihla povahu
odpovede [https://www.restapitutorial.com/httpstatuscodes.html](https://www.restapitutorial.com/httpstatuscodes.html).

Pre identifikáciu používateľa požiadavky použite autentifikačný mechanizmu **Basic access
authentication** [https://en.wikipedia.org/wiki/Basic_access_authentication](https://en.wikipedia.org/wiki/Basic_access_authentication)
. Prihlasovacie údaje použité nasledovné:

- prihlasovacie meno = používateľov email
- heslo = id v databáze

REST služby implementujte ako bezstavové. **Nevytvárajte žiadnu** používateľskú **reláciu** (Session), nevytvárajte žiadne
autorizačné tokeny, či iné spôsoby relácie a udržiavania stavu používateľa. **Authorization HTTP hlavička musí byť**
zaslaná
s každým dopytom na služby.

Aplikáciu **implementujte vo** frameworku **Jersey** ako tzv. **'standalone' webovú aplikáciu** (po vzore cvičení). Výstupom
riešenia musí byť spustiteľný JAR subor. HTTP server aplikácie má počúvať **na porte 8080**. V rámci implementácie služieb,
ktoré majú vrátiť kolekciu objektov nemusíte riešiť stránkovanie. **Pri štarte** aplikácie **vytvorte** jedného testovacieho
**používateľa s emailom admin@vsa.sk**.

V nasledujúcej sekcii je uvedená špecifikácia jednotlivých služieb pre REST resources. Štruktúra objektov uvedených pri
službách je na konci tohto dokumentu. Parametre uvedené pri GET službách slúžia na dodatočné vyhľadanie REST resource-u.

### Parkovací dom

| Metóda | Url            | Parametre                                             | Kód pre úspešnú odpoveď | Objekt dopytu | Objekt odpovede        |
|--------|----------------|-------------------------------------------------------|-------------------------|---------------|------------------------|
| GET    | /carparks      | **name**: String (názov parkovacieho domu; nepovinný) | 200                     |               | Array\<Parkovací dom\> |
| GET    | /carparks/{id} |                                                       | 200                     |               | Parkovací dom          |
| POST   | /carparks      |                                                       | 201                     | Parkovací dom | Parkovací dom          |
| PUT    | /carparks/{id} |                                                       | 200                     | Parkovací dom | Parkovací dom          |
| DELETE | /carparks/{id} |                                                       | 204                     |               |                        |

### Poschodie parkovacieho domu

| Metóda | Url                                | Kód pre úspešnú odpoveď | Objekt dopytu | Objekt odpovede    | Poznámka                                                  |
|--------|------------------------------------|-------------------------|---------------|--------------------|-----------------------------------------------------------|
| GET    | /carparks/{id}/floors              | 200                     |               | Array\<Poschodie\> |                                                           |
| GET    | /carparks/{id}/floors/{identifier} | 200                     |               | Poschodie          | Implementuj ak má Poschodie kompozitný primárny kľúč      |
| GET    | /carparkfloors/{id}                | 200                     |               | Poschodie          | Implementuj ak má Poschodie auto-generovaný primárny kľúč |
| POST   | /carparks/{id}/floors              | 201                     | Poschodie     | Poschodie          |                                                           |
| PUT    | /carparks/{id}/floors/{identifier} | 200                     | Poschodie     | Poschodie          |                                                           |
| DELETE | /carparks/{id}/floors/{identifier} | 204                     |               |                    |                                                           |

### Parkovacie miesto

| Metóda | Url                                      | Parametre                                                                       | Kód pre úspešnú odpoveď | Objekt dopytu     | Objekt odpovede            |
|--------|------------------------------------------|---------------------------------------------------------------------------------|-------------------------|-------------------|----------------------------|
| GET    | /carparks/{id}/spots                     | **free**: Boolean (true pre voľné miesta, false pre obsadené miesta; nepovinné) | 200                     |                   | Array\<Parkovacie miesto\> |
| GET    | /carparks/{id}/floors/{identifier}/spots |                                                                                 | 200                     |                   | Array\<Parkovacie miesto\> |
| GET    | /parkingspots/{id}                       |                                                                                 | 200                     |                   | Parkovacie miesto          |
| POST   | /carparks/{id}/floors/{identifier}/spots |                                                                                 | 201                     | Parkovacie miesto | Parkovacie miesto          |
| PUT    | /parkingspots/{id}                       |                                                                                 | 200                     | Parkovacie miesto | Parkovacie miesto          |
| DELETE | /parkingspots/{id}                       |                                                                                 | 204                     |                   |                            |

### Auto

| Metóda | Url        | Parametre                                                                                | Kód pre úspešnú odpoveď | Objekt dopytu | Objekt odpovede |
|--------|------------|------------------------------------------------------------------------------------------|-------------------------|---------------|-----------------|
| GET    | /cars      | **user**: Long (Id majiteľa auta; nepovinné) <br/> **vrp**: String (EČV auta; nepovinné) | 200                     |               | Array\<Auto\>   |
| GET    | /cars/{id} |                                                                                          | 200                     |               | Auto            |
| POST   | /cars      |                                                                                          | 201                     | Auto          | Auto            |
| PUT    | /cars/{id} |                                                                                          | 200                     | Auto          | Auto            |
| DELETE | /cars/{id} |                                                                                          | 204                     |               |                 |

### Zákazník

| Metóda | Url         | Parametre                                        | Kód pre úspešnú odpoveď | Objekt dopytu | Objekt odpovede   |
|--------|-------------|--------------------------------------------------|-------------------------|---------------|-------------------|
| GET    | /users      | **email**: String (email používateľa; nepovinné) | 200                     |               | Array\<Zákazník\> |
| GET    | /users/{id} |                                                  | 200                     |               | Zákazník          |
| POST   | /users      |                                                  | 201                     | Zákazník      | Zákazník          |
| PUT    | /users/{id} |                                                  | 200                     | Zákazník      | Zákazník          |
| DELETE | /users/{id} |                                                  | 204                     |               |                   |

### Rezervácia

| Metóda | Url                    | Parametre                                                                                                                                                                                                      | Kód pre úspešnú odpoveď | Objekt dopytu | Objekt odpovede     |
|--------|------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------|---------------|---------------------|
| GET    | /reservations          | **user**: Long (id používateľa; nepovinné) <br/> **spot**: Long (id parkovacieho miesta; povinné iba v kombinácií s 'date') <br/> **date**: Date (dátum; format yyyy-MM-dd; povinné iba v kombinácií s 'spot') | 200                     |               | Array\<Rezervácia\> |
| GET    | /reservations/{id}     |                                                                                                                                                                                                                | 200                     |               | Rezervácia          |
| POST   | /reservations/{id}/end |                                                                                                                                                                                                                | 200                     | Prázdny       | Rezervácia          |
| POST   | /reservations          |                                                                                                                                                                                                                | 201                     | Rezervácia    | Rezervácia          |
| PUT    | /reservations/{id}     |                                                                                                                                                                                                                | 200                     | Rezervácia    | Rezervácia          |

### Skupina A - Zľavový kupón

| Metóda | Url                         | Parametre                                  | Kód pre úspešnú odpoveď | Objekt dopytu | Objekt odpovede        |
|--------|-----------------------------|--------------------------------------------|-------------------------|---------------|------------------------|
| GET    | /coupons                    | **user**: Long (id používateľa; nepovinné) | 200                     |               | Array\<Zľavový kupón\> |
| GET    | /coupons/{id}               |                                            | 200                     |               | Zľavový kupón          |
| POST   | /coupons/{id}/give/{userId} |                                            | 200                     | Prázdny       | Zľavový kupón          |
| POST   | /coupons                    |                                            | 201                     | Zľavový kupón | Zľavový kupón          |
| DELETE | /coupons/{id}               |                                            | 204                     |               |                        |

### Skupina B - Typ auta

| Metóda | Url            | Parametre                                     | Kód pre úspešnú odpoveď | Objekt dopytu | Objekt odpovede   |
|--------|----------------|-----------------------------------------------|-------------------------|---------------|-------------------|
| GET    | /cartypes      | **name**: String (názov typu auta; nepovinné) | 200                     |               | Array\<Typ auta\> |
| GET    | /cartypes/{id} |                                               | 200                     |               | Typ auta          |
| POST   | /cartypes      |                                               | 201                     | Typ auta      | Typ auta          |
| DELETE | /cartypes/{id} |                                               | 204                     |               |                   |

### Skupina C - Sviatok

| Metóda | Url            | Parametre                                                   | Kód pre úspešnú odpoveď | Objekt dopytu | Objekt odpovede  |
|--------|----------------|-------------------------------------------------------------|-------------------------|---------------|------------------|
| GET    | /holidays      | **date**: Date(dátum sviatku; format yyyy-MM-dd; nepovinné) | 200                     |               | Array\<Sviatok\> |
| POST   | /holidays      |                                                             | 201                     | Sviatok       | Sviatok          |
| DELETE | /holidays/{id} |                                                             | 204                     |               |                  |

## Hodnotenie

Zadanie je hodnotené **20 bodmi**. Vypracovanie je nutné odovzdať **do 11.05.2022 23:59**.

Zadanie si naklonujte z repozitára zadania. Svoje vypracovanie nahrajte do vášho repozitára pre toto zadanie pomocou
programu Git (git commit + git push). Vypracovanie môžete "pusho-vať" priebežne. Do vytvoreného projektu nakopírujte
riešenie z 1. semestrálneho projektu.

Nakoľko v 1. zadaní boli update metódy nepovinné, rovnaké pravidlo bodovania (bonus body zo spoločného "poolu") platí aj
pre PUT služby uvedené v špecifikácií, nakoľko nie každý má update metódy implementované.

Hodnotiť sa bude iba master/main branch. Hodnotenie bude automatizované pomocou nástroja pre testovanie REST API (
Postman a pod.) Kvôli testom a zrýchleniu opravovania je nutné dodržať pokyny a štruktúru projektu, ako je stanovené v
zadaní! Iba kód poslednej verzie vypracovania (t.j. z posledného commitu) do termínu odovzdania sa berie do úvahy. Okrem
testov sa bude kód a funkcionalita kontrolovať aj manuálne. Hodnotiť sa bude len kód, ktorý je predmetom tohto zadania,
takže aj v prípadné chyby v JPA implementácií nebudú brané do úvahy. Hodnotiť sa budú iba skompilovateľné a spustiteľné
riešenia.

## Objekty

V tejto sekcii sú uvedené minimálne štruktúry objektov použité vo vyššie definovaných službách. Dátový typ **'int64'** reprezentuje typ
**Java Long**. Dátový typ 'number' môže byť akýkoľvek číselný typ. Atribút s priradením pomocou symbolu **'?:' je dobrovoľný**.
Atribút s hodnotou **'$ref: …' označuje**, že hodnota je **typu iného objektu**, na ktorý ukazuje referencia. Všetky **dátumy** sú vo
formáte **yyyy-MM-dd**, t.j. podľa štandardu ISO-8601.

### Parkovací dom

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

### Poschodie

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

### Parkovacie miesto

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

### Auto

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

### Zákazník

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

### Rezervácia

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

### Skupina A - Zľavová kupón

```
{
    "id" ?: int64,
    "name": "string",
    "discount": number
}
```

### Skupina B - Typ auta

```
{
    "id" ?: int64,
    "name": "string"
}
```

### Skupina C - Sviatok

```
{
    "id" ?: int64,
    "name": "string",
    "date": Date(yyyy-MM-dd)
}
```