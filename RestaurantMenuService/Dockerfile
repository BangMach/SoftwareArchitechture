FROM openjdk
VOLUME /redis-service
ADD target/Restaurant-Menu-Service.jar Restaurant-Menu-Service.jar
EXPOSE 8084
ENTRYPOINT ["java" , "-jar","Restaurant-Menu-Service.jar"]