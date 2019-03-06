#!/bin/bash

# Skript zum Kompilieren des Programms unter Linux und MacOS.

KLASSENPFAD=classes:lib/jetty-all-9.4.9.jar;lib/jackson-core-2.9.8.jar;lib/jackson-databind-2.9.8.jar;lib/jackson-annotations-2.9.8.jar


# Sicherstellen, dass Ausgabe-Verzeichnis fuer Compiler existiert
mkdir -p classes
# -p: Keinen Fehler ausgeben, wenn das Verzeichnis schon existiert


javac -cp $KLASSENPFAD -d classes -sourcepath src src/de/mide/restapi/bmi/*.java
# -cp         : Classpath setzen
# -d          : Destination folder (Zielverzeichnis fuer class-Dateien)
# -sourcepath : Einstiegsverzeichnis fuer Java-Quellcode-Dateien
