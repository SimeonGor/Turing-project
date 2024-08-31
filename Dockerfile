FROM openjdk:17-jdk-slim
LABEL authors="simeongor"

COPY sub.cer certificate.cer
RUN  echo yes | keytool -import -trustcacerts -file certificate.cer -alias russian_trusted_sub_ca-cer \
     -keystore $JAVA_HOME/lib/security/cacerts -storepass changeit

COPY ./target/Turing_project-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]