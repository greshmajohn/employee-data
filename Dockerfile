FROM openjdk:17
VOLUME /tmp
EXPOSE 8083
COPY target/data-0.0.1-SNAPSHOT.jar emp-data.jar
ENTRYPOINT ["java","-jar","/emp-data.jar"]