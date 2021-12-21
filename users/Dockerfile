FROM openjdk:17

WORKDIR /opt/server
COPY ./target/artifacts/users.jar server.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "server.jar"]