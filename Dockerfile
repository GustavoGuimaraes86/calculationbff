# Start with a base image containing Java runtime
FROM gradle:8.5.0-jdk21-alpine as gradleimage

# Create a directory
WORKDIR /app

# Copy all the files from the current directory to the image
COPY . .

# build the project avoiding tests
RUN gradle clean build -x test

FROM openjdk:21-slim

COPY --from=gradleimage /app/build/libs/calculationbff-0.0.1-SNAPSHOT.jar /app/app.jar

WORKDIR /app
# Expose port 8080
EXPOSE 8080

# Run the jar file
CMD ["java", "-jar", "app.jar"]