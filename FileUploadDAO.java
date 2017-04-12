/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATAconnect;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Dominik
 */

@ManagedBean
@SessionScoped
public class FileUploadDAO extends DAO {
    
    
    public void wpisywanie(String id, String folder, String name){
        try{
            pobieranie_sterownikow();
            
            statement.executeUpdate("INSERT INTO pictures VALUES ('" + id + "', '" + folder + "', '" + name + "')");          
            
            connection.close();
            
        } catch(Exception ex){
            System.out.println("blad " + ex.getMessage());
        }
    }
}
