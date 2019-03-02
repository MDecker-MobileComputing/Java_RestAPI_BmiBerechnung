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

    /**
     * Methode zur Beantwortung eines HTTP-Requests. 
     * 
     * @param target
     * @param baseRequest
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.setContentType("text/html; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("<h1>Hallo Welt</h1>");
        baseRequest.setHandled(true);
    }    

}
