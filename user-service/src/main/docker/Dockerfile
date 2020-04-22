FROM openjdk:8u242-jdk-buster

WORKDIR /usr/local/bin/

COPY maven/user-service-${project.version}.jar webapp.jar

RUN mkdir config

EXPOSE 8080

CMD ["java", "-jar", "webapp.jar"]