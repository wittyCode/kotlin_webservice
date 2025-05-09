# Webserver für Konvertierung von camelCase und snake_case

Dieser Webserver bietet eine einfache API, um Strings zwischen den Formaten `camelCase` und `snake_case` zu konvertieren. Er wurde mit [Ktor](https://ktor.io/) entwickelt, einem Framework für asynchrone Server- und Client-Anwendungen in Kotlin.

## Features

- **Konvertierung von camelCase zu snake_case**  
  Beispiel: `camelCase` → `camel_case`
  
- **Konvertierung von snake_case zu camelCase**  
  Beispiel: `snake_case` → `snakeCase`

- **Validierung von Eingaben**  
  Der Server überprüft, ob die Eingabe im `camelCase`- oder `snake_case`-Format vorliegt. Ungültige Eingaben werden mit einem Fehler beantwortet.

## Technologien

- **[Kotlin](https://kotlinlang.org/)**: Moderne, statisch typisierte Programmiersprache.
- **[Ktor](https://ktor.io/)**: Framework für die Entwicklung von asynchronen Server- und Client-Anwendungen.
- **[Gradle](https://gradle.org/)**: Build-Tool zur Verwaltung von Abhängigkeiten und Projektkonfiguration.
- **[JUnit 5](https://junit.org/junit5/)**: Framework für Unit-Tests.

## Endpunkte

### `GET /convert`

Konvertiert einen String zwischen `camelCase` und `snake_case`.

#### Query-Parameter

- `input` (optional): Der zu konvertierende String.

#### Antworten

- **200 OK**: Erfolgreiche Konvertierung.
  - Beispiel:  
    Anfrage: `GET /convert?input=camelCase`  
    Antwort: `camel_case`
- **400 Bad Request**: Ungültige Eingabe oder fehlender Parameter.
  - Beispiel:  
    Anfrage: `GET /convert?input=invalid-input`  
    Antwort: `Input is neither camelCase nor snake_case`

## Installation und Ausführung

1. **Voraussetzungen**:
   - [JDK 22](https://jdk.java.net/)
   - [Gradle](https://gradle.org/)

2. **Projekt klonen**:
   ```bash
   git clone https://github.com/wittyCode/kotlin_webservice.git
   cd kotlin_webservice
   ```

3. **Abhängigkeiten installieren**:
   ```bash
   ./gradlew build
   ```

4. **Server starten**:
   ```./gradlew run```

5. **API testen**:
   Der Server läuft standardmäßig auf http://localhost:8080. Verwende ein Tool wie Postman oder curl, um Anfragen zu senden:
   ```bash
   curl "http://localhost:8080/convert?input=camelCase"
   ```

## Tests
Die Anwendung nutzt JUnit 5 für Unit-Tests. Die Tests sind im Verzeichnis `src/test/kotlin` abgelegt.
Um die Tests auszuführen, stelle sicher, dass du die Abhängigkeiten installiert hast und der Server nicht läuft.
Führe den folgenden Befehl aus:
```bash
./gradlew test
```

## Setup mit Docker
Um den Webserver in einem Docker-Container auszuführen, kannst du das bereitgestellte Dockerfile verwenden. Stelle sicher, dass Docker installiert ist und der Daemon läuft.
1. **Docker-Image erstellen**:
   ```bash
   ./gradlew build
   ./gradlew dockerBuild
   ```
2. **Docker-Container starten**:
   ```bash
    docker run -p 8080:8080 kotlin_webservice:latest
    ```
   
## Web Interface

Der Service bietet zusätzlich zur API ein benutzerfreundliches Web Interface zur Konvertierung der Strings.

### Nutzung des Web Interfaces

1. **Zugriff auf das Interface**:
   - Stellen Sie sicher, dass der Server läuft (`./gradlew run`)
   - Öffnen Sie die `index.html` im Browser
   - Alternativ navigieren Sie zu `http://localhost:8080`

2. **Funktionen**:
   - Eingabefeld für den zu konvertierenden Text
   - Ein Konvertierungsbutton:
     - "To Camel Case / To Snake Case": Konvertiert automatisch zwischen den Formaten
   - Ausgabefeld für das Ergebnis
   - Fehleranzeige bei ungültigen Eingaben

3. **Beispiele für die Nutzung**:
   - camelCase zu snake_case:
     ```
     Eingabe: myVariableName
     Ausgabe: my_variable_name
     ```
   - snake_case zu camelCase:
     ```
     Eingabe: my_variable_name
     Ausgabe: myVariableName
     ```

4. **Fehlermeldungen**:
   - "Please enter some text to convert": Erscheint bei leerer Eingabe
   - "Conversion failed": Erscheint bei ungültigem Format oder Serverproblemen
   - Fehleranzeige bei Verbindungsproblemen zum Server

### Technische Details des Web Interfaces

- Reines HTML und JavaScript
- Keine externen Abhängigkeiten
- Responsives Design für verschiedene Bildschirmgrößen
- Kommuniziert mit dem Backend über REST-API (`http://localhost:8080/convert`)
- CORS-aktiviert für lokale Entwicklung
- Einfaches, übersichtliches Design mit grünem Aktionsbutton
- Automatische Formaterkennung und Konvertierung

### Tipps zur Nutzung

- Der Server muss auf Port 8080 laufen
- Die Konvertierung erfolgt automatisch in beide Richtungen
- Kopieren Sie den Text direkt in das Eingabefeld
- Das Ergebnisfeld ist schreibgeschützt (readonly)
- Bei Fehlern erscheint eine rote Fehlermeldung unter dem Button