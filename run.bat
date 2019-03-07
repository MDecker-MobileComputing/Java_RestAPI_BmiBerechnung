
@REM Startet Java-Programm dass dann HTTP-Port (Server-Port) oeffnet

@set KLASSENPFAD=classes;lib/jetty-all-9.4.9.jar;lib/jackson-core-2.9.8.jar;lib\jackson-databind-2.9.8.jar;lib\jackson-annotations-2.9.8.jar

java -server -cp %KLASSENPFAD% de.mide.restapi.bmi.MainServer
@REM -cp     : Classpath setzen
@REM -server : JVM im Server-Modus, siehe auch hier https://stackoverflow.com/a/198651/1364368 
