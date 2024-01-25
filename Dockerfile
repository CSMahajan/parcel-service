FROM openjdk:8-jdk-alpine
WORKDIR /app
COPY target/*.jar /app/parcel-service.jar
EXPOSE 8080
ENV DATABASE_URL jdbc:postgresql://localhost:5432/postgres
ENV DATABASE_USERNAME postgres
ENV DATABASE_PASSWORD postgres
CMD ["java", "-jar", "parcel-service.jar"]