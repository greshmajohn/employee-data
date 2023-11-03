FROM openjdk:17
VOLUME /tmp/app
EXPOSE 8082
COPY target/insurance-0.0.1-SNAPSHOT.jar emp-insurance.jar
ENTRYPOINT ["java","-jar","/emp-insurance.jar"]