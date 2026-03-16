FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
COPY inventory-service/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]