
## Snitch.io

#### Final Project for 16-week Skill Distillery Bootcamp

### Jubilee Dreamer

- Steve Sharp (Developer, DBA) https://github.com/stevie-steve
- Yettsy Jo Knapp (Developer, Repo Owner) https://github.com/yettsyjk
- Iryna Tretynyk (Developer) https://github.com/kefa4520

### Overview
> This is an application that uses Spring REST C.R.U.D. API and helps users report problems


### Table of REST Endpoints
| Verb   |   URI  | Request Body | Description |
|--------|--------|--------------|-------------|
|  POST  | api/ | Representation of new entry | Creates a new cycle entry |
|  GET   | api/ /{id} | Representation of entry at id number-- {id} | Retrieves a list of entries by id |
|  PUT  | api/  /{id} | Representation of a *new version* of entry at id number--{id}| Updates an existing entry |
|  DELETE  | api/ /{id} | Representation of an entry to be deleted | Removes an entry by id |


## Tech Used
* MySQL, MySQL Workbench
* JPA/Hibernate
* Spring Boot
* Spring Data JPA
* Git/Github
* Postman
* Angular
* JavaScript

## Learning Objectives
- Created a JPA Project
- Created a Java entity class POJO that models the database table.
- Mapped POJO using JPA.
- Configured a Spring Boot app to publish a REST API.
- Used Spring REST annotations.
- Used Spring Data JPA to perform all CRUD operations.
- Practiced sending and receiving JSON.
