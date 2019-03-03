package de.mide.restapi.bmi;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.ContextHandler;


/**
 * Haupt-Klasse mit main-Methode, startet den HTTP-Server (eingebetteter Servlet-Container "Jetty").
 * <br><br>
 *
 * This project is licensed under the terms of the BSD 3-Clause License.
 */
public class MainServer {

    /**
     * Konstante mit Port-Nummber, an der der Web-Server auf Anfragen von Clients "lauscht".
     * Der Standard-Port für HTTP-Anfragen ist aber Port 80, aber ein Server-Programm für diesen
     * Port kann auf vielen Rechnern nur mit Admin-Rechten gestartet werden.
     */
    protected static final int PORT_NUMMER = 8080;


    /**
     * Einstiegs-Methode, startet den eingebetteten Jetty-Server.
     * <br><br>
     *
     * Doku zur programmatischen Konfiguration eines ContextHandlers siehe
     * <a href="https://www.eclipse.org/jetty/documentation/9.4.x/quickstart-config-what.html#intro-jetty-configuration-contexts" target="_blank">hier</a>.
     *
     * @throws Exception  Fehler beim Server-Start aufgetreten.
     */
    public static void main(String[] args) throws Exception {

        String urlStr = "http://localhost:" + PORT_NUMMER + BmiRestApiHandler.CONTEXT_PFAD;

        System.out.println("\nLokale URL der REST-Methode: " + urlStr + "\n");


        AbstractHandler meinBmiBerechnungsHandler = new BmiRestApiHandler();

        ContextHandler contextHandler = new ContextHandler();
        contextHandler.setContextPath( BmiRestApiHandler.CONTEXT_PFAD );
        contextHandler.setHandler( meinBmiBerechnungsHandler );

        Server server = new Server(PORT_NUMMER);
        server.setHandler(contextHandler);
        server.start();
        server.join();
    }

}