# GhostNet Fishing

GhostNet Fishing ist eine Java-Webanwendung zur Meldung und Verwaltung sogenannter Geisternetze (verlorene oder aufgegebene Fischernetze im Meer).  
Das Projekt entstand im Rahmen einer Lehrveranstaltung und dient als Prototyp.

## Funktionen
- Geisternetz melden (anonym möglich)
- Offene Netze anzeigen
- Bergung eintragen
- Netz als geborgen melden
- Netz als verschollen melden (nicht anonym)
- Eingabevalidierung

## Technologien
- Java (Jakarta EE / JSF)
- XHTML
- Maven
- Payara Server
- Git/GitHub

## Voraussetzungen
- Java 17 oder höher
- Payara Server
- IDE (z. B. IntelliJ IDEA)

## Start
1. Projekt in der IDE öffnen
2. Payara-Server konfigurieren und das WAR deployen
3. Anwendung im Browser öffnen:
   `http://localhost:8080/ghostnet-fishing`

## Projektstruktur (kurz)
- `src/main/java` – Entities, Services, Beans
- `src/main/webapp` – XHTML-Seiten

Wichtige Seiten:
- `index.xhtml`, `melden.xhtml`, `offeneNetze.xhtml`, `eintragen.xhtml`, `verschollen.xhtml`

## Lizenz
Dieses Projekt wurde zu Lehr- und Demonstrationszwecken erstellt.