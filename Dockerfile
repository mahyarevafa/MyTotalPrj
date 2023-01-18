FROM openjdk:17-jdk-alpine
COPY MyTotalPrj-0.0.1-SNAPSHOT.war /app/
WORKDIR /app
ENTRYPOINT ["java","-jar","/app/MyTotalPrj-0.0.1-SNAPSHOT.war"]