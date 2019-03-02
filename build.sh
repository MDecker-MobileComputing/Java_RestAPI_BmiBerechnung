#!/bin/bash

# Skript zum Kompilieren des Programms unter Linux und MacOS.

KLASSENPFAD=classes:lib/jetty-all-9.4.9.jar

# Sicherstellen, dass Ausgabe-Verz vorhanden
mkdir -p classes
# -p: Keinen Fehler ausgeben, wenn das Verzeichnis schon existiert


javac -cp $KLASSENPFAD -d classes -sourcepath src src/de/mide/restapi/bmi/*.java
# -cp         : Classpath setzen
# -d          : Destination folder (Zielverzeichnis fuer class-Dateien)
# -sourcepath : Einstiegsverzeichnis fuer Java-Quellcode-Dateien
