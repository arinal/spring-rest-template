# spring-rest-platform
A showcase for building LOB application's common pattern like:
- Master details.
- Search item.
- Login with OAuth.
- CRUD.
- RESTful server.

The application is aiming to build POS with clean codebase by incorporating:
- Well encapsulated non-anemic domain model.
- Minimal code duplication between controllers and services.
- Simple DDD with all of its joy (hexagonal architecture, repository pattern, SOLID principles).
- REST architecture style.
- Java 8 closures.
 
Built using Spring 4 and Java 8.

## Application's architect overview
*TODO*

## Try it!
Assuming you already have JDK8+ and JAVA_HOME setup, just run:

```
git clone https://github.com/arinal/spring-rest-template.git && cd spring-rest-template
./gradlew bootrun
```

If everything is correct, the application has started successfully. If you want to setup the client side angular
website, please refer to this [repository](https://github.com/arinal/angular-rest-template.git).