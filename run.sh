#!/bin/bash

# Skript zum Start des Programms (Startet den HTTP-Server)

KLASSENPFAD=classes:lib/jetty-all-9.4.9.jar

java -cp $KLASSENPFAD de.mide.restapi.bmi.MainServer
# -cp         : Classpath setzen
