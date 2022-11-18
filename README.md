# zerokvm Project


## Running the application in dev mode (live coding enabled)

```shell script
./gradlew quarkusDev
```

> Dev UI: http://localhost:8080/q/dev/

## Packaging and running the application

The application can be packaged using:

```shell script
./gradlew build
./gradlew build -Dquarkus.package.type=uber-jar
./gradlew build -Dquarkus.package.type=native
./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true
```

The application can be run using:

```shell script
java -jar build/quarkus-app/quarkus-run.jar
java -jar build/*-runner.jar
./build/zerokvm-1.0-runner
```
