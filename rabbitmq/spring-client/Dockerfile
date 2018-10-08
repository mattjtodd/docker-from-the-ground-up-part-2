FROM maven:3-jdk-11-slim as build

WORKDIR /work

COPY pom.xml ./pom.xml
COPY src ./src

RUN mvn clean package

FROM openjdk:11-jre-slim

COPY --from=build /work/target/rabbitmq-spring-0.1.0-SNAPSHOT.jar .

CMD ["java", "-jar", "rabbitmq-spring-0.1.0-SNAPSHOT.jar"]