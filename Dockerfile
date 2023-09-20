FROM ubuntu:latest
RUN apt-get update && \
    apt-get install -y openjdk-17-jre && \
    apt-get clean
RUN mkdir /app
RUN ls ./build/libs/
COPY ./build/libs/*.jar /app/cicd.jar
WORKDIR /app
EXPOSE 8080
CMD ["java", "-jar", "cicd.jar"]
