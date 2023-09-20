FROM openjdk:17-jre
VOLUME /tmp
RUN mkdir /app
COPY . /app
WORKDIR /app
RUN /app/gradlew build
RUN mv /app/build/libs/*.jar /app/app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/app.jar"]