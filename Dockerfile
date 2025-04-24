FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY /target/price-api-0.0.1.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
