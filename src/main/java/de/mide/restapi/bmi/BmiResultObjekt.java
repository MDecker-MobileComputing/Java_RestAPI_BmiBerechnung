package de.mide.restapi.bmi;


/**
 * Ein Objekt dieser Klasse enthält alle Informationen, die die JSON-Response enthalten soll.
 * Dieses Objekt wird mit dem Jackson-Serialisierer in einen JSON-String umgewandelt, der als
 * HTTP-Response an den Web-API-Client zurückgegeben wird.
 * <br>
 *  
 * Es wird für alle öffentliche Member-Variablen ein Key-Value-Paar in den JSON-String geschrieben. 
 * <br><br>
 *
 * This project is licensed under the terms of the BSD 3-Clause License.
 */
public class BmiResultObjekt {
    
    /** Status ist immer "OK" wenn es gelingt, ein Objekt dieser Klasse zu serialisieren und zurückzugeben. */ 
    public String status = "OK"; 
   
    /** BMI-Wert;  Nur die ersten beiden Nachkommastellen sind noch enthalten. */
    public double bmiGerundet = 0.0;
    
    /** BMI-Wert (Berechnungsergebnis). */
    public double bmiUngerundet = 0.0;
    
    /** Bewertung, z.B. "Normalgewicht" oder "Untergewicht". */
    public String bewertung = "";
    
    
    /**
     * Konstruktor zum Setzen der Attribute-Werte.
     *  
     * @param bmiUngerundet  Ungerundeter BMI-Wert (keine Nachkommastellen abgeschnitten).
     * 
     * @param bmiGerundet  BMI-Wert, bei dem nur die ersten beiden Nachkommastellen nicht abgeschnitten wurden. 
     * 
     * @param bewertung  String mit Bewertung wie "Normalgewicht". 
     */
    public BmiResultObjekt(double bmiUngerundet, double bmiGerundet, String bewertung) {
        
        this.bmiUngerundet = bmiUngerundet;
        this.bmiGerundet   = bmiGerundet;
        this.bewertung     = bewertung;
    }
    

    /**
     * Liefert String mit einigen Attribut-Werten zurück, kann auf Logger geschrieben werden.
     * 
     * @return  String mit Wert von BMI (ungerundet und gerundet) sowie Bewertung.
     */
    public String toString() {
        
        return String.format("Ergebnis: bmiUngerundet=%f, bmiGerundet=%f, bewertung=\"%s\".",  
                             this.bmiUngerundet, this.bmiGerundet, this.bewertung ); 

    }
    
}
