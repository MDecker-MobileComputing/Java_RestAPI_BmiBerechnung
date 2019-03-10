#!/bin/bash

# Skript zum Start des Programms (Startet den HTTP-Server)

KLASSENPFAD=classes:lib/jetty-all-9.4.9.jar:lib/jackson-core-2.9.8.jar:lib/jackson-databind-2.9.8.jar:lib/jackson-annotations-2.9.8.jar

java -server -cp $KLASSENPFAD de.mide.restapi.bmi.MainServer
# -cp     : Classpath setzen
# -server : JVM im Server-Modus, siehe auch hier https://stackoverflow.com/a/198651/1364368

# Server an Non-Default-Port 9090 starten
#java -server -cp $KLASSENPFAD de.mide.restapi.bmi.MainServer 9090
