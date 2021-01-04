FROM openjdk:11
VOLUME /tmp
EXPOSE 8090
ADD ./target/microservicio-personas-0.0.1-SNAPSHOT.jar personas-app.jar
ENTRYPOINT ["java","-jar","/personas-app.jar"]