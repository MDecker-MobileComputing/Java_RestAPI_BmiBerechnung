package de.mide.restapi.bmi;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;


/**
 * Klasse mit der Logik zur Beantwortung eines HTTP-Requests.
 * <br><br>
 *
 * This project is licensed under the terms of the BSD 3-Clause License.
 */
public class BmiRestApiHandler extends AbstractHandler  {

    /** URL-Pfad, unter dem diese REST-Methode (Endpoint) zu erreichen ist. */
    public static final String CONTEXT_PFAD = "/bmiberechnung";



    /**
     * Methode zur Beantwortung eines HTTP-Requests.
     *
     * @param target             URL-Pfad von Request.
     * @param baseRequest        Ursprünglicher Request.
     * @param request            Anfrage-Objekt.
     * @param response           Antwort-Objekt.
     * @throws IOException       Allgemeiner Ein-/Ausgabe-Fehler.
     * @throws ServletException  Servlet/HTTP-Fehler; wird auch geworfen, wenn URL-Parameter fehlt
     *                           oder ungültigen Wert hat.
     */
    @Override
    public void handle( String              target     ,
                        Request             baseRequest,
                        HttpServletRequest  request    ,
                        HttpServletResponse response     ) throws IOException,
                                                                  ServletException {

        // URL-Parameter auswerten
        String gewichtStr = request.getParameter( "gewicht" ); // Gewicht in kg
        int gewichtKg = -1;
        try {
            gewichtKg = Integer.parseInt( gewichtStr );
        }
        catch (NumberFormatException ex) {
            throw new ServletException( "Kein zulaessiger Wert fuer URL-Parameter \"gewicht\": \"" + gewichtStr + "\"" );
        }

        String groesseStr = request.getParameter( "groesse" ); // Körpergröße in cm
        int groesseCm = -1;
        try {
            groesseCm = Integer.parseInt( groesseStr );
        }
        catch (NumberFormatException ex) {
            throw new ServletException( "Kein zulaessiger Wert fuer URL-Parameter \"groesse\": \"" + groesseStr + "\"" );
        }

        String geschlechtString = request.getParameter( "geschlecht" );
        if (geschlechtString == null) {
            throw new ServletException( "URL-Parameter \"geschlecht\" ist nicht gesetzt." );
        }
        boolean istMann = true;
        switch ( geschlechtString.toLowerCase() ) {
            case "mann": istMann = true;  break;
            case "frau": istMann = false; break;
            default: throw new ServletException( "Kein zulaessiger Wert fuer URL-Parameter \"geschlecht\": \"" + geschlechtString + "\"" );
        }


		// Eigentliche BMI-Berechenung.



        // HTTP-Response erstellen
        response.setContentType( "application/json" );
        response.setStatus( HttpServletResponse.SC_OK ); // HTTP-Antwort-Code 200
        response.getWriter().println( "{ \"status\": \"ok\"}" );

        // IDEE: JSON-Dokument mit Jackson erzeugen ( https://wilddiary.com/serialize-java-objects-json-back/ )

        baseRequest.setHandled( true );
    }

}
