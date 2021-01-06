FROM maven:3.6.3-jdk-8 AS BUILDER

ENV MY_HOME=/root
RUN mkdir -p $MY_HOME
WORKDIR $MY_HOME

ADD pom.xml $MY_HOME
ADD settings.xml $MY_HOME
RUN mvn dependency:go-offline --settings settings.xml
ADD . $MY_HOME
RUN mvn clean verify -DskipTests --settings settings.xml

FROM openjdk:8-jdk-stretch
ENV MY_HOME=/root
RUN mkdir -p $MY_HOME
WORKDIR $MY_HOME

COPY --from=BUILDER /root/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

