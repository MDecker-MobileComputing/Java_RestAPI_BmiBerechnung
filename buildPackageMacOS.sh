#!/bin/bash

# Skript zum Erstellen einer Installations-Datei fuer MacOS (DMG-Datei)

KLASSENPFAD=classes:lib/jetty-all-9.4.9.jar:lib/jackson-core-2.9.8.jar:lib/jackson-databind-2.9.8.jar:lib/jackson-annotations-2.9.8.jar

ZIELVERZEICHNIS=installer

# Sicherstellen, dass Ausgabe-Verzeichnis existiert
mkdir -p $ZIELVERZEICHNIS
# -p: Keinen Fehler ausgeben, wenn das Verzeichnis schon existiert


javapackager \
    -cp $KLASSENPFAD \
    -deploy \
    -native dmg \
    -BsystemWide=true \
#        -BjvmOptions=-Xmx128m
#        -BjvmOptions=-Xms128m
#        -BjvmOptions=-server
    -outdir $ZIELVERZEICHNIS \
    -outfile RestApiBmiBerechnung \
    -srcdir classes \
    -appclass de.mide.restapi.bmi.MainServer \
    -name RestApiBmiBerechnung \
    -title "REST-API BMI-Berechnung"

# -deploy dmg: "Assembles the application package for redistribution"; "dmg" ist Format fuer MacOS
# -BsystemWide:  "Set to true to install the application in Program Files. Set to false to install the application in the user's home directory."
# Quotes from https://docs.oracle.com/javase/8/docs/technotes/tools/windows/javapackager.html

 