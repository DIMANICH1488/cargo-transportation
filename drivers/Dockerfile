FROM openjdk:17

WORKDIR /opt/server
COPY ./target/artifacts/drivers.jar server.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "server.jar"]