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
    private List<String> ArrayList = new ArrayList<String>();
    private List<String> nazwyobrazow = new ArrayList<String>();

    public List<String> getNazwyobrazow() {
        return nazwyobrazow;
    }

    public void setNazwyobrazow(List<String> nazwyobrazow) {
        this.nazwyobrazow = nazwyobrazow;
    }

    public List<String> getArrayList() {
        return ArrayList;
    }

    public void setArrayList(List<String> ArrayList) {
        this.ArrayList = ArrayList;
    }
    
    public void clearLists() {
        ArrayList.clear();
        nazwyobrazow.clear();
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
                
                ArrayList.add(adres);
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
    
    public static int liczba=0;
    public void test(String nazwa) {
        System.out.println("dziala test: " + nazwa + " " + liczba);
        liczba++;
        //return "gallery.xhtml?faces-redirect=true";
    }
    
}
