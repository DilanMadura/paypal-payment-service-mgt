FROM openjdk:21-jdk
ADD target/basic-project.jar basic-project.jar
ENTRYPOINT ["java","-jar","/basic-project.jar"]