FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY target/jobms-0.0.1-SNAPSHOT.jar jobms-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "jobms-0.0.1-SNAPSHOT.jar"]