/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminsservice;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Dominik
 */

@ManagedBean
@SessionScoped
public class LogFile {
    
    private final String sciezka = "C:\\Users\\Dominik\\Desktop\\Server\\Tomcat\\webapps\\Baza\\admin\\logi\\";
    private static int iloscwejsc = 0;

    public int getIloscwejsc() {
        return iloscwejsc;
    }

    public void setIloscwejsc(int iloscwejsc) {
        this.iloscwejsc = iloscwejsc;
    }
    
    public void incIloscwejsc() {
        iloscwejsc += 1;
    }
    
    
    public void CreateLogFile() {
        
        String data;
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        data = dateFormat.format(date);
        
      try {
          
          // Nadpisze plik o tej nazwie               
              PrintWriter file = new PrintWriter(sciezka + data + ".txt", "UTF-8");
              
              file.write("Ilość wejść na stronę: ");
	      file.write(new Integer(iloscwejsc).toString());
              file.close();


    	} catch (IOException e) {
	      System.out.println("Blad utworzenia pliku: " + e);
	}
    }
}
