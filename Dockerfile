FROM openjdk:17
RUN mkdir /app
COPY ./build/libs/studytalk-0.0.1-SNAPSHOT.jar /app/cicd.jar
WORKDIR /app
EXPOSE 8080
CMD ["java", "-jar", "cicd.jar"]