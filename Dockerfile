# Verwende ein leichtes JDK-Image
FROM openjdk:22-jdk-slim

# Arbeitsverzeichnis setzen
WORKDIR /app

# Abhängigkeiten kopieren
COPY build/libs/*.jar kotlin_webservice.jar

# Port für den Server
EXPOSE 8080

# Startbefehl
ENTRYPOINT ["java", "-jar", "kotlin_webservice.jar"]