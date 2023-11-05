FROM openjdk:8-jdk-alpine
EXPOSE 8089
ADD target/achat-devops-1.0.jar achat-devops-1.0.jar
ENTRYPOINT ["java","-jar","/achat-1.0.jar"]