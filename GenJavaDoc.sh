#!/bin/bash

# Skript zum Erzeugen der JavaDoc-Doku unter Linux und MacOS.

KLASSENPFAD=classes:lib/jetty-all-9.4.9.jar:lib/jackson-core-2.9.8.jar:lib/jackson-databind-2.9.8.jar:lib/jackson-annotations-2.9.8.jar

# Sicherstellen dass Ausgabeverzeichnis existiert
mkdir -p javadoc
# -p: Keinen Fehler ausgeben, wenn das Verzeichnis schon existiert

javadoc -cp $KLASSENPFAD -private -doctitle "BMI-Berechnung als Java-Servlet mit Jetty" -d javadoc -sourcepath src -charset "UTF-8" -encoding "UTF-8" -docencoding "UTF-8" -subpackages de.mide.restapi.bmi #-html5
        
