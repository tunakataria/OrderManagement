FROM openjdk:8u242-jdk-buster

WORKDIR /usr/local/bin/

COPY maven/eureka-server-${project.version}.jar webapp.jar

EXPOSE 8761

CMD ["java", "-jar", "webapp.jar"]