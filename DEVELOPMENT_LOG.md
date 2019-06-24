# Development log

## Day 1

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

### Later that day

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

## Day 2

- I started by installing `Yarn`
- I created a new React application called "app" by typing `yarn create react-app app`
    - It fetches all sorts of stuff, and installs them and creates a project structure
    - After running with `yarn start`, browser screen was opened and the app is automatically refreshed when application files are changed
- Then I added Bootstrap `yarn add bootstrap reactstrap`, and React router `yarn add react-router-dom`
    - Bootstrap can be used by adding `import 'bootstrap/dist/css/bootstrap.min.css';` to `index.js`, 
    - and importing desired components in `App.js`, for example: `import {Button} from 'reactstrap';` and `<Button color="success">Yes button</Button>`
- Next thing is to integrate Spring Boot and React apps together somehow?

## Day 3

- I tried to use the Reactjs app and the Spring Boot app in separate ports. It quickly proved to require too much hacks to get it working. Maybe the next step is to `eject` the Reactjs Yarn project, and integrate it with the Spring Boot Gradle project.
- Problems encountered:
    - Because the apps run in different ports, CORS must be disabled in both ends
    - Even if I disabled them, Reactjs app seemed to receive only `Opaque responses`, which contain no data
    - Because of this, I removed those changes and try to integrate Reactjs and Spring Boot apps together in the same port