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

    /** Konstante mit Port-Nummber, an der der Web-Server auf Anfragen von Clients "lauscht". */
    protected static final int PORT_NUMMBER = 8080;

    public static void main(String[] args) throws Exception {

        AbstractHandler meinBmiBerechnungsHandler = new BmiRestApiHandler();

        ContextHandler contextHandler = new ContextHandler();
        contextHandler.setContextPath( "/bmiberechnung" );
        contextHandler.setHandler( meinBmiBerechnungsHandler );

        Server server = new Server(PORT_NUMMBER);
        server.setHandler(contextHandler);
        server.start();
        server.join();
    }

}