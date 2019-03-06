
@set KLASSENPFAD=classes;lib/jetty-all-9.4.9.jar;lib/jackson-core-2.9.8.jar;lib\jackson-databind-2.9.8.jar;lib\jackson-annotations-2.9.8.jar

@REM Sicherstellen, dass Ausgabe-Verzeichnis für Compiler existiert
@if not exist classes md classes

javac -cp %KLASSENPFAD% -d classes -sourcepath src src/de/mide/restapi/bmi/*.java
@REM -cp         : Classpath setzen
@REM -d          : Destination folder (Zielverzeichnis fuer class-Dateien)
@REM -sourcepath : Einstiegsverzeichnis fuer Java-Quellcode-Dateien
