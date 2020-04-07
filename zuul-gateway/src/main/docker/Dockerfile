FROM openjdk:8u242-jdk-buster

WORKDIR /usr/local/bin/

COPY maven/zuul-gateway-${project.version}.jar webapp.jar

EXPOSE 8080

CMD ["java", "-jar", "webapp.jar"]