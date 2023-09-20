FROM openjdk:17
COPY ./build/libs/*.jar /app/cicd.jar
WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "cicd.jar"]