# Eclipse RWT with Spring Boot
This is a small example, which demonstrates how to integrate [Eclipse RWT](https://www.eclipse.org/rap/) (the UI technology of [Eclipse RAP](https://www.eclipse.org/rap/developers-guide/)) as a [String Boot](https://spring.io/projects/spring-boot) web application using [Gradle](https://docs.gradle.org/current/userguide/userguide.html) as the build tool.

This example shows:
* how to declare the RWT and JFace dependencies in a [Gradle](https://docs.gradle.org/current/userguide/userguide.html) build,
* how to integrate the moving parts of RWT with a Spring Boot web application (Spring MVC),
* how to leverage Spring managed beans, dependency injection and a pure programmatic way of configuring and wiring together an RWT application (no need for a web.xml),
* how to provide a menu,
* how to provide support to login (sample very simple using only one account with username "user" and password "user"),
* how to use buttons,
* how to use clickable Labels, i.e. links

This APP is compose of two main views the menu side on the left and the data side on the right.
The menu side is compose of three distinctive areas a top one with the name of the APP, middle side with the menu, and a button side (footer) with the login/logout.

The menu provides five views, which are:
- **Home**: starting page, which does not require to be logged in to see it.
- **First view**: the first customised view, which requires to be logged in to see it.
- **Second view**: the second customised view, which requires to be logged in to see it.
- **Third view**: the third customised view, which requires to be logged in to see it.
- **About**: Some data about the application, which does not require to be logged in to see it.

If a view that requires to be logged in before it can be seen is selected/clicked and it is not already logged in then the login page is displayed. On successful logged in the previously selected/clicked view is displayed.

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

java -jar build/libs/rwtwithspringboot-0.0.1-SNAPSHOT.jar
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
