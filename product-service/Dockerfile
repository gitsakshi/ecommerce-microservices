FROM eclipse-temurin:21-jdk-jammy
WORKDIR /app
COPY product-service/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]