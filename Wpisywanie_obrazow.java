/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA_connect;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Dominik
 */

@ManagedBean
@SessionScoped
public class Wpisywanie_obrazow extends Laczenie_z_baza {
    
    
    public void wpisywanie(String sciezka){
        try{
            pobieranie_sterownikow();
            
            result=statement.executeQuery("INSERT INTO pictures VALUES ('admin', '/folder/nazwa.jpg");
            
            
            connection.close();
            
        } catch(Exception ex){
            System.out.println("blad " + ex.getMessage());
        }
    }
}
