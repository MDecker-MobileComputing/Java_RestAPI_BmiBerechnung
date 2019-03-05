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
     * Auswerten des URL-Parameters {@code gewicht}.
     * 
     * @param parameterWert  Wert des URL-Parameters {@code gewicht}
     * 
     * @return  Gewicht in kg
     * 
     * @throws ServletException  Unzulässiger Parameter-Wert (leer oder kann nicht nach int geparst werden).
     */
    protected int parseParameterGewicht(String parameterWert) throws ServletException {
        
        if (parameterWert == null || parameterWert.trim().length() == 0) {
            throw new ServletException( "Kein Wert fuer URL-Parameter \"gewicht\"." );
        }
        
        try {
            return Integer.parseInt( parameterWert );
        }
        catch (NumberFormatException ex) {
            throw new ServletException( "Kein zulaessiger Wert fuer URL-Parameter \"gewicht\": \"" + parameterWert + "\"" );
        }    
    }


    /**
     * Auswerten des URL-Parameters {@code groesse}.
     * 
     * @param parameterWert  Wert des URL-Parameters {@code groesse}
     * 
     * @return  Größe in cm
     * 
     * @throws ServletException  Unzulässiger Parameter-Wert (leer oder kann nicht nach int geparst werden).
     */
    protected int parseParameterGroesse(String parameterWert) throws ServletException {

        if (parameterWert == null || parameterWert.trim().length() == 0) {
            throw new ServletException( "Kein Wert fuer URL-Parameter \"groesse\"." );
        }
                
        try {
            return Integer.parseInt( parameterWert );
        }
        catch (NumberFormatException ex) {
            throw new ServletException( "Kein zulaessiger Wert fuer URL-Parameter \"groesse\": \"" + parameterWert + "\"" );
        }    
    }

    
    /**
     * Auswerten des URL-Parameters {@code geschlecht}.
     * 
     * @param parameterWert  Wert des URL-Parameters {@code geschlecht}
     * 
     * @return  {@code true} für männlich, {@code false} für weiblich.
     * 
     * @throws ServletException  Unzulässiger Parameter-Wert (leer oder anderer Wert als "mann" oder "frau").
     */    
    protected boolean parseParameterGeschlecht(String parameterWert) throws ServletException {
        
        if (parameterWert == null || parameterWert.trim().length() == 0) {
            throw new ServletException( "Kein Wert fuer URL-Parameter \"geschlecht\"." );                        
        }
        
        switch ( parameterWert.toLowerCase() ) {
        
            case "mann": 
                return true;
            
            case "frau": 
                return false;
            
            default: 
                throw new ServletException( "Kein zulaessiger Wert fuer URL-Parameter \"geschlecht\": \"" + parameterWert + "\"" );
        }        
    }


    /**
     * Methode zur Beantwortung eines HTTP-Requests.
     *
     * @param target  URL-Pfad von Request.
     * 
     * @param baseRequest  Ursprünglicher Request.
     * 
     * @param request  Anfrage-Objekt.
     * 
     * @param response  Antwort-Objekt.
     * 
     * @throws IOException  Allgemeiner Ein-/Ausgabe-Fehler.
     * 
     * @throws ServletException   Servlet/HTTP-Fehler; wird auch geworfen, wenn URL-Parameter fehlt
     *                            oder ungültigen Wert hat.
     */
    @Override
    public void handle( String              target     ,
                        Request             baseRequest,
                        HttpServletRequest  request    ,
                        HttpServletResponse response   ) throws IOException,
                                                                ServletException {

        // URL-Parameter auswerten
        String gewichtStr = request.getParameter( "gewicht" );
        int    gewichtKg  = parseParameterGewicht( gewichtStr );

        String groesseStr = request.getParameter( "groesse" );
        int    groesseCm  = parseParameterGroesse( groesseStr ); 
        
        String  geschlechtString = request.getParameter( "geschlecht" );
        boolean istMann          = parseParameterGeschlecht( geschlechtString ); 

        
		// TODO: Eigentliche BMI-Berechnung
        double bmi         = gewichtKg / Math.pow(groesseCm / 100.0, 2);
        double bmiGerundet = Math.round(bmi * 100.0) / 100.0;



        // HTTP-Response erstellen
        response.setContentType( "application/json" );
        response.setStatus( HttpServletResponse.SC_OK ); // HTTP-Antwort-Code 200
        response.getWriter().println( "{ \"status\": \"ok\", \"bmi\": " + bmiGerundet + "}" );

        // IDEE: JSON-Dokument mit Jackson erzeugen ( https://wilddiary.com/serialize-java-objects-json-back/ )

        baseRequest.setHandled( true );
    }

}
