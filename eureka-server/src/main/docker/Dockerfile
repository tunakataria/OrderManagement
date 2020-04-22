FROM openjdk:8u242-jdk-buster

WORKDIR /usr/local/bin/

COPY maven/eureka-server-${project.version}.jar webapp.jar

RUN mkdir config

EXPOSE 8761

CMD ["java", "-jar", "webapp.jar"]