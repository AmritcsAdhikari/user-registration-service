FROM openjdk:17-alpine

# can provide build arguments while building a docker image
# docker build -t thestackschool/user-registration-service:latest --build-arg JAR_FILE=target/some-name.jar .
ARG JAR_FILE=target/user-registration-service.jar
ADD ${JAR_FILE} user-registration-application.jar
ENTRYPOINT ["java","-jar","/user-registration-application.jar"]
