FROM amazoncorretto:21-alpine-jdk

COPY ./target/volodb-0.0.1.jar ./volodb-api.jar
CMD ["java","-jar","./volodb-api.jar", "--spring.config.additional-location=classpath:/application-staging.yaml"]