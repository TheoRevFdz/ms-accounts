FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/ms-accounts-0.0.1-SNAPSHOT.jar ./ms-accounts.jar

EXPOSE 8082

CMD [ "java", "-jar", "ms-accounts.jar" ]