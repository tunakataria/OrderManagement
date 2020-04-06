FROM openjdk:8u242-jdk-buster

WORKDIR /usr/local/bin/

ENV MODULE_NAME="auth-server"

ENV MODULE_VERSION="1.0-SNAPSHOT"

ENV WAR_FILE="./target/${MODULE_NAME}-${MODULE_VERSION}.jar"

COPY ${WAR_FILE} webapp.jar
EXPOSE 9991
CMD ["java", "-jar", "webapp.jar"]