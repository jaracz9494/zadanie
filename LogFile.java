/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adminsservice;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
    private static HashMap<String, Integer> hmap = new HashMap<String, Integer>();
    private static ArrayList<String> listascpl = new ArrayList<String>();
    private static ArrayList<String> listaplikow = new ArrayList<String>();
    private static int count=-1;
    

    public ArrayList<String> getListascpl() {
        return listascpl;
    }

    public void setListascpl(ArrayList<String> listascpl) {
        LogFile.listascpl = listascpl;
    }
  

    public ArrayList<String> getListaplikow() {
        return listaplikow;
    }

    public void setListaplikow(ArrayList<String> listaplikow) {
        this.listaplikow = listaplikow;
    }
    
    public void clearList() {
        listascpl.clear();
        listaplikow.clear();
        count = -1;
    }
    
    public void incIloscwejsc() {
        iloscwejsc += 1;
    }
    
    public void zmienRejestr(String nazwa) {
        incIloscwejsc();
        if (hmap.containsKey(nazwa)) {
            int var= hmap.get(nazwa);
            hmap.replace(nazwa, var, ++var);          
        } else {
            hmap.put(nazwa, 1);
        }
    }
    
    public void CreateUnauthAdmin(String login) {
        String data;
        SimpleDateFormat  dateFormat = new SimpleDateFormat("yyyy-MM-dd HHmmss");
        data = dateFormat.format(new Date());
        
      try {
                         
              PrintWriter file = new PrintWriter(sciezka + login + data + ".txt", "UTF-8");
              
              dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
              data = dateFormat.format(new Date());
              
              file.println("NIEAUTORYZOWANA PROBA DOSTANIA SIE DO STRONY ADMINISTRACYJNEJ!!!");
              file.println("Login: " + login);
              file.println("DATA: " + data);
              
              file.close();


    	} catch (IOException e) {
	      System.out.println("Blad utworzenia pliku: " + e);
	}
    }
    
    
    
    public String CreateLogFile() {
        
        String data;
        SimpleDateFormat  dateFormat = new SimpleDateFormat("yyyy-MM-dd HHmmss");
        data = dateFormat.format(new Date());
        
      try {
          
          // Nadpisze plik o tej nazwie               
              PrintWriter file = new PrintWriter(sciezka + data + ".txt", "UTF-8");
              
              dateFormat = new SimpleDateFormat("yyyy-MM-dd");
              data = dateFormat.format(new Date());
              file.println("DATA: " + data);
              file.println("--------------------");
              
              Set set = hmap.entrySet();
              Iterator iterator = set.iterator();
              while(iterator.hasNext()) {
                 Map.Entry mentry = (Map.Entry)iterator.next();
                 file.println("Ilosc wejsc dla uzytkownika " + mentry.getKey() + " wynosi: " + mentry.getValue());
              }              
              file.println("--------------------");
              
              file.print("Suma Ilosci wejsc na stronie: ");
	      file.println(new Integer(iloscwejsc).toString());
              file.close();
              
              CreateListofFiles();
              return "adminsActions?faces-redirect=true";

    	} catch (IOException e) {
	      System.out.println("Blad utworzenia pliku: " + e);
              return null;
	}
    }
    
    
    public void CreateListofFiles(){
        try{
            clearList();
            
            File folder = new File(sciezka);
            File[] listOfFiles = folder.listFiles();

                for (int i = 0; i < listOfFiles.length; i++) {
                  if (listOfFiles[i].isFile()) {
                    System.out.println("File " + listOfFiles[i].getName());
                    listaplikow.add(listOfFiles[i].getName());
                    listascpl.add("/Baza/admin/logi/"+listOfFiles[i].getName());
                  } else if (listOfFiles[i].isDirectory()) {
                    System.out.println("Directory " + listOfFiles[i].getName());
                  }
                }
            
        } catch(Exception ex){
            System.out.println("blad " + ex.getMessage());
        }
    }
    
    
    public void ustaw() {
        count = count + 1; 
    }
    
    public int numer() {
        return count; 
    }
    
    public void zeruj() {
        count = -1;
    }
    
    public String sciezkapliku() {
        
        return listascpl.get(count);
    } 
    
}
