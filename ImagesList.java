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
    private static int count=-1;

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
            String adres="../../../Baza/";
            pobieranie_sterownikow();
            
            result=statement.executeQuery("select FOLDER, NAME from pictures WHERE ID like '" + nazwa +"' and FOLDER like '" + galeria +"'");
            ResultSetMetaData metadata = result.getMetaData();
            
            int ilosckolumn = metadata.getColumnCount();
            
            while(result.next()) {
                adres = "../../../Baza/"+nazwa;
                nazwyobrazow.add(result.getString(2));
                
                for (int i=1; i<=ilosckolumn; i++) {
                    adres += "/"+result.getString(i);                    
                }
                
                sciezkiobrazow.add(adres);
                System.out.println(adres);                 
            }           
            
            connection.close();
            //System.out.println(galeria);
            //System.out.println("dziala glowne zadanie");
            return "gallery.xhtml?faces-redirect=true";
            
        } catch(Exception ex){
            System.out.println("blad " + ex.getMessage());
            return null;
        }
    }
    
    public void ustaw() {
        count = count + 1; 
        System.out.println(count);
    }
    
    public int numer() {
        return count; 
    }
    
    public void zeruj() {
        count = -1;
    }
    
    public String nazwaobrazu() {
        
        System.out.println("wtf: " + count);
        return sciezkiobrazow.get(count);
    } 
    
    /*
    public static int liczba=0;
    public void test(String nazwa) {
        System.out.println("dziala test: " + nazwa + " " + liczba);
        liczba++;
        //return "gallery.xhtml?faces-redirect=true";
    }
    */
}
