FROM openjdk:8
ARG JAR_FILE=target/Catalogs-1.0.0.jar
COPY ${JAR_FILE} catalog.jar
ENTRYPOINT ["java","-Xms1024m","-Xmx1024m","-jar","/catalog.jar"]