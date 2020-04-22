FROM openjdk:8u242-jdk-buster

WORKDIR /usr/local/bin/

COPY maven/auth-server-${project.version}.jar webapp.jar

RUN mkdir config

EXPOSE 9991

CMD ["java", "-jar", "webapp.jar"]