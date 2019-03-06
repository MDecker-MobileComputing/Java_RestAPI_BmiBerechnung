
@REM Startet Java-Programm dass dann HTTP-Port (Server-Port) öffnet

@set KLASSENPFAD=classes;lib/jetty-all-9.4.9.jar;lib/jackson-core-2.9.8.jar;lib\jackson-databind-2.9.8.jar;lib\jackson-annotations-2.9.8.jar

java -cp %KLASSENPFAD% de.mide.restapi.bmi.MainServer
@REM -cp : Classpath setzen
