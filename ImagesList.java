/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATAconnect;

import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Dominik
 */

@ManagedBean
@SessionScoped
public class ImagesList extends DAO {
    private List<String> sciezkiobrazow = new ArrayList<String>();
    private List<String> nazwyobrazow = new ArrayList<String>();
    private String puste; 
    private static int count=-1;
    
    public String getPuste() {
        return puste;
    }

    public void setPuste(String puste) {
        this.puste = puste;
    }

    public List<String> getNazwyobrazow() {
        return nazwyobrazow;
    }

    public void setNazwyobrazow(List<String> nazwyobrazow) {
        this.nazwyobrazow = nazwyobrazow;
    }

    public List<String> getSciezkiobrazow() {
        return sciezkiobrazow;
    }

    public void setSciezkiobrazow(List<String> sciezkiobrazow) {
        this.sciezkiobrazow = sciezkiobrazow;
    }
    
    public void clearLists() {
        sciezkiobrazow.clear();
        nazwyobrazow.clear();
        count=-1;
    }
    
    public String wyswietlanie_sciezek(String nazwa, String galeria){
        try{         

            clearLists();
            puste=null;
            String adres="../../../Baza/";
            pobieranie_sterownikow();
            
            result=statement.executeQuery("select FOLDER, NAME from pictures WHERE ID like '" + nazwa +"' and FOLDER like '" + galeria +"'");
            ResultSetMetaData metadata = result.getMetaData();
            
            int ilosckolumn = metadata.getColumnCount();
            
            
            if (!result.next()) {
                puste = "brak obrazków";
            } else {
            
            do {
                
                adres = "../../../Baza/"+nazwa;
                nazwyobrazow.add(result.getString(2));
                
                for (int i=1; i<=ilosckolumn; i++) {
                    adres += "/"+result.getString(i);                    
                }
                
                sciezkiobrazow.add(adres);                 
            } while(result.next());         
            }
            
            connection.close();
            return "gallery.xhtml?faces-redirect=true";
            
        } catch(Exception ex){
            System.out.println("blad " + ex.getMessage());
            return null;
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
    
    public String nazwaobrazu() {
        
        return sciezkiobrazow.get(count);
    } 
    
}
