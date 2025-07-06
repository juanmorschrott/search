FROM maven:3.9.8-eclipse-temurin-21-jammy as builder

WORKDIR /app

COPY pom.xml ./

COPY .mvn/ .mvn

COPY aggregate-report/pom.xml aggregate-report/pom.xml
COPY application/pom.xml application/pom.xml
COPY bootstrap/pom.xml bootstrap/pom.xml
COPY domain/pom.xml domain/pom.xml
COPY infrastructure/pom.xml infrastructure/pom.xml

COPY application/src /app/application/src
COPY bootstrap/src /app/bootstrap/src
COPY domain/src /app/domain/src
COPY infrastructure/src /app/infrastructure/src

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

COPY --from=builder /app/bootstrap/target/bootstrap-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
