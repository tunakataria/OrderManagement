FROM openjdk:8u242-jdk-buster

WORKDIR /usr/local/bin/

COPY maven/product-service-${project.version}.jar webapp.jar

RUN mkdir config

EXPOSE 9080

CMD ["java", "-jar", "webapp.jar"]