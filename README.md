# REST-API "BMI-Berechnung" (Java-Servlet mit Jetty) #

Java-Programm zur Bereitstellung einer einfachen REST-API zur Berechnung des [Body-Mass-Index (BMI)](https://www.tk.de/service/app/2002866/bmirechner/bmirechner.app).
Der BMI wird durch Division des Körpergewichts in kg durch die Größe in Metern berechnet.
Je nach Geschlecht kann anhand des BMI dann eine Aussage getroffen werden, ob die Person normal-, unter- oder übergewichtig ist.

<br>

Siehe auch [dieses Repository](https://github.com/MDecker-MobileComputing/Java_RestApi_SpringBoot) für eine Beispiel-App, die
eine REST-API mit Java implementiert, allerdings mit dem Framework *Spring Boot*.

<br>

----
## REST-API-Methode ##

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

Doku dieser REST-API mit *Swagger UI*: https://mdecker-mobilecomputing.github.io/Java_RestAPI_BmiBerechnung/

<br>

----
## Jetty ##

Das Java-Programm verwendet [Jetty](https://www.eclipse.org/jetty/) als eingebetteten Servlet-Container.
Ein [Servlet](http://openbook.rheinwerk-verlag.de/javainsel9/javainsel_23_001.htm#mjd7254da57686a2ef9e5fcb69a2a97220) ist eine Java-Klasse, die HTTP-Anfragen (als Web-Server-Anfragen) beantworten kann.

Jetty befindet sich in Form einer Jar-Datei im `lib/`-Ordner dieses Projekts.
Die neueste Version der Datei `jetty-all-<version>.jar` kann [von hier](http://central.maven.org/maven2/org/eclipse/jetty/aggregate/jetty-all/) heruntergeladen werden.

Siehe auch [dieses Tutorial](https://www.eclipse.org/jetty/documentation/9.2.22.v20170531/advanced-embedding.html)
für die Verwendung von Jetty, und [hier für die API-Dokumentation](https://www.eclipse.org/jetty/javadoc/9.4.14.v20181114/index.html?overview-summary.html).

<br>

----
## License ##

See the [LICENSE file](LICENSE.md) for license rights and limitations (BSD 3-Clause License).

<br>

Third-Party-Libraries:

* [Jetty](https://www.eclipse.org/jetty/) is dual licensed under the *Apache License 2.0* and *Eclipse Public License 1.0*, see [here](https://www.eclipse.org/jetty/licenses.html).

* [Jackson](https://github.com/FasterXML/jackson) is licensed under the *Apache License 2.0*, see [here](https://github.com/FasterXML/jackson/wiki/FAQ).

<br>

The jar files for these third-party libraries (one jar file for Jetty, and three for Jackson) are contained in this repositories folder `lib`.

<br>

[swagger-ui-dist](https://www.npmjs.com/package/swagger-ui-dist), which is used to display the API definition in file [osa.yml](osa.yml), is licensed under the *Apache License 2.0*, see [here](https://github.com/swagger-api/swagger-ui/blob/master/LICENSE).
These files are contained in folder [docs](docs/) of this repository, so they can be served by [GitHub Pages](https://pages.github.com/).
In file [docs/index.html](https://github.com/MDecker-MobileComputing/Java_RestAPI_BmiBerechnung/blob/master/docs/index.html#L43) the URL to file [oas.yml](oas.yml) from this repository was inserted.
