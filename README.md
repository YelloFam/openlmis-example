# OpenLMIS Example Service
This example is meant to show an OpenLMIS 3.x Independent Service at work.

## Prerequisites
* Docker 1.11+

## Quick Start
1. Fork/clone this repository from GitHub.

 ```shell
 git clone https://github.com/OpenLMIS/openlmis-template-service.git <openlmis-yourServiceName>
 ```
2. To properly test this example service is working, enter `spring.mail` values in the `application.properties` file.
3. Develop w/ Docker by running `docker-compose run --service-ports example`.  
See [Developing w/ Docker](#devdocker).
4. Run the migrations, using `gradle flywayMigrate`.
5. To start the Spring Boot application, run with: `gradle bootRun`.
6. Go to `http://<yourDockerIPAddress>:8080/api/` to see the APIs.
7. To test that email notifications are working in the example service, POST to:

 `http://<yourDockerIPAddress>:8080/api/notifications`
 
 some JSON object like so (put Content-Type: application/json in the headers):
 
 ```
 { "recipient": "<aValidEmailAddress>", "subject": "Test message", "message": "This is a test." }
 ```

## Building & Testing

Gradle is our usual build tool.  This template includes common tasks 
that most Services will find useful:

- `clean` to remove build artifacts
- `build` to build all source
- `generateMigration -PmigrationName=<yourMigrationName>` to create a 
"blank" database migration script. A file 
will be generated under `src/main/resources/db/migration`. Put your 
migration SQL into that file.

While Gradle is our usual build tool, OpenLMIS v3+ is a collection of 
Independent Services where each Gradle build produces 1 Service. 
To help work with these Services, we use Docker to develop, build and 
publish these.

See [Developing with Docker](#devdocker). 

## <a name="devdocker"></a> Developing with Docker

OpenLMIS utilizes Docker to help with development, building, publishing
and deployment of OpenLMIS Services. This helps keep development to 
deployment environments clean, consistent and reproducible and 
therefore using Docker is recommended for all OpenLMIS projects.

To enable development in Docker, OpenLMIS publishes a couple Docker 
Images:

- [openlmis/dev](https://hub.docker.com/r/openlmis/dev/) - for Service 
development.  Includes the JDK & Gradle plus common build tools.
- [openlmis/postgres](https://hub.docker.com/r/openlmis/postgres/) - for 
quickly standing up a shared PostgreSQL DB

In addition to these Images, each Service includes Docker Compose 
instructions to:

- standup a development environment (run Gradle)
- build a lean image of itself suitable for deployment
- publish its deployment image to a Docker Repository

### Development Environment
Launches into shell with Gradle & JDK available suitable for building 
Service.  PostgreSQL connected suitable for testing. If you run the 
Service, it should be available on port 8080.

```shell
> docker-compose run --service-ports <serviceName>
$ gradle clean build
$ gradle bootRun
```

### Build Deployment Image
```shell
> docker-compose -f docker-compose.builder.yml run builder
> docker-compose -f docker-compose.builder.yml build image
```

### Publish to Docker Repository
TODO
