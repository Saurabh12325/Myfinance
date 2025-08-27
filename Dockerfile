FROM openjdk:17-jdk
WORKDIR /app
COPY target/Myfinance-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "app.jar"]
