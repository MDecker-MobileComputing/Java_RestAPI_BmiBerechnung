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
     * @throws ServletException  Servlet/HTTP-Fehler.
     */
    @Override
    public void handle(String              target     , 
                       Request             baseRequest, 
                       HttpServletRequest  request    , 
                       HttpServletResponse response) throws IOException, ServletException {

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK); // HTTP-Antwort-Code 200
        response.getWriter().println("{ \"status\": \"ok\"}");

        baseRequest.setHandled(true);
    }    

}
