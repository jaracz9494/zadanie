/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATAconnect;

// PROJEKT PORZUCONY ZE WZGLĘDU NA: <ui:repeat> i jego błędne interpretacje (wiecej informacji w wymagania.txt)

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
public class GalleryList extends DAO {
    
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
            String adres;
            pobieranie_sterownikow();
            
            result=statement.executeQuery("select DISTINCT FOLDER from pictures WHERE ID like '" + nazwa +"'");
            
            while(result.next()) {
                adres = result.getString(1);
                
                ArrayList.add(adres);
                System.out.println(adres);                 
            }           
            
            connection.close();
            
        } catch(Exception ex){
            System.out.println("blad " + ex.getMessage());
        }
    }
    
}
