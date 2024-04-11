FROM openjdk:11
EXPOSE 8087
ADD target/Foyer-1.0.jar Foyer-1.0.jar
ENTRYPOINT ["java","-jar","/Foyer-1.0.jar"]
