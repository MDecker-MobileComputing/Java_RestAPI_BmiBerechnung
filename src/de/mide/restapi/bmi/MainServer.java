package de.mide.restapi.bmi;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;


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

        AbstractHandler meinHandler = new BmiRestApiHandler();

        Server server = new Server(PORT_NUMMBER);
        server.setHandler(meinHandler);
        server.start();
        server.join();
    }

}