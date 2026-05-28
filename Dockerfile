# Stage 1: Build the WAR file using Maven
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -q

# Stage 2: Run on Tomcat 10.1
FROM tomcat:10.1-jdk17
# Remove default Tomcat apps
RUN rm -rf /usr/local/tomcat/webapps/*
# Copy built WAR
COPY --from=build /app/target/autocare-hub.war /usr/local/tomcat/webapps/ROOT.war
# Expose port
EXPOSE 8080
# Start Tomcat
CMD ["catalina.sh", "run"]
