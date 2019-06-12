# Reservation app

## Overview 

Practise project for Spring Boot web application. It will be a FHIR appointment application.

## Goals

- The application will contain a user interface with a modern web technologies
- The application uses FHIR to communicate with the frontend and the backend
- The application is able to display medical appointments of a customer
- The application is able to cancel an appointment
- Bonus: The application acts as a proxy for another FHIR server.

## Development log

### Day 1

- I used the Spring Initialzr to create an application template. I added some dependencies at the start:
    - Spring Security
        - This adds a default login page
        - The username is `user`, and password is printed to console log upon statup
    - Spring Web Starter
    - Spring Boot Devtools
    - Along with those, came also Embedded Tomcat and test utilities.
    - I added FHIR core and server dependencies, as well as Jetbrains annotations
- When starting a FHIR server in Spring Boot, I did following things:
    - Server class was added
        - Extends the `ca.uhn.fhir.rest.server.RestfulServer` class
        - The server was decorated with `@Component` annotation
        - The server accepts autowired `IResourceProvider` as parameter
        - The server defines the _FHIR API level_ by calling it's super constructor
        - Also default encoding was set
        - `initialize` method was added, and it registers the Resource Providers
        - Initialize also setups pretty printing for the API for browser usage, but this is optional
    - Server was configured by adding a `ServerConfiguration` class
        - It stores the server instance and resource providers
        - It creates a `ServletRegistrationBean` which defines the API path
            - It can also define if the server is intialized on startup, or on demand
    - A Resource Provider was added
        - Resource Provider defines a FHIR endpoint and it's operations
        - Appointment provider was added to test out the API
    - `TomcatSettings` class was added too to allow one to use "|" character in requests (some FHIR parameters may use this)
- Then I commited the project to Github, and imported the project to Idea
    - The project was imported to Idea from Git, the version control integration was messed up if I tried to use the initial Idea project after push to Github

### Day 2

- I tried adding some web content
- If only static web page is needed when the address is accessed, one can simply add `/static/index.html` under resources
- Also tried adding Thymeleaf
    - Added `Thymeleaf starter dependency`
    - Added `IndexController`, which
        - is decorated with `@Controller`,
        - supplies with the html file name for a request path defined by `@GetMapping`
        - and can provide parameters to the Thymeleaf template
    - Added `resources/templates/index.html`
- If Thymeleaf is added, and there is also static page with same name (index.html), Thymeleaf wins
- Thymeleaf could be used to put some data from server side to page, even if front side framework is used, so I guess Thymeleaf will remain

## TODO

- Add user interface
- Customize login page
- Display appointments for customer
- Allow customer to cancel an appointment
- Utilize the Spring Boot Devtools
    - When java code is changed, it would be nice to update the app on the fly
    - Update front on the fly when front code is updated