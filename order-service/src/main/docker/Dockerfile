FROM openjdk:8u242-jdk-buster

WORKDIR /usr/local/bin/

COPY maven/order-service-${project.version}.jar webapp.jar

EXPOSE 9061

CMD ["java", "-jar", "webapp.jar"]