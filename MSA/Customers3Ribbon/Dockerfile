FROM openjdk:8
ARG JAR_FILE=target/Customers-1.0.0.jar
COPY ${JAR_FILE} customer.jar
ENTRYPOINT ["java","-Xms1024m","-Xmx1024m","-jar","/customer.jar"]