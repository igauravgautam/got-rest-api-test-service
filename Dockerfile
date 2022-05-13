FROM java:8
EXPOSE 8080
ADD /target/got.jar got.jar
ENTRYPOINT ["java","-jar","GOT-REST-API-1.0-SNAPSHOT.jar"]