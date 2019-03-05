# REST-API "BMI-Berechnung" (Java-Servlet mit Jetty)

Java-Programm zur Bereitstellung einer einfachen REST-API zur Berechnung des [Body-Mass-Index (BMI)](https://projekte.uni-hohenheim.de/wwwin140/info/interaktives/bmi.htm).
Der BMI wird durch Division des Körpergewichts in kg durch die Größe in Metern berechnet.
Je nach Geschlecht kann anhand des BMI dann eine Aussage getroffen werden, ob die Person normal-, unter- oder übergewichtig ist.

<br>

----
## REST-API-Methode

Wenn das Programm auf dem lokalen Rechner gestartet wird, dann ist die REST-API-Methode (Endpoint) für 
die BMI-Berechnung unter der folgenden URL verfügbar:

    http://localhost:8080/bmiberechnung/


Beim Aufruf dieser REST-API-Methode müssen immer die folgenden drei URL-Parameter übergeben werden:
* `gewicht`   : Gewicht in kg (ganzzahlig)
* `groesse`   : Körpergröße in cm (ganzzahlig)
* `geschlecht`: Entweder `mann` oder `frau`

Beispiel für eine zulässige URL:

    http://localhost:8080/bmiberechnung/?gewicht=75&groesse=180&geschlecht=mann

Wenn nur einer dieser URL-Parameter nicht gesetzt ist oder einen ungültigen Wert hat, dann wird eine Exception geworfen.

<br>

----
## Jetty

Das Java-Programm verwendet [Jetty](https://www.eclipse.org/jetty/) als eingebetteten Servlet-Container.
Ein [Servlet](http://openbook.rheinwerk-verlag.de/javainsel9/javainsel_23_001.htm#mjd7254da57686a2ef9e5fcb69a2a97220) ist eine Java-Klasse, die HTTP-Anfragen (als Web-Server-Anfragen) beantworten kann.

Jetty befindet sich in Form einer Jar-Datei im `lib/`-Ordner dieses Projekts.
Die neueste Version der Datei `jetty-all-<version>.jar` kann [von hier](http://central.maven.org/maven2/org/eclipse/jetty/aggregate/jetty-all/) heruntergeladen werden.

Siehe auch [dieses Tutorial](https://www.eclipse.org/jetty/documentation/9.2.22.v20170531/advanced-embedding.html)
für die Verwendung von Jetty, und [hier für die API-Dokumentation](https://www.eclipse.org/jetty/javadoc/9.4.14.v20181114/index.html?overview-summary.html).

<br>

----
## Kompilierung und Ausführung

Das Repository kann als Eclipse-Projekt importiert werden.

<br>

Unter Windows kann das Skript `build.bat` zur Kompilierung und danach `run.bat` zur Ausführung aufgerufen werden.

Unter Linux und MacOS kann das Skript `build.sh` zur Kompilierung und danach `run.sh` zur Ausführung aufgerufen werden.

<br>

----
# License

See the [LICENSE file](LICENSE.md) for license rights and limitations (BSD 3-Clause License).

[Jetty](https://www.eclipse.org/jetty/) (which is included as jar file in folder `lib/` of this project because it is a dependency of the program) is dual licensed under the Apache License 2.0 and Eclipse Public License 1.0., see also [here](https://www.eclipse.org/jetty/licenses.html).

