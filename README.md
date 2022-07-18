# M4 Summative DPC

Back-End Game Store Data Application. This repository handles the API backend that manages the inventory of games, console and T-shirts. It provides a mechanism by which users can purchase the products.

See the following link [M4-Summative-Front-End](https://github.com/ChauDeveloper/M4-Summative-Front-End) for the front end portion that displays the API through React. 

## Table of Contents

* [Setup](#setup)
* [Usage](#usage)
* [API Routes](#api-routes)
* [Contributing](#contributing)
* [License](#license)

## Setup
:floppy_disk:

This repository uses the following technology:
- [Java 1.8](https://docs.oracle.com/javase/8/docs/api/)
    - Java Platform Standard Ed. 8

This repository uses the following dependency:
- Spring Boot Web
- Spring Boot Starter Validation
- Spring Boot Starter Data JPA
- MySQL Connector Java
- Spring Boot Starter Test
- Junit v4.13
- Spring Doc Open API

## Usage

:computer:

If you cloned or copied the repository, ensure that the pom.xml is updated to reflect the dependency packages.

Ensure that your `application.properties` file under the `resources` folder has the correct mySql username, password, database and timezone for the application to run.
```js
spring.datasource.url= jdbc:mysql://localhost:3306/game_store_db?useSSL=false&serverTimezone=US/Eastern&createDatabaseIfNotExist=true&allowPublicKeyRetrieval=true
spring.datasource.username= root // typically root
spring.datasource.password= password // your password
spring.jpa.hibernate.ddl-auto= update
```
Note that this is related to your SQL database.

Start the server through the `M4SummativeDpcApplication`.

Start up Postman or Insomnia to check your API routes. Seed your database using the route `/seed`.


## API Routes

- Detailed API routes and instructions can be found in [openapi.yaml](./api-documentation.yaml) file. 

*Recommended to use API Routes through Postman or Insomnia
*Note that localhost:8080 is typically the default server. Please adjust server according to your specifications.

| Method | Routes                                                    | Description                                         |
|--------|-----------------------------------------------------------|-----------------------------------------------------|
|        | SEED ROUTE                                                |                                                     |
| POST   | http://localhost:8080/seed                                | Seeds the database with fake data.                  |
|        | GAME ROUTES                                               |                                                     |
| GET    | http://localhost:8080/games                               | Returns list of all Game(s)                         |
| GET    | http://localhost:8080/games/{id}                          | Returns a Game with with matching ID                |
| GET    | http://localhost:8080/games/title/{title}                 | Returns list of Games with matching Title           |
| GET    | http://localhost:8080/games/rating/{rating}               | Returns list of Games with matching ESRB rating     |
| GET    | http://localhost:8080/games/studio/{studio}               | Returns list of Games with matching studio          |
| POST   | http://localhost:8080/games                               | Creates a new Game entry                            |
| PUT    | http://localhost:8080/games                               | Updates Game with matching ID                       |
| DELETE | http://localhost:8080/games/{id}                          | Deletes a game with matching ID                     |
|        | CONSOLE ROUTES                                            |                                                     |
| GET    | http://localhost:8080/console                             | Returns list of all Console(s)                      |
| GET    | http://localhost:8080/console/{id}                        | Returns a Console with matching ID                  |
| GET    | http://localhost:8080/console/manufacturer/{manufacturer} | Returns list of consoles with matching manufacturer |
| POST   | http://localhost:8080/console                             | Creates a new Console entry                         |
| PUT    | http://localhost:8080/console                             | Updates a Console with matching ID                  |
| DELETE | http://localhost:8080/console/{id}                        | Deletes a Console with matching ID                  |
|        | T-SHIRT ROUTES                                            |                                                     |
| GET    | http://localhost:8080/tshirt                              | Returns list of all T-Shirts                        |
| GET    | http://localhost:8080/tshirt/{id}                         | Returns a T-Shirt with matching ID                  |
| GET    | http://localhost:8080/tshirt/color/{color}                | Returns list of T-Shirts with matching color        |
| GET    | http://localhost:8080/tshirt/size/{size}                  | Returns list of T-Shirts with matching size         |
| POST   | http://localhost:8080/tshirt                              | Creates a new T-Shirt entry                         |
| PUT    | http://localhost:8080/tshirt                              | Updates a T-Shirt with matching ID                  |
| DELETE | http://localhost:8080/tshirt/{id}                         | Deletes a T-Shirt with matching ID                  |
|        | INVOICE ROUTES                                            |                                                     |
| GET    | http://localhost:8080/invoices                            | Returns list of all Invoices                        |
| GET    | http://localhost:8080/invoices/{id}                       | Returns an Invoice with matching ID                 |
| POST   | http://localhost:8080/invoices                            | Creates a new Invoice entry                         |
| PUT    | http://localhost:8080/invoices                            | Updates an Invoice with matching ID                 |
| DELETE | http://localhost:8080/invoices/{id}                       | Deletes an Invoice with matching ID                 |

## Contributing

:octocat:

| Name         | Github                                             | Email                   |
|--------------|----------------------------------------------------|-------------------------|
| Debasu Eyasu | [@bayleyegn100](https://github.com/bayleyegn100)   | bayleyegn100@gmail.com  |
| Patrick Chen | [@paperpatch](https://github.com/paperpatch)       | patchen21@gmail.com     |
| Chau Nguyen  | [@ChauDeveloper](https://github.com/ChauDeveloper) | chaudeveloper@gmail.com |

## License

:receipt:

No current license available.