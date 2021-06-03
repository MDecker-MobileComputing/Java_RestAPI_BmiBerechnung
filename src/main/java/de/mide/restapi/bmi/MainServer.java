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
     * Diese Default-Port-Nummer kann mit einem Kommandozeilen-Argument überschrieben werden.
     */
    protected static final int DEFAULT_PORT_NUMMER = 8080;


    /**
     * Einstiegs-Methode, startet den eingebetteten Jetty-Server.
     * <br><br>
     *
     * Doku zur programmatischen Konfiguration eines ContextHandlers siehe
     * <a href="https://www.eclipse.org/jetty/documentation/9.4.x/quickstart-config-what.html#intro-jetty-configuration-contexts" target="_blank">hier</a>.
     *
     * @param args  Kommandozeilen-Argumente; als einziges Argument kann eine Port-Nummber übergeben werden
     *              um die in {@link MainServer#DEFAULT_PORT_NUMMER} definierte Default-Port-Nummber zu überschreiben.
     *
     * @throws Exception  Fehler beim Server-Start aufgetreten.
     */
    public static void main(String[] args) throws Exception {
        
        int portNummer = DEFAULT_PORT_NUMMER;
        
        if (args.length > 1) {

            throw new Exception("Mehr als ein Kommandozeilen-Argument uebergeben.");
        }
        
        if (args.length == 1) {
            
            try {
            	
                portNummer = Integer.parseInt( args[0] );
                
            } catch (NumberFormatException ex) {
                
                throw new Exception("Ungueltige Port-Nummber \"" + args[0] + "\" als Kommandozeilen-Argument uebergeben.");
            }            
        }
        

        String urlStr = "http://localhost:" + portNummer + BmiRestApiHandler.CONTEXT_PFAD;

        System.out.println("\nLokale URL der REST-Methode: " + urlStr + "\n");


        AbstractHandler meinBmiBerechnungsHandler = new BmiRestApiHandler();

        ContextHandler contextHandler = new ContextHandler();
        contextHandler.setContextPath( BmiRestApiHandler.CONTEXT_PFAD );
        contextHandler.setHandler( meinBmiBerechnungsHandler );

        Server server = new Server(portNummer);
        server.setHandler(contextHandler);
        server.start();
        server.join();
    }

}