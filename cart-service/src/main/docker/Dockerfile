FROM openjdk:8u242-jdk-buster

WORKDIR /usr/local/bin/

COPY maven/cart-service-${project.version}.jar webapp.jar

RUN mkdir config

EXPOSE 9060

CMD ["java", "-jar", "webapp.jar"]