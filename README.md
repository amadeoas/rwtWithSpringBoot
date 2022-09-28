# Eclipse RWT with Spring Boot
This is a small example, which demonstrates how to integrate [Eclipse RWT](https://www.eclipse.org/rap/) (the UI technology of [Eclipse RAP](https://www.eclipse.org/rap/developers-guide/)) as a [String Boot](https://spring.io/projects/spring-boot) web application using [Gradle](https://docs.gradle.org/current/userguide/userguide.html) as the build tool.

This example shows:
* how to declare the RWT and JFace dependencies in a [Gradle](https://docs.gradle.org/current/userguide/userguide.html) build,
* how to integrate the moving parts of RWT with a Spring Boot web application (Spring MVC),
* how to leverage Spring managed beans, dependency injection and a pure programmatic way of configuring and wiring together an RWT application (no need for a web.xml),
* how to provide a menu,
* how to provide support to login (sample very simple using only one account with username "user" and password "user")

The example is not sophisticated, but you should give an idea on using [Eclipse RAP](https://www.eclipse.org/rap/developers-guide/) and [String Boot](https://spring.io/projects/spring-boot).

# Getting Started

## Requirements

Java 11 (or later)

## Running locally

You can use the [Gradle](https://docs.gradle.org/current/userguide/userguide.html) wrapper that comes with the project to run the commands.
If you are on Windows, use `gradlew.bat` instead.

```bash
gradlew bootRun
```

This [Gradle](https://docs.gradle.org/current/userguide/userguide.html) command will build and run the application in an embedded Tomcat container.
After the app has fully started, open a browser and visit http://localhost:8080/ or http://localhost:8080/main

Alternatively, you can use `bootJar` to create a runnable standalone jar file with an embedded Tomcat container and run it like this:

```bash
gradlew bootJar

java -jar build/libs/rwt-on-spring-boot-0.0.1-SNAPSHOT.jar
```

## Deploying as a WAR file

To build a WAR file that you can deploy to a Tomcat container installation or run in a Docker image, simply use `war`:

```bash
gradlew war
```

The resulting WAR file will be located in `build/libs`.

## Backlog

* Fix the Home view when it's shown for first time.
* Implement the footer with button(s) part of the Card element.
* Add functionality to the Cards elements in the HomeView so when clicked the corresponding view is displayed.
* Add to other views some content.
* Add proper login authentication and authorisation.
* Add progress bar when logging in.

## Additional Links
These additional references should also help you:

* [Eclipse RAP Developer's Guide](https://www.eclipse.org/rap/developers-guide/)
* [RWT Standalone](https://www.eclipse.org/rap/developers-guide/devguide.php?topic=rwt-standalone.html&version=3.20)
* [RWT Application Configuration](https://www.eclipse.org/rap/developers-guide/devguide.php?topic=application-configuration.html&version=3.20)
* [Spring Boot](https://www.eclipse.org/rap/developers-guide/)
* [Gradle](https://docs.gradle.org/current/userguide/userguide.html)
* [Mention: related code](https://github.com/bwolff/rwt-on-spring-boot)
* [Mention: related article on Java Code Geeks](https://www.javacodegeeks.com/2018/11/eclipse-rap-spring-boot.html)