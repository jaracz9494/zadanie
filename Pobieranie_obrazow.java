/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA_connect;

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
public class Pobieranie_obrazow extends Laczenie_z_baza {
    
    private String adres;
    private List<String> ArrayList = new ArrayList<String>();

    public List<String> getArrayList() {
        return ArrayList;
    }

    public void setArrayList(List<String> ArrayList) {
        this.ArrayList = ArrayList;
    }
    
    public void clearArrayList() {
        ArrayList.clear();
    }
    
    public void wyswietlanie_sciezek(String nazwa){
        try{
            adres="../../../Baza/";
            pobieranie_sterownikow();
            
            result=statement.executeQuery("select FOLDER, NAME from pictures WHERE ID like '" + nazwa +"'");
            ResultSetMetaData metadata = result.getMetaData();
            
            int ilosckolumn = metadata.getColumnCount();
            
            while(result.next()) {
                adres = "../../../Baza/"+nazwa;
                for (int i=1; i<=ilosckolumn; i++) {
                    adres += "/"+result.getString(i);
                }
                
                ArrayList.add(adres);
                System.out.println(adres);                 
            }           
            
            connection.close();
            
        } catch(Exception ex){
            System.out.println("blad " + ex.getMessage());
        }
    }
    
}
