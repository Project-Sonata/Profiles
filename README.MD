# Sonata-Profiles
## Table of contents
* [General info](#general-info)
* [Technologies](#technologies-used)
* [Features](#features)
* [TODO](#todos)
* [Endpoints](#endpoints)

## General info
  Sonata-Profiles is a microservice that used to store user's data, provide access for public information about user. 
  Add support to manage user's account.


### Technologies used
```
$ Java 17
$ Lombok
$ Gradle
$ Project Reactor
$ Postgresql 
$ Flyway
$ Spring Core
$ Spring Webflux
$ Spring Eureka Client
$ Spring Data R2DBC
$ Mapstruct
$ Docker
$ JUnit 5
$ JavaFaker
$ Mockito
$ Bash
```

### Features
These section contains info about implemented features:

- Fetch user profile by ID
- Add user

### TODOs
These section contains info about future features:
- Get current user's profile
- Change display name
- Change profile picture
- Show current user activity

Accounts: 
- Change email
- Change password

### Endpoints
- [Fetch user profile by id](./docs/How-To-Get-Profile-Info-By-ID.MD)
- [Create user](./docs/How-To-Create-User.MD)