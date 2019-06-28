package de.mide.restapi.bmi;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


/**
 * Klasse mit der Logik zur Beantwortung eines HTTP-Requests.
 * <br><br>
 *
 * This project is licensed under the terms of the BSD 3-Clause License.
 */
public class BmiRestApiHandler extends AbstractHandler  {

    /** URL-Pfad, unter dem diese REST-Methode (Endpoint) zu erreichen ist. */
    public static final String CONTEXT_PFAD = "/bmiberechnung";

    /** Objekt zur Serialisierung eines Java-Objekts nach JSON; wird im Konstruktor erzeugt. */
    public ObjectMapper _jacksonObjectMapper = null;
    
    
    /**
     * Jackson-Serialisierer wird erzeugt. 
     */
    public BmiRestApiHandler() {
        
        _jacksonObjectMapper = new ObjectMapper();
        _jacksonObjectMapper.enable(SerializationFeature.INDENT_OUTPUT); // "Pretty Printing" einschalten        
    }


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
                throw new ServletException( "Kein zulaessiger Wert fuer URL-Parameter \"geschlecht\": \"" + parameterWert + "\" -- nur die Werte \"mann\" und \"frau\" sind zulaessig." );
        }        
    }
    
    
    /**
     * BMI-Wert auswerten nach (nach DGE, Ernährungsbericht 1992) in Abhängigkeit von Geschlecht.
     * 
     * @param bmiWert  Ungerundeter BMI-Wert.
     * 
     * @param istMann  {@code true} für männlich, {@code false} für weiblich.
     * 
     * @return  String mit Bewertung von BMI-Wert, z.B. "Untergewicht" oder "Normalgewicht". 
     */
    protected String bmiAuswerten(double bmiWert, boolean istMann) {
        
        if (istMann) {
            
            if (bmiWert < 20) {
                
                return "Untergewicht";
                
            } else if (bmiWert <= 25 ) {
                                
                return "Normalgewicht";
                
            } else if (bmiWert <= 30) {
                
                return "Übergewicht";
                
            } else if (bmiWert <= 40) {
                
                return "Adipositas";
                
            } else {
                
                return "Massive Adipositas";
            }
                        
        } else { // für Frauen
            
            if (bmiWert < 19) {
                
                return "Untergewicht";
                
            } else if (bmiWert <= 24 ) {
                                
                return "Normalgewicht";
                
            } else if (bmiWert <= 30) {
                
                return "Übergewicht";
                
            } else if (bmiWert <= 40) {
                
                return "Adipositas";
                
            } else {
                
                return "Massive Adipositas";
            }                        
        }        
    }


    /**
     * Methode zur Beantwortung eines HTTP-Requests.
     *
     * @param target  URL-Pfad von Request.
     * 
     * @param baseRequest  Ursprünglicher Request.
     * 
     * @param request  Anfrage-Objekt, aus dem die URL-Parameter ausgelesen werden.
     * 
     * @param response  Antwort-Objekt (JSON-String).
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
        
        String logString = String.format("%nURL-Parameter-Werte: Gewicht=%dkg, Groesse=%dcm, istMann=%b.", 
                                         gewichtKg, groesseCm, istMann);
        System.out.println( logString ); // Beispiel-Ausgabe: URL-Parameter-Werte: Gewicht=80kg, Groesse=170cm, istMann=true.

        
	// Eigentliche BMI-Berechnung                
        double groesseMeter  = groesseCm / 100.0;        
        double bmiUngerundet = gewichtKg / ( groesseMeter * groesseMeter ); 
        double bmiGerundet   = ((int)( bmiUngerundet * 100)) / 100.0;

        String bewertung = bmiAuswerten( bmiUngerundet, istMann);


        // Java-Objekt in JSON-String umwandeln
        BmiResultObjekt resultObjekt = new BmiResultObjekt(bmiUngerundet, bmiGerundet, bewertung);
        System.out.println( resultObjekt.toString() );
        String jsonResultString = _jacksonObjectMapper.writeValueAsString(resultObjekt);        


        // HTTP-Response erstellen
        response.setContentType( "application/json" );
        response.setStatus( HttpServletResponse.SC_OK ); // HTTP-Antwort-Code 200
        response.getWriter().println( jsonResultString );        
        
        baseRequest.setHandled( true );
    }

}
