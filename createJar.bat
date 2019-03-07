
@REM Erstellt JAR-Datei

cd classes
jar -cvmf ..\manifest.mf ..\RestApiBmiBerechnung.jar de/mide/restapi/bmi/*.class
cd ..
