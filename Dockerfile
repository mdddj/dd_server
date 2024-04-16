FROM openjdk:22
VOLUME /tmp
ADD ./build/libs/dd_service-2.0.0.jar dd_service.jar
ENTRYPOINT ["java","-jar","/dd_service.jar"]