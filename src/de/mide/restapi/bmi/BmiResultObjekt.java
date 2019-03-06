package de.mide.restapi.bmi;


/**
 * Ein Objekt dieser Klasse enthält alle Informationen, die die JSON-Response enthalten soll.
 * Dieses Objekt wird mit dem Jackson-Serialisierer in einen JSON-String umgewandelt, der als
 * HTTP-Response an den Web-API-Client zurückgegeben wird.  
 */
public class BmiResultObjekt {

    public String status = "OK"; 
   
    /** BMI-Wert;  Nur die ersten beiden Nachkommastellen sind noch enthalten. */
    public double bmiGerundet = 0.0;
    
    /** BMI-Wert (Berechnungsergebnis). */
    public double bmiNichtGerundet = 0.0;
    
    /** Bewertung, z.B. "Normalgewicht" oder "Untergewicht". */
    public String bewertung = "";
    
}
