FROM gradle:latest as build
COPY . /app
WORKDIR /app
RUN gradle build

FROM openjdk:17
COPY --from=build /app/build/libs/*.jar /app/cicd.jar
WORKDIR /app
EXPOSE 8080
CMD ["java", "-jar", "cicd.jar"]